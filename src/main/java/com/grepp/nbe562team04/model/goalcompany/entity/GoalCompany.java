package com.grepp.nbe562team04.model.goalcompany.entity;

import com.grepp.nbe562team04.model.goal.entity.Goal;
import com.grepp.nbe562team04.model.goalcompany.code.RecruitStep;
import com.grepp.nbe562team04.model.goalcompany.code.GoalStatus;
import com.grepp.nbe562team04.model.user.entity.User;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "goal_company")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GoalCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long companyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private RecruitStep status;
    private GoalStatus status;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Builder.Default
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Goal> goals = new ArrayList<>();
}
