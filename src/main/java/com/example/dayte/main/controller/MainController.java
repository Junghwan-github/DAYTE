package com.example.dayte.main.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String indexView () {
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
