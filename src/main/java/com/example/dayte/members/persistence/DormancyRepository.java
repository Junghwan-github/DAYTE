package com.example.dayte.members.persistence;

import com.example.dayte.members.domain.Dormancy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DormancyRepository extends JpaRepository<Dormancy, String> {

    List<Dormancy> findByDormancyDateLessThanEqual(LocalDate dormancyDate);

}
