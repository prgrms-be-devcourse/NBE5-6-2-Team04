package com.grepp.nbe562team04.app.controller.web.dashboard;

import com.grepp.nbe562team04.model.dashboard.DashboardService;
import com.grepp.nbe562team04.model.dashboard.dto.DashboardDto;
import com.grepp.nbe562team04.model.dashboard.dto.GoalCompanyDto;
import com.grepp.nbe562team04.model.user.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    // 대시보드
    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal Principal principal, Model model) {
        User user = principal.getUser();
        DashboardDto dashboardDto = dashboardService.getDashboard(user);
        model.addAttribute("dashboard", dashboardDto);
        return "dashboard/dashboard";
    }

    // 목표기업 상세 : 모달페이지
    @GetMapping("/dashboard/company/{id}")
    public String companyDetail(
        @PathVariable Long id,
        Model model
    ){
        GoalCompanyDto companyDto = dashboardService.getCompanyDetailById(id);
        model.addAttribute("company", companyDto);

        return "dashboard/company-detail";
    }
}
