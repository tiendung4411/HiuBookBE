package com.example.demo.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
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

    private String title;
    private String content;
    private String imageUrl;
    private String status;

    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "userId") // Sửa referencedColumnName thành "userId"
    private User creator;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
