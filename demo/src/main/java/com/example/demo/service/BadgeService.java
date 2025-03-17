package com.example.demo.service;

import com.example.demo.model.Badge;
import com.example.demo.repository.BadgeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BadgeService {

    @Autowired
    private BadgeRepository badgeRepository;

    /**
     * Create a new badge.
     */
    public Badge createBadge(Badge badge) {
        return badgeRepository.save(badge);
    }

    /**
     * Get all badges.
     */
    public List<Badge> getAllBadges() {
        return badgeRepository.findAll();
    }

    /**
     * Get a badge by ID.
     */
    public Optional<Badge> getBadgeById(String id) {
        return badgeRepository.findById(id);
    }

    /**
     * Get a badge by name.
     */
    public Optional<Badge> getBadgeByName(String name) {
        return badgeRepository.findByName(name);
    }

    /**
     * Delete a badge by ID.
     */
    public void deleteBadge(String id) {
        badgeRepository.deleteById(id);
    }
}
