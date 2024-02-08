package com.example.dayte.members.persistence;

import com.example.dayte.members.domain.RoleType;
import com.example.dayte.members.domain.User;
import com.example.dayte.myReview.domain.MyReview;
import com.example.dayte.post.domin.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUserEmail(String userEmail);

    // 회원 목록 + 검색 (페이징)
    Page<User> findAll(Pageable pageable);
    @Query("SELECT c FROM User c WHERE c.userName LIKE %:userName%")
    Page<User> findByUserName(String userName, Pageable pageable);

    @Query("SELECT c FROM User c WHERE c.userEmail LIKE %:userEmail%")
    Page<User> findByUserEmail(String userEmail, Pageable pageable);

    @Query("SELECT c FROM User c WHERE c.nickName LIKE %:nickName%")
    Page<User> findByNickName(String nickName, Pageable pageable);

    Page<User> findByRole(RoleType role, Pageable pageable);

    @Query("SELECT c FROM User c WHERE c.phone LIKE %:phone%")
    Page<User> findByPhone(String phone, Pageable pageable);

    Page<User> findByDel(boolean del, Pageable pageable);

    boolean existsByNickName(String nickName);

}
