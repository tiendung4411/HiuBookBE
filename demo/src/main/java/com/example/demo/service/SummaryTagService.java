package com.example.demo.service;

import com.example.demo.model.SummaryTag;
import com.example.demo.model.Summary;
import com.example.demo.model.Tag;
import com.example.demo.repository.SummaryTagRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SummaryTagService {

    @Autowired
    private SummaryTagRepository summaryTagRepository;

    /**
     * Create a new summary-tag association.
     */
    public SummaryTag createSummaryTag(SummaryTag summaryTag) {
        return summaryTagRepository.save(summaryTag);
    }

    /**
     * Get all tags for a specific summary.
     */
    public List<SummaryTag> getTagsBySummary(Summary summary) {
        return summaryTagRepository.findBySummary(summary);
    }

    /**
     * Get all summaries associated with a specific tag.
     */
    public List<SummaryTag> getSummariesByTag(Tag tag) {
        return summaryTagRepository.findByTag(tag);
    }

    /**
     * Delete a specific summary-tag association by ID.
     */
    public void deleteSummaryTag(String id) {
        summaryTagRepository.deleteById(id);
    }

    /**
     * Get a specific summary-tag association by ID.
     */
    public Optional<SummaryTag> getSummaryTagById(String id) {
        return summaryTagRepository.findById(id);
    }
}
