package com.example.projectt.reply.service;


import com.example.projectt.reply.domain.PostReply;
import com.example.projectt.reply.repository.PostReplyRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Log4j2
public class PostReplyService {

    @Autowired
    private PostReplyRepository postReplyRepository;

    // 새로운 댓글 추가
    @Transactional
    public void postReplyInsert(PostReply postReply) {
        postReplyRepository.save(postReply);
    }

    // 모든 댓글 목록 조회
    public List<PostReply> postReplyList() {
        return postReplyRepository.findAll();
    }

    // 댓글 삭제
    @Transactional
    public void postReplydelete(int num) {
        postReplyRepository.deleteById(num);
    }

    // 댓글 업데이트
    @Transactional
    public void postReplyUpdate(int num, String newContent) {
        // 주어진 댓글 번호에 해당하는 댓글을 찾음

        PostReply postReply = postReplyRepository.findById(num)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        postReply.setContent(newContent);
        postReply.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));

    }
}
