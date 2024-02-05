package com.example.dayte.post.postController;

import com.example.dayte.members.domain.User;
import com.example.dayte.members.dto.ResponseDTO;
import com.example.dayte.members.service.UserService;
import com.example.dayte.post.domin.Post;
import com.example.dayte.post.postDto.PostDTO;
import com.example.dayte.post.postService.PostService;
import com.example.dayte.reply.dto.PostReplyDTO;
import com.example.dayte.reply.service.FormatCreateDateService;
import com.example.dayte.reply.service.PostReplyService;
import com.example.dayte.security.dto.UserSecurityDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
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
import java.io.InputStream;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    private final ModelMapper modelMapper;

    private final UserService userService;

    private final FormatCreateDateService formatCreateDateService;

    private final PostReplyService postReplyService;


    // 포스트 등록 폼
    @GetMapping("/mainPostList/in")
    public String insertPost() {
        return "post/insertPost";
    }

    // 포스트 등록 로직
    @PostMapping("/mainPostList/reg") // @Valid
    public @ResponseBody ResponseDTO<?> insertPost(@Valid @RequestBody PostDTO postDTO, // title, content를 PostDTO로 받고 반환함
//                                                 BindingResult bindingResult,
                                                   @AuthenticationPrincipal UserSecurityDTO principal) {
        Post post = modelMapper.map(postDTO, Post.class); //


        User user = userService.getUser(principal.getUsername()); // 해당 user의 Email을 담음
        post.setUser(user);
        postService.insertPost(post);

        return new ResponseDTO<>(HttpStatus.OK.value(), "새로운 포스트를 등록했습니다.");
    }

    // 포스트 상세 조회 처리 및 화면 응답
    @GetMapping("/post/{id}")
    public String getPost(Model model, @PathVariable int id) {

        Post post = postService.getPost(id);

        model.addAttribute("post", post);

        List<PostReplyDTO> postReplyList = new ArrayList<>();

        postReplyService.postReplyList(post).forEach(postReply -> {
            PostReplyDTO postReplyDTO = modelMapper.map(postReply, PostReplyDTO.class);
            postReplyDTO.setFormatDate(formatCreateDateService.getFormattedCreateDate(postReplyDTO.getCreateDate()));
            postReplyList.add(postReplyDTO);
            System.out.println("postReplyDTO : " + postReplyDTO);
        });

        model.addAttribute("postReplyList", postReplyList);

        return "post/getPost";
    }

    // 포스트 리스트 페이지네이션
    @GetMapping({"/mainPostList"})
    public String getPostList(Model model, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        // 보이는 페이지에 게시글을 10개씩 보이게 하고 'id' 를 기준으로 정렬 설정
        model.addAttribute("postList", postService.getPostList(pageable));
        //

        Page<Post> postListPage = postService.getPostList(pageable);
        int postTotalPage = postListPage.getTotalPages();

        int nowPage = postListPage.getNumber();

        int pageSize = 5;

        int startPage = Math.max(0, (pageable.getPageNumber() / pageSize) * pageSize);
        int endPage = Math.min(startPage + pageSize - 1, postTotalPage - 1);

        model.addAttribute("postStartPage", startPage);
        model.addAttribute("postEndPage", endPage);
        model.addAttribute("postNowPage", nowPage);
        model.addAttribute("postList", postService.getPostList(pageable));


        return "post/mainPostList";
    }

    // 포스트 수정 화면 응답
    @GetMapping("/post/updatePost/{id}")
    public String updateForm(@PathVariable int id, Model model) {
        model.addAttribute("post", postService.getPost(id));
        return "post/updatePost";
    }

    // 포스트 수정 로직 수행
//    @PreAuthorize("principal.username==#post.user.username")
    @PutMapping("/post")
    public @ResponseBody ResponseDTO<?> updatePost(@RequestBody Post post) {
        postService.updatePost(post);
        return new ResponseDTO<>(HttpStatus.OK.value(), post.getId() + "번 포스트가 수정되었습니다.");
    }

    // 포스트 삭제 로직 수행
    @DeleteMapping("/post/{id}")
    public @ResponseBody ResponseDTO<?> deletePost(@PathVariable int id) {
        postService.deletePost(id);

        return new ResponseDTO<>(HttpStatus.OK.value(), id + "번 포스트가 삭제되었습니다.");
    }

    // 포스트 이미지 등록 로직 수행
    @PostMapping("/uploadSummernoteImageFile")
    @ResponseBody
    public ResponseEntity<Map<String, String>> uploadImage(@RequestParam("files") MultipartFile multipartFile) {
        return postService.uploadImage(multipartFile);
    }
}