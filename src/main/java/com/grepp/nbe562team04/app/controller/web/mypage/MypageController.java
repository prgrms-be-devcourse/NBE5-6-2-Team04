package com.grepp.nbe562team04.app.controller.web.mypage;

import com.grepp.nbe562team04.model.auth.domain.Principal;
import com.grepp.nbe562team04.model.level.LevelRepository;
import com.grepp.nbe562team04.model.level.LevelService;
import com.grepp.nbe562team04.model.level.entity.Level;
import com.grepp.nbe562team04.model.user.UserRepository;
import com.grepp.nbe562team04.model.user.UserService;
import com.grepp.nbe562team04.model.user.dto.UserDto;
import com.grepp.nbe562team04.model.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("users")
@RequiredArgsConstructor
public class MypageController {

    private final UserRepository userRepository;
    private final LevelRepository levelRepository;
    private final UserService userService;
    private final LevelService levelService;

    @GetMapping("mypage")
    public String index(@AuthenticationPrincipal Principal principal , Model model){
        String email = principal.getUsername();
        User user = userService.findByEmail(email);

        int progress = levelService.levelProgress(user);
        model.addAttribute("user", user);
        model.addAttribute("progressPercent", progress);
        return "mypage/mypage";
    }

    @GetMapping("update")
    public String showUpdatePage(@AuthenticationPrincipal Principal principal, Model model) {
        String email = principal.getUsername();
        User user = userService.findByEmail(email);

        int progress = levelService.levelProgress(user);
        model.addAttribute("user", user);
        model.addAttribute("progressPercent", progress);
        return "mypage/mypageUpdate";
    }

    @PostMapping("update")
    public String updateUser(@AuthenticationPrincipal Principal principal,
                             @ModelAttribute UserDto dto,
                             @RequestParam("userImageFile") MultipartFile file,
                             @RequestParam("currentPassword") String currentPassword,
                             Model model) throws IOException {

        String email = principal.getUsername();
        User user = userService.findByEmail(email);

        // 현재 비밀번호 확인
        if (!userService.checkPassword(user, currentPassword)) {
            int progress = levelService.levelProgress(user);
            model.addAttribute("user", user);
            model.addAttribute("passwordError", "비밀번호를 확인하세요");
            model.addAttribute("progressPercent", progress);
            return "mypage/mypageUpdate"; // 다시 수정 페이지로
        }
        userService.updateUser(email, dto, file);
        return "redirect:/users/mypage";
    }

    @PostMapping("delete")
    @ResponseBody
    public ResponseEntity<String> deleteAccount(@AuthenticationPrincipal Principal principal) {
        String email = principal.getUsername();
        userService.softDeleteUser(email);
        return ResponseEntity.ok("탈퇴 완료");
    }
}
