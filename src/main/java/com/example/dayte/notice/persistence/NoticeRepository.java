package com.example.dayte.notice.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NoticeRepository extends JpaRepository<com.example.dayte.notice.domain.Notice, Integer> {

    @Query("SELECT n FROM Notice n WHERE n.viewCheck = true ORDER BY CASE WHEN n.priority > 0 THEN 0 ELSE 1 END, n.priority ASC")
    Page<com.example.dayte.notice.domain.Notice> findAllByPriority(Pageable pageable);

    @Query("SELECT n FROM Notice n WHERE n.no = (SELECT MIN(n2.no) FROM Notice n2 WHERE n2.no > :currentId)")
    Optional<com.example.dayte.notice.domain.Notice> findNextNotice(@Param("currentId") int currentId);

    @Query("SELECT n FROM Notice n WHERE n.no = (SELECT Max(n2.no) FROM Notice n2 WHERE n2.no < :currentId)")
    Optional<com.example.dayte.notice.domain.Notice> findPrevNotice(@Param("currentId") int currentId);

    @Query("SELECT n FROM Notice n WHERE n.priority>0 ORDER BY n.priority ASC")
    List<com.example.dayte.notice.domain.Notice> findTruePriority();

    @Query("SELECT n FROM Notice n WHERE n.priority=0 ORDER BY n.priority DESC")
    Page<com.example.dayte.notice.domain.Notice> findDefaultPriority(Pageable pageable);

    @Query("SELECT n FROM Notice n ORDER BY n.priority DESC LIMIT 1")
    com.example.dayte.notice.domain.Notice findMaxPriorityNotice();


    /*@Query("SELECT n FROM Notice n WHERE n.title LIKE %:searchWord% OR n.content LIKE %:searchWord%")*/
    @Query("select n FROM Notice n where n.priority = 0 and case when :searchOption = 'title' then (n.title like %:searchWord%) when :searchOption = 'content' then (n.content like %:searchWord%) when :searchOption = 'all' then (n.title LIKE %:searchWord% OR n.content LIKE %:searchWord%) end ")
    Page<com.example.dayte.notice.domain.Notice> searchNoticesAdmin(Pageable pageable, @Param("searchWord") String searchWord, @Param("searchOption") String searchOption);

    @Query("select n FROM Notice n where n.priority = 0 and case when :searchOption = 'title' then (n.title like %:searchWord%) when :searchOption = 'content' then (n.content like %:searchWord%) when :searchOption = 'all' then (n.title LIKE %:searchWord% OR n.content LIKE %:searchWord%) end ")
    Page<com.example.dayte.notice.domain.Notice> searchNotices(Pageable pageable, String searchWord, String searchOption);
}


