package com.example.projectt.reply.repository;



import com.example.projectt.reply.domain.PostReply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostReplyRepository extends JpaRepository<PostReply, Integer> {
}
