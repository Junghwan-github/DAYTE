package com.example.dayte.members.controller;

import com.example.dayte.admin.mianslider.domain.VisitorStatistics;
import com.example.dayte.admin.mianslider.dto.VisitorStatisticsDTO;
import com.example.dayte.admin.mianslider.service.VisitorStatisticsService;
import com.example.dayte.members.domain.RoleType;
import com.example.dayte.members.domain.User;
import com.example.dayte.members.dto.ResponseDTO;
import com.example.dayte.members.dto.UserDTO;
import com.example.dayte.members.service.UserService;
import com.example.dayte.schedule.service.ScheduleService;
import com.example.dayte.security.dto.UserSecurityDTO;
import jakarta.validation.Valid;
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;

@Controller
public class UserController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private VisitorStatisticsService visitorStatisticsService;

    @PostMapping("/members/joinForm")
    public @ResponseBody ResponseDTO<?> insertUser(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errMap = new HashMap<>();
            for (FieldError err : bindingResult.getFieldErrors()) {
                errMap.put(err.getField(), err.getDefaultMessage());
            }
            return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), errMap);
        }

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        userDTO.setRole(RoleType.USER);
        System.out.println("userDTO : " + userDTO);
        User user = modelMapper.map(userDTO, User.class);
        System.out.println("user : " + user);

        User findUser = userService.newUser(user.getUserEmail());
        System.out.println("user.getUserEmail() : " + user.getUserEmail());
        if (findUser.getUserEmail() == null && findUser.getNickName() == null) {
            if(user.getNickName().equals(findUser.getNickName())){
                return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "중복된 닉네임 입니다.");
            }else {
                System.out.println("user : " + user);
                userService.insertUser(user);
                return new ResponseDTO<>(HttpStatus.OK.value(), user.getUserName() + "님 회원가입을 축하드립니다. 로그인을 해주세요.");
            }

        } else {
            return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), user.getUserName() + "님은 이미 회원입니다.");
        }
    }

    // 관리자페이지 메인, 검색
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/home")
    public String adminHome(Model model,
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
        } else if (field.equals("userEmail")) {
            ulist = userService.userListByUserEmail(word, pageable);
        } else if (field.equals("nickName")) {
            ulist = userService.userListByNickName(word, pageable);
        } else if (field.equals("role")) {
            ulist = userService.userListByRole(word, pageable);
        } else if (field.equals("phone")) {
            ulist = userService.userListByPhone(word, pageable);
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

        return "adminPage/adminHome";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/editUser/{userEmail}")
    public String userDetail(@PathVariable String userEmail, Model model) {
        model.addAttribute("user", userService.getUser(userEmail));
        return "adminPage/editUser/editUser";
    }
    
    // 관리자 - 회원정보 수정
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/editUser")
    public @ResponseBody ResponseDTO<?> updateUser(@RequestBody UserDTO userDTO) throws IOException {
        userDTO.setRole(userDTO.getRole());
        userDTO.setUserName(userDTO.getUserName());
        userDTO.setPhone(userDTO.getPhone());
        System.out.println("================================회원정보 수정 : " + userDTO);

        if(userService.updateUser(userDTO)) {
            return new ResponseDTO<>(HttpStatus.OK.value(), "회원 정보가 수정되었습니다.");
        } else{
            return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "닉네임이 중복되었습니다.");
        }
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/members/editForm")
    public String modifyUserForm(Model model,
                                 @AuthenticationPrincipal UserSecurityDTO userSecurityDTO) {
        model.addAttribute("userInfo", userService.getUser(userSecurityDTO.getUserEmail()));
        model.addAttribute("mySchedule", scheduleService.myPageTestSchedule(userSecurityDTO.getUserEmail()));
        return "members/myProfile";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/members/editPwdForm")
    public String modifyPasswordForm(Model model,
                                     @AuthenticationPrincipal UserSecurityDTO userSecurityDTO) {
        model.addAttribute("userInfo", userService.getUser(userSecurityDTO.getUserEmail()));
        return "members/updatePwd";

    }

    @PutMapping("/members/editForm")
    public @ResponseBody ResponseDTO<?> modifyUser(
            @ModelAttribute UserDTO userDTO, // 변경된 부분
            @AuthenticationPrincipal UserSecurityDTO principal
    ) {
        if (userDTO.getImage() != null && !userDTO.getImage().isEmpty()){
            userService.profileImage(userDTO);
        }
        User user = userService.modifyUser(principal, userDTO);
        principal.setProfileImagePath(user.getProfileImagePath());
        principal.setNickName(user.getNickName());
        return new ResponseDTO<>(HttpStatus.OK.value(), "회원 정보가 수정되었습니다.");
    }

    // 사용자 - 회원 탈퇴
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/members/delForm")
    public String deletePage() {
        return "members/delForm";
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/members/delForm/{userEmail}")
    public @ResponseBody ResponseDTO<?> deleteUser(@PathVariable String userEmail,
                                                   @RequestBody Map<String, Object> map) {

        String encodedPassword = userService.getUser(userEmail).getPassword();
        String rawPassword = (String) map.get("password");

        if (passwordEncoder.matches(rawPassword, encodedPassword)) {
            userService.testDelUser(userEmail);
            return new ResponseDTO<>(HttpStatus.OK.value(), "회원 탈퇴가 완료되었습니다.");
        } else {
            return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "비밀번호를 다시 확인해주세요.");
        }
    }

    @PostMapping("/members/nickNameCheck/{nickName}")
    public @ResponseBody ResponseDTO<?> nickNameChk(
            @PathVariable String nickName,
            @AuthenticationPrincipal UserSecurityDTO userSecurityDTO
    ) {

        if (!userService.nickNameChk(nickName) || nickName.equals(userSecurityDTO.getNickName())) {
                return new ResponseDTO<>(HttpStatus.OK.value(), "사용 할 수 있는 닉네임 입니다.");
            } else {
                return new ResponseDTO<>(HttpStatus.CONFLICT.value(), "중복된 닉네임 입니다.");
            }
    }

    // 사용자 - 비밀번호 변경
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/members/pwdForm")
    public @ResponseBody ResponseDTO<?> checkPwd(@AuthenticationPrincipal UserSecurityDTO principal,
                                                 @RequestBody Map<String, Object> map){

        String encodedPwd = userService.getUser(principal.getUserEmail()).getPassword();
        String rawPwdCheck = (String) map.get("checkPwd");

        String rawNewPwd = (String)map.get("newPwd");

        // 기존 비밀번호 일치
        if (passwordEncoder.matches(rawPwdCheck, encodedPwd)) {
            userService.checkPassword(principal, rawNewPwd);

            return new ResponseDTO<>(HttpStatus.OK.value(), "비밀번호가 변경되었습니다. 다시 로그인 해주세요.");
        }else{
            //기존 비밀번호 불일치
            return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "현재 비밀번호를 확인해주세요.");
        }
    }

    // 관리자 - 선택 삭제
    @PutMapping("/members/delUsers")
    public @ResponseBody ResponseDTO<?> deleteUsers(@RequestParam(required = false)Map<String[], Object> userList) {
        String[] grpCode = userList.values().toString().split(",");
        System.out.println("================== " + grpCode.length); // 삭제할 회원 수 체크
        int successCount = 0;
        int failCount = 0;
        for(int i=0; i<grpCode.length; i++){
            String userEmail = grpCode[i].replaceAll("[\\[\\] ]","");
            System.out.println(userEmail); // 삭제할 회원 이메일 체크
            if(userService.testDelUser(userEmail) == true){
                successCount++;
            }else{
                failCount++;
            }
        }
        System.out.println("===================== 성공 : " + successCount + " 실패 : " + failCount );
        if(successCount > 0 && failCount == 0) {
            return new ResponseDTO<>(HttpStatus.OK.value(), successCount + " 건 탈퇴가 완료되었습니다.");
        }else if(successCount > 0 && failCount > 0) {
            return new ResponseDTO<>(HttpStatus.OK.value(), successCount + " 건 탈퇴 완료, " + failCount + " 건 탈퇴 실패");
        }else {
            return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), failCount +"건 탈퇴를 실패하였습니다.");
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
}
