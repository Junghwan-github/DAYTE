package com.example.dayte.members.controller;

import com.example.dayte.members.domain.RoleType;
import com.example.dayte.members.domain.User;
import com.example.dayte.members.dto.ResponseDTO;
import com.example.dayte.members.dto.UserDTO;
import com.example.dayte.members.service.UserService;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public @ResponseBody ResponseDTO<?> updateUser(@RequestBody UserDTO userDTO) {
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
        return "members/editForm";
    }


    @PutMapping("/members/editForm")
    public @ResponseBody ResponseDTO<?> modifyUser(
            @ModelAttribute UserDTO userDTO, // 변경된 부분
            @AuthenticationPrincipal UserSecurityDTO principal
            ) throws IOException {

        if (userDTO.getImage() != null && !userDTO.getImage().isEmpty()) {
            userService.profileImage(userDTO.getImage(), userDTO);
        }

        //  userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userService.modifyUser(userDTO);
        principal.setProfileImagePath(userDTO.getProfileImagePath());
        return new ResponseDTO<>(HttpStatus.OK.value(), "회원 정보가 수정되었습니다.");
    }
}
