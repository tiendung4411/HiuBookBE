package com.example.demo.controller;

import com.example.demo.model.ContributionHistory;
import com.example.demo.service.ContributionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contribution-histories")
public class ContributionHistoryController {

    @Autowired
    private ContributionHistoryService contributionHistoryService;

    @GetMapping
    public ResponseEntity<List<ContributionHistory>> getAllContributionHistories() {
        List<ContributionHistory> contributionHistories = contributionHistoryService.getAllContributionHistories();
        return new ResponseEntity<>(contributionHistories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContributionHistory> getContributionHistoryById(@PathVariable String id) {
        Optional<ContributionHistory> contributionHistory = contributionHistoryService.getContributionHistoryById(id);
        return contributionHistory.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ContributionHistory> createContributionHistory(
            @RequestBody ContributionHistory contributionHistory) {
        ContributionHistory createdContributionHistory = contributionHistoryService
                .createContributionHistory(contributionHistory);
        return new ResponseEntity<>(createdContributionHistory, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContributionHistory(@PathVariable String id) {
        contributionHistoryService.deleteContributionHistory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Các API khác liên quan đến ContributionHistory (ví dụ: lấy theo userId,
    // summaryId)
}
