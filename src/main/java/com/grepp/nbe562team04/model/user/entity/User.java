package com.grepp.nbe562team04.model.user.entity;

import com.grepp.nbe562team04.model.level.entity.Level;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import javax.management.relation.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String userImage;
    private String profileImageUrl;
    private String nickname;
    @ManyToOne
    @JoinColumn(name = "level_id")
    private Level level;
    private Integer exp;
    private LocalDateTime createdAt;
    private String comment;
}