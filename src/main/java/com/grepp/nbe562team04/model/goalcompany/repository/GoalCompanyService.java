package com.grepp.nbe562team04.model.goalcompany.repository;

import com.grepp.nbe562team04.model.goalcompany.dto.GoalCompanyRequestDto;
import com.grepp.nbe562team04.model.goalcompany.entity.GoalCompany;
import com.grepp.nbe562team04.model.user.entity.User;
import com.grepp.nbe562team04.model.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class GoalCompanyService {

    private final GoalCompanyRepository goalCompanyRepository;
    private final UserRepository userRepository;

    public void createGoalCompany(GoalCompanyRequestDto dto) {
        // 임시: userId 2번 유저 사용 (로그인 연동 전)
        User user = userRepository.findById(2L).orElseThrow(() -> new RuntimeException("User not found"));

        GoalCompany company = GoalCompany.builder()
                .user(user)
                .companyName(dto.getCompanyName())
                .content(dto.getContent())
                .status(dto.getStatus())
                .endDate(dto.getEndDate())
                .createdAt(LocalDateTime.now())
                .build();

        goalCompanyRepository.save(company);
    }
}
