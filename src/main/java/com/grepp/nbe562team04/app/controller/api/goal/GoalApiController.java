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
@RequestMapping("/dashboard/goal")
public class GoalApiController {

    private final GoalService goalService;

    //  목표 생성
    @PostMapping
    public ResponseEntity<String> create(@RequestBody GoalRequestDto dto) {
        goalService.createGoal(dto);
        return ResponseEntity.ok("목표 생성 완료");
    }

    //  기업별 목표 목록 조회
    @GetMapping("/company/{companyId}")
    public List<GoalResponseDto> getGoalsByCompanyId(@PathVariable Long companyId) {
        return goalService.getGoalsByCompanyId(companyId);
    }

    //  목표 상세 조회
//    @GetMapping("/{id}")
//    public GoalResponseDto getGoal(@PathVariable Long id) {
//        return goalService.getGoalById(id);
//    }

    //  목표 수정
    @PutMapping("/{id}/edit")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody GoalRequestDto dto) {
        goalService.updateGoal(id, dto);
        return ResponseEntity.ok("목표 수정 완료");
    }

    //  목표 삭제
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        goalService.deleteGoal(id);
        return ResponseEntity.ok("목표 삭제 완료");
    }
}