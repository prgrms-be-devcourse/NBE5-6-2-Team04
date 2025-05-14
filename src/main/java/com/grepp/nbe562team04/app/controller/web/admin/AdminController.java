package com.grepp.nbe562team04.app.controller.web.admin;

import com.grepp.nbe562team04.app.controller.web.member.payload.SignupRequest;
import com.grepp.nbe562team04.model.auth.code.Role;
import com.grepp.nbe562team04.model.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("admin")
//@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final UserService userService;

    @GetMapping("signup")
    public String signup(){
        return "admin/signup";
    }

    @PostMapping("signup")
    public String signup(@Valid SignupRequest form, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "admin/signup";
        }

        userService.signup(form.toDto(), Role.ROLE_ADMIN);
        return "redirect:/";
    }

}
