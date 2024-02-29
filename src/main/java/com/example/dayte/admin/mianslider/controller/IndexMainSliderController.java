package com.example.dayte.admin.mianslider.controller;

import com.example.dayte.admin.mianslider.dto.IndexMainSliderDTO;
import com.example.dayte.admin.mianslider.service.IndexMainSliderService;
import com.example.dayte.members.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class IndexMainSliderController {

    @Autowired
    IndexMainSliderService indexMainSliderService;

    @GetMapping("/admin/home/settings/index")
    public String IndexMainSliderView () {
        return "adminPage/settings/indexMainSlider";
    }

    @PostMapping("/admin/home/registration/index")
    public @ResponseBody ResponseDTO<?> insertIndexMainSlider (@RequestPart("images") MultipartFile images,
                                                               @RequestPart("category") String category,
                                                               @RequestPart("mainTitle") String mainTitle,
                                                               @RequestPart("subTitle") String subTitle,
                                                               @RequestPart("schedule") String schedule,
                                                               @RequestPart("address") String address,
                                                               @RequestPart("summary") String summary,
                                                               @RequestPart("href") String href) {

        IndexMainSliderDTO indexMainSliderDTO = new IndexMainSliderDTO();
        indexMainSliderDTO.setImages(images);
        indexMainSliderDTO.setCategory(category);
        indexMainSliderDTO.setMainTitle(mainTitle);
        indexMainSliderDTO.setSubTitle(subTitle);
        indexMainSliderDTO.setSchedule(schedule);
        indexMainSliderDTO.setAddress(address);
        indexMainSliderDTO.setSummary(summary);
        indexMainSliderDTO.setHref(href);
        indexMainSliderService.InsertSlider(indexMainSliderDTO);
        return new ResponseDTO<>(HttpStatus.OK.value(), "정상처리 되었습니다.");
    }


}
