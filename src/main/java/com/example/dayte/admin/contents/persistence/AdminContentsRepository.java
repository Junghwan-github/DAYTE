package com.example.dayte.admin.contents.persistence;

import com.example.dayte.admin.contents.domain.AdminContents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdminContentsRepository extends JpaRepository<AdminContents, String> {

    @Query("SELECT c FROM AdminContents c WHERE c.businessName LIKE %:search% OR c.gu LIKE %:search% OR c.detailedAddress LIKE %:search% OR c.keyword LIKE %:search%")
    List<AdminContents> findAllBySearch(String search);

    List<AdminContents> findAllByCategory(String category);

    @Query("SELECT c FROM AdminContents c WHERE c.category = :category AND " +
            "(c.businessName LIKE %:search% OR c.gu LIKE %:search% OR c.detailedAddress LIKE %:search% OR c.keyword LIKE %:search%)")
    List<AdminContents> findAllBySearch(String category, String search);

    @Query("SELECT c FROM AdminContents c WHERE c.category = :category AND " +
            "c.gu = :search")
    List<AdminContents> findAllByGugunSearch(String category, String search);

    @Query("SELECT c FROM AdminContents c WHERE c.category = :category AND " +
            "c.keyword LIKE %:search%")
    List<AdminContents> findAllByKeyWordSearch(String category, String search);

    @Query(value = "SELECT * FROM admin_contents order by RAND() limit 5",nativeQuery = true)
    List<AdminContents> randomContentsList();
}
