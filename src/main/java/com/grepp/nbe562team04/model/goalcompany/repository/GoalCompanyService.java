package com.grepp.nbe562team04.model.goalcompany.repository;

import com.grepp.nbe562team04.model.goalcompany.dto.GoalCompanyRequestDto;
import com.grepp.nbe562team04.model.goalcompany.dto.GoalCompanyResponseDto;
import com.grepp.nbe562team04.model.goalcompany.entity.GoalCompany;
import com.grepp.nbe562team04.model.user.entity.User;
import com.grepp.nbe562team04.model.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GoalCompanyService {

    private final GoalCompanyRepository goalCompanyRepository;
    private final UserRepository userRepository;

    public void createGoalCompany(GoalCompanyRequestDto dto) {
        // 임시: userId 2번 유저 사용 (로그인 연동 전)
        User user = userRepository.findById(2L).orElseThrow(() -> new RuntimeException("User not found"));

        // 로그인 구현시 사용
//        CustomUserDetails userDetails = (CustomUserDetails)
//                SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        Long userId = userDetails.getUser().getUserId();
//
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));


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

    // 기업 리스트 조회
    public List<GoalCompanyResponseDto> getAllGoalCompanies() {
        return null;
    }

    // 해당 기업 상세 조회
    public GoalCompanyResponseDto getCompanyById(Long id) {
        return null;
    }

    // 수정
    public void updateGoalCompany(Long id, GoalCompanyRequestDto dto) {
    }

    // 삭제
    public void deleteGoalCompany(Long id) {

    }
}
