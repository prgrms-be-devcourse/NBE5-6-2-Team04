package com.grepp.nbe562team04.model.user;

import com.grepp.nbe562team04.model.achieve.AchieveRepository;
import com.grepp.nbe562team04.model.achieve.UsersAchieve;
import com.grepp.nbe562team04.model.auth.code.Role;
import com.grepp.nbe562team04.model.auth.domain.Principal;
import com.grepp.nbe562team04.model.level.LevelRepository;
import com.grepp.nbe562team04.model.level.entity.Level;
import com.grepp.nbe562team04.model.user.dto.UserDto;
import com.grepp.nbe562team04.model.user.entity.User;

import jakarta.persistence.EntityNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserDetailsService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;
    private final LevelRepository levelRepository;
    private final UsersAchieveRepository usersAchieveRepository;
    private final AchieveRepository achieveRepository;

    @Transactional
    public void signup(UserDto dto, Role role){

//        if (userRepository.existsById(dto.getUserId()))
//            throw new CommonException(ResponseCode.BAD_REQUEST);

        User user = mapper.map(dto, User.class);
        Level defaultLevel = levelRepository.findFirstByOrderByLevelIdAsc()
            .orElseThrow(() -> new IllegalStateException("기본 레벨이 존재하지 않습니다."));

        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        user.setPassword(encodedPassword);
        user.setRole(role);
        user.setLevel(defaultLevel);
        user.setExp(0);
        user.setCreatedAt(LocalDate.now());
        log.info("user:{}", user);
        log.info("저장 전 최종 비밀번호: {}", user.getPassword()); // 반드시 해시 형태여야 함
        userRepository.save(user);
    }

    public User findByEmail(String email) {

        User user = userRepository.findByEmailAndDeletedAtIsNull(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다: " + email));

        Level currentLevel = levelRepository.findTopByXpLessThanEqualOrderByXpDesc(user.getExp())
                .orElseThrow(() -> new IllegalStateException("레벨 데이터가 없습니다."));
        user.setLevel(currentLevel);
        return user;
    }

    @Transactional
    public void updateUser(String email, UserDto dto, MultipartFile file) throws IOException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자 없음"));

        user.setComment(dto.getComment());

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            String hashed = passwordEncoder.encode(dto.getPassword());
            user.setPassword(hashed);
        }

        if (file != null && !file.isEmpty()) {
            String uploadDir = System.getProperty("user.dir") + "/uploads/profile/";
            String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filepath = Paths.get(uploadDir, filename);
            Files.createDirectories(filepath.getParent());
            Files.write(filepath, file.getBytes());
            user.setUserImage(filename);
        }
        userRepository.save(user);

        if (isTutorialCompleted(user)) {
            giveTutorialAchievement(user.getUserId());
        }
    }

    @Transactional
    public boolean giveTutorialAchievement(Long userId) {
        Long achieveId = 1L;
        boolean already = usersAchieveRepository.existsByUser_UserIdAndAchievement_AchieveId(userId, achieveId);
        if (already) return false;

        UsersAchieve ua = new UsersAchieve();
        ua.setUser(userRepository.getReferenceById(userId));
        ua.setAchievement(achieveRepository.getReferenceById(achieveId));
        ua.setAchievedAt(LocalDateTime.now());
        usersAchieveRepository.save(ua);

        return true;
    }
    public boolean isTutorialCompleted(User user) {
        return StringUtils.hasText(user.getEmail()) &&
                StringUtils.hasText(user.getNickname()) &&
                StringUtils.hasText(user.getComment()) &&
                StringUtils.hasText(user.getUserImage());
    }

    @Transactional
    public Map<String, List<UserDto>> findUsersGroupedByStatus() {
        List<UserDto> users = Optional.of(userRepository.findAll())
            .orElse(Collections.emptyList()).stream()
            .map(UserDto::new)
            .toList();

        List<UserDto> activeUsers = users.stream()
            .filter(user -> user.getDeletedAt() == null && !user.getRole().name().equals("ROLE_ADMIN"))
            .collect(Collectors.toList());

        List<UserDto> deletedUsers = users.stream()
            .filter(user -> user.getDeletedAt() != null)
            .collect(Collectors.toList());

        List<UserDto> adminUsers = users.stream()
            .filter(user -> user.getRole().name().equals("ROLE_ADMIN") && user.getDeletedAt() == null)
            .collect(Collectors.toList());

        Map<String, List<UserDto>> result = new HashMap<>();
        result.put("activeUsers", activeUsers);
        result.put("deletedUsers", deletedUsers);
        result.put("adminUsers", adminUsers);

        return result;
    }
    public boolean checkPassword(User user, String rawPassword) {
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmailAndDeletedAtIsNull(email)
                .orElseThrow(() -> new UsernameNotFoundException("탈퇴했거나 존재하지 않는 사용자입니다."));

        return new Principal(user);
    }

    @Transactional
    public void softDeleteUser(String email) {
        User user = userRepository.findByEmailAndDeletedAtIsNull(email)
            .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 사용자입니다."));
        user.setDeletedAt(LocalDate.now()); // 또는 LocalDateTime.now()
        userRepository.save(user);
    }

}
