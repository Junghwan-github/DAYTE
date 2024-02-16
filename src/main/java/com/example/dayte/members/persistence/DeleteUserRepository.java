package com.example.dayte.members.persistence;

import com.example.dayte.members.domain.DeleteUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DeleteUserRepository extends JpaRepository<DeleteUser, Long> {

    List<DeleteUser> findAllByDeleteDateLessThanEqual(LocalDate deleteDate);

    Optional<DeleteUser> findByUserEmail(String userEmail);

}
