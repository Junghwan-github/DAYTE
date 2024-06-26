package com.example.dayte.main.controller;

import com.example.dayte.admin.contents.domain.AdminContents;
import com.example.dayte.admin.contents.service.AdminContentsService;
import com.example.dayte.admin.mianslider.domain.IndexMainSlider;
import com.example.dayte.admin.mianslider.listener.MySessionListener;
import com.example.dayte.admin.mianslider.service.IndexMainSliderService;
import com.example.dayte.post.service.PostService;
import com.example.dayte.reply.service.ContentReplyService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private IndexMainSliderService indexMainSliderService;

    @Autowired
    private AdminContentsService adminContentsService;

    @Autowired
    private PostService postService;

    @Autowired
    private MySessionListener mySessionListener;

    @Autowired
    private ContentReplyService contentReplyService;

    @GetMapping({"/"})
    public String getIndexView(Model model, HttpServletRequest request) {
        List<IndexMainSlider> sliderList = indexMainSliderService.sliderList();
        HttpSession session = request.getSession();
        if (session.getAttribute("flag") == null) {
            mySessionListener.addActiveUsers(mySessionListener.getCurrentUsername());
            session.setAttribute("flag", mySessionListener.getCurrentUsername());
        }

        List<AdminContents> adminContents = adminContentsService.randomContents();

        model.addAttribute("sliderList", sliderList)
                .addAttribute("contentList", adminContents);
        return "index";
    }

    @GetMapping("/event/{eventName}")
    public String getEventName(Model model, @PathVariable String eventName) {
        List<IndexMainSlider> sliderList = indexMainSliderService.eventPageList(eventName);
        model.addAttribute("sliderList", sliderList);
        return "event/page";
    }

    @GetMapping("/members/login")
    public String login(@RequestParam(required = false) boolean hasMessage,
                        @RequestParam(required = false) String errorMessage,
                        Model model) {
        model.addAttribute("hasMessage", hasMessage);
        model.addAttribute("errorMessage", errorMessage);
        return "members/login";
    }

    @GetMapping("/members/join")
    public String join() {
        return "members/usingTerms";
    }

    @GetMapping("/members/joinForm")
    public String joinForm() {
        return "members/joinForm";
    }

    @GetMapping("/indexSearch")
    public String allSearches(@RequestParam("indexSearch") String searchWord, Model model) {
        model.addAttribute("contentsList", adminContentsService.searchByContents(searchWord))
                .addAttribute("postList", postService.postSearchToAll(searchWord))
                .addAttribute("postListText",postService.extractPostContentText())
                .addAttribute("starList", contentReplyService.avgStarList());
        return "contents/allSearches";
    }

}
