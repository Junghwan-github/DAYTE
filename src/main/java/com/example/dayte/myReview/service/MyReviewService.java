package com.example.dayte.myReview.service;

import com.example.dayte.members.domain.User;
import com.example.dayte.members.persistence.UserRepository;
import com.example.dayte.post.domin.Post;
import com.example.dayte.post.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyReviewService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    /*@Transactional(readOnly = true)
    public List<MyReview> getMyReview(UserSecurityDTO principal) {
        *//*return myReviewRepository.findAllByUser(userEmail);*//*
    }*/

    @Transactional
    public Page<Post> getMyReview(User user, Pageable pageable) {
        User loginUser = userRepository.findById(user.getUserEmail()).orElse(new User());
//        System.out.println("loginUser : " + loginUser);
//        User user = modelMapper.map(loginUser, User.class);
        return postRepository.findAllByUser(loginUser, pageable);
    }

}
