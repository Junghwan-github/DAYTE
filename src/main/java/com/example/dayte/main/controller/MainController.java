package com.example.dayte.main.controller;

import com.example.dayte.admin.contents.service.AdminContentsService;
import com.example.dayte.admin.mianslider.domain.IndexMainSlider;
import com.example.dayte.admin.mianslider.listener.MySessionListener;
import com.example.dayte.admin.mianslider.service.IndexMainSliderService;
import com.example.dayte.admin.mianslider.service.VisitorStatisticsService;
import com.example.dayte.post.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {


    @Autowired
    private IndexMainSliderService indexMainSliderService;

    @Autowired
    private AdminContentsService adminContentsService;

    @Autowired
    private PostService postService;

    @GetMapping({"/"})
    public String getIndexView(Model model, HttpServletRequest request) {
        List<IndexMainSlider> sliderList = indexMainSliderService.sliderList();

        HttpSession session = request.getSession();
        // 세션에 "visitedMain" 속성이 없으면 방문자 수 증가
        if (session.getAttribute("visitedMain") == null) {
            session.setAttribute("visitedMain", true);
        }

        model.addAttribute("sliderList", sliderList);
        return "index";
    }

    @GetMapping("/members/login")
    public String login (@RequestParam(required = false)boolean hasMessage,
                         @RequestParam(required = false)String errorMessage,
                         Model model) {
        model.addAttribute("hasMessage", hasMessage);
        model.addAttribute("errorMessage", errorMessage);
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



//    @GetMapping("/contents/detail") // 임시로 불러오게만~~
//    public String content() { return "contents/contentsInfo";}

    @GetMapping("/indexSearch")
    public String allSearches(@RequestParam("indexSearch") String searchWord, Model model) {
        model.addAttribute("contentsList", adminContentsService.searchByContents(searchWord))
                .addAttribute("postList", postService.postSearchToAll(searchWord));
        return "contents/allSearches";
    }

}
