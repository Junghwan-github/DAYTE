package com.example.dayte.members.controller;

import com.example.dayte.members.domain.RoleType;
import com.example.dayte.members.domain.User;
import com.example.dayte.members.dto.UserDTO;
import com.example.dayte.members.service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

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
        if(bindingResult.hasErrors()) {
            Map<String, String> errMap = new HashMap<>();
            for(FieldError err : bindingResult.getFieldErrors()) {
                errMap.put(err.getField(), err.getDefaultMessage());
            }
            return  HttpStatus.BAD_REQUEST.value();
        }

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        userDTO.setRole(RoleType.USER);
        System.out.println("userDTO : " + userDTO);
        User user = modelMapper.map(userDTO, User.class);
        System.out.println("user : " + user);

        User findUser = userService.getUser(user.getUserEmail());
        System.out.println("user.getUserEmail() : " + user.getUserEmail());
        if(findUser.getUserEmail() == null && findUser.getNickName() == null) {
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
                            @PageableDefault(size = 10, sort = "joinDate" , direction = Sort.Direction.DESC) Pageable pageable,
                            @RequestParam(required = false, defaultValue = "")String field,
                            @RequestParam(required = false, defaultValue = "")String word) {
        Page<User> allList = userService.userList(pageable);
        Page<User> ulist = userService.userList(pageable);
        // 사용자 상세 검색
        if(field.equals("userName")) {
            ulist = userService.userListByUserName(word, pageable);
        }else if(field.equals("userEmail")) {
            ulist = userService.userListByUserEmail(word, pageable);
        }else if(field.equals("nickName")) {
            ulist = userService.userListByNickName(word, pageable);
        }else if(field.equals("role")) {
            ulist = userService.userListByRole(word, pageable);
        }else if(field.equals("phone")) {
            ulist = userService.userListByPhone(word, pageable);
        }



        int pageNumber = ulist.getPageable().getPageNumber(); // 현재 페이지
        int totalPages = ulist.getTotalPages();
        int pageBlock = 5; // 페이지 블럭 수
        int startBlockPage = ((pageNumber)/pageBlock)*pageBlock+1;

        int endBlockPage = startBlockPage+pageBlock-1;
        endBlockPage = totalPages < endBlockPage ? totalPages : endBlockPage;

        model.addAttribute("startBlockPage", startBlockPage);
        model.addAttribute("endBlockPage",endBlockPage);
        model.addAttribute("ulist", ulist);
        model.addAttribute("allList",allList);

        return "adminPage/adminHome";
    }
}