package com.grepp.nbe562team04.app.controller.api.goalCompany;

import com.grepp.nbe562team04.model.auth.domain.Principal;
import com.grepp.nbe562team04.model.goalcompany.GoalCompanyService;
import com.grepp.nbe562team04.model.goalcompany.dto.GoalCompanyRequestDto;
import com.grepp.nbe562team04.model.goalcompany.dto.GoalCompanyResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/companies")
public class GoalCompanyApiController {

    private final GoalCompanyService goalCompanyService; // 생성자 주입

    // 생성
    @PostMapping
    public ResponseEntity<?> createCompany(@RequestBody GoalCompanyRequestDto dto,
        @AuthenticationPrincipal Principal principal) { // json -> dto 자동 변환
        Long userId = principal.getUser().getUserId();
        String achievementName = goalCompanyService.createGoalCompany(dto, userId);// 로그인된 유저 ID 꺼내기
        if (achievementName != null) {
            return ResponseEntity.ok(Map.of(
                    "message", "등록 완료",
                    "achievementName", achievementName
            ));
        } else {
            return ResponseEntity.ok(Map.of(
                    "message", "등록 완료"
            ));
        }
    }

    // 조회(단순 화면 상에 출력) -> 추후 뷰 컨트롤러로 이동 필요
//    @GetMapping
//    public List<GoalCompanyResponseDto> getAllCompanies(){
//        return goalCompanyService.getAllGoalCompanies();
//    }


    // 상세 조회
    @GetMapping("/{companyId}")
    public GoalCompanyResponseDto getCompanyById(@PathVariable Long companyId) {
        return goalCompanyService.getCompanyById(companyId);
    }

    // 수정
    @PutMapping("/{companyId}/update")
    public ResponseEntity<String> updateCompany(@PathVariable Long companyId,
        @RequestBody GoalCompanyRequestDto dto) {
        goalCompanyService.updateGoalCompany(companyId, dto);
        return ResponseEntity.ok("수정 완료");
    }

    // 삭제
    @DeleteMapping("/{companyId}/delete")
    public ResponseEntity<String> deleteCompany(@PathVariable Long companyId) {
        goalCompanyService.deleteGoalCompany(companyId);
        return ResponseEntity.ok("삭제 완료");
    }

}