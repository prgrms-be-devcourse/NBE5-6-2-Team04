package com.grepp.nbe562team04.app.controller.web.member;

import com.grepp.nbe562team04.app.controller.web.member.payload.SigninRequest;
import com.grepp.nbe562team04.app.controller.web.member.payload.SignupRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("user")
public class UserController {

    // 로그인
    @GetMapping("signin")
    public String signin(SigninRequest form) { return "user/signin"; }

    @GetMapping("signup")
    public String signup(SignupRequest form) { return "user/signup";}

}
