package com.example.dayte.content.controller;


import com.example.dayte.admin.contents.domain.AdminContents;
import com.example.dayte.admin.contents.service.AdminContentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class ContentController {

    @Autowired
    private AdminContentsService adminContentsService;



    @GetMapping("/contents/category/{category}")
    public String showContentsList (@PathVariable String category, Model model ) {
        List<AdminContents> contentsCategoryList = adminContentsService.getContentsCategoryList(category);
        model.addAttribute("contentsListCategory", contentsCategoryList);
        return "contents/allcontents";
    }


    @GetMapping("/contents/detail/{id}")
    public String showContentsDetail (Model model, @PathVariable String id)  {
            AdminContents contents = adminContentsService.getShowContentsDetail(id);
            model.addAttribute("showContentsDetail", contents);
        return "contents/contentsInfo";
    }

}
