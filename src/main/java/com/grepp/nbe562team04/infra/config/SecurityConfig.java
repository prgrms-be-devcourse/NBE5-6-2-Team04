package com.grepp.nbe562team04.infra.config;


import com.grepp.nbe562team04.model.user.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {


//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
//        return http.getSharedObject(AuthenticationManagerBuilder.class)
//            .build();
//    }

//    @Bean
//    public HandlerMappingIntrospector mvcHandlerMappingIntrospector() {
//        return new HandlerMappingIntrospector();
//    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request,
                HttpServletResponse response,
                Authentication authentication) throws IOException, ServletException {
                Object principal = authentication.getPrincipal();
                log.info("로그인 성공: 사용자: {}", principal);

                boolean isAdmin = authentication.getAuthorities()
                    .stream()
                    .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));

                if (isAdmin) {
                    response.sendRedirect("/admin");
                    return;
                }

                response.sendRedirect("/");
            }
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, UserService userService) throws Exception {
        http.csrf(csrf -> csrf.disable());
        http.userDetailsService(userService)
            .authorizeHttpRequests(authz -> authz
                .anyRequest().permitAll()
            )
            .formLogin(form -> form
                .loginPage("/user/signin")
                .usernameParameter("email")
                .loginProcessingUrl("/user/signin")
                .defaultSuccessUrl("/dashboard", true)
                .successHandler(successHandler())
                .permitAll());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserService userService) {
        return userService;
    }
}