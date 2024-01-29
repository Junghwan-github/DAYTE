package com.example.dayte.schedule.persistence;

import com.example.dayte.schedule.domain.Contents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ContentsRepository extends JpaRepository<Contents,String> {

    List<Contents> findAllByBusinessNameContaining(String businessName);
}
