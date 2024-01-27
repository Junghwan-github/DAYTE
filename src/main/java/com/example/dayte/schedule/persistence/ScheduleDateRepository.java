
package com.example.dayte.schedule.persistence;

import com.example.dayte.members.domain.User;
import com.example.dayte.schedule.domain.ScheduleDate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ScheduleDateRepository extends JpaRepository<ScheduleDate, String> {

    Optional<ScheduleDate> findByUserAndStartDate (User user, LocalDate startDate);


    List<ScheduleDate> findByUserOrderByStartDate(User user);
}
