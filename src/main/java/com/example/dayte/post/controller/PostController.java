package com.example.dayte.post.controller;

import com.example.dayte.members.domain.User;
import com.example.dayte.members.dto.ResponseDTO;
import com.example.dayte.members.service.UserService;
import com.example.dayte.post.domin.Post;
import com.example.dayte.post.dto.PostDTO;
import com.example.dayte.post.service.PostService;
import com.example.dayte.reply.dto.PostReplyDTO;
import com.example.dayte.reply.service.FormatCreateDateService;
import com.example.dayte.reply.service.PostReplyService;
import com.example.dayte.security.dto.UserSecurityDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequiredArgsConstructor
@Log4j2
public class PostController {

    private final PostService postService;

    private final ModelMapper modelMapper;

    private final UserService userService;

    private final FormatCreateDateService formatCreateDateService;

    private final PostReplyService postReplyService;

    // ----------------------- 포스트 등록 폼 -----------------------
    @GetMapping("/mainPostList/in")
    public String insertPost() {
        return "post/insertPost";
    }

    // 포스트 등록 로직
    @PostMapping("/mainPostList/reg") // @Valid
    public  @ResponseBody ResponseDTO<?> insertPost(
                @Valid @RequestBody PostDTO postDTO, // title, content를 PostDTO로 받고 반환함
                @AuthenticationPrincipal UserSecurityDTO principal) {
        User user = userService.getUser(principal.getUserEmail()); // 해당 user의 Email을 담음

        postDTO.setUser(user);
        Post post = modelMapper.map(postDTO, Post.class); //
        postService.insertPost(post);
        postService.extractPostContentImages(post);
        return new ResponseDTO<>(HttpStatus.OK.value(), "새로운 포스트를 등록했습니다.");
    }

    // ----------------------- 포스트 상세 조회 처리 및 화면 응답 -----------------------
    @GetMapping("/post/{id}")
    public String getPost(Model model, @PathVariable Long id) {

        Post post = postService.getPost(id);

        model.addAttribute("post", post);

        List<PostReplyDTO> postReplyList = new ArrayList<>();

        postReplyService.postReplyList(post).forEach(postReply -> {
            PostReplyDTO postReplyDTO = modelMapper.map(postReply, PostReplyDTO.class);
            postReplyDTO.setFormatDate(formatCreateDateService.getFormattedCreateDate(postReplyDTO.getCreateDate()));
            postReplyList.add(postReplyDTO);
        });

        model.addAttribute("postReplyList", postReplyList);
        return "post/getPost";
    }

    // ----------------------- 포스트 검색 및 페이지네이션 -----------------------
    @GetMapping({"/mainPostList"})
    public String getPostList(Model model,
                              @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                              // @PageableDefault = pageable 의 기본 설정
                              // size = 한 페이지당 담길 데이터 양
                              // sort = 무엇을 기준으로 정렬할 것인가
                              // direction = 정렬방향을 정함
                              @RequestParam(required = false, defaultValue = "") String postField,
                              @RequestParam(required = false, defaultValue = "") String postWord
    ) {

        int postTotalPage;
        Page<Post> postListPage;
        // 인풋창에 공백인 상태로 검색버튼을 눌렀을때 postListPage 을 담아 넘겨주고
        // 검색 키워드가 있을시 postSearchList 을 남아 넘겨줌
        String msg = "default";

            switch (postField) {
                case "postTitle" -> postListPage = postService.getPostSearchToTitleList(pageable, postWord);
                case "postContent" -> postListPage = postService.getPostSearchToContentList(pageable, postWord);
                case "postAll" -> postListPage = postService.getPostSearchToAllList(pageable, postWord);
                default -> postListPage = postService.getPostList(pageable);
            }
            postTotalPage = postListPage.getTotalPages();
            msg = "searched";

        // postListPage 필드에 담긴 페이지네이션화된 전체 데이터를 postTotalPage 필드에 대입
        int postNowPage = postListPage.getNumber(); // 현재 게시판 페이지

        int pageSize = 10;
        // 한 페이지에 표시될 게시물 수를 정함

        int postStartPage = Math.max(0, (pageable.getPageNumber() / pageSize) * pageSize);
        // 현재 페이지를 기준으로 페이징된 시작 페이지를 계산

        int postEndPage = Math.min(postStartPage + pageSize - 1, postTotalPage - 1);
        // 현재 페이지를 기준으로 페이징된 끝 페이지를 계산

        model.addAttribute("postStartPage", postStartPage);
        model.addAttribute("postEndPage", postEndPage);
        model.addAttribute("postNowPage", postNowPage);
        model.addAttribute("postList", postListPage);
        model.addAttribute("msg", msg);
        model.addAttribute("postListText",postService.extractPostContentText());
        return "post/mainPostList";
    }

    // ----------------------- 포스트 수정 화면 응답 -----------------------
    @GetMapping("/post/updatePost/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        model.addAttribute("post", postService.getPost(id));
        return "post/updatePost";
    }

    // ----------------------- 포스트 수정 로직 수행 -----------------------
    @PutMapping("/post")
    public @ResponseBody ResponseDTO<?> updatePost(@RequestBody Post post) {
        postService.updatePost(post);
        postService.deletePostImage(post);
        postService.extractPostContentImages(post);
        return new ResponseDTO<>(HttpStatus.OK.value(), post.getId() + "번 포스트가 수정되었습니다.");
    }

    // ----------------------- 포스트 삭제 로직 수행 -----------------------
    @DeleteMapping("/post/{id}")
    public @ResponseBody ResponseDTO<?> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return new ResponseDTO<>(HttpStatus.OK.value(), id + "번 포스트가 삭제되었습니다.");
    }

    // ----------------------- 포스트 이미지 등록 로직 수행 -----------------------
    @PostMapping("/uploadSummernoteImageFile")
    @ResponseBody
    public ResponseEntity<Map<String, String>> uploadSummernoteImageFile(@RequestParam("files") MultipartFile multipartFile) {
        return postService.uploadImage(multipartFile);
    }
}