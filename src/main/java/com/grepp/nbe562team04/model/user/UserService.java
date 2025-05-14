package com.grepp.nbe562team04.model.user;

import com.grepp.nbe562team04.model.auth.code.Role;
import com.grepp.nbe562team04.model.level.LevelRepository;
import com.grepp.nbe562team04.model.level.entity.Level;
import com.grepp.nbe562team04.model.user.dto.UserDto;
import com.grepp.nbe562team04.model.user.entity.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;
    private final LevelRepository levelRepository;

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
        user.setCreatedAt(LocalDateTime.now());
        log.info("user:{}", user);
        userRepository.save(user);
    }

    public User findByEmail(String email) {

        User user = userRepository.findByEmail(email)
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
    }

}
