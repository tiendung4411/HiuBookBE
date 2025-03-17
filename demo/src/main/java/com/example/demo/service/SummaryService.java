package com.example.demo.service;

import com.example.demo.model.Summary;
import com.example.demo.model.User;
import com.example.demo.repository.SummaryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SummaryService {

    @Autowired
    private SummaryRepository summaryRepository;

    @Autowired
    private UserService userService;

    public Summary createSummary(Summary summary) {
        return summaryRepository.save(summary);
    }

    public void deleteSummary(String id) {
        summaryRepository.deleteById(id);
    }

    public List<Summary> getAllSummaries() {
        return summaryRepository.findAll();
    }

    public Optional<Summary> getSummaryById(String id) {
        return summaryRepository.findById(id);
    }

    public void updateSummaryStatus(String id, String status) {
        Optional<Summary> optionalSummary = getSummaryById(id);
        if (optionalSummary.isPresent()) {
            Summary summary = optionalSummary.get();
            summary.setStatus(status);
            summaryRepository.save(summary);
        }
    }

    public List<Summary> getSummariesByStatus(String status) {
        return summaryRepository.findByStatus(status);
    }

    public List<Summary> getSummariesByUser(String userId) {
        Optional<User> optionalUser = userService.getUserById(userId);
        if (optionalUser.isPresent()) {
            return summaryRepository.findByCreatedBy(optionalUser.get());
        }
        return List.of();
    }

    public List<Summary> getSummariesByGrade(String grade) {
        return summaryRepository.findByGrade(grade);
    }

    public List<Summary> getSummariesByMethod(String method) {
        return summaryRepository.findByMethod(method); // Fetch summaries by method
    }
}
