package com.example.projectt.members.controller;

import com.example.projectt.members.domain.RoleType;
import com.example.projectt.members.domain.User;
import com.example.projectt.members.dto.ResponseDTO;
import com.example.projectt.members.dto.UserDTO;
import com.example.projectt.members.service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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

}
