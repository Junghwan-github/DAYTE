package com.example.dayte.admin.contents.controller;

import com.example.dayte.admin.contents.domain.AdminContents;
import com.example.dayte.admin.contents.dto.AdminContentsDTO;
import com.example.dayte.admin.contents.dto.AdminContentsImageDTO;
import com.example.dayte.admin.contents.service.AdminContentsService;
import com.example.dayte.reply.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    ) {

        adminContentsService.insertAdminContents(adminContentsDTO);
        adminContentsService.insertAdminContentsImage(new AdminContentsImageDTO(adminContentsDTO.getImageFiles()));
        return new ResponseDTO<>(HttpStatus.OK.value(), "정상처리 되었습니다.");
    }

    @DeleteMapping("/admin/home/registration/contents/{uuid}")
    public @ResponseBody ResponseDTO<?> removeContents(@PathVariable String uuid) {
        adminContentsService.removeContent(uuid);

        return new ResponseDTO<>(HttpStatus.OK.value(), "해당 컨텐츠가 삭제되었습니다.");
    }

    // 검색 기능
    @PostMapping("/search")
    public @ResponseBody List<AdminContents> searchContents(@RequestBody Map<String, String> search) {
        List<AdminContents> searchByContents = adminContentsService.searchByContents(search.get("search"));
        return searchByContents;
    }

}
