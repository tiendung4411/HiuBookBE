package com.example.demo.service;

import com.example.demo.model.Comment;
import com.example.demo.model.Summary;
import com.example.demo.model.User;
import com.example.demo.repository.CommentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    /**
     * Create a new comment.
     */
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    /**
     * Get all comments.
     */
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    /**
     * Get a comment by ID.
     */
    public Optional<Comment> getCommentById(String id) {
        return commentRepository.findById(id);
    }

    /**
     * Get all comments for a specific summary.
     */
    public List<Comment> getCommentsBySummary(Summary summary) {
        return commentRepository.findBySummary(summary);
    }

    /**
     * Get all comments made by a specific user.
     */
    public List<Comment> getCommentsByUser(User user) {
        return commentRepository.findByUser(user);
    }

    /**
     * Delete a comment by ID.
     */
    public void deleteComment(String id) {
        commentRepository.deleteById(id);
    }
}
