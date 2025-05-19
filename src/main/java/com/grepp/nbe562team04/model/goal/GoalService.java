package com.grepp.nbe562team04.model.goal;

import com.grepp.nbe562team04.model.goal.dto.GoalRequestDto;
import com.grepp.nbe562team04.model.goal.dto.GoalResponseDto;
import com.grepp.nbe562team04.model.goal.entity.Goal;
import com.grepp.nbe562team04.model.goalcompany.entity.GoalCompany;
import com.grepp.nbe562team04.model.goalcompany.GoalCompanyRepository;
import com.grepp.nbe562team04.model.todo.TodoRepository;
import com.grepp.nbe562team04.model.todo.entity.Todo;
import com.grepp.nbe562team04.model.user.UserRepository;
import com.grepp.nbe562team04.model.user.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GoalService {

    private final GoalRepository goalRepository;
    private final GoalCompanyRepository goalCompanyRepository;
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

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
                .isDone(false) // 명시적으로 false 표시
                .createdAt(LocalDate.now())
                .build();

        goalRepository.save(goal);
    }

    // 기업별 목표 목록 조회
    @Transactional
    public List<GoalResponseDto> getGoalsByCompanyId(Long companyId) {
        return goalRepository.findByCompanyCompanyId(companyId).stream()
                .map(goal -> {
                    List<Todo> todos = todoRepository.findByGoalGoalId(goal.getGoalId());
                    long total = todos.size();
                    long done = todos.stream().filter(Todo::getIsDone).count();
                    int progress = total == 0 ? 0 : (int) ((done * 100.0) / total);

                    return GoalResponseDto.builder()
                            .goalId(goal.getGoalId())
                            .title(goal.getTitle())
                            .startDate(goal.getStartDate())
                            .endDate(goal.getEndDate())
                            .isDone(goal.getIsDone())
                            .createdAt(goal.getCreatedAt())
                            .progress(progress)
                            .build();
                })
                .collect(Collectors.toList());
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

    // 목표 상세 조회
    public GoalResponseDto getGoalById(Long goalId) {
        Goal goal = goalRepository.findById(goalId)
                .orElseThrow(() -> new RuntimeException("목표 없음"));

        List<Todo> todos = todoRepository.findByGoalGoalId(goalId);
        long total = todos.size();
        long done = todos.stream().filter(Todo::getIsDone).count();
        int percent = total == 0 ? 0 : (int) ((done * 100.0) / total);

        return GoalResponseDto.builder()
                .goalId(goal.getGoalId())
                .title(goal.getTitle())
                .startDate(goal.getStartDate())
                .endDate(goal.getEndDate())
                .isDone(goal.getIsDone())
                .createdAt(goal.getCreatedAt())
                .progress(percent)
                .build();
    }


    @Transactional
    public void completeGoal(Long goalId, User user) {

        Goal goal = goalRepository.findById(goalId)
                .orElseThrow(() -> new RuntimeException("해당 목표가 존재하지 않습니다."));

        if (goal.getIsDone()) {
            throw new IllegalStateException("이미 완료된 목표입니다.");
        }

        List<Todo> todos = todoRepository.findByGoalGoalId(goalId);


        boolean allDone = todos.stream().allMatch(Todo::getIsDone);
        if (!allDone) {
            throw new IllegalStateException("아직 완료되지 않은 할 일이 있습니다.");
        }

        goal.setIsDone(true);
        goalRepository.save(goal);


        user.addXp(10);
        userRepository.save(user);
    }
}
