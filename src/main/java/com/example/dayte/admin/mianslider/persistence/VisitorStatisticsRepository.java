package com.example.dayte.admin.mianslider.persistence;

import com.example.dayte.admin.mianslider.domain.VisitorStatistics;
import com.example.dayte.admin.mianslider.dto.VisitorStatisticsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VisitorStatisticsRepository extends JpaRepository<VisitorStatistics, Long> {

        @Query("SELECT new com.example.dayte.admin.mianslider.dto.VisitorStatisticsDTO(vs.date,vs.visitors) " +
                "FROM VisitorStatistics vs " +
                "WHERE vs.date > :date")
List<VisitorStatisticsDTO> findAllByDateAfter(LocalDate date);

        @Query(value = "SELECT YEAR(date) AS year, " +
                "MONTH(date) AS month, " +
                "ROUND(AVG(visitors)) AS average_value " +
                "FROM visitor_statistics " +
                "WHERE date BETWEEN :date AND NOW() " +
                "GROUP BY YEAR(date), MONTH(date) " +
                "ORDER BY year, month", nativeQuery = true)
        List<Object[]> good(LocalDate date);

        Optional<VisitorStatistics> findByDate(LocalDate date);

}
