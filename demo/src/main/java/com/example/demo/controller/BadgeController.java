package com.example.demo.controller;

import com.example.demo.model.Badge;
import com.example.demo.service.BadgeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/badges")
public class BadgeController {

    @Autowired
    private BadgeService badgeService;

    // Get all badges
    @GetMapping
    public ResponseEntity<List<Badge>> getAllBadges() {
        List<Badge> badges = badgeService.getAllBadges();
        return new ResponseEntity<>(badges, HttpStatus.OK);
    }

    // Get a specific badge by ID
    @GetMapping("/{id}")
    public ResponseEntity<Badge> getBadgeById(@PathVariable String id) {
        Optional<Badge> badge = badgeService.getBadgeById(id);
        return badge.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get a specific badge by name
    @GetMapping("/name/{name}")
    public ResponseEntity<Badge> getBadgeByName(@PathVariable String name) {
        Optional<Badge> badge = badgeService.getBadgeByName(name);
        return badge.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Create a new badge
    @PostMapping
    public ResponseEntity<Badge> createBadge(@RequestBody Badge badge) {
        Badge createdBadge = badgeService.createBadge(badge);
        return new ResponseEntity<>(createdBadge, HttpStatus.CREATED);
    }

    // Delete a specific badge by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBadge(@PathVariable String id) {
        Optional<Badge> existingBadge = badgeService.getBadgeById(id);
        if (existingBadge.isPresent()) {
            badgeService.deleteBadge(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
