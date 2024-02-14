package com.example.dayte.reply.service;


import com.example.dayte.admin.contents.domain.AdminContents;
import com.example.dayte.admin.contents.persistence.AdminContentsRepository;
import com.example.dayte.members.domain.User;
import com.example.dayte.reply.domain.ContentReply;
import com.example.dayte.reply.repository.ContentReplyRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;


import java.util.List;

@Service
public class ContentReplyService {

    @Autowired
    private AdminContentsRepository adminContentsRepository;

    @Autowired
    private ContentReplyRepository contentReplyRepository;

    @Autowired
    private ModelMapper modelMapper;


    public boolean findContentReply(String userEmail, String uuid) {
        List<ContentReply> findContent = contentReplyRepository.findByContentUuid(uuid);

        if (!findContent.isEmpty()) {
            for (ContentReply contentReply : findContent) {
                if (contentReply.getUser().getUserEmail().equals(userEmail)) {
                    return true;
                }
            }
        }

        return false;
    }


    // 새로운 댓글 추가
    @Transactional
    public void contentReplyinsert(ContentReply contentReply, String contentUuid) {
        /*List<String> contReply = contentReplyRepository.findByContentsId(contentReply.getUser().getUserEmail());

        boolean istrue = contReply.contains(contentReply.getUser().getUserEmail());

        if(istrue) {
            System.out.println("끼얏호");
        } else {}*/

            AdminContents adminContents = adminContentsRepository.findById(contentUuid).get();
            contentReply.setContents(adminContents);
            contentReplyRepository.save(contentReply);

    }

    // 모든 댓글 목록 조회
    public List<ContentReply> contentReplyList() {

        return contentReplyRepository.findAll();
    }

    // 댓글 삭제
    @Transactional
    public void contentReplydelete(long num) {
        contentReplyRepository.deleteById(num);
    }

    // 댓글 업데이트
    @Transactional
    public void contentReplyUpdate(long num, String newContent) {
        // 주어진 댓글 번호에 해당하는 댓글을 찾음

        ContentReply contentReply = contentReplyRepository.findById(num)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        contentReply.setContent(newContent);

        contentReplyRepository.save(contentReply);



    }


}
