package com.grepp.nbe562team04.app.controller.web.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardViewController {

    @GetMapping("/dashboard")
    public String showDashboard(){
        return "dashboard";
    }
    @GetMapping("/todo")
    public String showTodo(){
        return "todo";
    }
}
