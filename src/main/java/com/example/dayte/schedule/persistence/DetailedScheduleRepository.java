package com.example.dayte.schedule.persistence;

import com.example.dayte.admin.contents.domain.AdminContents;
import com.example.dayte.schedule.domain.DetailedSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetailedScheduleRepository extends JpaRepository<DetailedSchedule, Long> {
    List<DetailedSchedule> findAllByAdminContents(AdminContents adminContents);

}
