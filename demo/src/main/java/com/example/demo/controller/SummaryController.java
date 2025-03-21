package com.example.demo.controller;

import com.example.demo.dto.SummaryDTO;
import com.example.demo.model.Summary;
import com.example.demo.model.User;
import com.example.demo.service.SummaryService;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/summaries")
public class SummaryController {

    @Autowired
    private SummaryService summaryService;

    @Autowired
    private UserRepository userRepository; // To handle user retrieval by ID

    // Get all summaries
@GetMapping
public ResponseEntity<List<SummaryDTO>> getAllSummaries() {
    List<SummaryDTO> summaries = summaryService.getAllSummaries();
    return new ResponseEntity<>(summaries, HttpStatus.OK);
}


@GetMapping("/{id}")
public ResponseEntity<SummaryDTO> getSummaryById(@PathVariable String id, @RequestParam String userId) {
    Optional<Summary> summaryOpt = summaryService.getSummaryById(id);

    if (summaryOpt.isPresent()) {
        Summary summary = summaryOpt.get();

        // Log the read history with the userId (no need to fetch User object)
        User user = userRepository.findById(userId).orElse(null); // Fetch the user using userId
        if (user != null) {
            summaryService.logReadHistory(user, summary); // Log the read history
        }

        // Increment the read count
        summary.setReadCount(summary.getReadCount() + 1);
        summaryService.updateSummary(summary);

        // Map the Summary entity to SummaryDTO
        SummaryDTO summaryDTO = mapToSummaryDTO(summary);
        return new ResponseEntity<>(summaryDTO, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // If the summary is not found
    }
}


@GetMapping("/top10")
public ResponseEntity<List<SummaryDTO>> getTop10MostReadSummaries() {
    List<Summary> topSummaries = summaryService.getTop10MostReadSummaries();
    
    // Map the entities to DTOs
    List<SummaryDTO> topSummaryDTOs = topSummaries.stream()
        .map(this::mapToSummaryDTO)
        .collect(Collectors.toList());
        
    return new ResponseEntity<>(topSummaryDTOs, HttpStatus.OK);
}



@GetMapping("/search")
public ResponseEntity<List<SummaryDTO>> searchSummariesByTitle(@RequestParam String searchTerm) {
    List<Summary> summaries = summaryService.searchSummariesByTitle(searchTerm);
    List<SummaryDTO> summaryDTOs = summaries.stream()
            .map(this::mapToSummaryDTO)
            .collect(Collectors.toList());
    return new ResponseEntity<>(summaryDTOs, HttpStatus.OK);
}

private SummaryDTO mapToSummaryDTO(Summary summary) {
    SummaryDTO summaryDTO = new SummaryDTO();
    summaryDTO.setSummaryId(summary.getSummaryId());
    summaryDTO.setTitle(summary.getTitle());
    summaryDTO.setContent(summary.getContent());
    summaryDTO.setSummaryContent(summary.getSummaryContent());
    summaryDTO.setStatus(summary.getStatus());
    summaryDTO.setMethod(summary.getMethod());
    summaryDTO.setGrade(summary.getGrade());
    summaryDTO.setReadCount(summary.getReadCount());
    summaryDTO.setCreatedByUserId(summary.getCreatedBy().getUserId()); // Assuming you're using a User object for createdBy

    // Add createdAt and approvedAt
    summaryDTO.setCreatedAt(summary.getCreatedAt());
    summaryDTO.setApprovedAt(summary.getApprovedAt());

    return summaryDTO;
}
@PostMapping
public ResponseEntity<Summary> createSummary(@RequestBody Summary summary) {
    // Manually set createdAt if it's not already set
    if (summary.getCreatedAt() == null) {
        summary.setCreatedAt(new Date()); // Set the current date and time as createdAt
    }

    // Handle approval time if necessary
    if ("APPROVED".equals(summary.getStatus())) {
        summary.setApprovedAt(new Date()); // Set the approval time when the status is APPROVED
    }

    Optional<User> user = userRepository.findById(summary.getCreatedBy().getUserId());
    if (user.isPresent()) {
        summary.setCreatedBy(user.get()); // Set the user for the summary
        Summary createdSummary = summaryService.createSummary(summary);
        return new ResponseEntity<>(createdSummary, HttpStatus.CREATED);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // If the user does not exist
    }
}

    

    // Update an existing summary
    @PutMapping("/{id}")
    public ResponseEntity<Summary> updateSummary(@PathVariable String id, @RequestBody Summary summary) {
        Optional<Summary> existingSummary = summaryService.getSummaryById(id);
        if (existingSummary.isPresent()) {
            summary.setSummaryId(id); // Ensure the ID is not changed
            Summary updatedSummary = summaryService.createSummary(summary);
            return new ResponseEntity<>(updatedSummary, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a summary
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSummary(@PathVariable String id) {
        summaryService.deleteSummary(id);
        //print the console
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get all summaries by status (e.g., PENDING, APPROVED)
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Summary>> getSummariesByStatus(@PathVariable String status) {
        List<Summary> summaries = summaryService.getSummariesByStatus(status);
        return new ResponseEntity<>(summaries, HttpStatus.OK);
    }

    // Get all summaries created by a specific user
    @GetMapping("/contributor/{userId}")
    public ResponseEntity<List<SummaryDTO>> getSummariesByContributor(@PathVariable String userId) {
        List<SummaryDTO> summaries = summaryService.getSummariesByContributor(userId);
        return new ResponseEntity<>(summaries, HttpStatus.OK);
    }
    // Approve or reject a summary
    @PutMapping("/{id}/status")
    public ResponseEntity<Void> updateSummaryStatus(@PathVariable String id, @RequestParam String status) {
        summaryService.updateSummaryStatus(id, status);
        return new ResponseEntity<>(HttpStatus.OK);
    }

   
     // Get all summaries by grade
     @GetMapping("/grade/{grade}")
     public ResponseEntity<List<SummaryDTO>> getSummariesByGrade(@PathVariable String grade) {
         List<SummaryDTO> summaries = summaryService.getSummariesByGrade(grade);
         return new ResponseEntity<>(summaries, HttpStatus.OK);
     }

    // Get all summaries by method (e.g., extractive, abstractive)
    @GetMapping("/method/{method}")
    public ResponseEntity<List<Summary>> getSummariesByMethod(@PathVariable String method) {
        List<Summary> summaries = summaryService.getSummariesByMethod(method);
        return new ResponseEntity<>(summaries, HttpStatus.OK);
    }
}
