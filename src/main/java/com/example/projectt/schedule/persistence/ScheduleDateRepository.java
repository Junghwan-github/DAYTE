
package com.example.projectt.schedule.persistence;

import com.example.projectt.members.User;
import com.example.projectt.schedule.domain.ScheduleDate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ScheduleDateRepository extends JpaRepository<ScheduleDate, String> {

    Optional<ScheduleDate> findByUserAndStartDate (User user, LocalDate startDate);

    List<ScheduleDate> findByUser(User user);
}
