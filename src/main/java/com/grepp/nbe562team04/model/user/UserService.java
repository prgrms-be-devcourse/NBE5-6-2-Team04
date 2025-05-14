package com.grepp.nbe562team04.model.user;

import com.grepp.nbe562team04.model.auth.code.Role;
import com.grepp.nbe562team04.model.level.LevelRepository;
import com.grepp.nbe562team04.model.level.entity.Level;
import com.grepp.nbe562team04.model.user.dto.UserDto;
import com.grepp.nbe562team04.model.user.entity.User;
import java.time.LocalDateTime;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

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

}
