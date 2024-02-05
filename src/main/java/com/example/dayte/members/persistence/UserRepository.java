package com.example.dayte.members.persistence;

import com.example.dayte.members.domain.RoleType;
import com.example.dayte.members.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUserEmail(String userEmail);

    // 회원 목록 + 검색 (페이징)
    Page<User> findAll(Pageable pageable);
    Page<User> findByUserName(String userName, Pageable pageable);

    Page<User> findByUserEmail(String userEmail, Pageable pageable);

    Page<User> findByNickName(String nickName, Pageable pageable);

    Page<User> findByRole(RoleType role, Pageable pageable);

    Page<User> findByPhone(String phone, Pageable pageable);

    Page<User> findByDel(boolean del, Pageable pageable);

    Optional<User> findByNickName(String nickName);


}
