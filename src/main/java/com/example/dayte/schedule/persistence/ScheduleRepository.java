package com.example.dayte.schedule.persistence;

import com.example.dayte.members.domain.User;
import com.example.dayte.schedule.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, String> {

    Optional<Schedule> findByUserAndStartDate (User user, LocalDate startDate);

    List<Schedule> findAllByUserOrderByStartDate(User user);

}
