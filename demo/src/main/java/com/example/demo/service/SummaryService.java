package com.example.demo.service;

import com.example.demo.model.Summary;
import com.example.demo.model.User;
import com.example.demo.repository.SummaryRepository;
import com.example.demo.repository.UserRepository; // Cần UserRepository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SummaryService {

    @Autowired
    private SummaryRepository summaryRepository;

    @Autowired
    private UserRepository userRepository; // Inject UserRepository

    public Summary createSummary(Summary summary) {
        // Thêm logic nghiệp vụ (ví dụ: set created_at, updated_at)
        return summaryRepository.save(summary);
    }

    public Optional<Summary> getSummaryById(String summaryId) {
        return summaryRepository.findById(summaryId);
    }

    public List<Summary> getAllSummaries() {
        return summaryRepository.findAll();
    }

    public List<Summary> getSummariesByCreatedBy(String createdBy) {
        Optional<User> userOptional = userRepository.findById(createdBy); // Tìm User theo userId
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return summaryRepository.findByCreator(user); // Truyền User object
        } else {
            // Xử lý trường hợp không tìm thấy User
            return null; // Hoặc throw một exception
        }
    }

    public List<Summary> getSummariesByStatus(String status) {
        return summaryRepository.findByStatus(status);
    }

    public void deleteSummary(String summaryId) {
        summaryRepository.deleteById(summaryId);
    }

    // Các phương thức nghiệp vụ khác liên quan đến Summary (ví dụ: duyệt/từ chối)
}
