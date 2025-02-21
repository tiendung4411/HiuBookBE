package com.example.demo.repository;

import com.example.demo.model.ContributionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.User;
import java.util.List;

@Repository
public interface ContributionHistoryRepository extends JpaRepository<ContributionHistory, String> {

    List<ContributionHistory> findByUser(User user); // Tìm kiếm theo User object

    List<ContributionHistory> findBySummaryId(String summaryId);
}
