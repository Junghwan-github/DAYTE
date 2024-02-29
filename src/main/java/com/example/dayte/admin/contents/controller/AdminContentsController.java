package com.example.dayte.admin.contents.controller;

import com.example.dayte.admin.contents.domain.AdminContents;
import com.example.dayte.admin.contents.dto.AdminContentsDTO;
import com.example.dayte.admin.contents.dto.AdminContentsImageDTO;
import com.example.dayte.admin.contents.dto.PageableDTO;
import com.example.dayte.admin.contents.service.AdminContentsService;
import com.example.dayte.admin.mianslider.dto.VisitorStatisticsDTO;
import com.example.dayte.admin.mianslider.listener.MySessionListener;
import com.example.dayte.admin.mianslider.service.VisitorStatisticsService;
import com.example.dayte.members.domain.RoleType;
import com.example.dayte.members.domain.User;
import com.example.dayte.members.dto.ResponseDTO;
import com.example.dayte.members.dto.UserDTO;
import com.example.dayte.members.service.UserService;
import com.example.dayte.post.domin.Post;
import com.example.dayte.post.service.PostService;
import com.example.dayte.security.dto.UserSecurityDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Log4j2
@RequiredArgsConstructor
@Controller
public class AdminContentsController {

    private final AdminContentsService adminContentsService;

    private final UserService userService;

    private final PostService postService;

    private final VisitorStatisticsService visitorStatisticsService;

    private final MySessionListener mySessionListener;

    // 관리자페이지 메인
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/home")
    public String adminHome(Model model) {
        int recentUserCount = 10;
        int recentPostCount = 10;

        List<User> recentUsers = userService.getRecentUsers(recentUserCount);
        List<Post> recentPosts = postService.getRecentPosts(recentPostCount);
        model.addAttribute("recentUsers", recentUsers);
        model.addAttribute("recentPosts", recentPosts);
        return "adminPage/adminHome";
    }

    // 관리자 검색
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/user")
    public String adminUser(Model model,
                            @PageableDefault(size = 10, sort = "joinDate", direction = Sort.Direction.DESC) Pageable pageable,
                            @RequestParam(required = false, defaultValue = "") String field,
                            @RequestParam(required = false, defaultValue = "") String word
    ) {
        Page<User> allList = userService.userList(pageable); // 총 회원수
        Page<User> delList = userService.delList(pageable); // 탈퇴 회원수
        Page<User> bList = userService.userListByRole("BLOCK", pageable); // 블락 회원수
        Page<User> dList = userService.userListByRole("DORMANCY", pageable); // 휴면 계정 수
        // 사용자 상세 검색
        Page<User> ulist = userService.userList(pageable);
        if (field.equals("userName")) {
            ulist = userService.userListByUserName(word, pageable);
            log.info("사용자 정보 조회 - 사용자 이름 : {}", word);
        } else if (field.equals("userEmail")) {
            ulist = userService.userListByUserEmail(word, pageable);
            log.info("사용자 정보 조회 - 사용자 ID : {}", word);
        } else if (field.equals("nickName")) {
            ulist = userService.userListByNickName(word, pageable);
            log.info("사용자 정보 조회 - 사용자 닉네임 : {}", word);
        } else if (field.equals("role")) {
            ulist = userService.userListByRole(word, pageable);
            log.info("사용자 정보 조회 - 사용자 권한 : {}", word);
        } else if (field.equals("phone")) {
            ulist = userService.userListByPhone(word, pageable);
            log.info("사용자 정보 조회 - 사용자 연락처 : {}", word);
        }

        int pageNumber = ulist.getPageable().getPageNumber(); // 현재 페이지
        int totalPages = ulist.getTotalPages();
        int pageBlock = 5; // 페이지 블럭 수
        int startBlockPage = ((pageNumber) / pageBlock) * pageBlock + 1;

        int endBlockPage = startBlockPage + pageBlock - 1;
        endBlockPage = totalPages < endBlockPage ? totalPages : endBlockPage;

        model.addAttribute("startBlockPage", startBlockPage);
        model.addAttribute("endBlockPage", endBlockPage);
        model.addAttribute("ulist", ulist);
        model.addAttribute("allList", allList);
        model.addAttribute("delList", delList);
        model.addAttribute("bList", bList);
        model.addAttribute("dList", dList);

        return "adminPage/adminUsers";
    }

    // 관리자 회원 상세보기
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/editUser/{userEmail}")
    public String userDetail(@PathVariable String userEmail, Model model) {
        log.info("관리자 사용자 상세정보 조회 - 사용자 ID : {}", userEmail);
        model.addAttribute("user", userService.getUser(userEmail));
        return "adminPage/editUser/editUser";
    }

