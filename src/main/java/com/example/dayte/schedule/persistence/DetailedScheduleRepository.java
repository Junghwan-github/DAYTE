package com.example.dayte.schedule.persistence;

import com.example.dayte.schedule.domain.DetailedSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailedScheduleRepository extends JpaRepository<DetailedSchedule, Long> {
}
