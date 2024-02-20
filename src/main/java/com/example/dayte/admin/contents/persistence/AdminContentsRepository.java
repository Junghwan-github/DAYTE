package com.example.dayte.admin.contents.persistence;

import com.example.dayte.admin.contents.domain.AdminContents;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdminContentsRepository extends JpaRepository<AdminContents, String> {

    @Query("SELECT c FROM AdminContents c WHERE c.category != '1d53a954b6cf' AND " +
            "(c.businessName LIKE %:search% OR c.gu LIKE %:search% OR c.detailedAddress LIKE %:search% OR c.keyword LIKE %:search%)")
    List<AdminContents> findAllBySearch(String search);

    List<AdminContents> findAllByCategory(String category);

//    List<AdminContents> findAllByCategoryAndCategoryNot(String category, String not);

    @Query("SELECT c FROM AdminContents c WHERE c.category = :category AND " +
            "(c.businessName LIKE %:search% OR c.gu LIKE %:search% OR c.detailedAddress LIKE %:search% OR c.keyword LIKE %:search%)")
    List<AdminContents> findAllBySearch(String category, String search);

    @Query("SELECT c FROM AdminContents c WHERE c.category = :category AND " +
            "c.gu = :search")
    List<AdminContents> findAllByGugunSearch(String category, String search);

    @Query("SELECT c FROM AdminContents c WHERE c.category = :category AND " +
            "c.keyword LIKE %:search%")
    List<AdminContents> findAllByKeyWordSearch(String category, String search);

    @Query(value = "SELECT * FROM admin_contents WHERE category != '1d53a954b6cf' ORDER BY RAND() limit 5",nativeQuery = true)
    List<AdminContents> randomContentsList();

//    @Query("SELECT c FROM AdminContents c WHERE c.category = :category AND c.category != '1d53a954b6cf' AND " +
//            "(c.businessName LIKE %:search% OR c.gu LIKE %:search% OR c.detailedAddress LIKE %:search% OR c.keyword LIKE %:search%)")
//    List<AdminContents> findAllByCategorySearch(String category, String search);

    @Query("SELECT c FROM AdminContents c WHERE c.category != '1d53a954b6cf' AND " +
            "(c.businessName LIKE %:contentsWord% " +
            "OR c.gu LIKE %:contentsWord% OR c.category LIKE %:contentsWord% OR c.keyword LIKE %:contentsWord%)")
    Page<AdminContents> findAll(String contentsWord, Pageable pageable);

    @Query("SELECT c FROM AdminContents c WHERE c.category != '1d53a954b6cf' AND c.businessName LIKE %:contentsWord%")
    Page<AdminContents> getContentsListByBusinessName(String contentsWord, Pageable pageable);

    @Query("SELECT c FROM AdminContents c WHERE c.category != '1d53a954b6cf' AND c.category LIKE %:contentsWord%")
    Page<AdminContents> getContentsListByCategory(String contentsWord, Pageable pageable);

    @Query("SELECT c FROM AdminContents c WHERE c.category != '1d53a954b6cf' AND  c.gu LIKE %:contentsWord%")
    Page<AdminContents> getContentsListByGu(String contentsWord, Pageable pageable);

    @Query("SELECT c FROM AdminContents c WHERE c.category != '1d53a954b6cf' AND  c.keyword LIKE %:contentsWord%")
    Page<AdminContents> getContentsListByKeyword(String contentsWord, Pageable pageable);

    @Query("SELECT c FROM AdminContents c WHERE c.category = '1d53a954b6cf'")
    Page<AdminContents> getDeleteContentsList(Pageable pageable);

}
