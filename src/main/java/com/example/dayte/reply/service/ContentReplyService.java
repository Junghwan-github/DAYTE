package com.example.dayte.reply.service;


import com.example.dayte.admin.contents.domain.AdminContents;
import com.example.dayte.admin.contents.persistence.AdminContentsRepository;
import com.example.dayte.content.dto.AvgStarViewDTO;
import com.example.dayte.reply.domain.ContentReply;
import com.example.dayte.reply.dto.UpdateContentReplyDTO;
import com.example.dayte.reply.repository.ContentReplyRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentReplyService {

    @Autowired
    private AdminContentsRepository adminContentsRepository;

    @Autowired
    private ContentReplyRepository contentReplyRepository;


    public boolean findContentReply(String userEmail, String uuid) {
        List<ContentReply> findContent = contentReplyRepository.findByContentUuid(uuid);

        if (!findContent.isEmpty()) {
            for (ContentReply contentReply : findContent) {
                if (contentReply.getUser().getUserEmail().equals(userEmail))
                    return true;
            }
        }
        return false;
    }

    // 새로운 댓글 추가
    @Transactional
    public void contentReplyinsert(ContentReply contentReply, String contentUuid) {
            AdminContents adminContents = adminContentsRepository.findById(contentUuid).get();
            contentReply.setContents(adminContents);
            contentReplyRepository.save(contentReply);
    }

    //댓글 수정창에서 수정 로직
    public void updateReply(String userEmail, UpdateContentReplyDTO updateContentReplyDTO) {
        ContentReply findContentReply = contentReplyRepository.findUserContentReply(userEmail, updateContentReplyDTO.getUuid());
        findContentReply.setContent(updateContentReplyDTO.getNewReply());
        findContentReply.setRating(updateContentReplyDTO.getNewRating());

        contentReplyRepository.save(findContentReply);
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

    //컨텐츠에 해당 유저가 작성한 댓글 불러오기
    public ContentReply findUserContentReply(String userEmail, String uuid) {
        ContentReply contentReply = contentReplyRepository.findUserContentReply(userEmail, uuid);
        return contentReply;
    }

    @Transactional
    public List<ContentReply> findContentsReplyList (String uuid) {
        return contentReplyRepository.findByContentUuid(uuid);
    }

    public List<AvgStarViewDTO> avgStarList() {
        return contentReplyRepository.avgStarList();
    }

    public AvgStarViewDTO avgStar(String uuid) {
        return contentReplyRepository.avgStar(uuid);
    }
}
