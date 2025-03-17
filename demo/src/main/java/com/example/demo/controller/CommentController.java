package com.example.demo.controller;

import com.example.demo.model.Comment;
import com.example.demo.model.Summary;
import com.example.demo.model.User;
import com.example.demo.service.CommentService;
import com.example.demo.service.SummaryService;
import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private SummaryService summaryService;

    @Autowired
    private UserService userService;

    // Get all comments
    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments() {
        List<Comment> comments = commentService.getAllComments();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    // Get a specific comment by ID
    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable String id) {
        Optional<Comment> comment = commentService.getCommentById(id);
        return comment.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get all comments for a specific summary
    @GetMapping("/summary/{summaryId}")
    public ResponseEntity<List<Comment>> getCommentsBySummary(@PathVariable String summaryId) {
        Optional<Summary> summary = summaryService.getSummaryById(summaryId);
        if (summary.isPresent()) {
            List<Comment> comments = commentService.getCommentsBySummary(summary.get());
            return new ResponseEntity<>(comments, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Get all comments made by a specific user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Comment>> getCommentsByUser(@PathVariable String userId) {
        Optional<User> user = userService.getUserById(userId);
        if (user.isPresent()) {
            List<Comment> comments = commentService.getCommentsByUser(user.get());
            return new ResponseEntity<>(comments, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Create a new comment
    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        Comment createdComment = commentService.createComment(comment);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    // Delete a comment by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable String id) {
        Optional<Comment> existingComment = commentService.getCommentById(id);
        if (existingComment.isPresent()) {
            commentService.deleteComment(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
