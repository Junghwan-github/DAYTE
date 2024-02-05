package com.example.dayte.notice.controller;

import com.example.dayte.notice.controller.advice.FileUtil;
import com.example.dayte.notice.domain.FilesInfo;
import com.example.dayte.notice.service.FileService;
import com.nimbusds.jose.shaded.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FileUtils;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;


@Controller
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private FileUtil fileUtils;

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


    @PostMapping(value="/uploadNoticeImageFile", produces = "application/json")
    @ResponseBody
    public JsonObject uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile) {

        JsonObject jsonObject = new JsonObject();

        String fileRoot = "C:\\summernote_image\\";	//저장될 외부 파일 경로
        String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자

        String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명

        File targetFile = new File(fileRoot + savedFileName);

        try {
            InputStream fileStream = multipartFile.getInputStream();
            FileUtils.copyInputStreamToFile(fileStream, targetFile);	//파일 저장
            jsonObject.addProperty("url", "/summernoteImage/"+savedFileName);
            jsonObject.addProperty("responseCode", "success");

        } catch (IOException e) {
            FileUtils.deleteQuietly(targetFile);	//저장된 파일 삭제
            jsonObject.addProperty("responseCode", "error");
            e.printStackTrace();
        }

        System.out.println("^^^^^^^");
        System.out.println(jsonObject);
        return jsonObject;
    }




}
