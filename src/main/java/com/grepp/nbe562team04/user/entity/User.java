package com.grepp.nbe562team04.user.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "User")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @Column(name = "user_id")
    private Long userId;

    // 다른 필드는 생략 — goal_company 참조용으로 이거 하나면 됨
}