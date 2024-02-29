package com.example.dayte.reply.repository;


import com.example.dayte.content.dto.AvgStarViewDTO;
import com.example.dayte.members.domain.User;
import com.example.dayte.reply.domain.ContentReply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContentReplyRepository extends JpaRepository<ContentReply, Long> {

    @Query("SELECT cr.user.userEmail FROM ContentReply cr WHERE cr.user.userEmail = :userId")
    List<String> findByContentsId(@Param("userId") String userId);

    @Query("select cr FROM ContentReply cr where cr.contents.uuid = :uuid")
    List<ContentReply> findByContentUuid(@Param("uuid") String uuid);

    @Query("select cr From ContentReply cr where cr.contents.uuid = :uuid and cr.user.userEmail = :userEmail")
    ContentReply findUserContentReply(@Param("userEmail") String userEmail, @Param("uuid") String uuid);

    Page<ContentReply> findAllByUser(User user, Pageable pageable);

    // 별점 리스트
    @Query("SELECT new com.example.dayte.content.dto.AvgStarViewDTO(cr.contents.uuid, AVG(cr.rating)) FROM ContentReply cr GROUP BY cr.contents")
    List<AvgStarViewDTO> avgStarList();

    // 상세보기 별점
    @Query("SELECT new com.example.dayte.content.dto.AvgStarViewDTO(cr.contents.uuid, AVG(cr.rating)) FROM ContentReply cr where cr.contents.uuid = :uuid GROUP BY cr.contents")
    AvgStarViewDTO avgStar(String uuid);
}
