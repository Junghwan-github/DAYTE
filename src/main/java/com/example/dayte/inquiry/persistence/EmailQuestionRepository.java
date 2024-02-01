package com.example.dayte.inquiry.persistence;

import com.example.dayte.inquiry.domain.EmailQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailQuestionRepository extends JpaRepository<EmailQuestion, Integer> {
}
