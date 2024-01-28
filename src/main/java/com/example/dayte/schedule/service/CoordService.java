package com.example.dayte.schedule.service;

import com.example.dayte.schedule.domain.Coordinate;
import com.example.dayte.schedule.persistence.CoordRepository;
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
