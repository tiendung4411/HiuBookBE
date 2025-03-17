// package com.example.demo.model;

// import jakarta.persistence.*;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// @Entity
// @Table(name = "summary_subject")
// @Data
// @NoArgsConstructor
// public class SummarySubject {
// @Id
// @GeneratedValue(strategy = GenerationType.UUID)
// private String summarySubjectId;

// @ManyToOne
// @JoinColumn(name = "summary_id", referencedColumnName = "summaryId")
// private Summary summary;

// @ManyToOne
// @JoinColumn(name = "subject_id", referencedColumnName = "subjectId")
// private Subject subject;
// }
