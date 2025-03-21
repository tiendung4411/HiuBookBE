package com.example.demo.service;

import com.example.demo.dto.SummaryDTO;
import com.example.demo.model.ReadHistory;
import com.example.demo.model.Summary;
import com.example.demo.model.User;
import com.example.demo.repository.ReadHistoryRepository;
import com.example.demo.repository.SummaryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SummaryService {

    @Autowired
    private SummaryRepository summaryRepository;

    @Autowired
    private ReadHistoryRepository readHistoryRepository;

        
    @Autowired
    private UserService userService;

    
    public void logReadHistory(User user, Summary summary) {
        // Create a new ReadHistory entry
        ReadHistory readHistory = new ReadHistory();
        readHistory.setUser(user);
        readHistory.setSummary(summary);

        // Save the read history to the database
        readHistoryRepository.save(readHistory);
    }
    public Summary createSummary(Summary summary) {
        return summaryRepository.save(summary);
    }

    public void deleteSummary(String id) {
        summaryRepository.deleteById(id);
    }

    public List<SummaryDTO> getAllSummaries() {
        List<Summary> summaries = summaryRepository.findAll();
        List<SummaryDTO> summaryDTOs = new ArrayList<>();
    
        for (Summary summary : summaries) {
            SummaryDTO dto = new SummaryDTO();
            dto.setSummaryId(summary.getSummaryId());
            dto.setTitle(summary.getTitle());
            dto.setContent(summary.getContent());
            dto.setSummaryContent(summary.getSummaryContent());
            dto.setStatus(summary.getStatus());
            dto.setMethod(summary.getMethod());
            dto.setGrade(summary.getGrade());
            dto.setReadCount(summary.getReadCount());
            dto.setCreatedByUserId(summary.getCreatedBy().getUserId()); // Just the User ID
            dto.setCreatedAt(summary.getCreatedAt());  // Add createdAt to DTO
            dto.setApprovedAt(summary.getApprovedAt());  // Add approvedAt to DTO
            summaryDTOs.add(dto);
        }
    
        return summaryDTOs;
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

    public List<SummaryDTO> getSummariesByContributor(String userId) {
        Optional<User> optionalUser = userService.getUserById(userId);
        if (optionalUser.isPresent()) {
            List<Summary> summaries = summaryRepository.findByCreatedBy(optionalUser.get());
            List<SummaryDTO> summaryDTOs = new ArrayList<>();
    
            for (Summary summary : summaries) {
                SummaryDTO dto = new SummaryDTO();
                dto.setSummaryId(summary.getSummaryId());
                dto.setTitle(summary.getTitle());
                dto.setContent(summary.getContent());
                dto.setSummaryContent(summary.getSummaryContent());
                dto.setStatus(summary.getStatus());
                dto.setMethod(summary.getMethod());
                dto.setGrade(summary.getGrade());
                dto.setCreatedAt(summary.getCreatedAt());  // Add createdAt to DTO
                dto.setApprovedAt(summary.getApprovedAt());
                dto.setCreatedByUserId(summary.getCreatedBy().getUserId()); // Set User ID
                summaryDTOs.add(dto);
            }
    
            return summaryDTOs;
        } else {
            return new ArrayList<>();
        }
    }
    public Summary updateSummary(Summary summary) {
        return summaryRepository.save(summary);  // Save the updated summary to the database
    }
    public List<Summary> searchSummaries(String searchTerm, String status, String method, String grade) {
        return summaryRepository.findByTitleContainingOrContentContainingOrStatusContainingOrMethodContainingOrGradeContaining(
            searchTerm, searchTerm, status, method, grade
        );
    }
    public List<Summary> getTop10MostReadSummaries() {
        return summaryRepository.findTop10ByOrderByReadCountDesc(); // Fetch top 10 summaries sorted by readCount
    }    
    public List<SummaryDTO> getSummariesByGrade(String grade) {
        List<Summary> summaries = summaryRepository.findByGrade(grade);
        List<SummaryDTO> summaryDTOs = new ArrayList<>();
    
        for (Summary summary : summaries) {
            SummaryDTO dto = new SummaryDTO();
            dto.setSummaryId(summary.getSummaryId());
            dto.setTitle(summary.getTitle());
            dto.setContent(summary.getContent());
            dto.setSummaryContent(summary.getSummaryContent());
            dto.setStatus(summary.getStatus());
            dto.setMethod(summary.getMethod());
            dto.setGrade(summary.getGrade());
           
            dto.setCreatedAt(summary.getCreatedAt());  // Add createdAt to DTO
            dto.setApprovedAt(summary.getApprovedAt());
            dto.setCreatedByUserId(summary.getCreatedBy().getUserId()); // Set User ID
            summaryDTOs.add(dto);
        }
    
        return summaryDTOs;
    }
   public List<Summary> searchSummariesByTitle(String searchTerm) {
        return summaryRepository.findByTitleContainingIgnoreCase(searchTerm);  // Case-insensitive search
    }
    public List<Summary> getSummariesByMethod(String method) {
        return summaryRepository.findByMethod(method); // Fetch summaries by method
    }
}
