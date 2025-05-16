package com.grepp.nbe562team04.model.user;

import com.grepp.nbe562team04.model.achieve.UsersAchieve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersAchieveRepository extends JpaRepository<UsersAchieve, Long> {
    boolean existsByUser_UserIdAndAchievement_AchieveId(Long userId, Long achieveId);

}