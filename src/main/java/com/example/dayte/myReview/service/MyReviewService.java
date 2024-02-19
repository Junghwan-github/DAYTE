package com.example.dayte.myReview.service;

import com.example.dayte.admin.contents.persistence.AdminContentsImageRepository;
import com.example.dayte.admin.contents.persistence.AdminContentsRepository;
import com.example.dayte.members.domain.User;
import com.example.dayte.members.persistence.UserRepository;
import com.example.dayte.post.domin.Post;
import com.example.dayte.post.repository.PostRepository;
import com.example.dayte.reply.domain.ContentReply;
import com.example.dayte.reply.repository.ContentReplyRepository;
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
    private AdminContentsRepository adminContentsRepository;

    @Autowired
    private AdminContentsImageRepository adminContentsImageRepository;

    @Autowired
    private ContentReplyRepository contentReplyRepository;

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

    @Transactional
    public Page<ContentReply> getMyContentsReview(User user, Pageable pageable) {
        User loginUser = userRepository.findById(user.getUserEmail()).orElse(new User());

        return contentReplyRepository.findAllByUser(user, pageable);
    }
}
