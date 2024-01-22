package com.example.projectt.schedule.service;

import com.example.projectt.schedule.domain.Coordinate;
import com.example.projectt.schedule.persistence.CoordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CoordService {
    @Autowired
    private CoordRepository coordRepository;

    @Transactional(readOnly = true)
    public List<Coordinate> getCoordList() {
        return coordRepository.findAll();
    }
}
