package com.example.projectt.schedule.persistence;

import com.example.projectt.schedule.domain.Coordinate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoordRepository extends JpaRepository<Coordinate, String> {


}
