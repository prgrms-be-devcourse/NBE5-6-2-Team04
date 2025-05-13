package com.grepp.nbe562team04.model.user.repository;

import com.grepp.nbe562team04.model.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    // 기본적인 CRUD 다 제공됨

    @Query("SELECT u FROM User u JOIN FETCH u.interest WHERE u.id = :id")
    Optional<User> findUserWithInterestById(@Param("id") Long id);
}
