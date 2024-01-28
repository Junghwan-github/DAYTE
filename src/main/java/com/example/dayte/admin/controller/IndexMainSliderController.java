package com.example.dayte.admin.controller;

import com.example.dayte.admin.dto.IndexMainSliderDTO;
import com.example.dayte.admin.dto.ResponseDTO;
import com.example.dayte.admin.service.IndexMainSliderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Controller
public class IndexMainSliderController {

    @Autowired
    IndexMainSliderService indexMainSliderService;



    @GetMapping("/admin/home/registration/index")
    public String IndexMainSliderView () {
        return "adminPage/registration/indexMainSlider";
    }

    @PostMapping("/admin/home/registration/index")
    public @ResponseBody ResponseDTO<?> insertIndexMainSlider (@RequestPart("images") MultipartFile images,
                                                               @RequestPart("category") String category,
                                                               @RequestPart("mainTitle") String mainTitle,
                                                               @RequestPart("subTitle") String subTitle,
                                                               @RequestPart("schedule") String schedule,
                                                               @RequestPart("address") String address,
                                                               @RequestPart("summary") String summary) {


        IndexMainSliderDTO indexMainSliderDTO = new IndexMainSliderDTO();
        indexMainSliderDTO.setImages(images);
        indexMainSliderDTO.setCategory(category);
        indexMainSliderDTO.setMainTitle(mainTitle);
        indexMainSliderDTO.setSubTitle(subTitle);
        indexMainSliderDTO.setSchedule(schedule);
        indexMainSliderDTO.setAddress(address);
        indexMainSliderDTO.setSummary(summary);
        indexMainSliderService.InsertSlider(indexMainSliderDTO);
        return new ResponseDTO<>(HttpStatus.OK.value(), "정상처리 되었습니다.");
    }
}
