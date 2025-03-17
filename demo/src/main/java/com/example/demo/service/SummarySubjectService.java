// package com.example.demo.service;

// import com.example.demo.model.Summary;
// import com.example.demo.model.SummarySubject;
// import com.example.demo.repository.SummaryRepository;
// import com.example.demo.repository.SummarySubjectRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.List;
// import java.util.Optional;

// @Service
// public class SummarySubjectService {

// @Autowired
// private SummarySubjectRepository summarySubjectRepository;

// public SummarySubject createSummarySubject(SummarySubject summarySubject) {
// return summarySubjectRepository.save(summarySubject);
// }

// public Optional<SummarySubject> getSummarySubjectById(String
// summarySubjectId) {
// return summarySubjectRepository.findById(summarySubjectId);
// }

// public List<SummarySubject> getAllSummarySubjects() {
// return summarySubjectRepository.findAll();
// }

// @Autowired
// private SummaryRepository summaryRepository; // Cần SummaryRepository

// public List<SummarySubject> getSummarySubjectsBySummaryId(String summaryId) {
// Optional<Summary> summaryOptional = summaryRepository.findById(summaryId);
// if (summaryOptional.isPresent()) {
// Summary summary = summaryOptional.get();
// return summarySubjectRepository.findBySummary(summary); // Sử dụng
// findBySummary
// } else {
// // Xử lý trường hợp không tìm thấy Summary
// return null; // Hoặc throw một exception
// }
// }

// public void deleteSummarySubject(String summarySubjectId) {
// summarySubjectRepository.deleteById(summarySubjectId);
// }
// }
