package com.grepp.nbe562team04.app.controller.web.dashboard;

import com.grepp.nbe562team04.model.auth.domain.Principal;
import com.grepp.nbe562team04.model.dashboard.DashboardService;
import com.grepp.nbe562team04.model.dashboard.dto.DashboardDto;
import com.grepp.nbe562team04.model.dashboard.dto.GoalCompanyDto;
import com.grepp.nbe562team04.model.goal.GoalService;
import com.grepp.nbe562team04.model.goal.dto.GoalResponseDto;
import com.grepp.nbe562team04.model.todo.TodoService;
import com.grepp.nbe562team04.model.todo.dto.TodoResponseDto;
import com.grepp.nbe562team04.model.user.UserRepository;
import com.grepp.nbe562team04.model.user.entity.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DashboardController {

    private final DashboardService dashboardService;
    private final UserRepository userRepository;

    private final GoalService goalService;
    private final TodoService todoService;

    public DashboardController(DashboardService dashboardService, UserRepository userRepository,
        GoalService goalService, TodoService todoService) {
        this.dashboardService = dashboardService;
        this.userRepository = userRepository;
        this.goalService = goalService;
        this.todoService = todoService;
    }

    // 대시보드
    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal Principal principal, Model model) {
        User user = userRepository.findById(principal.getUser().getUserId())
            .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        DashboardDto dto = dashboardService.getDashboard(user);
        model.addAttribute("dashboard", dto);
        return "dashboard/dashboard";
    }

    // 알림 토글
    @PostMapping("/dashboard/notification-toggle")
    public String toggleNotification(@AuthenticationPrincipal Principal principal) {
        User user = userRepository.findById(principal.getUser().getUserId())
            .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));
        user.setNotificationOn(!user.isNotificationOn());
        userRepository.save(user);
        return "redirect:/dashboard";
    }

    // 목표기업 단일 조회
    @GetMapping("/companies/{CompanyId}/select")
    public String companyDetail(@PathVariable Long CompanyId, Model model) {
        GoalCompanyDto companyDto = dashboardService.getCompanyDetailById(CompanyId);
        List<GoalResponseDto> goalList = goalService.getGoalsByCompanyId(CompanyId);

        Map<Long, List<TodoResponseDto>> todoMap = new HashMap<>();
        for (GoalResponseDto goal : goalList) {
            List<TodoResponseDto> todos = todoService.getByGoal(goal.getGoalId());
            todoMap.put(goal.getGoalId(), todos);
        }

        model.addAttribute("company", companyDto);   // 기업 정보
        model.addAttribute("goals", goalList);       // 목표 리스트
        model.addAttribute("todoMap", todoMap);

        return "goal/goal";
    }

    // todo 페이지
    @GetMapping("/todo")
    public String showTodo() {
        return "todo";
    }

    // 마이페이지
    @GetMapping("/mypage")
    public String myPage(@AuthenticationPrincipal Principal principal, Model model) {
        User user = principal.getUser();
        model.addAttribute("user", user);
        return "mypage/mypage";
    }
}
