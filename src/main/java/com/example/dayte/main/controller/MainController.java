package com.example.dayte.main.controller;

import com.example.dayte.admin.domain.IndexMainSlider;
import com.example.dayte.admin.dto.IndexMainSliderDTO;
import com.example.dayte.admin.service.IndexMainSliderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {


    @Autowired
    private IndexMainSliderService indexMainSliderService;

    @GetMapping({"/"})
    public String getIndexView(Model model) {
        List<IndexMainSlider> sliderList = indexMainSliderService.sliderList();
       model.addAttribute("sliderList", sliderList);
        return "index";
    }

    @GetMapping("/members/login")
    public String login () {
        return "members/login";
    }

    @GetMapping("/members/join")
    public String join () {
        return "members/usingTerms";
    }

    @GetMapping("/members/joinForm")
    public String joinForm () {
        return "members/joinForm";
    }
    @GetMapping("/contents/detail") // 임시로 불러오게만~~
    public String content() { return "contents/contentsInfo";}


}
