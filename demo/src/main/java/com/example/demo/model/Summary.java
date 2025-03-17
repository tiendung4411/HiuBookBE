package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "summaries")
@Data
@NoArgsConstructor
public class Summary {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String summaryId;

    @Column(nullable = false)
    private String title; // Title of the summary

    @Column(nullable = false, length = 5000)
    private String content; // Content of the summary

    @Column(nullable = true)
    private String imageUrl; // Optional image URL for the summary

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy; // Reference to the user who created this summary

    @Column(nullable = false)
    private String status = "PENDING"; // Status: PENDING, APPROVED, REJECTED

    @Column(nullable = true)
    private String grade; // Optional field for grade (e.g., "Grade 5")

    @Column(nullable = false)
    private String method; // Summarization method: PHOBERT or T5_DIEN_GIAI

    // @CreationTimestamp
    // private LocalDateTime createdAt;

    // @UpdateTimestamp
    // private LocalDateTime updatedAt;

}
