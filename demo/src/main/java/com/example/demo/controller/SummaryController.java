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

    @GetMapping
    public ResponseEntity<List<Summary>> getAllSummaries() {
        List<Summary> summaries = summaryService.getAllSummaries();
        return new ResponseEntity<>(summaries, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Summary> getSummaryById(@PathVariable String id) {
        Optional<Summary> summary = summaryService.getSummaryById(id);
        return summary.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Summary> createSummary(@RequestBody Summary summary) {
        Summary createdSummary = summaryService.createSummary(summary);
        return new ResponseEntity<>(createdSummary, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Summary> updateSummary(@PathVariable String id, @RequestBody Summary summary) {
        Optional<Summary> existingSummary = summaryService.getSummaryById(id);
        if (existingSummary.isPresent()) {
            summary.setSummaryId(id); // Đảm bảo ID không bị thay đổi
            Summary updatedSummary = summaryService.createSummary(summary);
            return new ResponseEntity<>(updatedSummary, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSummary(@PathVariable String id) {
        summaryService.deleteSummary(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Các API khác liên quan đến Summary (ví dụ: duyệt/từ chối, lấy theo createdBy,
    // status)
}
