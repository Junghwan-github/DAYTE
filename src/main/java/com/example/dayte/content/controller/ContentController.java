package com.example.dayte.content.controller;


import com.example.dayte.admin.contents.domain.AdminContents;
import com.example.dayte.admin.contents.service.AdminContentsService;
import com.example.dayte.post.domin.Post;
import com.example.dayte.post.service.PostService;
import com.example.dayte.reply.domain.ContentReply;
import com.example.dayte.reply.service.ContentReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Controller
public class ContentController {

    @Autowired
    private AdminContentsService adminContentsService;

    @Autowired
    private PostService postService;

    @Autowired
    private ContentReplyService contentReplyService;

    @GetMapping("/contents/category/{category}")
    public String showContentsList (@PathVariable String category, Model model ) {
        List<AdminContents> contentsCategoryList = adminContentsService.getContentsCategoryList(category);
        model.addAttribute("contentsListCategory", contentsCategoryList);
        return "contents/allcontents";
    }

    @PostMapping("/searchContentsCategory")
    public @ResponseBody List<AdminContents> searchContentsList (@RequestBody Map<String, String> categoryAndSearch) {
        List<AdminContents> searchByContents = adminContentsService.searchByCategory(categoryAndSearch.get("category"),categoryAndSearch.get("search"));
        return searchByContents;
    }


    @GetMapping("/contents/detail/{id}")
    public String showContentsDetail (Model model, @PathVariable String id)  {
            AdminContents contents = adminContentsService.getShowContentsDetail(id);
            List<Post> post = postService.postSearchToAll(contents.getBusinessName());
            List<ContentReply> contentReply = contentReplyService.findContentsReplyList(id);
            model.addAttribute("showContentsDetail", contents)
                    .addAttribute("postList", post)
                    .addAttribute("contentReply", contentReply);
        model.addAttribute("postListText",postService.extractPostContentText());
        return "contents/contentsInfo";
    }

}
