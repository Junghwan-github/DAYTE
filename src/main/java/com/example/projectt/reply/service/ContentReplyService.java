package com.example.projectt.reply.service;


import com.example.projectt.reply.domain.ContentReply;
import com.example.projectt.reply.repository.ContentReplyRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentReplyService {

    @Autowired
    private ContentReplyRepository contentReplyRepository;

    @Autowired
    private ModelMapper modelMapper;

    // 새로운 댓글 추가
    @Transactional
    public void contentReplyinsert(ContentReply contentReply) {
        contentReplyRepository.save(contentReply);
    }

    // 모든 댓글 목록 조회
    public List<ContentReply> contentReplyList() {
        return contentReplyRepository.findAll();
    }

    // 댓글 삭제
    @Transactional
    public void contentReplydelete(int num) {
        contentReplyRepository.deleteById(num);
    }

    // 댓글 업데이트
    @Transactional
    public void contentReplyUpdate(int num, String newContent) {
        // 주어진 댓글 번호에 해당하는 댓글을 찾음

        ContentReply contentReply = contentReplyRepository.findById(num)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        contentReply.setContent(newContent);

        contentReplyRepository.save(contentReply);



    }
}