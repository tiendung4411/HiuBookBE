package com.example.demo.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    // hide it from the Swagger UI
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String userId;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password; // Will store the hashed password

    @Column(nullable = false)
    private String role; // Roles: CHILD, CONTRIBUTOR, ADMIN

    @Column(unique = true, nullable = false)
    private String email; // For communication and notifications

    private String fullName; // Optional field for personalization

    private String phoneNumber; // Optional field for contributors/admins

    private String avatarUrl; // Optional field for contributors/admins to upload their avatar

    private boolean isActive = true; // To manage account status (active/inactive)

    // @CreationTimestamp
    // @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    // private LocalDateTime createdAt;

    // @UpdateTimestamp
    // @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    // private LocalDateTime updatedAt;

    /**
     * Hash the user's password using BCrypt.
     */
    public void setPassword(String rawPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.password = encoder.encode(rawPassword);
    }

    /**
     * Check if a raw password matches the hashed password.
     */
    public boolean checkPassword(String rawPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(rawPassword, this.password);
    }

    // get AvatarUrl
    public String getAvatarUrl() {
        return avatarUrl;
    }
}
