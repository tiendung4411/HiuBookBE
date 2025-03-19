package com.example.demo.repository;

import com.example.demo.model.SummarySession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SummarySessionRepository extends JpaRepository<SummarySession, Long> {
    // You can add custom queries if necessary
}
