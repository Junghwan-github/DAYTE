package com.example.dayte.schedule.persistence;

import com.example.dayte.schedule.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("SELECT s FROM Schedule s WHERE s.scheduleDate.uuid = :uuid ORDER BY s.nowDate ASC, s.sequence")
    List<Schedule> findByUuid(@Param("uuid") String uuid);
}
