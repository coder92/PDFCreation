package com.example.demo.repository;

import com.example.demo.model.UserAskedQuestionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAskedQuestionRepository extends JpaRepository<UserAskedQuestionModel, Long> {
}
