package com.example.projectt.reply.controller;


import com.example.projectt.members.domain.User;
import com.example.projectt.reply.domain.ContentReply;
import com.example.projectt.reply.dto.ContentReplyDTO;
import com.example.projectt.reply.dto.ResponseDTO;
import com.example.projectt.reply.service.ContentReplyService;
import com.example.projectt.security.dto.UserSecurityDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ContentReplyController {

    private final ContentReplyService contentReplyService;

    private final ModelMapper modelMapper;

    @PostMapping("/contentReply")
    public @ResponseBody ResponseDTO<?> ContentReplyGet(@RequestBody ContentReplyDTO contentReplyDTO
                                                        ,@AuthenticationPrincipal UserSecurityDTO principal) {
        contentReplyDTO.setUser(modelMapper.map(principal, User.class));
        ContentReply contentReply = modelMapper.map(contentReplyDTO, ContentReply.class);
        contentReplyService.contentReplyinsert(contentReply);
        return new ResponseDTO<>(HttpStatus.OK.value(), "댓글이 등록됐습니다.");
    }

    @GetMapping("/contentReply")
    public String index(Model model) {
        model.addAttribute("contentReplyList", contentReplyService.contentReplyList());
        return "reply/contentReply";
        // replyService.replyList()를 사용하여 댓글 목록을 가져와서 모델에 추가한 후 "ContentReply" view 를 띄움
    }

    //    delete 부분
    @DeleteMapping("/contentReply/{num}")
    public @ResponseBody ResponseDTO<?> contentDeleteReply(@PathVariable int num) {
        contentReplyService.contentReplydelete(num);
        return new ResponseDTO<>(HttpStatus.OK.value(), num + "번 댓글이 삭제됐습니다.");
        // /contentReply/{num} 경로에 대한 DELETE 요청을 처리
        // 해당 번호{num}의 댓글을 삭제한 후에 성공적으로 삭제되었다는 메시지를 포함하는 응답을 반환
    }

    //    update 부분
    @PutMapping("/contentReply/{num}")
    public @ResponseBody ResponseDTO<?> contentUpdateReply(@PathVariable int num, @RequestBody String newContent) {
        contentReplyService.contentReplyUpdate(num, newContent);
        return new ResponseDTO<>(HttpStatus.OK.value(), num + "번 댓글이 업데이트되었습니다.");
        // "/contentReply/{num}" 경로에 대한 PUT 요청을 처리하고,
        // 해당 번호의 댓글을 새 내용으로 업데이트한 후에 성공적으로 업데이트되었다는 메시지를 포함하는 응답을 반환합니다.
    }
}
