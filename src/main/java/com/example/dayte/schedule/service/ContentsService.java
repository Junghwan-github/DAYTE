package com.example.dayte.schedule.service;

import com.example.dayte.schedule.domain.Contents;
import com.example.dayte.schedule.persistence.ContentsRepository;
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

    public List<Contents> searchByContents(String searchContents) {
        if(searchContents == null) searchContents = "";
        return contentsRepository.findAllBySearch(searchContents);
    }
}
