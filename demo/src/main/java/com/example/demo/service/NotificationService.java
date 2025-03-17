package com.example.demo.service;

import com.example.demo.model.Notification;
import com.example.demo.model.User;
import com.example.demo.repository.NotificationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    /**
     * Create a new notification.
     */
    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    /**
     * Get all notifications.
     */
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    /**
     * Get a notification by ID.
     */
    public Optional<Notification> getNotificationById(String id) {
        return notificationRepository.findById(id);
    }

    /**
     * Get all notifications for a specific user.
     */
    public List<Notification> getNotificationsByUser(User user) {
        return notificationRepository.findByUser(user);
    }

    /**
     * Get unread notifications for a specific user.
     */
    public List<Notification> getUnreadNotificationsByUser(User user) {
        return notificationRepository.findByUserAndIsRead(user, false);
    }

    /**
     * Mark a notification as read.
     */
    public void markAsRead(String id) {
        Optional<Notification> optionalNotification = getNotificationById(id);
        if (optionalNotification.isPresent()) {
            Notification notification = optionalNotification.get();
            notification.setIsRead(true);
            notificationRepository.save(notification);
        }
    }

    /**
     * Delete a notification by ID.
     */
    public void deleteNotification(String id) {
        notificationRepository.deleteById(id);
    }
}
