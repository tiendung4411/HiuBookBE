package com.example.demo.service;

import com.example.demo.model.Rating;
import com.example.demo.model.Summary;
import com.example.demo.model.User;
import com.example.demo.repository.RatingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    /**
     * Create a new rating.
     */
    public Rating createRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    /**
     * Get all ratings.
     */
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    /**
     * Get a rating by ID.
     */
    public Optional<Rating> getRatingById(String id) {
        return ratingRepository.findById(id);
    }

    /**
     * Get all ratings for a specific summary.
     */
    public List<Rating> getRatingsBySummary(Summary summary) {
        return ratingRepository.findBySummary(summary);
    }

    /**
     * Get all ratings given by a specific user.
     */
    public List<Rating> getRatingsByUser(User user) {
        return ratingRepository.findByUser(user);
    }

    /**
     * Delete a rating by ID.
     */
    public void deleteRating(String id) {
        ratingRepository.deleteById(id);
    }
}
