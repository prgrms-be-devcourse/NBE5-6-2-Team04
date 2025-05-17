package com.grepp.nbe562team04.model.achieve;

import com.grepp.nbe562team04.model.achieve.dto.AchievementDto;
import com.grepp.nbe562team04.model.achieve.entity.Achievement;
import com.grepp.nbe562team04.model.user.entity.UsersAchieve;
import com.grepp.nbe562team04.model.user.UserRepository;
import com.grepp.nbe562team04.model.user.UsersAchieveRepository;
import com.grepp.nbe562team04.model.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

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
        log.info("🧪 업적 확인 시작 for userId={}", userId); // 호출 횟수 확인용
        Long achieveId = 1L;

        boolean already = usersAchieveRepository.existsByUser_UserIdAndAchievement_AchieveId(userId, achieveId);
        if (already) {
            log.info("🎯 이미 업적을 가지고 있음. 리턴 null");
            return null; // 이미 획득한 경우 null 반환
        }

        UsersAchieve ua = new UsersAchieve();
        ua.setUser(userRepository.getReferenceById(userId));
        Achievement achievement = achieveRepository.getReferenceById(achieveId);
        ua.setAchievement(achievement);
        ua.setAchievedAt(LocalDateTime.now());

        usersAchieveRepository.save(ua);
        return achievement.getName();
    }

    @Transactional
    public String giveGoalCompanyAchievement(Long userId) {
        log.info("🧪 업적 확인 시작 for userId={}", userId); // 호출 횟수 확인용

        Long achieveId = 2L;

        boolean already = usersAchieveRepository.existsByUser_UserIdAndAchievement_AchieveId(userId, achieveId);
        if (already) {
            log.info("🎯 이미 업적을 가지고 있음. 리턴 null");
            return null;
        }

        UsersAchieve ua = new UsersAchieve();
        ua.setUser(userRepository.getReferenceById(userId));
        Achievement achievement = achieveRepository.findById(achieveId).orElseThrow();
        ua.setAchievement(achievement);
        ua.setAchievedAt(LocalDateTime.now());

        usersAchieveRepository.save(ua);
        log.info("업적 : {}", achievement.getName());
        return achievement.getName();
    }


    public boolean isTutorialCompleted(User user) {
        return StringUtils.hasText(user.getEmail()) &&
                StringUtils.hasText(user.getNickname()) &&
                StringUtils.hasText(user.getComment()) &&
                StringUtils.hasText(user.getUserImage());
    }

    public List<AchievementDto> getUserAchievements(Long userId) {
        List<UsersAchieve> usersAchievements = usersAchieveRepository.findWithAchievementByUserId(userId);
        return usersAchievements.stream()
                .map(ua -> new AchievementDto(
                        ua.getAchievement().getName(),
                        ua.getAchievement().getDescription()
                )).toList();
    }
}
