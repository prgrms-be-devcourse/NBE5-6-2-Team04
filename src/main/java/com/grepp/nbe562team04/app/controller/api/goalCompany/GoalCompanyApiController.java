package com.grepp.nbe562team04.app.controller.api.goalCompany;

import com.grepp.nbe562team04.model.auth.domain.Principal;
import com.grepp.nbe562team04.model.goalcompany.GoalCompanyService;
import com.grepp.nbe562team04.model.goalcompany.dto.GoalCompanyRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/companies")
public class GoalCompanyApiController {

    private final GoalCompanyService goalCompanyService; // 생성자 주입

    // 생성
    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody GoalCompanyRequestDto dto,
        @AuthenticationPrincipal Principal principal) { // json -> dto 자동 변환
        Long userId = principal.getUser().getUserId();  // 로그인된 유저 ID 꺼내기
        goalCompanyService.createGoalCompany(dto, userId);
        return ResponseEntity.ok("등록 완료");
    }

    // 조회(단순 화면 상에 출력) -> 추후 뷰 컨트롤러로 이동 필요
//    @GetMapping
//    public List<GoalCompanyResponseDto> getAllCompanies(){
//        return goalCompanyService.getAllGoalCompanies();
//    }

    // TODO: api 위치 변경 필요
//    // 상세 조회
//    @GetMapping("/{id}")
//    public GoalCompanyResponseDto getCompanyById(@PathVariable Long id) {
//        return goalCompanyService.getCompanyById(id);
//    }

    // 수정
    @PutMapping("/{companyId}/update")
    public ResponseEntity<String> updateCompany(@PathVariable Long id,
        @RequestBody GoalCompanyRequestDto dto) {
        goalCompanyService.updateGoalCompany(id, dto);
        return ResponseEntity.ok("수정 완료");
    }

    // 삭제
    @DeleteMapping("/{companyId}/delete")
    public ResponseEntity<String> deleteCompany(@PathVariable Long companyId) {
        goalCompanyService.deleteGoalCompany(companyId);
        return ResponseEntity.ok("삭제 완료");
    }

}