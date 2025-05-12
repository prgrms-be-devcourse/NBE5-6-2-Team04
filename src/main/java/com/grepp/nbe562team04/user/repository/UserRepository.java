package com.grepp.nbe562team04.user.repository;

import com.grepp.nbe562team04.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // 기본적인 CRUD 다 제공됨
}
