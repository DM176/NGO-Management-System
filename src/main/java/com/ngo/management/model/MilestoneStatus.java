package com.ngo.management.model;

/**
 * Enum representing the status of project milestones
 */
public enum MilestoneStatus {
    PENDING("Pending", "Milestone is waiting to be started"),
    IN_PROGRESS("In Progress", "Milestone is currently being worked on"),
    COMPLETED("Completed", "Milestone has been successfully completed"),
    DELAYED("Delayed", "Milestone is behind schedule"),
    CANCELLED("Cancelled", "Milestone has been cancelled");
    
    private final String displayName;
    private final String description;
    
    MilestoneStatus(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public String getDescription() {
        return description;
    }
    
    @Override
    public String toString() {
        return displayName;
    }
} 