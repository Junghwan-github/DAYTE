package com.example.dayte.myReview.controller;

import com.example.dayte.members.domain.User;
import com.example.dayte.myReview.service.MyReviewService;
import com.example.dayte.post.domin.Post;
import com.example.dayte.reply.domain.ContentReply;
import com.example.dayte.security.dto.UserSecurityDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MyReviewController {

    private final MyReviewService myReviewService;

    private final ModelMapper modelMapper;

    // 내가 등록한 일정후기
    @GetMapping("/myReview")
    public String myReviewPage(Model model,
                               @AuthenticationPrincipal UserSecurityDTO principal,
                               @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {

        User user = modelMapper.map(principal, User.class);

        Page<Post> myReviewPage = myReviewService.getMyReview(user, pageable);

        model.addAttribute("myReviewPage", myReviewPage);

        int totalPages = myReviewPage.getTotalPages();
        int pageSize = 5;
        int startPage = Math.max(0, (pageable.getPageNumber() / pageSize) * pageSize);
        int endPage = Math.min(startPage + pageSize - 1, totalPages - 1);

        model.addAttribute("startPage", startPage);
        if (endPage >= 0) {
            model.addAttribute("endPage", endPage);
        }

        return "myReview/myReview";
    }

    //내가 등록한 별점리뷰
    @GetMapping("/myRating")
    public String myRatingPage(
            Model model,
            @PageableDefault(size = 10, sort = "user", direction = Sort.Direction.DESC) Pageable pageable,
            @AuthenticationPrincipal UserSecurityDTO principal
    ) {
        User user = modelMapper.map(principal, User.class);
        model.addAttribute("myReview", myReviewService.getMyContentsReview(user, pageable));


        Page<ContentReply> myReviewPage = myReviewService.getMyContentsReview(user, pageable);

        model.addAttribute("myReviewPage", myReviewPage);

        int totalPages = myReviewPage.getTotalPages();
        int pageSize = 5;
        int startPage = Math.max(0, (pageable.getPageNumber() / pageSize) * pageSize);
        int endPage = Math.min(startPage + pageSize - 1, totalPages - 1);

        model.addAttribute("startPage", startPage);
        if (endPage >= 0) {
            model.addAttribute("endPage", endPage);
        }

        return "myReview/myRating";
    }

}
