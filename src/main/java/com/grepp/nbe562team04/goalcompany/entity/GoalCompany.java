package com.grepp.nbe562team04.goalcompany.entity;

import com.grepp.nbe562team04.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "`goal_company`")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GoalCompany {

    @Id // 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 숫자 자동 증가
    @Column(name = "company_id")
    private Long companyId;

    @ManyToOne(fetch = FetchType.LAZY) // 지연 로딩
    @JoinColumn(name = "user_id") // 외래키
    private User user;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
