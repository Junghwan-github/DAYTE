package com.example.dayte.reply.repository;



import com.example.dayte.post.domin.Post;
import com.example.dayte.post.postRepository.PostRepository;
import com.example.dayte.reply.domain.PostReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostReplyRepository extends JpaRepository<PostReply, Integer> {
    List<PostReply> findAllByPost(Post post);

}
