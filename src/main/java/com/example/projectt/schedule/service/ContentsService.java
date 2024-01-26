package com.example.projectt.schedule.service;

import com.example.projectt.schedule.domain.Contents;
import com.example.projectt.schedule.persistence.ContentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContentsService {

    @Autowired
    ContentsRepository contentsRepository;

    @Transactional(readOnly = true)
    public List<Contents> getContentsList() {
        return contentsRepository.findAll();
    }
}
