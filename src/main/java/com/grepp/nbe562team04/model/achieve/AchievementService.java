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
            log.info("🎯 [목표 기업 3개] 이미 업적을 가지고 있음. 리턴 null");
            return null;
        }

        User user = userRepository.findById(userId).orElseThrow();
        int companyCount = user.getGoalCompanies().size(); // <- 연관관계 기반으로 판단

        if (companyCount < 3) {
            log.info("📌 목표 기업이 {}개로 부족합니다. 업적 지급되지 않음.", companyCount);
            return null;
        }

        Achievement achievement = achieveRepository.findById(achieveId).orElseThrow();
        UsersAchieve ua = new UsersAchieve();
        ua.setUser(user);
        ua.setAchievement(achievement);
        ua.setAchievedAt(LocalDateTime.now());

        usersAchieveRepository.save(ua);
        log.info("🎉 목표 기업 3개 업적 지급 완료: {}", achievement.getName());
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
