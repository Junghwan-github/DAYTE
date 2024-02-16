package com.example.dayte.members.controller;

import com.example.dayte.admin.mianslider.dto.VisitorStatisticsDTO;
import com.example.dayte.admin.mianslider.service.VisitorStatisticsService;
import com.example.dayte.members.domain.RoleType;
import com.example.dayte.members.domain.User;
import com.example.dayte.members.dto.ResponseDTO;
import com.example.dayte.members.dto.SocialResponseDTO;
import com.example.dayte.members.dto.UserDTO;
import com.example.dayte.members.service.UserService;
import com.example.dayte.schedule.service.ScheduleService;
import com.example.dayte.security.dto.UserSecurityDTO;
import jakarta.validation.Valid;
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
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
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

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

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
            if (user.getNickName().equals(findUser.getNickName())) {
                return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "중복된 닉네임 입니다.");
            } else {
                System.out.println("user : " + user);
                userService.insertUser(user);
                return new ResponseDTO<>(HttpStatus.OK.value(), user.getUserName() + "님 회원가입을 축하드립니다. 로그인을 해주세요.");
            }

        } else {
            return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), user.getUserName() + "님은 이미 회원입니다.");
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
    @GetMapping("/members/pwdForm")
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
        if (userDTO.getImage() != null && !userDTO.getImage().isEmpty()) {
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

    // 소셜 회원의 회원탈퇴
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/members/delForm/")
    public @ResponseBody SocialResponseDTO<?> deleteSocialUser(
            @AuthenticationPrincipal UserSecurityDTO principal) {

        userService.testDelUser(principal.getUserEmail());

        System.out.println("principal.getSocialName() : " + principal.getSocialName());
        System.out.println("principal.getUserEmail() : " + principal.getUserEmail());

        OAuth2AuthorizedClient client = null;
        switch (principal.getSocialName()) {
            case "Naver" -> client = authorizedClientService.loadAuthorizedClient(
                    "naver", principal.getUserEmail());
            case "Google" -> client = authorizedClientService.loadAuthorizedClient(
                    "google", principal.getUserEmail());
            case "kakao" -> client = authorizedClientService.loadAuthorizedClient(
                    "kakao", principal.getUserEmail());
        }
        System.out.println(
                "-------------------- client --------------------\n"
                        + "client.getClientRegistration() : " + client.getClientRegistration() + "\n"
                        + "client.getPrincipalName() : " + client.getPrincipalName() + "\n"
                        + "client.getAccessToken() : " + client.getAccessToken() + "\n"
                        + "client.getRefreshToken() : " + client.getRefreshToken() + "\n"
        );

        return new SocialResponseDTO<>(
                HttpStatus.OK.value(), "회원 탈퇴가 완료되었습니다.",
                principal.getSocialName(), client.getAccessToken(), client.getClientRegistration());
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
                                                 @RequestBody Map<String, Object> map) {

        String encodedPwd = userService.getUser(principal.getUserEmail()).getPassword();
        String rawPwdCheck = (String) map.get("checkPwd");

        String rawNewPwd = (String) map.get("newPwd");

        // 기존 비밀번호 일치
        if (passwordEncoder.matches(rawPwdCheck, encodedPwd)) {
            userService.checkPassword(principal, rawNewPwd);

            return new ResponseDTO<>(HttpStatus.OK.value(), "비밀번호가 변경되었습니다. 다시 로그인 해주세요.");
        } else {
            //기존 비밀번호 불일치
            return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "현재 비밀번호를 확인해주세요.");
        }
    }

}
