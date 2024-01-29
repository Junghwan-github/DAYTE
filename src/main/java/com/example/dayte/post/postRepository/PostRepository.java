package com.example.dayte.post.postRepository;


import com.example.dayte.post.domin.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {



}
