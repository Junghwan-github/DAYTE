package com.example.dayte.content.controller;


import com.example.dayte.admin.contents.domain.AdminContents;
import com.example.dayte.admin.contents.service.AdminContentsService;
import com.example.dayte.post.domin.Post;
import com.example.dayte.post.service.PostService;
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

    @Autowired
    private PostService postService;

    @GetMapping("/contents/category/{category}")
    public String showContentsList (@PathVariable String category, Model model ) {
        List<AdminContents> contentsCategoryList = adminContentsService.getContentsCategoryList(category);
        model.addAttribute("contentsListCategory", contentsCategoryList);
        return "contents/allcontents";
    }


    @GetMapping("/contents/detail/{id}")
    public String showContentsDetail (Model model, @PathVariable String id)  {
            AdminContents contents = adminContentsService.getShowContentsDetail(id);
            List<Post> post = postService.postSearchToAll(contents.getBusinessName());
            model.addAttribute("showContentsDetail", contents);
        return "contents/contentsInfo";
    }

}
