package com.example.dayte.members.controller;

import com.example.dayte.members.domain.RoleType;
import com.example.dayte.members.domain.User;
import com.example.dayte.members.dto.ResponseDTO;
import com.example.dayte.members.dto.UserDTO;
import com.example.dayte.members.service.UserService;
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
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/members/joinForm")
    public @ResponseBody int insertUser(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errMap = new HashMap<>();
            for (FieldError err : bindingResult.getFieldErrors()) {
                errMap.put(err.getField(), err.getDefaultMessage());
            }
            return HttpStatus.BAD_REQUEST.value();
        }

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        userDTO.setRole(RoleType.USER);
        System.out.println("userDTO : " + userDTO);
        User user = modelMapper.map(userDTO, User.class);
        System.out.println("user : " + user);

        User findUser = userService.getUser(user.getUserEmail());
        System.out.println("user.getUserEmail() : " + user.getUserEmail());
        if (findUser.getUserEmail() == null && findUser.getNickName() == null) {
            System.out.println("user : " + user);
            userService.insertUser(user);
            return HttpStatus.OK.value();

        } else {
            return HttpStatus.BAD_REQUEST.value();
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

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/editUser")
    public @ResponseBody ResponseDTO<?> updateUser(@RequestBody UserDTO userDTO) throws IOException {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        //userDTO.setRole(userDTO.getRole());
        userDTO.setUserName(userDTO.getUserName());
        userDTO.setNickName(userDTO.getNickName());
        userDTO.setPhone(userDTO.getPhone());
        System.out.println("================================회원정보 수정 : " + userDTO);


        userService.updateUser(userDTO);
        return new ResponseDTO<>(HttpStatus.OK.value(), "회원 정보가 수정되었습니다.");

    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/members/editForm")
    public String modifyUserForm(Model model,
                                 @AuthenticationPrincipal UserSecurityDTO userSecurityDTO) {
        model.addAttribute("userInfo", userService.getUser(userSecurityDTO.getUserEmail()));
        return "members/myProfile";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/members/editPsForm")
    public String modifyPasswordForm(Model model,
                                     @AuthenticationPrincipal UserSecurityDTO userSecurityDTO) {
        model.addAttribute("userInfo", userService.getUser(userSecurityDTO.getUserEmail()));
        return "members/editPassword";

    }

    @PutMapping("/members/editForm")
    public @ResponseBody ResponseDTO<?> modifyUser(
            @ModelAttribute UserDTO userDTO, // 변경된 부분
            @AuthenticationPrincipal UserSecurityDTO principal
    ) {

        if (userDTO.getImage() != null && !userDTO.getImage().isEmpty()) {
            userService.profileImage(userDTO);
        } else {
            userDTO.setProfileImageName("default_icon_profile.png");
            userDTO.setProfileImagePath("/images/default_icon_profile.png");
        }

        userService.modifyUser(principal, userDTO);
        principal.setProfileImagePath(userDTO.getProfileImagePath());
        principal.setNickName(userDTO.getNickName());
        return new ResponseDTO<>(HttpStatus.OK.value(), "회원 정보가 수정되었습니다.");
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/members/delete")
    public String deletePage() {
        return "members/delForm";
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/members/delete/{userEmail}")
    public @ResponseBody ResponseDTO<?> deleteUser(@PathVariable String userEmail,
                                                   @RequestBody Map<String, Object> map) {

        String encodedPassword = userService.getUser(userEmail).getPassword();
        String rawPassword = (String) map.get("password");

        if (passwordEncoder.matches(rawPassword, encodedPassword)) {
            userService.deleteUser(userEmail);
            return new ResponseDTO<>(HttpStatus.OK.value(), "회원 탈퇴가 완료되었습니다.");
        } else {
            return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "회원 탈퇴를 실패했습니다.");
        }
    }

    @PostMapping("/members/nickNameChk/{nickName}")
    public @ResponseBody ResponseDTO<?> nickNameChk(
            @PathVariable String nickName // 변경된 부분
    ) throws IOException {
        if (nickName != null && !userService.nickNameChk(nickName)) {
                return new ResponseDTO<>(HttpStatus.OK.value(), "사용 할 수 있는 닉네임 입니다.");
            } else {
                return new ResponseDTO<>(HttpStatus.CONFLICT.value(), "중복된 닉네임 입니다.");
            }
    }
}
