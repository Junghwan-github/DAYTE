package com.example.dayte.members.service;

import com.example.dayte.members.domain.User;
import com.example.dayte.members.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

}
