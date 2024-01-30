package com.example.dayte.notice.controller;

import com.example.dayte.notice.controller.advice.FileUtils;
import com.example.dayte.notice.domain.FilesInfo;
import com.example.dayte.notice.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


@Controller
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private FileUtils fileUtils;

    @GetMapping("/notice/{noticeNo}/files/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable int noticeNo, @PathVariable int fileId){
//        System.out.println(noticeNo);
//        System.out.println(fileId);
        FilesInfo file = fileService.findByfileID(fileId);
        Resource resource = fileUtils.readFileAsResource(file);

        try{
            String filename = URLEncoder.encode(file.getOriginalName(), "UTF-8");
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + filename + "\";")
                    .header(HttpHeaders.CONTENT_LENGTH, file.getFileSize() + "")
                    .body(resource);
        }  catch (UnsupportedEncodingException e) {
            throw new RuntimeException("filename encoding failed : " + file.getOriginalName());
        }
    }


}
