package com.example.demo.controller;

import com.example.demo.model.SummarySubject;
import com.example.demo.service.SummarySubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/summary-subjects")
public class SummarySubjectController {

    @Autowired
    private SummarySubjectService summarySubjectService;

    @GetMapping
    public ResponseEntity<List<SummarySubject>> getAllSummarySubjects() {
        List<SummarySubject> summarySubjects = summarySubjectService.getAllSummarySubjects();
        return new ResponseEntity<>(summarySubjects, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SummarySubject> getSummarySubjectById(@PathVariable String id) {
        Optional<SummarySubject> summarySubject = summarySubjectService.getSummarySubjectById(id);
        return summarySubject.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<SummarySubject> createSummarySubject(@RequestBody SummarySubject summarySubject) {
        SummarySubject createdSummarySubject = summarySubjectService.createSummarySubject(summarySubject);
        return new ResponseEntity<>(createdSummarySubject, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSummarySubject(@PathVariable String id) {
        summarySubjectService.deleteSummarySubject(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/summary/{summaryId}")
    public ResponseEntity<List<SummarySubject>> getSummarySubjectsBySummaryId(@PathVariable String summaryId) {
        List<SummarySubject> summarySubjects = summarySubjectService.getSummarySubjectsBySummaryId(summaryId);
        return new ResponseEntity<>(summarySubjects, HttpStatus.OK);
    }
}