    // 관리자 - 회원정보 수정
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/editUser")
    public @ResponseBody ResponseDTO<?> updateUser(@ModelAttribute UserDTO userDTO,
                                                   @AuthenticationPrincipal UserSecurityDTO principal) throws IOException {
        userDTO.setRole(userDTO.getRole());
        userDTO.setUserName(userDTO.getUserName());
        userDTO.setPhone(userDTO.getPhone());

        LocalDateTime now = LocalDateTime.now().withNano(0);
        LocalDateTime blackDate =
                switch (userDTO.getBlack()) {
                    case "1일" -> now.plusDays(1);
                    case "2일" -> now.plusDays(2);
                    case "1주" -> now.plusWeeks(1);
                    case "2주" -> now.plusWeeks(2);
                    case "1달" -> now.plusMonths(1);
                    case "2달" -> now.plusMonths(2);
                    case "6개월" -> now.plusMonths(6);
                    case "1년" -> now.plusYears(1);
                    case "영구" -> LocalDateTime.MAX;
                    default -> null;
                };
        if (blackDate != null) {
            userDTO.setRole(RoleType.BLOCK);
            userDTO.setBlockDate(Timestamp.valueOf(blackDate));
        }

        if (userDTO.getImage() != null) {
            userService.profileImage(userDTO);
        }
        if (userService.updateUser(userDTO)) {
            log.info("관리자 사용자 정보 수정 - 사용자 ID : {}", userDTO.getUserEmail());
            return new ResponseDTO<>(HttpStatus.OK.value(), "회원 정보가 수정되었습니다.");
        } else {
            log.info("사용자 정보 수정 실패 - 중복된 닉네임. 사용자 ID : {}", userDTO.getUserEmail());
            return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "닉네임이 중복되었습니다.");
        }
    }

    // 관리자 - 선택 삭제
    @PutMapping("/members/delUsers")
    public @ResponseBody ResponseDTO<?> deleteUsers(@RequestParam(required = false) Map<String[], Object> userList) {
        String[] grpCode = userList.values().toString().split(",");
        int successCount = 0;
        int failCount = 0;
        for (int i = 0; i < grpCode.length; i++) {
            String userEmail = grpCode[i].replaceAll("[\\[\\] ]", "");
            if (userService.testDelUser(userEmail) == true) {
                log.info("사용자 탈퇴 - 사용자 ID : {}", userEmail);
                successCount++;
            } else {
                log.info("사용자 탈퇴 실패 - 사용자 ID : {}", userEmail);
                failCount++;
            }
        }
        log.info("사용자 탈퇴 - 성공 : {}, 실패 : {}", successCount, failCount);
        if (successCount > 0 && failCount == 0) {
            return new ResponseDTO<>(HttpStatus.OK.value(), successCount + " 건 탈퇴가 완료되었습니다.");
        } else if (successCount > 0 && failCount > 0) {
            return new ResponseDTO<>(HttpStatus.OK.value(), successCount + " 건 탈퇴 완료, " + failCount + " 건 탈퇴 실패");
        } else {
            return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), failCount + "건 탈퇴를 실패하였습니다.");
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/visitors")
    public @ResponseBody List<VisitorStatisticsDTO> view(@RequestBody Map<String, String> value) {
        LocalDate date = LocalDate.now();
        boolean flag = false;
        switch (value.get("num")) {
            case "1", "5" -> {
                date = date.minusWeeks(1);
                flag = true;
            }
            case "2" -> {
                date = date.minusMonths(1);
                flag = true;
            }
            case "3" -> {
                date = date.minusMonths(5);
                flag = false;
            }
            case "4" -> {
                date = date.minusMonths(11);
                flag = false;
            }
        }
        List<VisitorStatisticsDTO> VisitorStatisticsDTO = visitorStatisticsService.getVisitorsCountList(date, flag);
        VisitorStatisticsDTO.get(0).setNum(value.get("num"));
        return VisitorStatisticsDTO;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/home/settings/contents/add")
    public String IndexContentsSliderView() {
        return "adminPage/settings/scheduleContentsList";
    }

    // 컨텐츠 등록 로직
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/home/registration/contents")
    public @ResponseBody ResponseDTO<?> insertIndexMainSlider(
            @ModelAttribute AdminContentsDTO adminContentsDTO
    ) {

        adminContentsService.insertAdminContents(adminContentsDTO);
        adminContentsService.insertAdminContentsImage(new AdminContentsImageDTO(adminContentsDTO.getImageFiles()));
        return new ResponseDTO<>(HttpStatus.OK.value(), "정상처리 되었습니다.");
    }

    @DeleteMapping("/admin/home/registration/contents/{uuid}")
    public @ResponseBody ResponseDTO<?> removeContents(@PathVariable String uuid) {
        adminContentsService.removeContent(uuid);

        return new ResponseDTO<>(HttpStatus.OK.value(), "해당 컨텐츠가 삭제되었습니다.");
    }

    @GetMapping("/admin/totalVisitor")
    public String view() {
        return "adminPage/adminTotalVisitor";
    }


    @GetMapping("/admin/loginUser")
    public String loginUser(Model model) {
        model.addAttribute("userList", mySessionListener.getActiveUsers());
        return "adminPage/loginUser";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/post")
    public String adminPost(Model model,
                            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                            @RequestParam(required = false, defaultValue = "") String postField,
                            @RequestParam(required = false, defaultValue = "") String postWord) {
        int postTotalPage;
        Page<Post> postListPage;
        // 인풋창에 공백인 상태로 검색버튼을 눌렀을때 postListPage 을 담아 넘겨주고
        // 검색 키워드가 있을시 postSearchList 을 남아 넘겨줌

        postListPage = switch (postField) {
            case "postTitle" -> postService.getPostSearchToTitleList(pageable, postWord);
            case "postContent" -> postService.getPostSearchToContentList(pageable, postWord);
            case "postAll" -> postService.getPostSearchToAllList(pageable, postWord);
            default -> postService.getPostList(pageable);
        };
        postTotalPage = postListPage.getTotalPages();
        // postListPage 필드에 담긴 페이지네이션화된 전체 데이터를 postTotalPage 필드에 대입
        int postNowPage = postListPage.getNumber(); // 현재 게시판 페이지

        int pageSize = 5;
        // 한 페이지에 표시될 게시물 수를 정함

        int postStartPage = ((postNowPage) / pageSize) * pageSize + 1;
        // 현재 페이지를 기준으로 페이징된 시작 페이지를 계산

        int postEndPage = postStartPage + pageSize - 1;
        int totalPages = postListPage.getTotalPages();
        postEndPage = totalPages < postEndPage ? totalPages : postEndPage;
        // 현재 페이지를 기준으로 페이징된 끝 페이지를 계산

        model.addAttribute("postStartPage", postStartPage);
        model.addAttribute("postEndPage", postEndPage);
        model.addAttribute("postNowPage", postNowPage);
        model.addAttribute("postList", postListPage);

        model.addAttribute("postListText", postService.extractPostContentText());
        return "adminPage/postManagement";
    }

    // 관리자 - 게시글 선택 삭제
    @DeleteMapping("/admin/delPosts")
    public @ResponseBody ResponseDTO<?> deletePosts(@RequestParam(required = false) Map<String[], Object> postList) {
        String[] grpCode = postList.values().toString().split(",");
        int i = 0;
        for (i = 0; i < grpCode.length; i++) {
            Long postId = Long.parseLong(grpCode[i].replaceAll("[\\[\\] ]", ""));
            postService.deletePost(postId);
        }
        return new ResponseDTO<>(HttpStatus.OK.value(), i + "건 삭제 하셨습니다.");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/home/settings/contents")
    public String removeContents(Model model,
                                 @PageableDefault(size = 10, sort = "businessName", direction = Sort.Direction.ASC)
                                 Pageable pageable,
                                 @RequestParam(required = false, defaultValue = "") String field,
                                 @RequestParam(required = false, defaultValue = "") String word) {

        Page<AdminContents> contentsPage =
                adminContentsService.getPageableContentsList(field, word, pageable);

        int contentsNowPage = contentsPage.getNumber();

        int contentsStartPage = (contentsNowPage / 10) * 10 + 1;

        int contentsEndPage = contentsStartPage + 10 - 1;
        int totalPages = contentsPage.getTotalPages();

        contentsEndPage = totalPages < contentsEndPage ? totalPages : contentsEndPage;

        PageableDTO pageableDTO = new PageableDTO();
        pageableDTO.setContentsStartPage(contentsStartPage);
        pageableDTO.setContentsEndPage(contentsEndPage);
        pageableDTO.setPostNowPage(contentsNowPage);
        pageableDTO.setField(field);
        pageableDTO.setWord(word);

        model
                .addAttribute("arrangedList",
                        adminContentsService.getArrangedList()) // 콘텐츠 수 표기
                .addAttribute("contentsList", contentsPage) // 콘텐츠 리스트들
                .addAttribute("contentsDTO", pageableDTO) // 페이지네이션에 필요한 값들
                .addAttribute("scheduleNumberList",
                        adminContentsService.saveScheduleNumber(contentsPage.getContent())) // 삭제된 일정이 등록된 수
        ;

        return "adminPage/settings/mainAdminContents";
    }

}
