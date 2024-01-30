package com.example.dayte.notice.controller.advice;


import com.example.dayte.notice.domain.FilesInfo;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileUtils {

    private final String uploadPath = Paths.get("C:", "upload-files").toString();

    //다중 파일 업로드
    public List<FilesInfo> uploadFiles(final List<MultipartFile> multipartFiles){
        List<FilesInfo> files = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty())
                files.add(uploadFile(multipartFile));

//           System.out.println("^^^^^^^^^^^^^^^^^^^^^");
//            System.out.println(multipartFile.getOriginalFilename());

        }
        return files;
    }

    //단일 파일 업로드 + 다중 파일일 경우 하나씩 처리
    public FilesInfo uploadFile(MultipartFile multipartFile){

        if (multipartFile.isEmpty()) {
            return null;
        }

        String savename = nameSaveInFile(multipartFile.getOriginalFilename());
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd")).toString();
        String uploadPath = getUploadPath(today) + File.separator + savename;
        File uploadFile = new File(uploadPath);

        try{
            multipartFile.transferTo(uploadFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return FilesInfo.builder()
                .originalName(multipartFile.getOriginalFilename())
                .saveName(savename)
                .fileSize(multipartFile.getSize())
                .build();


    }

    //서버단에 저장할 이름 with UUID
    public String nameSaveInFile(String filename){
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String extension = StringUtils.getFilenameExtension(filename);


        return uuid + "." + extension;
    }

    private String getUploadPath() {
        return makeDirectories(uploadPath);
    }

    private String getUploadPath(final String addPath) {
        return makeDirectories(uploadPath + File.separator + addPath);
    }

    //getUpload가 부르는 함수, 폴더의 경로 설정
    private String makeDirectories(final String path) {
        File dir = new File(path);
        if (dir.exists() == false) {
            dir.mkdirs();
        }
        return dir.getPath();
    }

}
