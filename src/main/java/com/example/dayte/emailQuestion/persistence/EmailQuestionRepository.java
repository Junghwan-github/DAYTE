package com.example.dayte.emailQuestion.persistence;

import com.example.dayte.emailQuestion.domain.EmailQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailQuestionRepository extends JpaRepository<EmailQuestion, Integer> {
}
