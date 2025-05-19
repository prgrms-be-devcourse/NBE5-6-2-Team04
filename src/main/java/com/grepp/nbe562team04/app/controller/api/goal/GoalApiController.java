package com.grepp.nbe562team04.app.controller.api.goal;

import com.grepp.nbe562team04.model.goal.dto.GoalRequestDto;
import com.grepp.nbe562team04.model.goal.dto.GoalResponseDto;
import com.grepp.nbe562team04.model.goal.GoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/goals")
public class GoalApiController {

    private final GoalService goalService;

    //  목표 생성
    @PostMapping("/{companyId}/create")
    public ResponseEntity<String> createGoal(@RequestBody GoalRequestDto dto) {
        goalService.createGoal(dto);

        return ResponseEntity.ok("목표 생성 완료");
    }

    //  목표 단건 조회
    @GetMapping("/{goalId}/select")
    public GoalResponseDto selectGoal(@PathVariable Long goalId) {
        return goalService.getGoalById(goalId);
    }

    //  목표 수정
    @PutMapping("/{goalId}/update")
    public ResponseEntity<String> update(@PathVariable Long goalId, @RequestBody GoalRequestDto dto) {
        goalService.updateGoal(goalId, dto);
        return ResponseEntity.ok("목표 수정 완료");
    }

    //  목표 삭제
    @DeleteMapping("/{goalId}/delete")
    public ResponseEntity<String> delete(@PathVariable Long goalId) {
        goalService.deleteGoal(goalId);
        return ResponseEntity.ok("목표 삭제 완료");
    }
}