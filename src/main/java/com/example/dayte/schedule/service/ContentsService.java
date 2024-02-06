package com.example.dayte.schedule.service;

import com.example.dayte.admin.contents.domain.AdminContents;
import com.example.dayte.admin.contents.persistence.AdminContentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContentsService {

    @Autowired
    AdminContentsRepository adminContentsRepository;

    @Transactional(readOnly = true)
    public List<AdminContents> getContentsList() {
        return adminContentsRepository.findAll();
    }

    public List<AdminContents> searchByContents(String searchContents) {
        if(searchContents == null) searchContents = "";
        return adminContentsRepository.findAllBySearch(searchContents);
    }
}
