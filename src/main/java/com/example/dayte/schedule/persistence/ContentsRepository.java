package com.example.dayte.schedule.persistence;

import com.example.dayte.schedule.domain.Contents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ContentsRepository extends JpaRepository<Contents,String> {

    @Query("SELECT c FROM Contents c WHERE c.businessName LIKE %:search% OR c.category LIKE %:search% OR c.gu LIKE %:search% OR c.ro LIKE %:search%")
    List<Contents> findAllBySearch(String search);
}
