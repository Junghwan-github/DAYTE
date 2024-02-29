package com.example.dayte.notice.persistence;


import com.example.dayte.notice.domain.FilesInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FileRepository extends JpaRepository<FilesInfo, Integer> {

    @Query("select f from FilesInfo f where f.notice.no = :no ")
    List<FilesInfo> findByPostNo(@Param("no") int no);

    @Query("SELECT f FROM FilesInfo f WHERE f.notice.no = :no and f.saveName NOT IN :savedFile")
    List<FilesInfo> findFilesNotInSavedFile(List<String> savedFile, @Param("no") int no);

    @Modifying
    @Query("delete from FilesInfo f where f.notice.no = :no")
    void deleteByPostID(@Param("no") int no);

}
