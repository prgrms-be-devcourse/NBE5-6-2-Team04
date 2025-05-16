package com.grepp.nbe562team04.model.dashboard;

import com.grepp.nbe562team04.model.dashboard.dto.DashboardDto;
import com.grepp.nbe562team04.model.dashboard.dto.GoalCompanyDto;
import com.grepp.nbe562team04.model.dashboard.dto.InterestDto;
import com.grepp.nbe562team04.model.goalcompany.GoalCompanyRepository;
import com.grepp.nbe562team04.model.goalcompany.entity.GoalCompany;
import com.grepp.nbe562team04.model.interest.code.Type;
import com.grepp.nbe562team04.model.interest.entity.Interest;
import com.grepp.nbe562team04.model.user.UserRepository;
import com.grepp.nbe562team04.model.user.entity.User;
import com.grepp.nbe562team04.model.user.entity.UserInterest;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class DashboardService {

    private final DashboardRepository dashboardRepository;
    private final UserRepository userRepository;

    public DashboardService(DashboardRepository dashboardRepository,
        UserRepository userRepository, GoalCompanyRepository goalCompanyRepository) {
        this.dashboardRepository = dashboardRepository;
        this.userRepository = userRepository;
    }

    // 대시보드 조회
    @Transactional
    public DashboardDto getDashboard(User user) {

        user.getUserInterests().forEach(ui -> ui.getInterest().getInterestName());

        // 사용자 정보
        DashboardDto dto = new DashboardDto();
        dto.setNickname(user.getNickname());
        dto.setComment(user.getComment());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUserImage(user.getUserImage());

        long dayCount = ChronoUnit.DAYS.between(user.getCreatedAt().atStartOfDay().toLocalDate(), LocalDate.now()) + 1;
        dto.setDayCount(dayCount);

        // 관심 분야 필터링
        List<InterestDto> interests = user.getUserInterests().stream()
            .map(UserInterest::getInterest)
            .map(i -> new InterestDto(i.getType().name(), i.getInterestName()))
            .toList();
        dto.setInterests(interests);

        List<String> roles = user.getUserInterests().stream()
            .map(UserInterest::getInterest)
            .filter(i -> i.getType() == Type.ROLE)
            .map(Interest::getInterestName)
            .toList();

        List<String> skills = user.getUserInterests().stream()
            .map(UserInterest::getInterest)
            .filter(i -> i.getType() == Type.SKILL)
            .map(Interest::getInterestName)
            .toList();

        // 빈 리스트 방어처리
        dto.setJobType(roles.isEmpty() ? List.of("직무 없음") : roles);
        dto.setDevLang(skills.isEmpty() ? List.of("언어 없음") : skills);

        // 레벨 정보
        dto.setLevelName(user.getLevel().getLevelName());
        dto.setLevelValue(user.getLevel().getLevelId().intValue());
        dto.setExp(user.getExp());

        // 알림
        dto.setNotificationOn(user.isNotificationOn());

        // 목표기업 정보
        List<GoalCompany> goalCompanies = dashboardRepository.findGoalCompaniesByUser(user);
        List<GoalCompanyDto> companyDtos = goalCompanies.stream()
            .map(this::convertToDto)
            .toList();

        dto.setGoalCompanies(companyDtos);
        return dto;
    }


    // Id 로 목표기업 조회
    public GoalCompanyDto getCompanyDetailById(Long id) {
        GoalCompany company = dashboardRepository.findByCompanyId(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 ID의 회사가 존재하지 않습니다: " + id));
        return convertToDto(company);
    }

    // 알림 토글 처리 로직
    @Transactional
    public void toggleNotification(User user) {
        user.setNotificationOn(!user.isNotificationOn());
        userRepository.save(user);
    }



    private GoalCompanyDto convertToDto(GoalCompany company) {
        GoalCompanyDto dto = new GoalCompanyDto();
        dto.setCompanyId(company.getCompanyId());
        dto.setCompanyName(company.getCompanyName());
        dto.setStatus(company.getStatus());
        dto.setContent(company.getContent());

        if (!company.getGoals().isEmpty()) {
            dto.setStartDate(company.getGoals().get(0).getStartDate());
        }
        dto.setStatusLabel(company.getStatus().getLabel());
        dto.setEndDate(company.getEndDate());
        dto.setDDay(ChronoUnit.DAYS.between(LocalDate.now(), company.getEndDate()));

        return dto;
    }
}

















