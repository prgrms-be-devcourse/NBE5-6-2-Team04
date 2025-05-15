package com.grepp.nbe562team04.model.dashboard.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DashboardDto {

    // 사용자 정보
    private String nickname;
    private String comment;
    private LocalDate createdAt;
    private long dayCount;
    private String userImage;

    // 관심 분야
    private List<String> jobType;
    private List<String> devLang;
    private List<String> framework;
    private List<InterestDto> interests;

    // 레벨 정보
    private String levelName;
    private int levelValue;
    private int exp;

    // 목표 기업
    private List<GoalCompanyDto> goalCompanies;

    // 알림 토글
    private boolean notificationOn;



}
