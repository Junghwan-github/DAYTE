package com.example.projectt.post.postController;

import com.example.projectt.members.domain.User;
import com.example.projectt.members.dto.ResponseDTO;
import com.example.projectt.members.service.UserService;
import com.example.projectt.post.domin.Post;
import com.example.projectt.post.postDto.PostDTO;
import com.example.projectt.post.postService.PostService;
import com.example.projectt.reply.dto.PostReplyDTO;
import com.example.projectt.reply.service.FormatCreateDateService;
import com.example.projectt.reply.service.PostReplyService;
import com.example.projectt.security.dto.UserSecurityDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    private final ModelMapper modelMapper;

    private final UserService userService;

    private final FormatCreateDateService formatCreateDateService;

    private final PostReplyService postReplyService;

    @GetMapping({"/mainPostList"})
    public String getPostList(Model model,
                              @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC)Pageable pageable) {
        model.addAttribute("postList", postService.getPostList(pageable));
        return "post/mainPostList";
    }

    //     포스트 등록 폼
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
        System.out.println("^^^^^^^^^^^^");
        System.out.println("post : " + post);
        postService.insertPost(post);

        return new ResponseDTO<>(HttpStatus.OK.value(), "새로운 포스트를 등록했습니다.");
    }

    // @PreAuthorize("hasRole('ADMIN')")
    //@PreAuthorize("isAuthenticated()") // 인증된(로그인된) 사용자만 접근 가능
    // 포스트 상세 조회 처리 및 화면 응답
    @GetMapping("/post/{id}")
    public String getPost(Model model, @PathVariable int id) {

        model.addAttribute("post", postService.getPost(id));

        List<PostReplyDTO> postReplyList = new ArrayList<>();

        postReplyService.postReplyList().forEach(postReply -> {
            PostReplyDTO postReplyDTO = modelMapper.map(postReply, PostReplyDTO.class);
            postReplyDTO.setFormatDate(formatCreateDateService.getFormattedCreateDate(postReplyDTO.getCreateDate()));
            postReplyList.add(postReplyDTO);
            System.out.println("postReplyDTO : " + postReplyDTO);
        });

        model.addAttribute("postReplyList", postReplyList);

        return "post/getPost";
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

}