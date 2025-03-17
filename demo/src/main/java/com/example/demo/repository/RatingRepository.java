package com.example.demo.repository;

import com.example.demo.model.Rating;
import com.example.demo.model.Summary;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, String> {

    List<Rating> findBySummary(Summary summary); // Find ratings for a specific summary

    List<Rating> findByUser(User user); // Find ratings given by a specific user
}
