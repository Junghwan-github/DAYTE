package com.example.dayte.admin.mianslider.listener;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;

public class MySessionListener implements HttpSessionListener {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static int activeSessions = 0;

    public static int getActiveSessions() {
        return activeSessions;
    }

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        activeSessions++;
        LocalDate today = LocalDate.now();
        jdbcTemplate.update("INSERT INTO visitor_statistics (date, visitors) VALUES (?, 1) " +
                        "ON DUPLICATE KEY UPDATE visitors = ?",
                today, activeSessions);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
    }

    @Scheduled(cron = "0 0 0 * * *")
    private void resetActiveSessions() {
        activeSessions = 0;
    }
}
