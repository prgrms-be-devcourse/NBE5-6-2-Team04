package com.grepp.nbe562team04.model.achieve.entity;

import com.grepp.nbe562team04.model.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "users_achieve")
public class UsersAchieve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "achieve_id")
    private Achievement achievement;

    @Column(name = "achieved_at")
    private LocalDateTime achievedAt;
}
