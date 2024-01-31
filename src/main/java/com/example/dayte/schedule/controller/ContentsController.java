package com.example.dayte.schedule.controller;

import com.example.dayte.schedule.domain.Contents;
import com.example.dayte.schedule.service.ContentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class ContentsController {

    @Autowired
    ContentsService contentsService;

    // 검색 기능
    @PostMapping("/search")
    public @ResponseBody List<Contents> searchContents(@RequestBody Map<String, String> search) {
        List<Contents> searchByContents = contentsService.searchByContents(search.get("search"));
        return searchByContents;
    }

}
