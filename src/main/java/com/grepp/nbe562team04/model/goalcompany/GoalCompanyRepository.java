package com.grepp.nbe562team04.model.goalcompany;

import com.grepp.nbe562team04.model.goalcompany.entity.GoalCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalCompanyRepository extends JpaRepository<GoalCompany, Long> {

}