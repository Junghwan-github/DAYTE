package com.example.dayte.myReview.persistence;

import com.example.dayte.members.domain.User;
import com.example.dayte.myReview.domain.MyReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyReviewRepository extends JpaRepository<MyReview, Integer> {
    List<MyReview> findAllByUser(User user);

    Page<MyReview> findAllByUser(User user, Pageable pageable);
}
