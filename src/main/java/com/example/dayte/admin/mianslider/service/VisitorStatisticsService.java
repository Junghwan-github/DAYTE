package com.example.dayte.admin.mianslider.service;

import com.example.dayte.admin.mianslider.dto.VisitorStatisticsDTO;
import com.example.dayte.admin.mianslider.persistence.VisitorStatisticsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class VisitorStatisticsService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private VisitorStatisticsRepository visitorStatisticsRepository;

    public List<VisitorStatisticsDTO> getVisitorsCountList(LocalDate date, boolean flag) {
        if(!flag){
            List<Object[]> visitorStatisticsList = visitorStatisticsRepository.getAvgLocalDate(date);
            List<VisitorStatisticsDTO> dtos = new ArrayList<>();
            for (Object[] row : visitorStatisticsList) {
                int year = (int) row[0];
                int month = (int) row[1];
                BigDecimal averageVisitorsDecimal = (BigDecimal) row[2];
                double averageVisitors = averageVisitorsDecimal.doubleValue();
                dtos.add(new VisitorStatisticsDTO(year, month, (int) Math.round(averageVisitors)));
            }
            return dtos;
        }
        return visitorStatisticsRepository.findAllByDateAfter(date);
    }

}
