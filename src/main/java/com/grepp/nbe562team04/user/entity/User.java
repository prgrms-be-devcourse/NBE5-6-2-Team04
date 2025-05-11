package com.grepp.nbe562team04.user.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "`User`")
@Getter
@Setter
@NoArgsConstructor // 파라미터 없는 기본 생성자 자동 생성
@AllArgsConstructor // 모든 필드 포함 생성자 자동 생성
@Builder
public class User {

    @Id // 테이블의 기본키 임을 나타냄
    @Column(name = "user_id")
    private Long userId;

    // 다른 필드는 생략 — 추가 필요
}