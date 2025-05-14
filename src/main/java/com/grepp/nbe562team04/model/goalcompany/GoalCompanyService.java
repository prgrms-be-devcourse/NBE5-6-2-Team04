package com.grepp.nbe562team04.model.goalcompany;

import com.grepp.nbe562team04.model.goalcompany.dto.GoalCompanyRequestDto;
import com.grepp.nbe562team04.model.goalcompany.dto.GoalCompanyResponseDto;
import com.grepp.nbe562team04.model.goalcompany.entity.GoalCompany;
import com.grepp.nbe562team04.model.user.entity.User;
import com.grepp.nbe562team04.model.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return goalCompanyRepository.findByUserUserId(2L).stream()
                .map(company -> GoalCompanyResponseDto.builder()
                        .companyName(company.getCompanyName())
                        .content(company.getContent())
                        .status(company.getStatus().name())
                        .endDate(company.getEndDate())
                        .build())
                .toList();
    }

    // 해당 기업 상세 조회
    public GoalCompanyResponseDto getCompanyById(Long id) {
        GoalCompany company = goalCompanyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 기업이 존재하지 않습니다."));

        GoalCompanyResponseDto dto = GoalCompanyResponseDto.builder()
                .companyName(company.getCompanyName())
                .content(company.getContent())
                .status(company.getStatus().name())
                .endDate(company.getEndDate())
                .build();

        return dto;
    }

    // 수정
    @Transactional
    public void updateGoalCompany(Long id, GoalCompanyRequestDto dto) {
        GoalCompany company = goalCompanyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 기업이 존재하지 않습니다."));

        company.setCompanyName(dto.getCompanyName());
        company.setContent(dto.getContent());
        company.setStatus(dto.getStatus());
        company.setEndDate(dto.getEndDate());
    }

    // 삭제
    @Transactional
    public void deleteGoalCompany(Long id) {
        GoalCompany company = goalCompanyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 기업이 존재하지 않습니다."));

        goalCompanyRepository.delete(company);
    }
}
