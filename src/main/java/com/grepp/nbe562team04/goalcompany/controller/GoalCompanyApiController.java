package com.grepp.nbe562team04.goalcompany.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GoalCompanyApiController {

    @GetMapping("/dashboard/company/create")
    public String createCompanyPage(){
        return "create_company";
    }
}
