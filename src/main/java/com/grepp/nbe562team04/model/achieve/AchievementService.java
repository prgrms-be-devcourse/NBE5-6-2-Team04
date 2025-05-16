package com.grepp.nbe562team04.model.achieve;

import com.grepp.nbe562team04.model.achieve.entity.Achievement;
import com.grepp.nbe562team04.model.achieve.entity.UsersAchieve;
import com.grepp.nbe562team04.model.user.UserRepository;
import com.grepp.nbe562team04.model.user.UsersAchieveRepository;
import com.grepp.nbe562team04.model.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AchievementService {

    private final UsersAchieveRepository usersAchieveRepository;
    private final UserRepository userRepository;
    private final AchieveRepository achieveRepository;

    @Transactional
    public String giveTutorialAchievement(Long userId) {
        Long achieveId = 1L;

        boolean already = usersAchieveRepository.existsByUser_UserIdAndAchievement_AchieveId(userId, achieveId);
        if (already) return null; // 이미 획득한 경우 null 반환

        UsersAchieve ua = new UsersAchieve();
        ua.setUser(userRepository.getReferenceById(userId));
        Achievement achievement = achieveRepository.getReferenceById(achieveId);
        ua.setAchievement(achievement);
        ua.setAchievedAt(LocalDateTime.now());

        usersAchieveRepository.save(ua);
        return achievement.getName(); // 새로 획득한 업적 이름 반환
    }

    public boolean isTutorialCompleted(User user) {
        return StringUtils.hasText(user.getEmail()) &&
                StringUtils.hasText(user.getNickname()) &&
                StringUtils.hasText(user.getComment()) &&
                StringUtils.hasText(user.getUserImage());
    }
}
