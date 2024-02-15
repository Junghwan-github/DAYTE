package com.example.dayte.reply.controller;


import com.example.dayte.members.domain.User;
import com.example.dayte.members.dto.UserDTO;
import com.example.dayte.reply.domain.ContentReply;
import com.example.dayte.reply.dto.ContentReplyDTO;
import com.example.dayte.reply.dto.ResponseDTO;
import com.example.dayte.reply.dto.UpdateContentReplyDTO;
import com.example.dayte.reply.service.ContentReplyService;
import com.example.dayte.security.dto.UserSecurityDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ContentReplyController {

    private final ContentReplyService contentReplyService;

    private final ModelMapper modelMapper;



    @GetMapping("/contentReply")
    public String index(Model model) {
        model.addAttribute("contentReplyList", contentReplyService.contentReplyList());
        return "reply/contentReply";
        // replyService.replyList()를 사용하여 댓글 목록을 가져와서 모델에 추가한 후 "ContentReply" view 를 띄움
    }



    //댓글 등록창 가기전 이 유저가 해당 컨텐츠에 댓글을 썼는지 안 썼는지 체크
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/checkcontentsReview/{uuid}")
    public @ResponseBody ResponseDTO<?> checkcontentsReview(@PathVariable String uuid, @AuthenticationPrincipal UserSecurityDTO principal){

        String userEmail = principal.getUserEmail();

        Boolean writtenUser = contentReplyService.findContentReply(userEmail, uuid);

        if(writtenUser){
            return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "이미 해당 컨텐츠에 댓글을 등록한 유저입니다.");
        } else {
            return new ResponseDTO<>(HttpStatus.OK.value(), "댓글 등록창으로 이동합니다");
        }

    }

    //댓글 등록창 이동
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/contentsReview/{uuid}")
    public String contentsReview(Model model, @PathVariable String uuid){

            model.addAttribute("msg", "insertReply");
            model.addAttribute("uuid", uuid);
            System.out.println("^^^^^^^^^");
            System.out.println(uuid);

        return "reply/contentReply";

    }

    //댓글 수정창으로 이동
    @GetMapping("/modReview/{uuid}")
    public String goToModReviewPage(Model model, @PathVariable String uuid, @AuthenticationPrincipal UserSecurityDTO principal){

        ContentReply contentReply = contentReplyService.findUserContentReply(principal.getUserEmail(), uuid);

        model.addAttribute("msg", "updateReply");
        model.addAttribute("contentReply", contentReply);

        return "reply/contentReply";
    }

    //댓글 수정창에서 수정 로직
    @PutMapping("/modReview")
    public @ResponseBody ResponseDTO<?> updateReview(@RequestBody UpdateContentReplyDTO updateContentReplyDTO, @AuthenticationPrincipal UserSecurityDTO principal){


        String userEmail = principal.getUserEmail();

        contentReplyService.updateReply(userEmail, updateContentReplyDTO);

        return new ResponseDTO<>(HttpStatus.OK.value(), "댓글 수정이 완료되었습니다.");
    }




    @PostMapping("/contentReply")
    public @ResponseBody ResponseDTO<?> ContentReplyGet(@RequestBody ContentReplyDTO contentReplyDTO
                                                        ,@AuthenticationPrincipal UserSecurityDTO principal) {
        contentReplyDTO.setUser(modelMapper.map(principal, User.class));
        ContentReply contentReply = modelMapper.map(contentReplyDTO, ContentReply.class);

        String contentUuid = contentReplyDTO.getUuid();
        contentReplyService.contentReplyinsert(contentReply, contentUuid);

        return new ResponseDTO<>(HttpStatus.OK.value(), "댓글이 등록됐습니다.");
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
