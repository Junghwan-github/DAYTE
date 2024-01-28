package com.example.dayte.reply.repository;



import com.example.dayte.reply.domain.PostReply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostReplyRepository extends JpaRepository<PostReply, Integer> {
}
