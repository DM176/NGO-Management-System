package com.ngo.management.model;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Simplified VolunteerFeedback model for collecting volunteer feedback
 */
public class VolunteerFeedback {
    
    private String feedbackId;
    private String volunteerId;
    private String projectId;
    private double rating; // 1-5 scale
    private String comments;
    private FeedbackType feedbackType;
    private LocalDateTime createdAt;
    
    public VolunteerFeedback(String volunteerId, String projectId, double rating, String comments) {
        this.feedbackId = "FB-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.volunteerId = volunteerId;
        this.projectId = projectId;
        this.rating = Math.max(1.0, Math.min(5.0, rating)); // Ensure rating is between 1-5
        this.comments = comments;
        this.feedbackType = FeedbackType.GENERAL;
        this.createdAt = LocalDateTime.now();
    }
    
    // Getters and Setters
    public String getFeedbackId() { return feedbackId; }
    
    public String getVolunteerId() { return volunteerId; }
    public void setVolunteerId(String volunteerId) { this.volunteerId = volunteerId; }
    
    public String getProjectId() { return projectId; }
    public void setProjectId(String projectId) { this.projectId = projectId; }
    
    public double getRating() { return rating; }
    public void setRating(double rating) { 
        this.rating = Math.max(1.0, Math.min(5.0, rating)); 
    }
    
    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }
    
    public FeedbackType getFeedbackType() { return feedbackType; }
    public void setFeedbackType(FeedbackType feedbackType) { this.feedbackType = feedbackType; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    
    public String getRatingDescription() {
        if (rating >= 4.5) return "Excellent";
        if (rating >= 4.0) return "Very Good";
        if (rating >= 3.5) return "Good";
        if (rating >= 3.0) return "Satisfactory";
        if (rating >= 2.0) return "Below Average";
        return "Poor";
    }
    
    @Override
    public String toString() {
        return String.format("VolunteerFeedback[ID=%s, Volunteer=%s, Project=%s, Rating=%.1f]", 
            feedbackId, volunteerId, projectId, rating);
    }
} 