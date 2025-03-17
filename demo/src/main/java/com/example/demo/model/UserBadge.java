package com.example.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Table(name = "user_badges")
@Data
@NoArgsConstructor
public class UserBadge {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)

    private String userBadgeId; // Primary key

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Reference to the user who earned the badge

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "badge_id", nullable = false)
    private Badge badge; // Reference to the badge awarded

    // @CreationTimestamp
    // private LocalDateTime awardedAt; // Timestamp for when the badge was awarded
}
