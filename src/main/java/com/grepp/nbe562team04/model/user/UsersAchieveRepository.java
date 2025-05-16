package com.grepp.nbe562team04.model.user;

import com.grepp.nbe562team04.model.achieve.entity.UsersAchieve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersAchieveRepository extends JpaRepository<UsersAchieve, Long> {
    boolean existsByUser_UserIdAndAchievement_AchieveId(Long userId, Long achieveId);

    // 최근 3개 업적 조회
    List<UsersAchieve> findTop3ByUser_UserIdOrderByAchievedAtDesc(Long userId);
    // 전체 업적 조회
    List<UsersAchieve> findByUser_UserIdOrderByAchievedAtDesc(Long userId);
}