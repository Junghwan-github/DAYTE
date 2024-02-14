package com.example.dayte.admin.mianslider.listener;

import com.example.dayte.admin.mianslider.domain.VisitorStatistics;
import com.example.dayte.admin.mianslider.persistence.VisitorStatisticsRepository;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;

public class MySessionListener implements HttpSessionListener {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private VisitorStatisticsRepository visitorStatisticsRepository;

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        LocalDate today = LocalDate.now();
        VisitorStatistics visitorStatistics = visitorStatisticsRepository.findByDate(today)
                .orElseGet(() -> new VisitorStatistics());
        jdbcTemplate.update("INSERT INTO visitor_statistics (date, visitors) VALUES (?, 1) " +
                        "ON DUPLICATE KEY UPDATE visitors = ?",
                today, visitorStatistics.getVisitors() + 1);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
    }
}
