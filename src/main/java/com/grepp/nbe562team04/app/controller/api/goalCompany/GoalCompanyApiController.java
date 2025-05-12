package com.grepp.nbe562team04.app.controller.api.goalCompany;

import com.grepp.nbe562team04.model.goalcompany.dto.GoalCompanyRequestDto;
import com.grepp.nbe562team04.model.goalcompany.dto.GoalCompanyResponseDto;
import com.grepp.nbe562team04.model.goalcompany.repository.GoalCompanyRepository;
import com.grepp.nbe562team04.model.goalcompany.repository.GoalCompanyService;
import com.grepp.nbe562team04.model.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dashboard/company")
public class GoalCompanyApiController {

    private final GoalCompanyService goalCompanyService; // 생성자 주입

    // 생성
    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody GoalCompanyRequestDto dto) { // json -> dto 자동 변환
        goalCompanyService.createGoalCompany(dto);
        return ResponseEntity.ok("등록 완료");
    }

    // 조회(단순 화면 상에 출력) -> 추후 뷰 컨트롤러로 이동 필요
    @GetMapping
    public List<GoalCompanyResponseDto> getAllCompanies(){
        return goalCompanyService.getAllGoalCompanies();
    }

    // 상세 조회
    @GetMapping("/{id}")
    public GoalCompanyResponseDto getCompanyById(@PathVariable Long id) {
        return goalCompanyService.getCompanyById(id);
    }

    // 수정
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody GoalCompanyRequestDto dto) {
        goalCompanyService.updateGoalCompany(id, dto);
        return ResponseEntity.ok("수정 완료");
    }

    // 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
        goalCompanyService.deleteGoalCompany(id);
        return ResponseEntity.ok("삭제 완료");
    }

}
