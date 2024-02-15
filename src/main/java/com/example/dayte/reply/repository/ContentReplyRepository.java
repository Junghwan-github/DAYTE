package com.example.dayte.reply.repository;


import com.example.dayte.reply.domain.ContentReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ContentReplyRepository extends JpaRepository<ContentReply, Integer> {

    @Query("SELECT cr.user.userEmail FROM ContentReply cr WHERE cr.user.userEmail = :userId")
    List<String> findByContentsId(@Param("userId") String userId);

    @Query("select cr FROM ContentReply cr where cr.adminContents.uuid = :uuid")
    List<ContentReply> findByContentUuid(@Param("uuid") String uuid);

    @Query("select cr From ContentReply cr where cr.adminContents.uuid = :uuid and cr.user.userEmail = :userEmail")
    ContentReply findUserContentReply(@Param("userEmail") String userEmail, @Param("uuid") String uuid);
}
