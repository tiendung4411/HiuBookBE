package com.example.demo.repository;

import com.example.demo.model.Summary;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SummaryRepository extends JpaRepository<Summary, String> {
    List<Summary> findByCreator(User creator); // Tìm kiếm theo User object

    List<Summary> findByStatus(String status);
}
