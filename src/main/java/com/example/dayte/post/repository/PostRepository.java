package com.example.dayte.post.repository;


import com.example.dayte.members.domain.User;
import com.example.dayte.post.domin.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    // 제목으로 검색했을 경우
    @Query(value = "select * from Post p where REGEXP_REPLACE(p.title, '<[^>]*>', '') LIKE %:postWord%", nativeQuery = true)
    Page<Post> postSearchToPostTitle(Pageable pageable, @Param("postWord")String postWord);

    // 내용으로 검색했을 경우
    @Query(value = "select * from Post p where REGEXP_REPLACE(p.content, '<[^>]*>', '') LIKE %:postWord%", nativeQuery = true)
    Page<Post> postSearchToPostContent(Pageable pageable, @Param("postWord")String postWord);

    // 전체로 검색했을 경우
    @Query(value = "select * from Post p where REGEXP_REPLACE(p.title, '<[^>]*>', '') LIKE %:postWord% or REGEXP_REPLACE(p.content, '<[^>]*>', '') like %:postWord%", nativeQuery = true)
    Page<Post> postSearchToPostAll(Pageable pageable, @Param("postWord")String postWord);

    Page<Post> findAllByUser(User user, Pageable pageable);

    @Query(value = "select * from Post p where REGEXP_REPLACE(p.title, '<[^>]*>', '') LIKE %:postWord% or REGEXP_REPLACE(p.content, '<[^>]*>', '') like %:postWord%", nativeQuery = true)
    List<Post> postSearchToAll(@Param("postWord")String postWord);

    // --- 관리자 페이지 최근 게시글 ----
    @Query("SELECT p FROM Post p ORDER BY p.createDate DESC LIMIT :count")
    List<Post> findTopByOrderByCreateDateDesc(int count);

}
