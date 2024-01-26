package com.example.projectt.post.postRepository;


import com.example.projectt.post.domin.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
