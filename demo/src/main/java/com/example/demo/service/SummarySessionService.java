package com.example.demo.service;

import com.example.demo.model.SummarySession;
import com.example.demo.model.User;
import com.example.demo.repository.SummarySessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SummarySessionService {

    @Autowired
    private SummarySessionRepository summarySessionRepository;

    // Create a new Summary Session
    public SummarySession createSummarySession(String title, String content, User user) {
        SummarySession session = new SummarySession();
        session.setTitle(title);
        session.setContent(content);
        session.setCreatedBy(user);
        session.setStatus("PENDING"); // Initially, the status is "Pending"
        // Save the session to the database
        return summarySessionRepository.save(session);
    }
}
