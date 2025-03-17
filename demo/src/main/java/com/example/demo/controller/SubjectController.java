// package com.example.demo.controller;

// import com.example.demo.model.Subject;
// import com.example.demo.service.SubjectService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;
// import java.util.Optional;

// @RestController
// @RequestMapping("/api/subjects")
// public class SubjectController {

// @Autowired
// private SubjectService subjectService;

// @GetMapping
// public ResponseEntity<List<Subject>> getAllSubjects() {
// List<Subject> subjects = subjectService.getAllSubjects();
// return new ResponseEntity<>(subjects, HttpStatus.OK);
// }

// @GetMapping("/{id}")
// public ResponseEntity<Subject> getSubjectById(@PathVariable String id) {
// Optional<Subject> subject = subjectService.getSubjectById(id);
// return subject.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
// .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
// }

// @PostMapping
// public ResponseEntity<Subject> createSubject(@RequestBody Subject subject) {
// Subject createdSubject = subjectService.createSubject(subject);
// return new ResponseEntity<>(createdSubject, HttpStatus.CREATED);
// }

// @PutMapping("/{id}")
// public ResponseEntity<Subject> updateSubject(@PathVariable String id,
// @RequestBody Subject subject) {
// Optional<Subject> existingSubject = subjectService.getSubjectById(id);
// if (existingSubject.isPresent()) {
// subject.setSubjectId(id); // Đảm bảo ID không bị thay đổi
// Subject updatedSubject = subjectService.createSubject(subject);
// return new ResponseEntity<>(updatedSubject, HttpStatus.OK);
// } else {
// return new ResponseEntity<>(HttpStatus.NOT_FOUND);
// }
// }

// @DeleteMapping("/{id}")
// public ResponseEntity<Void> deleteSubject(@PathVariable String id) {
// subjectService.deleteSubject(id);
// return new ResponseEntity<>(HttpStatus.NO_CONTENT);
// }

// // Các API khác liên quan đến Subject (ví dụ: lấy theo gradeLevel)
// }
