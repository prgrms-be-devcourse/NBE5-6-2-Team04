package com.grepp.nbe562team04.app.controller.api.goalCompany;

import com.grepp.nbe562team04.model.goalcompany.dto.GoalCompanyRequestDto;
import com.grepp.nbe562team04.model.goalcompany.repository.GoalCompanyRepository;
import com.grepp.nbe562team04.model.goalcompany.repository.GoalCompanyService;
import com.grepp.nbe562team04.model.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dashboard/company")
public class GoalCompanyApiController {

    private final GoalCompanyService goalCompanyService;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody GoalCompanyRequestDto dto) {
        goalCompanyService.createGoalCompany(dto);
        return ResponseEntity.ok("등록 완료");
    }
}
