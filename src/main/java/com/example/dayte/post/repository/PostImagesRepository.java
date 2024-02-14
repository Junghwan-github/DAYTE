package com.example.dayte.post.repository;

import com.example.dayte.post.domin.Post;
import com.example.dayte.post.domin.PostImages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostImagesRepository extends JpaRepository<PostImages,Long> {

    void deleteAllByPost(Post post);
}
