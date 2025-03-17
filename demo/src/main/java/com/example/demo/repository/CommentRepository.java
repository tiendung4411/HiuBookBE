package com.example.demo.repository;

import com.example.demo.model.Comment;
import com.example.demo.model.Summary;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {

    List<Comment> findBySummary(Summary summary); // Find comments by summary

    List<Comment> findByUser(User user); // Find comments by user
}
