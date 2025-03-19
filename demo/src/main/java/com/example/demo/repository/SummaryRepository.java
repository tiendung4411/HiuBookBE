package com.example.demo.repository;

import com.example.demo.model.Summary;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SummaryRepository extends JpaRepository<Summary, String> {

    List<Summary> findByCreatedBy(User createdBy);

    List<Summary> findByStatus(String status);

    List<Summary> findByGrade(String grade);
    List<Summary> findTop10ByOrderByReadCountDesc(); // Fetch top 10 summaries ordered by readCount
    List<Summary> findByTitleContainingOrContentContainingOrStatusContainingOrMethodContainingOrGradeContaining(
        String title, String content, String status, String method, String grade
    );

    List<Summary> findByTitleContainingIgnoreCase(@Param("title") String title);

    List<Summary> findByMethod(String method); // Filter summaries by method (PHOBERT or T5_DIEN_GIAI)
}
