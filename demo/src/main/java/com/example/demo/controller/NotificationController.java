package com.example.demo.controller;

import com.example.demo.model.Notification;
import com.example.demo.model.User;
import com.example.demo.service.NotificationService;
import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserService userService;

    // Get all notifications
    @GetMapping
    public ResponseEntity<List<Notification>> getAllNotifications() {
        List<Notification> notifications = notificationService.getAllNotifications();
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }

    // Get all notifications for a specific user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Notification>> getNotificationsByUser(@PathVariable String userId) {
        Optional<User> user = userService.getUserById(userId);
        if (user.isPresent()) {
            List<Notification> notifications = notificationService.getNotificationsByUser(user.get());
            return new ResponseEntity<>(notifications, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Get unread notifications for a specific user
    @GetMapping("/user/{userId}/unread")
    public ResponseEntity<List<Notification>> getUnreadNotificationsByUser(@PathVariable String userId) {
        Optional<User> user = userService.getUserById(userId);
        if (user.isPresent()) {
            List<Notification> notifications = notificationService.getUnreadNotificationsByUser(user.get());
            return new ResponseEntity<>(notifications, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Create a new notification
    @PostMapping
    public ResponseEntity<Notification> createNotification(@RequestBody Notification notification) {
        Notification createdNotification = notificationService.createNotification(notification);
        return new ResponseEntity<>(createdNotification, HttpStatus.CREATED);
    }

    // Mark a notification as read
    @PutMapping("/{id}/read")
    public ResponseEntity<Void> markAsRead(@PathVariable String id) {
        Optional<Notification> existingNotification = notificationService.getNotificationById(id);
        if (existingNotification.isPresent()) {
            notificationService.markAsRead(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Delete a notification by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable String id) {
        Optional<Notification> existingNotification = notificationService.getNotificationById(id);
        if (existingNotification.isPresent()) {
            notificationService.deleteNotification(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
