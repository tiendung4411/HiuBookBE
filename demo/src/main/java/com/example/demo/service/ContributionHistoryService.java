package com.example.demo.service;

import com.example.demo.model.ContributionHistory;
import com.example.demo.repository.ContributionHistoryRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContributionHistoryService {

    @Autowired
    private ContributionHistoryRepository contributionHistoryRepository;
    @Autowired
    private UserRepository userRepository; // Inject UserRepository

    public ContributionHistory createContributionHistory(ContributionHistory contributionHistory) {
        return contributionHistoryRepository.save(contributionHistory);
    }

    public Optional<ContributionHistory> getContributionHistoryById(String contributionId) {
        return contributionHistoryRepository.findById(contributionId);
    }

    public List<ContributionHistory> getAllContributionHistories() {
        return contributionHistoryRepository.findAll();
    }

    public List<ContributionHistory> getContributionHistoriesByUserId(String userId) {
        Optional<User> userOptional = userRepository.findById(userId); // Tìm User theo userId
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return contributionHistoryRepository.findByUser(user); // Truyền User object
        } else {
            // Xử lý trường hợp không tìm thấy User
            return null; // Hoặc throw một exception
        }
    }

    public List<ContributionHistory> getContributionHistoriesBySummaryId(String summaryId) {
        return contributionHistoryRepository.findBySummaryId(summaryId);
    }

    public void deleteContributionHistory(String contributionId) {
        contributionHistoryRepository.deleteById(contributionId);
    }
}
