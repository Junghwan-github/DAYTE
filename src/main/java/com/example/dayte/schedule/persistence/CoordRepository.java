package com.example.dayte.schedule.persistence;

import com.example.dayte.schedule.domain.Coordinate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoordRepository extends JpaRepository<Coordinate, String> {


}
