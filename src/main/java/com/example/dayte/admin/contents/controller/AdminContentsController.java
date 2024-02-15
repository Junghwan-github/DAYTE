package com.example.dayte.admin.contents.controller;

import com.example.dayte.admin.contents.domain.AdminContents;
import com.example.dayte.admin.contents.dto.AdminContentsDTO;
import com.example.dayte.admin.contents.dto.AdminContentsImageDTO;
import com.example.dayte.admin.contents.service.AdminContentsService;
import com.example.dayte.admin.mianslider.dto.VisitorStatisticsDTO;
import com.example.dayte.admin.mianslider.listener.MySessionListener;
import com.example.dayte.admin.mianslider.service.VisitorStatisticsService;
import com.example.dayte.members.domain.User;
import com.example.dayte.members.dto.UserDTO;
import com.example.dayte.members.service.UserService;
import com.example.dayte.post.domin.Post;
import com.example.dayte.post.service.PostService;
import com.example.dayte.reply.dto.ResponseDTO;
import com.example.dayte.security.dto.UserSecurityDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
@Log4j2
@RequiredArgsConstructor
@Controller
public class AdminContentsController {

    private final AdminContentsService adminContentsService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private final PostService postService;

    @Autowired
    private VisitorStatisticsService visitorStatisticsService;

    @Autowired
    private MySessionListener mySessionListener;

    // 관리자페이지 메인
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/home")
    public String adminHome(Model model){
        int recentUserCount = 8;
        int recentPostCount = 5;

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
        Page<User> dList = userService.delList(pageable); // 탈퇴 회원수

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
    public @ResponseBody com.example.dayte.members.dto.ResponseDTO<?> updateUser(@ModelAttribute UserDTO userDTO,
                                                                                 @AuthenticationPrincipal UserSecurityDTO principal) throws IOException {
        userDTO.setRole(userDTO.getRole());
        userDTO.setUserName(userDTO.getUserName());
        userDTO.setPhone(userDTO.getPhone());

        System.out.println("================================회원정보 수정 : " + userDTO);
        if (userDTO.getImage() != null) {
            userService.profileImage(userDTO);
        }
        if(userService.updateUser(userDTO)) {
            log.info("관리자 사용자 정보 수정 - 사용자 ID : {}", userDTO.getUserEmail());
            principal.setProfileImagePath(userDTO.getProfileImagePath());
            return new com.example.dayte.members.dto.ResponseDTO<>(HttpStatus.OK.value(), "회원 정보가 수정되었습니다.");
        } else{
            log.info("사용자 정보 수정 실패 - 중복된 닉네임. 사용자 ID : {}",userDTO.getUserEmail());
            return new com.example.dayte.members.dto.ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "닉네임이 중복되었습니다.");

        }
    }

    // 관리자 - 선택 삭제
    @PutMapping("/members/delUsers")
    public @ResponseBody com.example.dayte.members.dto.ResponseDTO<?> deleteUsers(@RequestParam(required = false)Map<String[], Object> userList) {
        String[] grpCode = userList.values().toString().split(",");
        int successCount = 0;
        int failCount = 0;
        for(int i=0; i<grpCode.length; i++){
            String userEmail = grpCode[i].replaceAll("[\\[\\] ]","");
            if(userService.testDelUser(userEmail) == true){
                log.info("사용자 탈퇴 - 사용자 ID : {}",userEmail);
                successCount++;
            }else{
                log.info("사용자 탈퇴 실패 - 사용자 ID : {}",userEmail);
                failCount++;
            }
        }
        log.info("사용자 탈퇴 - 성공 : {}, 실패 : {}", successCount, failCount);
        if(successCount > 0 && failCount == 0) {
            return new com.example.dayte.members.dto.ResponseDTO<>(HttpStatus.OK.value(), successCount + " 건 탈퇴가 완료되었습니다.");
        }else if(successCount > 0 && failCount > 0) {
            return new com.example.dayte.members.dto.ResponseDTO<>(HttpStatus.OK.value(), successCount + " 건 탈퇴 완료, " + failCount + " 건 탈퇴 실패");
        }else {
            return new com.example.dayte.members.dto.ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), failCount +"건 탈퇴를 실패하였습니다.");
        }
    }

    @PostMapping("/admin/visitors")
    public @ResponseBody List<VisitorStatisticsDTO> view (@RequestBody Map<String, String> value) {
        LocalDate date = LocalDate.now();
        boolean flag = false;
        switch(value.get("num")){
            case "1" -> {
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
        return visitorStatisticsService.getVisitorsCountList(date, flag);
    }


    @GetMapping("/admin/home/settings/contents")
    public String IndexContentsSliderView () {
        return "adminPage/settings/scheduleContentsList";
    }

    // 컨텐츠 등록 로직
    @PostMapping("/admin/home/registration/contents")
    public @ResponseBody ResponseDTO<?> insertIndexMainSlider (
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

    // 검색 기능
    @PostMapping("/search")
    public @ResponseBody List<AdminContents> searchContents(@RequestBody Map<String, String> search) {
        List<AdminContents> searchByContents = adminContentsService.searchByContents(search.get("search"));
        return searchByContents;
    }

    @GetMapping("/admin/view")
    public String view() {
        return "adminPage/index";
    }

    @GetMapping("/admin/loginUser")
    public String loginUser(Model model) {
        model.addAttribute("userList", mySessionListener.getActiveUsers());
        System.out.println("하와와" + mySessionListener.getActiveUsers().toString());
        return "adminPage/loginUser";
    }
}
