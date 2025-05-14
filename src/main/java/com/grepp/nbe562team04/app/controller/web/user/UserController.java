package com.grepp.nbe562team04.app.controller.web.user;

import com.grepp.nbe562team04.app.controller.web.user.payload.SigninRequest;
import com.grepp.nbe562team04.app.controller.web.user.payload.SignupRequest;
import com.grepp.nbe562team04.model.auth.code.Role;
import com.grepp.nbe562team04.model.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    // 회원가입 폼
    @GetMapping("signup")
    public String signup() { return "user/signup";}

    @PostMapping("signup")
    public String signup(
        @Valid SignupRequest form,
        BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            System.out.println("유효성 검사 오류 발생:");
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println(error.getDefaultMessage());
            });
            return "user/signup"; }

        userService.signup(form.toDto(), Role.ROLE_USER);

        return "redirect:/user/signin";
    }

    // 로그인 폼
    @GetMapping("signin")
    public String signin(SigninRequest form) { return "user/signin"; }



}
