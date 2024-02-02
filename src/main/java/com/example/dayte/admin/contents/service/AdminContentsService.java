package com.example.dayte.admin.contents.service;

import com.example.dayte.admin.contents.domain.AdminContents;
import com.example.dayte.admin.contents.domain.AdminContentsImage;
import com.example.dayte.admin.contents.dto.AdminContentsDTO;
import com.example.dayte.admin.contents.dto.AdminContentsImageDTO;
import com.example.dayte.admin.contents.persistence.AdminContentsImageRepository;
import com.example.dayte.admin.contents.persistence.AdminContentsRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminContentsService {

    private final AdminContentsRepository adminContentsRepository;
    private final AdminContentsImageRepository adminContentsImageRepository;
    private final ModelMapper modelMapper;

    private String uuid;

    private static final String contentsImageUploadPath = "/temp/images/admin/contentsList/";

    @Transactional
    public void insertAdminContents(AdminContentsDTO adminContentsDTO) {
        this.uuid = UUID.randomUUID().toString();
        adminContentsDTO.setUuid(this.uuid);
        adminContentsRepository.save(modelMapper.map(adminContentsDTO, AdminContents.class));
    }

    @Transactional
    public void insertAdminContentsImage(
            AdminContentsImageDTO adminContentsImageDTO
    ){

        createDirectory();

        System.out.println("adminContentsImageDTO.getImageFiles() : " + adminContentsImageDTO.getImages());

        adminContentsImageDTO.getImages().forEach(image -> {
            String contentListURL = saveImage(image);
            adminContentsImageDTO.setImageURL(contentListURL);
            adminContentsImageDTO.setAdminContents(adminContentsRepository.findById(uuid).get());
            AdminContentsImage adminContentsImage = modelMapper.map(adminContentsImageDTO, AdminContentsImage.class);

            adminContentsImageRepository.save(adminContentsImage);
        });

    }
    public String saveImage(MultipartFile image) {

        try {
            String encodedFileName = UriUtils.encode(Objects.requireNonNull(image.getOriginalFilename()), StandardCharsets.UTF_8);
            String fileName = this.uuid + "_" + encodedFileName;

            Path targetPath = Path.of("\\\\192.168.10.75"+this.contentsImageUploadPath + fileName);

            Files.copy(image.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            return this.contentsImageUploadPath + fileName;
        } catch (IOException e) {
            ;;
            return null;
        }
    }

    // 기본으로 저장될 주소에 폴더가 없으면 새로 생성하는 메서드
    private void createDirectory() {
        try {
            Path path = Path.of(this.contentsImageUploadPath);

            if (!Files.exists(path))
                Files.createDirectories(path);

        } catch (IOException e) {
            ;;
            return;
        }
    }

    public void removeContent(String uuid) {
        adminContentsRepository.delete(adminContentsRepository.findById(uuid).get());
    }

    @Transactional
    public AdminContents getShowContentsDetail (String id) {
        Optional<AdminContents> adminContentsOptional = adminContentsRepository.findById(id);

        if (adminContentsOptional.isPresent()) {
            return adminContentsOptional.get();
        } else {
            // Handle the case when the entity with the specified UUID is not found
            throw new EntityNotFoundException("id: " + id);
        }

    }

}
