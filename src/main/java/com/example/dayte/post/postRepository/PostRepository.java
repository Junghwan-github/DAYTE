package com.example.dayte.post.postRepository;


import com.example.dayte.notice.domain.Notice;
import com.example.dayte.post.domin.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query("select p FROM Post p where case when :postBordSearchDropDownMenu = 'postTitle' then (p.title like %:postSearchInputBox%) when :postBordSearchDropDownMenu = 'postContent' then (p.content like %:postSearchInputBox%) when :postBordSearchDropDownMenu = 'postAll' then (p.title LIKE %:postSearchInputBox% OR p.content LIKE %:postSearchInputBox%) end ")
    Page<Post> postSearch(Pageable pageable, @Param("postSearchInputBox") String postSearchInputBox, @Param("postBordSearchDropDownMenu")String postBordSearchDropDownMenu);

//    @Query("SELECT p FROM Post p ORDER BY p.id ASC")
//    List<com.example.dayte.post.domin.Post> findSearchPosts();

}

