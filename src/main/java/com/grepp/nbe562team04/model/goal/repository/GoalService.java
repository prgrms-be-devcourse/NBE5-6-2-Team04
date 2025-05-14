package com.grepp.nbe562team04.model.goal.repository;

import com.grepp.nbe562team04.model.goal.dto.GoalRequestDto;
import com.grepp.nbe562team04.model.goal.dto.GoalResponseDto;
import com.grepp.nbe562team04.model.goal.entity.Goal;
import com.grepp.nbe562team04.model.goal.repository.GoalRepository;
import com.grepp.nbe562team04.model.goalcompany.entity.GoalCompany;
import com.grepp.nbe562team04.model.goalcompany.repository.GoalCompanyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GoalService {

    private final GoalRepository goalRepository;
    private final GoalCompanyRepository goalCompanyRepository;

    // 목표 생성
    @Transactional
    public void createGoal(GoalRequestDto dto) {
        GoalCompany company = goalCompanyRepository.findById(dto.getCompanyId())
                .orElseThrow(() -> new RuntimeException("해당 기업이 존재하지 않습니다."));

        Goal goal = Goal.builder()
                .company(company)
                .title(dto.getTitle())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .isDone(dto.getIsDone())
                .createdAt(LocalDateTime.now())
                .build();

        goalRepository.save(goal);
    }

    // 기업별 목표 목록 조회
    @Transactional
    public List<GoalResponseDto> getGoalsByCompanyId(Long companyId) {
        return goalRepository.findByCompanyCompanyId(companyId).stream()
                .map(goal -> GoalResponseDto.builder()
                        .goalId(goal.getGoalId())
                        .title(goal.getTitle())
                        .startDate(goal.getStartDate())
                        .endDate(goal.getEndDate())
                        .isDone(goal.getIsDone())
                        .createdAt(goal.getCreatedAt())
                        .build())
                .collect(Collectors.toList());
    }

    // 목표 상세 조회
    @Transactional
    public GoalResponseDto getGoalById(Long goalId) {
        Goal goal = goalRepository.findById(goalId)
                .orElseThrow(() -> new RuntimeException("해당 목표가 존재하지 않습니다."));

        return GoalResponseDto.builder()
                .goalId(goal.getGoalId())
                .title(goal.getTitle())
                .startDate(goal.getStartDate())
                .endDate(goal.getEndDate())
                .isDone(goal.getIsDone())
                .createdAt(goal.getCreatedAt())
                .build();
    }

    // 목표 수정
    @Transactional
    public void updateGoal(Long goalId, GoalRequestDto dto) {
        Goal goal = goalRepository.findById(goalId)
                .orElseThrow(() -> new RuntimeException("해당 목표가 존재하지 않습니다."));

        goal.setTitle(dto.getTitle());
        goal.setStartDate(dto.getStartDate());
        goal.setEndDate(dto.getEndDate());
        goal.setIsDone(dto.getIsDone());
    }

    // 목표 삭제
    @Transactional
    public void deleteGoal(Long goalId) {
        Goal goal = goalRepository.findById(goalId)
                .orElseThrow(() -> new RuntimeException("해당 목표가 존재하지 않습니다."));

        goalRepository.delete(goal);
    }

}
