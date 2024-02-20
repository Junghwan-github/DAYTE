package com.example.dayte.admin.contents.service;

import com.example.dayte.admin.contents.domain.AdminContents;
import com.example.dayte.admin.contents.domain.AdminContentsImage;
import com.example.dayte.admin.contents.dto.AdminContentsDTO;
import com.example.dayte.admin.contents.dto.AdminContentsImageDTO;
import com.example.dayte.admin.contents.persistence.AdminContentsImageRepository;
import com.example.dayte.admin.contents.persistence.AdminContentsRepository;
import com.example.dayte.post.domin.Post;
import com.example.dayte.schedule.domain.DetailedSchedule;
import com.example.dayte.schedule.persistence.DetailedScheduleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AdminContentsService {

    private final AdminContentsRepository adminContentsRepository;
    private final AdminContentsImageRepository adminContentsImageRepository;
    private final ModelMapper modelMapper;
    private final DetailedScheduleRepository detailedScheduleRepository;

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

            Path targetPath = Path.of("\\\\192.168.10.203" +this.contentsImageUploadPath + ( this.uuid  + "_"+ image.getOriginalFilename()));

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

    // 2024.02.19 --------------------------------------------------------------------------------------------
    public void removeContent(String uuid) {

        List<DetailedSchedule> detailedSchedules =
                detailedScheduleRepository.findAllByAdminContents(adminContentsRepository.findById(uuid).get());

        detailedScheduleRepository.deleteAll(detailedSchedules);
        adminContentsRepository.deleteById(uuid);

        if (!detailedSchedules.isEmpty()) {
            System.out.println("!detailedSchedules.isEmpty() : " + !detailedSchedules.isEmpty());
            AdminContents adminContents = AdminContents.builder()
                    .uuid(uuid)
                    .businessName("관리자에 의해 삭제된 컨텐츠입니다.")
                    .category("1d53a954b6cf")
                    .gu(null)
                    .positionX(BigDecimal.valueOf(99.999999))
                    .positionY(BigDecimal.valueOf(99.999999))
                    .detailedAddress(null)
                    .contactInfo(null)
                    .opening(null)
                    .closed(null)
                    .keyword(null)
                    .detailedDescription(null)
                    .adminContentsImageList(null)
                    .facilities(null)
                    .contentReplyList(null)
                    .build();
            adminContentsRepository.save(adminContents);

            List<DetailedSchedule> newDetailedSchedules= new ArrayList<>();
            for (DetailedSchedule schedule: detailedSchedules) {
                DetailedSchedule detailedSchedule = DetailedSchedule.builder()
                        .id(schedule.getId())
                        .scheduleDate(schedule.getScheduleDate())
                        .adminContents(adminContents)
                        .build();
                newDetailedSchedules.add(detailedSchedule);
            }
            detailedScheduleRepository.saveAll(newDetailedSchedules);
        }

    }

    // --------------------------------------------------------------------------------------------------


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
    @Transactional(readOnly = true)
    public List<AdminContents> getContentsList() {
        return adminContentsRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<AdminContents> searchByContents(String searchContents) {
        if(searchContents == null) searchContents = "";
        return adminContentsRepository.findAllBySearch(searchContents);
    }

    @Transactional//(readOnly = true)
    public List<AdminContents> findAllBySearch(String category ,String search) {
        String categoryNames = "";
        if(search == null)
            search = "";

        switch (category) {
            case "hotels" -> categoryNames = "숙소";
            case "restaurants" -> categoryNames = "맛집";
            case "cafes" -> categoryNames = "카페";
            case "events" -> categoryNames = "이벤트";
        }
        if (search.contains("#")) {
            return adminContentsRepository.findAllByKeyWordSearch(categoryNames, search);
        } else if (List.of("중구", "수성구", "북구", "동구", "남구", "서구", "달서구", "달성군", "군위군").contains(search)) {
            return adminContentsRepository.findAllByGugunSearch(categoryNames, search);
        } else {
            return adminContentsRepository.findAllBySearch(categoryNames, search);
        }
    }

    @Transactional(readOnly = true)
    public List<AdminContents> getContentsCategoryList (String category) {
        String categoryNames = "";

        switch (category) {
            case "hotels" -> categoryNames = "숙소";
            case "restaurants" -> categoryNames = "맛집";
            case "cafes" -> categoryNames = "카페";
            case "events" -> categoryNames = "이벤트";
        }

        return adminContentsRepository.findAllByCategory(categoryNames);
    }

    // 2024.02.19 ---------------------------------------------------------------------------------

    @Transactional(readOnly = true)
    public Map<String, Integer> getArrangedList() {
        Map<String, Integer> arrangedList = new HashMap<>();
        arrangedList.put("all", adminContentsRepository.findAll().size());
        arrangedList.put("restaurant", adminContentsRepository.findAllByCategory("맛집").size());
        arrangedList.put("cafe", adminContentsRepository.findAllByCategory("카페").size());
        arrangedList.put("accommodations", adminContentsRepository.findAllByCategory("숙소").size());
        arrangedList.put("event", adminContentsRepository.findAllByCategory("이벤트").size());
        arrangedList.put("deleted", adminContentsRepository.findAllByCategory("1d53a954b6cf").size());
        return arrangedList;
    }

    @Transactional(readOnly = true)
    public Page<AdminContents> getPageableContentsList(
            String contentsField, String contentsWord, Pageable pageable) {

        Page<AdminContents> contentsPage;

        switch (contentsField) {
            case "businessName"
                    -> contentsPage = adminContentsRepository.getContentsListByBusinessName(contentsWord, pageable);
            case "category"
                    -> contentsPage = adminContentsRepository.getContentsListByCategory(contentsWord, pageable);
            case "gu"
                    -> contentsPage = adminContentsRepository.getContentsListByGu(contentsWord, pageable);
            case "keyword"
                    -> contentsPage = adminContentsRepository.getContentsListByKeyword(contentsWord, pageable);
            case "deleted"
                    -> contentsPage = adminContentsRepository.getDeleteContentsList(pageable);
            default
                    -> contentsPage = adminContentsRepository.findAll(contentsWord, pageable);
        }
        return contentsPage;
    }

}
