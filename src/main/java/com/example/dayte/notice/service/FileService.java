package com.example.dayte.notice.service;

import com.example.dayte.notice.domain.FilesInfo;
import com.example.dayte.notice.persistence.FileRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    @Transactional
    public FilesInfo findByfileID(int fileId){

       FilesInfo filesInfo = fileRepository.findById(fileId).get();

        return filesInfo;
    }

    public Map<String, String> uploadSummernoteImageFile(MultipartFile multipartFile) {

        Map<String, String> fileObject = new HashMap<>();

        String fileRoot = "\\\\192.168.10.203/temp/files/images/";	//저장될 외부 파일 경로
        // Path targetPath = Path.of("\\\\192.168.10.203" +this.noticeImageUploadPath);

        String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자

        String savedFileName = UUID.randomUUID() + extension;	//저장될 파일명
        File targetFile = new File(fileRoot + savedFileName);

        try {
            InputStream fileStream = multipartFile.getInputStream();
            FileUtils.copyInputStreamToFile(fileStream, targetFile);	//파일 저장
            fileObject.put("url", "/temp/files/images/"+savedFileName);
            fileObject.put("responseCode", "success");
        } catch (IOException e) {
            FileUtils.deleteQuietly(targetFile);	//저장된 파일 삭제
            e.printStackTrace();
        }

        return fileObject;
    }
}
