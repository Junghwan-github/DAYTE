package com.example.dayte.content.controller;


import com.example.dayte.admin.contents.domain.AdminContents;
import com.example.dayte.admin.contents.service.AdminContentsService;
import com.example.dayte.content.dto.AvgStarViewDTO;
import com.example.dayte.post.domin.Post;
import com.example.dayte.post.service.PostService;
import com.example.dayte.reply.domain.ContentReply;
import com.example.dayte.reply.service.ContentReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

    // 메인에서 카테고리 목록을 가져오는것
    @GetMapping("/contents/category/{category}")
    public String showContentsList (@PathVariable String category, Model model ) {
        List<AdminContents> contentsCategoryList = adminContentsService.getContentsCategoryList(category);
        model.addAttribute("contentsListCategory", contentsCategoryList);
        return "contents/allcontents";
    }

    // 위의 목록 가져온것에서 검색하는것
    @PostMapping("/contents/category/searchContentsCategory")
    public @ResponseBody Map<String, Object> searchContentsList (@RequestBody Map<String, String> categoryAndSearch) {
        Map<String, Object> result = new HashMap<>();
        List<AdminContents> searchByContents = adminContentsService.findAllBySearch(categoryAndSearch.get("category"),categoryAndSearch.get("search"));
        List<AvgStarViewDTO> avgStarViewDTOList = contentReplyService.avgStarList();
        result.put("searchByContents", searchByContents);
        result.put("avgStarViewDTOList", avgStarViewDTOList);
        System.out.println(result+"가나다");
        return result;
    }


    @GetMapping("/contents/detail/{id}")
    public String showContentsDetail (Model model, @PathVariable String id)  {
            AdminContents contents = adminContentsService.getShowContentsDetail(id);
            List<Post> post = postService.postSearchToAll(contents.getBusinessName());
            List<ContentReply> contentReply = contentReplyService.findContentsReplyList(id);

            model.addAttribute("showContentsDetail", contents)
                    .addAttribute("postList", post)
                    .addAttribute("contentReply", contentReply)
                    .addAttribute("postListText",postService.extractPostContentText())
                    .addAttribute("star", contentReplyService.avgStar(id));
        return "contents/contentsInfo";
    }

    // 스캐줄 검색 기능
    @PostMapping("/schedule/search")
    public @ResponseBody Map<String, Object> searchContents(@RequestBody Map<String, String> search) {
        Map<String, Object> result = new HashMap<>();
        List<AvgStarViewDTO> avgStarViewDTOList = contentReplyService.avgStarList();
        List<AdminContents> searchByContents = adminContentsService.searchByContents(search.get("search"));

        result.put("avgStarViewDTOList", avgStarViewDTOList);
        result.put("searchByContents", searchByContents);
        return result;
    }

    // 인덱스 통합 검색
    @GetMapping("/contents/indexSearch")
    public String allSearches(@RequestParam("indexSearch") String searchWord, Model model) {
        model.addAttribute("contentsList", adminContentsService.searchByContents(searchWord))
                .addAttribute("postList", postService.postSearchToAll(searchWord))
                .addAttribute("postListText",postService.extractPostContentText())
                .addAttribute("starList", contentReplyService.avgStarList());
        return "contents/allSearches";
    }
}
