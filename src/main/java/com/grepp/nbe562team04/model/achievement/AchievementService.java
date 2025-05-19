package com.grepp.nbe562team04.model.achievement;

import com.grepp.nbe562team04.model.achievement.dto.AchievementDto;
import com.grepp.nbe562team04.model.achievement.entity.Achievement;
import com.grepp.nbe562team04.model.user.entity.UsersAchieve;
import com.grepp.nbe562team04.model.user.UserRepository;
import com.grepp.nbe562team04.model.user.UsersAchieveRepository;
import com.grepp.nbe562team04.model.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Long achieveId = 1L;

        boolean already = usersAchieveRepository.existsByUser_UserIdAndAchievement_AchieveId(userId, achieveId);
        if (already) {
            return null;
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
        Long achieveId = 2L;

        boolean already = usersAchieveRepository.existsByUser_UserIdAndAchievement_AchieveId(userId, achieveId);
        if (already) return null;

        UsersAchieve ua = new UsersAchieve();
        ua.setUser(userRepository.getReferenceById(userId));
        Achievement achievement = achieveRepository.findById(achieveId).orElseThrow();
        ua.setAchievement(achievement);
        ua.setAchievedAt(LocalDateTime.now());

        usersAchieveRepository.save(ua);
        log.info("업적 : {}", achievement.getName());
        return achievement.getName();
    }

    @Transactional
    public String giveThreeGoalCompaniesAchievement(Long userId) {
        Long achieveId = 10L;

        boolean already = usersAchieveRepository.existsByUser_UserIdAndAchievement_AchieveId(userId, achieveId);
        if (already) {
            return null;
        }

        User user = userRepository.findById(userId).orElseThrow();
        int companyCount = user.getGoalCompanies().size();

        if (companyCount < 3) {
            return null;
        }

        Achievement achievement = achieveRepository.findById(achieveId).orElseThrow();
        UsersAchieve ua = new UsersAchieve();
        ua.setUser(user);
        ua.setAchievement(achievement);
        ua.setAchievedAt(LocalDateTime.now());

        usersAchieveRepository.save(ua);
        return achievement.getName();
    }

    @Transactional
    public String giveFirstGoalCreateAchievement(Long userId) {
        Long achieveId = 16L; // 업적10의 ID

        boolean already = usersAchieveRepository.existsByUser_UserIdAndAchievement_AchieveId(userId, achieveId);
        if (already) {
            return null;
        }

        User user = userRepository.findById(userId).orElseThrow();
        Achievement achievement = achieveRepository.findById(achieveId).orElseThrow();

        UsersAchieve ua = new UsersAchieve();
        ua.setUser(user);
        ua.setAchievement(achievement);
        ua.setAchievedAt(LocalDateTime.now());

        usersAchieveRepository.save(ua);
        return achievement.getName();
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
