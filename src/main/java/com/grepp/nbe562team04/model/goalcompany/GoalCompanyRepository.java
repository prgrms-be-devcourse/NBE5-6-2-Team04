package com.grepp.nbe562team04.model.goalcompany;

import com.grepp.nbe562team04.model.goalcompany.entity.GoalCompany;
import com.grepp.nbe562team04.model.user.entity.User;
import java.util.Arrays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalCompanyRepository extends JpaRepository<GoalCompany, Long> {
//    기본적인 CRUD 다 제공됨
//    findById(id)	    SELECT * FROM table WHERE id = ?
//    findAll()	        SELECT * FROM table
//    save(entity)	    INSERT 또는 UPDATE
//    deleteById(id)	DELETE FROM table WHERE id = ?
//    existsById(id)	존재 여부 확인

    List<GoalCompany> findByUserUserId(Long userId);

    // 알림 생성 시 유저의 모든 목표 기업 조회
    List<GoalCompany> findAllByUser(User user);
}