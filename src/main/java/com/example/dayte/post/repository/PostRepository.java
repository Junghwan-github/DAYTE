package com.example.dayte.post.repository;


import com.example.dayte.members.domain.User;
import com.example.dayte.post.domin.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {


        // 수정이 필요한 코드
//    @Query("select p FROM Post p where case when :postField = 'postTitle' then (REGEXP_REPLACE(p.title, '<[^>]*>', '') like %:postWord%) when :postField = 'postContent' then (REGEXP_REPLACE(p.content, '<[^>]*>', '')like %:postWord%) when :postField = 'postAll' then ((REGEXP_REPLACE(p.title, '<[^>]*>', '')like %:postWord%) OR (REGEXP_REPLACE(p.content, '<[^>]*>', '')like %:postWord%)) end")
//    Page<Post> postSearch(Pageable pageable, @Param("postField") String postField, @Param("postWord")String postWord);


    @Query("select p FROM Post p where case when :postField = 'postTitle' then (p.title like %:postWord%) when :postField = 'postContent' then (p.content like %:postWord%) when :postField = 'postAll' then (p.title LIKE %:postWord% OR p.content LIKE %:postWord%) end")
    Page<Post> postSearch(Pageable pageable, @Param("postField") String postField, @Param("postWord")String postWord);



    Page<Post> findAllByUser(User user, Pageable pageable);
}
