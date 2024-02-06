package com.example.dayte.post.postRepository;


import com.example.dayte.members.domain.User;
import com.example.dayte.post.domin.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {

    Page<Post> findAllByUser(User user, Pageable pageable);

}
