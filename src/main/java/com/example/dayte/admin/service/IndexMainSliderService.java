package com.example.dayte.admin.service;


import com.example.dayte.admin.domain.IndexMainSlider;
import com.example.dayte.admin.dto.IndexMainSliderDTO;
import com.example.dayte.admin.persistence.IndexMainSliderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;


@Service
public class IndexMainSliderService {

    @Autowired
    private IndexMainSliderRepository indexMainSliderRepository;

    @Autowired
    private ModelMapper modelMapper;



    private static final String imageUploadPath = "/data/images/index/";

    public void InsertSlider(IndexMainSliderDTO indexMainSliderDTO) {
        IndexMainSlider indexMainSlider = modelMapper.map(indexMainSliderDTO, IndexMainSlider.class);

        createDirectory();

        String indexSliderURL = saveImage(indexMainSliderDTO.getImages());
        indexMainSliderDTO.setImageUrl(indexSliderURL);

        indexMainSlider.setImages(indexSliderURL);

        indexMainSliderRepository.save(indexMainSlider);

    }
    public String saveImage(MultipartFile image) {

        try {
            String uuid = UUID.randomUUID().toString();
            String encodedFileName = URLEncoder.encode(Objects.requireNonNull(image.getOriginalFilename()), StandardCharsets.UTF_8);
            String fileName = uuid + "_" +encodedFileName;

            Path targetPath = Path.of(imageUploadPath + fileName);

            Files.copy(image.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            return imageUploadPath + fileName;
        } catch (IOException e) {
           ;;
            return null;
        }
    }

    private void createDirectory() {
        try {
            Path path = Path.of(IndexMainSliderService.imageUploadPath);

            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
        } catch (IOException e) {
           ;;
           return;
        }
    }
}
