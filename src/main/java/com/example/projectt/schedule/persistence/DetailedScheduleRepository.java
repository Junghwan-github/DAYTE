package com.example.projectt.schedule.persistence;

import com.example.projectt.schedule.domain.DetailedSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DetailedScheduleRepository extends JpaRepository<DetailedSchedule, Long> {

}
