package com.grepp.nbe562team04.model.dashboard;

import com.grepp.nbe562team04.model.dashboard.dto.DashboardDto;
import com.grepp.nbe562team04.model.dashboard.dto.GoalCompanyDto;
import com.grepp.nbe562team04.model.goalcompany.entity.GoalCompany;
import com.grepp.nbe562team04.model.user.entity.User;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final DashboardRepository dashboardRepository;

    public DashboardService(DashboardRepository dashboardRepository) {
        this.dashboardRepository = dashboardRepository;
    }

    // 대시보드 조회
    public DashboardDto getDashboard(User user) {

        DashboardDto dto = new DashboardDto();
        dto.setNickname(user.getNickname());
        dto.setComment(user.getComment());
        dto.setCreatedAt(user.getCreatedAt());

        dto.setJobType(List.of(user.getInterest().getJobType()));
        dto.setDevLang(List.of(user.getInterest().getDevLang()));
        dto.setFramework(List.of(user.getInterest().getFramework()));

        dto.setLevelName(user.getLevel().getLevelName());
        dto.setLevelValue(user.getLevel().getLevelId().intValue());
        dto.setExp(user.getExp());

        List<GoalCompany> goalCompanies = dashboardRepository.findGoalCompaniesByUser(user);
        List<GoalCompanyDto> companyDtos = goalCompanies.stream()
            .map(this::convertToDto)
            .toList();

        dto.setGoalCompanies(companyDtos);
        return dto;
    }


    // Id 로 목표기업 조회
    public GoalCompanyDto getCompanyDetailById(Long id) {
        GoalCompany company = dashboardRepository.findGoalCompanyById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 ID의 회사가 존재하지 않습니다: " + id));
        return convertToDto(company);
    }


    private GoalCompanyDto convertToDto(GoalCompany company) {
        GoalCompanyDto dto = new GoalCompanyDto();
        dto.setCompanyName(company.getCompanyName());
        dto.setStatus(company.getStatus());
        dto.setContent(company.getContent());

        if (!company.getGoals().isEmpty()) {
            dto.setStartDate(company.getGoals().get(0).getStartDate());
        }

        dto.setEndDate(company.getEndDate());
        dto.setDDay(ChronoUnit.DAYS.between(LocalDateTime.now(), company.getEndDate()));

        return dto;
    }
}

















