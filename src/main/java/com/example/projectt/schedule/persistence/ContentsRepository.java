package com.example.projectt.schedule.persistence;

import com.example.projectt.schedule.domain.Contents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ContentsRepository extends JpaRepository<Contents,String> {

    @Query("SELECT s FROM Schedule s WHERE s.scheduleDate = :uuid")
    Optional<Contents> getSelectContents(String uuid);
}
