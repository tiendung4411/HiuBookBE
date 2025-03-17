package com.example.demo.controller;

import com.example.demo.model.UserBadge;
import com.example.demo.model.User;
import com.example.demo.model.Badge;
import com.example.demo.service.UserService;
import com.example.demo.service.BadgeService;
import com.example.demo.service.UserBadgeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user-badges")
public class UserBadgeController {

    @Autowired
    private UserBadgeService userBadgeService;

    @Autowired
    private UserService userService;

    @Autowired
    private BadgeService badgeService;

    // Get all badges earned by a specific user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserBadge>> getBadgesByUser(@PathVariable String userId) {
        Optional<User> user = userService.getUserById(userId);
        if (user.isPresent()) {
            List<UserBadge> badges = userBadgeService.getBadgesByUser(user.get());
            return new ResponseEntity<>(badges, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Get all users who earned a specific badge
    @GetMapping("/badge/{badgeId}")
    public ResponseEntity<List<UserBadge>> getUsersByBadge(@PathVariable String badgeId) {
        Optional<Badge> badge = badgeService.getBadgeById(badgeId);
        if (badge.isPresent()) {
            List<UserBadge> users = userBadgeService.getUsersByBadge(badge.get());
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Award a badge to a user
    @PostMapping
    public ResponseEntity<UserBadge> awardUserWithABadge(@RequestBody UserBadge userBadge) {
        UserBadge createdUserBadge = userBadgeService.awardBadge(userBadge);
        return new ResponseEntity<>(createdUserBadge, HttpStatus.CREATED);
    }

    // Delete an awarded badge by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserAwardedWithABadge(@PathVariable String id) {
        Optional<UserBadge> existingUserBadge = userBadgeService.getUserBadgeById(id);
        if (existingUserBadge.isPresent()) {
            userBadgeService.deleteUserBadge(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
