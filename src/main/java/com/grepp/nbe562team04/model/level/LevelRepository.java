package com.grepp.nbe562team04.model.level;

import com.grepp.nbe562team04.model.level.entity.Level;
import com.grepp.nbe562team04.model.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {

    Optional<Level> findFirstByOrderByLevelIdAsc();
}
