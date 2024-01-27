package com.example.dayte.admin.controller;

import com.example.dayte.admin.dto.IndexMainSliderDTO;
import com.example.dayte.admin.dto.ResponseDTO;
import com.example.dayte.admin.service.IndexMainSliderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class IndexMainSliderController {

    @Autowired
    IndexMainSliderService indexMainSliderService;



    @GetMapping("/admin/home/registration/indexmainslider")
    public String IndexMainSliderView () {
        return "adminPage/registration/indexMainSlider";
    }

    @PostMapping("/admin/home/registration/indexmainslider")
    public @ResponseBody ResponseDTO<?> insertIndexMainSlider (@RequestBody IndexMainSliderDTO indexMainSliderDTO) {
        indexMainSliderService.InsertSlider(indexMainSliderDTO);
        return new ResponseDTO<>(HttpStatus.OK.value(), "정상처리 되었습니다.");
    }
}
