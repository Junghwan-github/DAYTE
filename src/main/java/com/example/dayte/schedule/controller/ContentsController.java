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

import java.util.List;

@Controller
public class ContentsController {

    @Autowired
    ContentsService contentsService;

    @PostMapping("/search")
    public ResponseEntity<List<Contents>> searchContents(@RequestBody String searchInput) {
        int startIndex = searchInput.indexOf("\"searchInput\":\"") + 15;
        int endIndex = searchInput.indexOf("\"", startIndex);
        String businessName = searchInput.substring(startIndex, endIndex);

        List<Contents> searchByContents = contentsService.searchByContents(businessName);
        return new ResponseEntity<>(searchByContents, HttpStatus.OK);
    }

}
