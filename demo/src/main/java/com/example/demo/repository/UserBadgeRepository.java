package com.example.demo.repository;

import com.example.demo.model.UserBadge;
import com.example.demo.model.User;
import com.example.demo.model.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserBadgeRepository extends JpaRepository<UserBadge, String> {

    List<UserBadge> findByUser(User user); // Find all badges earned by a specific user

    List<UserBadge> findByBadge(Badge badge); // Find all users who earned a specific badge
}
