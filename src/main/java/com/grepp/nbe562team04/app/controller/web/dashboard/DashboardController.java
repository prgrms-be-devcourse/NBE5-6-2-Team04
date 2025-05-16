package com.grepp.nbe562team04.app.controller.web.dashboard;

import com.grepp.nbe562team04.model.auth.domain.Principal;
import com.grepp.nbe562team04.model.dashboard.DashboardRepository;
import com.grepp.nbe562team04.model.dashboard.DashboardService;
import com.grepp.nbe562team04.model.dashboard.dto.DashboardDto;
import com.grepp.nbe562team04.model.dashboard.dto.GoalCompanyDto;
import com.grepp.nbe562team04.model.goalcompany.entity.GoalCompany;
import com.grepp.nbe562team04.model.user.UserRepository;
import com.grepp.nbe562team04.model.user.entity.User;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DashboardController {

    private final DashboardService dashboardService;
    private final UserRepository userRepository;
    private final DashboardRepository dashboardRepository;

    public DashboardController(DashboardService dashboardService, UserRepository userRepository,
        DashboardRepository dashboardRepository) {
        this.dashboardService = dashboardService;
        this.userRepository = userRepository;
        this.dashboardRepository = dashboardRepository;
    }

    // 대시보드
    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal Principal principal, Model model) {
        User detachedUser = principal.getUser();

        User managedUser = userRepository.findById(detachedUser.getUserId())
            .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        DashboardDto dto = dashboardService.getDashboard(managedUser);
        model.addAttribute("dashboard", dto);
        return "dashboard/dashboard";
    }

    // 마이페이지
    @GetMapping("/mypage")
    public String myPage(@AuthenticationPrincipal Principal principal, Model model) {
        User user = principal.getUser();
        model.addAttribute("user", user);
        return "mypage/mypage";
    }

//    // 목표기업 생성
//    @PostMapping("/goal-company")
//    public String createGoalCompany(@AuthenticationPrincipal Principal principal,
//        @RequestParam String companyName,
//        @RequestParam String content,
//        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
//        User user = principal.getUser();
//        GoalCompany company = new GoalCompany();
//        company.setCompanyName(companyName);
//        company.setContent(content);
//        company.setEndDate(endDate);
//        company.setUser(user);
//
//        dashboardRepository.save(company);
//
//        return "redirect:/dashboard";
//    }

    // 목표기업 단일 조회
    @GetMapping("/companies/{id}/select")
    public String companyDetail(@PathVariable Long id, Model model) {
        GoalCompanyDto companyDto = dashboardService.getCompanyDetailById(id);


        model.addAttribute("company", companyDto);

        return "goal/goal";
    }

//     알림 토글
    @PostMapping("/dashboard/notification-toggle")
    public String toggleNotification(@AuthenticationPrincipal Principal principal) {
        User user = userRepository.findById(principal.getUser().getUserId())
            .orElseThrow(() -> new IllegalArgumentException("유저 없음"));
        user.setNotificationOn(!user.isNotificationOn());
        userRepository.save(user);
        return "redirect:/dashboard";
    }

    // todo 페이지
    @GetMapping("/todo")
    public String showTodo(){
        return "todo";
    }
}
