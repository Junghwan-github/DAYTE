package com.example.dayte.admin.contents.persistence;

import com.example.dayte.admin.contents.domain.AdminContents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminContentsRepository extends JpaRepository<AdminContents, String> {

}
