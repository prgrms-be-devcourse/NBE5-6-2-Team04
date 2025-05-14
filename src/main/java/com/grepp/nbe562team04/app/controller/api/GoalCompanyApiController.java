package com.grepp.nbe562team04.app.controller.api;

import com.grepp.nbe562team04.model.goalcompany.GoalCompanyRepository;
import com.grepp.nbe562team04.model.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dashboard/company")
public class GoalCompanyApiController {

    private final GoalCompanyRepository goalCompanyRepository;
    private final UserRepository userRepository;

}
