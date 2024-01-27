package com.example.dayte.schedule.persistence;

import com.example.dayte.schedule.domain.Contents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ContentsRepository extends JpaRepository<Contents,String> {

    @Query("SELECT s FROM Schedule s WHERE s.scheduleDate = :uuid")
    Optional<Contents> getSelectContents(String uuid);
}
