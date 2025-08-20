package com.ngo.management.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Simplified VolunteerActivity model for tracking volunteer work
 */
public class VolunteerActivity {
    
    private String activityId;
    private String projectId;
    private String description;
    private double hours;
    private LocalDate activityDate;
    private ActivityType activityType;
    private LocalDateTime createdAt;
    
    public VolunteerActivity(String projectId, String description, double hours) {
        this.activityId = "ACT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.projectId = projectId;
        this.description = description;
        this.hours = hours;
        this.activityDate = LocalDate.now();
        this.activityType = ActivityType.GENERAL;
        this.createdAt = LocalDateTime.now();
    }
    
    // Getters and Setters
    public String getActivityId() { return activityId; }
    
    public String getProjectId() { return projectId; }
    public void setProjectId(String projectId) { this.projectId = projectId; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public double getHours() { return hours; }
    public void setHours(double hours) { this.hours = hours; }
    
    public LocalDate getActivityDate() { return activityDate; }
    public void setActivityDate(LocalDate activityDate) { this.activityDate = activityDate; }
    
    public ActivityType getActivityType() { return activityType; }
    public void setActivityType(ActivityType activityType) { this.activityType = activityType; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    
    @Override
    public String toString() {
        return String.format("VolunteerActivity[ID=%s, Project=%s, Hours=%.1f, Date=%s]", 
            activityId, projectId, hours, activityDate);
    }
} 