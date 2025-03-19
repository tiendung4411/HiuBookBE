package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "summary_sessions")
@Data
@NoArgsConstructor
public class SummarySession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sessionId; // Unique ID for the session

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy; // User who initiated this summary session

    @Column(nullable = false)
    private String title; // Title of the original summary

    @Column(nullable = false)
    private String content; // Original content to be summarized

    @Column(nullable = true)
    private String status = "PENDING"; // Status of the session

    @Column(nullable = false)
    private String grade; // Grade if applicable

    // You can add a timestamp or other metadata fields as necessary

}
