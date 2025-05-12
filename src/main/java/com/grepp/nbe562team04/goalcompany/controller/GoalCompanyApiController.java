package com.grepp.nbe562team04.goalcompany.controller;

import com.grepp.nbe562team04.goalcompany.dto.GoalCompanyRequestDto;
import com.grepp.nbe562team04.goalcompany.entity.GoalCompany;
import com.grepp.nbe562team04.goalcompany.repository.GoalCompanyRepository;
import com.grepp.nbe562team04.user.entity.User;
import com.grepp.nbe562team04.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dashboard/company")
public class GoalCompanyApiController {

    private final GoalCompanyRepository goalCompanyRepository;
    private final UserRepository userRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<String> create(@RequestBody GoalCompanyRequestDto dto) {
        // 임시 User 객체 (실제 로그인/세션 연동 시에는 DB 조회 필요)
        System.out.println("🔥 POST /dashboard/company 요청 도착: " + dto.getCompanyName());

        // 👉 user_id를 하드코딩하거나 추후 로그인 세션에서 꺼내기
        Long userId = 1L; // TODO: 나중에 로그인 된 유저로 바꿔야 함
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) return ResponseEntity.badRequest().body("유저 없음");

        User user = userOpt.get();  // DB에서 가져온 User 그대로 사용

//        User user = User.builder()
//                .userId(dto.getUserId())
//                .build();

        GoalCompany goalCompany = GoalCompany.builder()
                .user(user)
                .companyName(dto.getCompanyName())
                .content(dto.getContent())
                .createdAt(LocalDateTime.now())
                .build();

        goalCompanyRepository.save(goalCompany);
        return ResponseEntity.ok("기업 등록 완료!");
    }
}
