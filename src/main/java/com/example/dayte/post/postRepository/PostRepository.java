package com.example.dayte.post.postRepository;


import com.example.dayte.post.domin.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
