package com.example.dayte.reply.controller;

import com.example.dayte.members.domain.User;
import com.example.dayte.members.dto.ResponseDTO;
import com.example.dayte.post.domin.Post;
import com.example.dayte.post.service.PostService;
import com.example.dayte.reply.domain.PostReply;
import com.example.dayte.reply.dto.PostReplyDTO;
import com.example.dayte.reply.service.PostReplyService;
import com.example.dayte.security.dto.UserSecurityDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@Log4j2
public class PostReplyController {

    private final PostService postService;

    private final PostReplyService postReplyService;

    private final ModelMapper modelMapper;

    @PostMapping("/postReply")
    public @ResponseBody ResponseDTO<?> PostReplyGet(@RequestBody PostReplyDTO postReplyDTO,
                                                     @AuthenticationPrincipal UserSecurityDTO principal) {
        postReplyDTO.setUser(modelMapper.map(principal, User.class));

        Long id = postReplyDTO.getId();
        Post post = postService.getPost(id);
        postReplyDTO.setPost(post);
        PostReply postReply = modelMapper.map(postReplyDTO, PostReply.class);
        postReplyService.postReplyInsert(postReply);
        return new ResponseDTO<>(HttpStatus.OK.value(), "댓글이 등록됐습니다.");
    }

    // delete 부분
    @DeleteMapping("/postReply/{num}")
    public @ResponseBody ResponseDTO<?> postDeleteReply(@PathVariable int num) {
        postReplyService.postReplyDelete(num);
        return new ResponseDTO<>(HttpStatus.OK.value(), "댓글이 삭제됐습니다.");
        // /contentReply/{num} 경로에 대한 DELETE 요청을 처리
        // 해당 번호{num}의 댓글을 삭제한 후에 성공적으로 삭제되었다는 메시지를 포함하는 응답을 반환
    }

    // update 부분
    @PutMapping("/postReply/{num}")
    public @ResponseBody ResponseDTO<?> postUpdateReply(@PathVariable int num, @RequestBody Map<String, String> newContent) {
        postReplyService.postReplyUpdate(num, newContent.get("content"));
        return new ResponseDTO<>(HttpStatus.OK.value(), "댓글이 수정되었습니다.");
        // "/contentReply/{num}" 경로에 대한 PUT 요청을 처리하고,
        // 해당 번호의 댓글을 새 내용으로 업데이트한 후에 성공적으로 업데이트되었다는 메시지를 포함하는 응답을 반환합니다.
    }

}
