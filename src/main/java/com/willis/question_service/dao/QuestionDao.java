package com.willis.question_service.dao;


import com.willis.question_service.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionDao extends JpaRepository<Question,Integer> {
    List<Question> findByCategory(String category);

    @Query(value="SELECT * FROM question q Where q.category=:category ORDER BY RANDOM()", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category);
}
