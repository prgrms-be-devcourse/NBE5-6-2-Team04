package com.grepp.nbe562team04.model.goalcompany.repository;

import com.grepp.nbe562team04.model.goalcompany.entity.GoalCompany;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoalCompanyRepository extends JpaRepository<GoalCompany, Long> {
//    기본적인 CRUD 다 제공됨
//    findById(id)	    SELECT * FROM table WHERE id = ?
//    findAll()	        SELECT * FROM table
//    save(entity)	    INSERT 또는 UPDATE
//    deleteById(id)	DELETE FROM table WHERE id = ?
//    existsById(id)	존재 여부 확인

    List<GoalCompany> findByUserUserId(Long userId);
}