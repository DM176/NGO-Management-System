package com.ngo.management.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Simplified ProjectMilestone model for managing project milestones
 */
public class ProjectMilestone {
    
    private String milestoneId;
    private String name;
    private String description;
    private LocalDate targetDate;
    private LocalDate actualDate;
    private MilestoneStatus status;
    private LocalDateTime createdAt;
    
    public ProjectMilestone(String name, String description, LocalDate targetDate) {
        this.milestoneId = "MIL-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.name = name;
        this.description = description;
        this.targetDate = targetDate;
        this.status = MilestoneStatus.PENDING;
        this.createdAt = LocalDateTime.now();
    }
    
    // Getters and Setters
    public String getMilestoneId() { return milestoneId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public LocalDate getTargetDate() { return targetDate; }
    public void setTargetDate(LocalDate targetDate) { this.targetDate = targetDate; }
    
    public LocalDate getActualDate() { return actualDate; }
    public void setActualDate(LocalDate actualDate) { this.actualDate = actualDate; }
    
    public MilestoneStatus getStatus() { return status; }
    public void setStatus(MilestoneStatus status) { this.status = status; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    
    public boolean isOverdue() {
        return targetDate != null && LocalDate.now().isAfter(targetDate) && status != MilestoneStatus.COMPLETED;
    }
    
    @Override
    public String toString() {
        return String.format("ProjectMilestone[ID=%s, Name=%s, Status=%s]", 
            milestoneId, name, status);
    }
} 