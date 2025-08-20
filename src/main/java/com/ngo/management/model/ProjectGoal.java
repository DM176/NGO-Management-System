package com.ngo.management.model;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Simplified ProjectGoal model for managing project objectives
 */
public class ProjectGoal {
    
    private String goalId;
    private String description;
    private String target;
    private boolean isCompleted;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;
    
    public ProjectGoal(String description, String target) {
        this.goalId = "GOAL-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.description = description;
        this.target = target;
        this.isCompleted = false;
        this.createdAt = LocalDateTime.now();
    }
    
    // Getters and Setters
    public String getGoalId() { return goalId; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getTarget() { return target; }
    public void setTarget(String target) { this.target = target; }
    
    public boolean isCompleted() { return isCompleted; }
    public void setCompleted(boolean completed) { 
        this.isCompleted = completed; 
        if (completed) {
            this.completedAt = LocalDateTime.now();
        }
    }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    
    public LocalDateTime getCompletedAt() { return completedAt; }
    
    @Override
    public String toString() {
        return String.format("ProjectGoal[ID=%s, Description=%s, Completed=%s]", 
            goalId, description, isCompleted);
    }
} 