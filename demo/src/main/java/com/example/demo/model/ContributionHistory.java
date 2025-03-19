package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CONTRIBUTION_HISTORY")
public class ContributionHistory {

    @Id
    private String contributionId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId") // Sửa referencedColumnName thành "userId"
    private User user;

    private String summaryId;
    private String action;
    private String timestamp;

    // Getters and setters
    public String getContributionId() {
        return contributionId;
    } 

    public void setContributionId(String contributionId) {
        this.contributionId = contributionId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSummaryId() {
        return summaryId;
    }

    public void setSummaryId(String summaryId) {
        this.summaryId = summaryId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
