package com.example.demo.service;

import com.example.demo.model.Summary;
import com.example.demo.model.SummaryHistory;
import com.example.demo.model.SummarySession;
import com.example.demo.repository.SummaryHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SummaryHistoryService {

    @Autowired
    private SummaryHistoryRepository summaryHistoryRepository;

    // Save a new Summary History entry
    public SummaryHistory saveSummaryHistory(Summary summary, SummarySession session, String method) {
        SummaryHistory history = new SummaryHistory();
        history.setSession(session); // Link to the session
        history.setSummary(summary); // Link to the summary generated
        history.setMethod(method); // The method used for summarization (e.g., PHOBERT, T5)
        history.setSummaryContent(summary.getSummaryContent()); // The generated summary content
        history.setStatus("PENDING"); // Initially, set the status to "PENDING"
        return summaryHistoryRepository.save(history);
    }

    // Update the status of a summary history entry (e.g., "APPROVED")
    public SummaryHistory updateSummaryHistoryStatus(Long historyId, String status) {
        SummaryHistory history = summaryHistoryRepository.findById(historyId)
                .orElseThrow(() -> new RuntimeException("History not found"));
        history.setStatus(status); // Set status to either "APPROVED" or "REJECTED"
        return summaryHistoryRepository.save(history);
    }
}
