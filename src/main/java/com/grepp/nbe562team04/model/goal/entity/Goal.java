package com.grepp.nbe562team04.model.goal.entity;

import com.grepp.nbe562team04.model.goalcompany.entity.GoalCompany;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "goal")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goal_id")
    private Long goalId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private GoalCompany goalCompany;

    @Column(nullable = false)
    private String title;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "is_done")
    private Boolean isDone;
}