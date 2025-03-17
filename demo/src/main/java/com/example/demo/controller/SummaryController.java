package com.example.demo.controller;

import com.example.demo.model.Summary;
import com.example.demo.service.SummaryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/summaries")
public class SummaryController {

    @Autowired
    private SummaryService summaryService;

    // Get all summaries
    @GetMapping
    public ResponseEntity<List<Summary>> getAllSummaries() {
        List<Summary> summaries = summaryService.getAllSummaries();
        return new ResponseEntity<>(summaries, HttpStatus.OK);
    }

    // Get a summary by ID
    @GetMapping("/{id}")
    public ResponseEntity<Summary> getSummaryById(@PathVariable String id) {
        Optional<Summary> summary = summaryService.getSummaryById(id);
        return summary.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Create a new summary
    @PostMapping
    public ResponseEntity<Summary> createSummary(@RequestBody Summary summary) {
        Summary createdSummary = summaryService.createSummary(summary);
        return new ResponseEntity<>(createdSummary, HttpStatus.CREATED);
    }

    // Update an existing summary
    @PutMapping("/{id}")
    public ResponseEntity<Summary> updateSummary(@PathVariable String id, @RequestBody Summary summary) {
        Optional<Summary> existingSummary = summaryService.getSummaryById(id);
        if (existingSummary.isPresent()) {
            summary.setSummaryId(id); // Ensure the ID is not changed
            Summary updatedSummary = summaryService.createSummary(summary);
            return new ResponseEntity<>(updatedSummary, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a summary
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSummary(@PathVariable String id) {
        summaryService.deleteSummary(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get all summaries by status (e.g., PENDING, APPROVED)
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Summary>> getSummariesByStatus(@PathVariable String status) {
        List<Summary> summaries = summaryService.getSummariesByStatus(status);
        return new ResponseEntity<>(summaries, HttpStatus.OK);
    }

    // Get all summaries created by a specific user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Summary>> getSummariesByUser(@PathVariable String userId) {
        List<Summary> summaries = summaryService.getSummariesByUser(userId);
        return new ResponseEntity<>(summaries, HttpStatus.OK);
    }

    // Approve or reject a summary
    @PutMapping("/{id}/status")
    public ResponseEntity<Void> updateSummaryStatus(@PathVariable String id, @RequestParam String status) {
        summaryService.updateSummaryStatus(id, status);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Get all summaries by grade level
    @GetMapping("/grade/{grade}")
    public ResponseEntity<List<Summary>> getSummariesByGrade(@PathVariable String grade) {
        List<Summary> summaries = summaryService.getSummariesByGrade(grade);
        return new ResponseEntity<>(summaries, HttpStatus.OK);
    }

    @GetMapping("/method/{method}")
    public ResponseEntity<List<Summary>> getSummariesByMethod(@PathVariable String method) {
        List<Summary> summaries = summaryService.getSummariesByMethod(method);
        return new ResponseEntity<>(summaries, HttpStatus.OK);
    }
}
