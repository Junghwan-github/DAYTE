
package com.example.dayte.schedule.persistence;

import com.example.dayte.schedule.domain.ScheduleDate;
import com.example.dayte.schedule.domain.ScheduleDateId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleDateRepository extends JpaRepository<ScheduleDate, ScheduleDateId> {

}