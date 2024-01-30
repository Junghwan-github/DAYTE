package com.example.dayte.notice.service;

import com.example.dayte.notice.domain.FilesInfo;
import com.example.dayte.notice.persistence.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    @Transactional
    public FilesInfo findByfileID(int fileId){

       FilesInfo filesInfo = fileRepository.findById(fileId).get();

        return filesInfo;
    }


}
