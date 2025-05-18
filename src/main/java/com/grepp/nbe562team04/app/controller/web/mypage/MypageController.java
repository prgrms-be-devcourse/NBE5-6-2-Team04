package com.grepp.nbe562team04.app.controller.web.mypage;

import com.grepp.nbe562team04.model.level.LevelRepository;
import com.grepp.nbe562team04.model.level.entity.Level;
import com.grepp.nbe562team04.model.user.UserRepository;
import com.grepp.nbe562team04.model.user.UserService;
import com.grepp.nbe562team04.model.user.dto.UserDto;
import com.grepp.nbe562team04.model.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.csrf.CsrfToken;
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

    @GetMapping("mypage")
    public String index(@AuthenticationPrincipal UserDetails userDetails , Model model, CsrfToken csrfToken){
        String email = userDetails.getUsername();
        User user = userService.findByEmail(email);

        int progress = levelProgress(user);
        model.addAttribute("user", user);
        model.addAttribute("progressPercent", progress);
        model.addAttribute("_csrf", csrfToken);
        return "mypage/mypage";
    }

    @GetMapping("update")
    public String showUpdatePage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String email = userDetails.getUsername();
        User user = userService.findByEmail(email);

        int progress = levelProgress(user);
        model.addAttribute("user", user);
        model.addAttribute("progressPercent", progress);
        return "mypage/mypageUpdate";
    }

    @PostMapping("update")
    public String updateUser(@AuthenticationPrincipal UserDetails userDetails,
                             @ModelAttribute UserDto dto,
                             @RequestParam("userImageFile") MultipartFile file) throws IOException {

        String email = userDetails.getUsername();
        userService.updateUser(email, dto, file);
        return "redirect:/users/mypage";
    }

    public int levelProgress(User user){
        int userExp = user.getExp();
        int currentXp = user.getLevel().getXp();

        Optional<Level> nextLevelOpt = levelRepository.findTopByXpGreaterThanOrderByXpAsc(userExp);
        int nextXp = nextLevelOpt.map(Level::getXp).orElse(currentXp + 1);

        int progress = 0;
        if (nextXp > currentXp) {
            progress = (int)(((double)(userExp - currentXp) / (nextXp - currentXp)) * 100);
        }
        progress = Math.max(0, Math.min(progress, 100));

        return progress;
    }
}
