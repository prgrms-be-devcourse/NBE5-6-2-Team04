package com.grepp.nbe562team04.model.achieve;

import com.grepp.nbe562team04.model.achieve.entity.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AchieveRepository extends JpaRepository<Achievement, Long> {
    Achievement findByName(String name);
}