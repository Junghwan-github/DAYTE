package com.example.dayte.post.repository;


import com.example.dayte.members.domain.User;
import com.example.dayte.post.domin.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p FROM Post p where case when :postBordSearchDropDownMenu = 'postTitle' then (p.title like %:postSearchInputBox%) when :postBordSearchDropDownMenu = 'postContent' then (p.content like %:postSearchInputBox%) when :postBordSearchDropDownMenu = 'postAll' then (p.title LIKE %:postSearchInputBox% OR p.content LIKE %:postSearchInputBox%) end ")
    Page<Post> postSearch(Pageable pageable, @Param("postSearchInputBox") String postSearchInputBox, @Param("postBordSearchDropDownMenu")String postBordSearchDropDownMenu);

    Page<Post> findAllByUser(User user, Pageable pageable);

//    @Query("SELECT p FROM Post p ORDER BY p.id ASC")
//    List<com.example.dayte.post.domin.Post> findSearchPosts();

}
