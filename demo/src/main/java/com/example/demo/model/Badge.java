package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "badges")
@Data
@NoArgsConstructor
public class Badge {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String badgeId; // Primary key

    @Column(nullable = false, unique = true)
    private String name; // Unique name of the badge (e.g., "Top Contributor")

    @Column(nullable = false, length = 500)
    private String description; // Description of what the badge represents

    // @CreationTimestamp
    // private LocalDateTime createdAt; // Timestamp for when the badge was created
}
