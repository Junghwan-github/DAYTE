package com.example.dayte.members.service;

import com.example.dayte.members.domain.RoleType;
import com.example.dayte.members.domain.User;
import com.example.dayte.members.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void insertUser(User user){
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User getUser(String userEmail) {
        User findUser = userRepository.findByUserEmail(userEmail).orElseGet(User::new);
        return findUser;
    }
    @Transactional
    public Page<User> userList(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Transactional
    public Page<User> userListByUserName(String userName, Pageable pageable) {
        return userRepository.findByUserName(userName, pageable);
    }

    @Transactional
    public Page<User> userListByUserEmail(String userEmail, Pageable pageable) {
        return userRepository.findByUserEmail(userEmail, pageable);
    }

    @Transactional
    public Page<User> userListByNickName(String nickName, Pageable pageable) {
        return userRepository.findByNickName(nickName, pageable);
    }

    @Transactional
    public Page<User> userListByRole(String role, Pageable pageable) {
        RoleType roleType = convert(role);
        return userRepository.findByRole(roleType, pageable);
    }

    @Transactional
    public Page<User> userListByPhone(String phone, Pageable pageable) {
        return userRepository.findByPhone(phone, pageable);
    }

    public static RoleType convert(String role) {
        try {
            String roleName = "";
            if (role.equals("유저") || role.toUpperCase().equals("USER")) {
                roleName = "USER";
            } else if (role.equals("관리자") || role.toUpperCase().equals("ADMIN")) {
                roleName = "ADMIN";
            } else if (role.equals("휴면") || role.toUpperCase().equals("DORMANCY")) {
                roleName = "DORMANCY";
            } else if (role.equals("탈퇴") || role.toUpperCase().equals("DEL")) {
                roleName = "DEL";
            } else if (role.equals("정지") || role.toUpperCase().equals("BLOCK")) {
                roleName = "BLOCK";
            }
            return RoleType.valueOf(roleName.toUpperCase());

        } catch (IllegalArgumentException | NullPointerException e) {
            throw new IllegalArgumentException("Invalid RoleType value");
        }
    }

}
