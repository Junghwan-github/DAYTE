package com.example.dayte.admin.contents.persistence;

import com.example.dayte.admin.contents.domain.AdminContents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdminContentsRepository extends JpaRepository<AdminContents, String> {

    @Query("SELECT c FROM AdminContents c WHERE c.businessName LIKE %:search% OR c.category LIKE %:search% OR c.gu LIKE %:search%")
    List<AdminContents> findAllBySearch(String search);

    List<AdminContents> findAllByCategory(String category);


}
