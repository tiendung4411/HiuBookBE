package com.example.demo.repository;

import com.example.demo.model.SummaryHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SummaryHistoryRepository extends JpaRepository<SummaryHistory, Long> {
    // You can add custom queries if necessary
}
