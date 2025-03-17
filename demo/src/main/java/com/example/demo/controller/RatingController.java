package com.example.demo.controller;

import com.example.demo.model.Rating;
import com.example.demo.model.Summary;
import com.example.demo.model.User;
import com.example.demo.service.RatingService;
import com.example.demo.service.SummaryService;
import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @Autowired
    private SummaryService summaryService;

    @Autowired
    private UserService userService;

    // Get all ratings
    @GetMapping
    public ResponseEntity<List<Rating>> getAllRatings() {
        List<Rating> ratings = ratingService.getAllRatings();
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }

    // Get a specific rating by ID
    @GetMapping("/{id}")
    public ResponseEntity<Rating> getRatingById(@PathVariable String id) {
        Optional<Rating> rating = ratingService.getRatingById(id);
        return rating.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get all ratings for a specific summary
    @GetMapping("/summary/{summaryId}")
    public ResponseEntity<List<Rating>> getRatingsBySummary(@PathVariable String summaryId) {
        Optional<Summary> summary = summaryService.getSummaryById(summaryId);
        if (summary.isPresent()) {
            List<Rating> ratings = ratingService.getRatingsBySummary(summary.get());
            return new ResponseEntity<>(ratings, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Get all ratings given by a specific user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUser(@PathVariable String userId) {
        Optional<User> user = userService.getUserById(userId);
        if (user.isPresent()) {
            List<Rating> ratings = ratingService.getRatingsByUser(user.get());
            return new ResponseEntity<>(ratings, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Create a new rating
    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
        Rating createdRating = ratingService.createRating(rating);
        return new ResponseEntity<>(createdRating, HttpStatus.CREATED);
    }

    // Delete a rating by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable String id) {
        Optional<Rating> existingRating = ratingService.getRatingById(id);
        if (existingRating.isPresent()) {
            ratingService.deleteRating(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
