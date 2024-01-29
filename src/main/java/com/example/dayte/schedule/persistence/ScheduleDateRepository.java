
package com.example.dayte.schedule.persistence;

import com.example.dayte.members.domain.User;
import com.example.dayte.schedule.domain.ScheduleDate;
import com.example.dayte.schedule.domain.ScheduleDateId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ScheduleDateRepository extends JpaRepository<ScheduleDate, ScheduleDateId> {

}