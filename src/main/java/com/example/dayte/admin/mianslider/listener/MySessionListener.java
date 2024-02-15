package com.example.dayte.admin.mianslider.listener;

import com.example.dayte.admin.mianslider.domain.VisitorStatistics;
import com.example.dayte.admin.mianslider.persistence.VisitorStatisticsRepository;
import com.example.dayte.security.dto.UserSecurityDTO;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MySessionListener implements HttpSessionListener {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private VisitorStatisticsRepository visitorStatisticsRepository;
    private List<String> activeUsers = new ArrayList<>();

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
        activeUsers.remove(getCurrentUsername());
    }

    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserSecurityDTO) {
            UserSecurityDTO user = (UserSecurityDTO) authentication.getPrincipal();
            return user.getUserEmail();
        }
        return null;
    }

    public void addActiveUsers(String userEmail) {
        if(userEmail != null && !userEmail.trim().isEmpty()) {
            this.activeUsers.add(userEmail);
        }
    }
    public List<String> getActiveUsers() {
        return activeUsers;
    }

    @Override
    public String toString() {
        return "MySessionListener{" +
                "activeUsers=" + activeUsers +
                '}';
    }
}
