package com.example.dayte.admin.contents.controller;

import com.example.dayte.admin.contents.dto.AdminContentsDTO;
import com.example.dayte.admin.contents.dto.AdminContentsImageDTO;
import com.example.dayte.admin.contents.service.AdminContentsService;
import com.example.dayte.reply.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AdminContentsController {

    private final ModelMapper modelMapper;

    private final AdminContentsService adminContentsService;

    @GetMapping("/admin/home/settings/contents")
    public String IndexContentsSliderView () {
        return "adminPage/settings/scheduleContentsList";
    }

    // 컨텐츠 등록 로직
    @PostMapping("/admin/home/registration/contents")
    public @ResponseBody ResponseDTO<?> insertIndexMainSlider (
                    @ModelAttribute AdminContentsDTO adminContentsDTO
//                    @RequestPart("imageFiles") MultipartFile[] images,
//                    @RequestPart("businessName") String businessName,
//                    @RequestPart("category") String category,
//                    @RequestPart("gu") String gu,
//                    @RequestPart("positionX") String positionX,
//                    @RequestPart("positionY") String positionY,
//                    @RequestPart("address") String address,
//                    @RequestPart("keyword") String keyword,
//                    @RequestPart("detailedDescription") String detailedDescription
                    ) {

        System.out.println("post로들어옴");

        for (int i = 0; i < adminContentsDTO.getImageFiles().size(); i++) {
            System.out.println("image : " + adminContentsDTO.getImageFiles().get(i).getOriginalFilename());
            System.out.println("image : " + adminContentsDTO.getImageFiles().get(i));
        }

        System.out.println("businessName : " + adminContentsDTO.getBusinessName());
        System.out.println("category : " + adminContentsDTO.getCategory());
        System.out.println("gu : " + adminContentsDTO.getGu());
        System.out.println("positionX : " + adminContentsDTO.getPositionX());
        System.out.println("positionY : " + adminContentsDTO.getPositionY());
        System.out.println("address : " + adminContentsDTO.getDetailedAddress());
        System.out.println("keyword : " + adminContentsDTO.getKeyword());
        System.out.println("detailedDescription : " + adminContentsDTO.getDetailedDescription());

        adminContentsService.insertAdminContents(adminContentsDTO);
        adminContentsService.insertAdminContentsImage(new AdminContentsImageDTO(adminContentsDTO.getImageFiles()));
        return new ResponseDTO<>(HttpStatus.OK.value(), "정상처리 되었습니다.");
    }

    @DeleteMapping("/admin/home/registration/contents/{uuid}")
    public @ResponseBody ResponseDTO<?> removeContents(@PathVariable String uuid) {
        adminContentsService.removeContent(uuid);

        return new ResponseDTO<>(HttpStatus.OK.value(), "해당 컨텐츠가 삭제되었습니다.");
    }

}
