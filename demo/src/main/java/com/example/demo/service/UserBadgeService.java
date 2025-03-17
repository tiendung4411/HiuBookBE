package com.example.demo.service;

import com.example.demo.model.UserBadge;
import com.example.demo.model.User;
import com.example.demo.model.Badge;
import com.example.demo.repository.UserBadgeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserBadgeService {

    @Autowired
    private UserBadgeRepository userBadgeRepository;

    /**
     * Award a badge to a user.
     */
    public UserBadge awardBadge(UserBadge userBadge) {
        return userBadgeRepository.save(userBadge);
    }

    /**
     * Get all badges earned by a specific user.
     */
    public List<UserBadge> getBadgesByUser(User user) {
        return userBadgeRepository.findByUser(user);
    }

    /**
     * Get all users who earned a specific badge.
     */
    public List<UserBadge> getUsersByBadge(Badge badge) {
        return userBadgeRepository.findByBadge(badge);
    }

    /**
     * Delete an awarded badge by ID.
     */
    public void deleteUserBadge(String id) {
        userBadgeRepository.deleteById(id);
    }

    /**
     * Get a specific awarded badge by ID.
     */
    public Optional<UserBadge> getUserBadgeById(String id) {
        return userBadgeRepository.findById(id);
    }
}
