package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "summary_history")
@Data
@NoArgsConstructor
public class SummaryHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyId; // Unique ID for the history entry

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id")
    private SummarySession session; // Link to a specific session

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "summary_id")
    private Summary summary; // Link to a specific summary

    @Column(nullable = false)
    private String method; // The summarization method used (e.g., "PHOBERT" or "T5_DIEN_GIAI")

    @Column(nullable = false)
    private String status; // The status of the summary (e.g., "PENDING", "APPROVED", "REJECTED")

    @Column(nullable = false)
    private String summaryContent; // The generated summary content

    @Column(nullable = true)
    private String imageUrl; // Optional image URL associated with this summary
    
    // Other relevant fields such as date of creation or modification can be added
}
