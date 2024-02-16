package com.example.dayte.notice.controller.advice;


import com.example.dayte.notice.domain.FilesInfo;
import net.minidev.json.JSONUtil;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Component
public class FileUtil {

    private final String uploadPath = Paths.get("C:", "upload-files").toString();

    private static final String noticeImageUploadPath = "/temp/files/notice/";

    private String originPath;

    //다중 파일 업로드
    public List<FilesInfo> uploadFiles(final List<MultipartFile> multipartFiles){
        System.out.println(uploadPath);
        List<FilesInfo> files = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()){
                FilesInfo upFile = uploadFile(multipartFile);
                files.add(upFile);

                try {
                    String encodedFileName = UriUtils.encode(Objects.requireNonNull(multipartFile.getOriginalFilename()), StandardCharsets.UTF_8);

                    Path targetPath = Path.of("\\\\192.168.10.203" +this.noticeImageUploadPath + ( upFile.getSaveName()  + "_"+ multipartFile.getOriginalFilename()));

                    Path sourcePath = Paths.get(originPath);

                    Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            
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
        originPath = uploadPath;
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


    public Resource readFileAsResource(FilesInfo file) {
        String uploadedDate = file.getCreateDate().toLocalDateTime().format(DateTimeFormatter.ofPattern("yyMMdd"));
        String filename = file.getSaveName();
        Path filePath = Paths.get(uploadPath, uploadedDate, filename);

        try {
            Resource resource = new UrlResource(filePath.toUri());
            if (!resource.exists() || !resource.isFile()) {
                throw new RuntimeException("file not found: " + filePath.toString());
            }
            return resource;
        } catch (MalformedURLException e) {
            throw new RuntimeException("Malformed URL: " + filePath.toString());
        }
    }
}
