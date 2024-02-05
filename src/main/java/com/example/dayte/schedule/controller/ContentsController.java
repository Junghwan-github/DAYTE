package com.example.dayte.schedule.controller;

import com.example.dayte.admin.contents.domain.AdminContents;
import com.example.dayte.admin.contents.service.AdminContentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class ContentsController {

    @Autowired
    AdminContentsService contentsService;

    // 검색 기능
    @PostMapping("/search")
    public @ResponseBody List<AdminContents> searchContents(@RequestBody Map<String, String> search) {
        List<AdminContents> searchByContents = contentsService.searchByContents(search.get("search"));
        return searchByContents;
    }

}
