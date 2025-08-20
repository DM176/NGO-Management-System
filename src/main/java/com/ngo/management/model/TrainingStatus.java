package com.ngo.management.model;

/**
 * Enum representing the status of volunteer training
 */
public enum TrainingStatus {
    PLANNED("Planned", "Training is scheduled but not yet started"),
    IN_PROGRESS("In Progress", "Training is currently ongoing"),
    COMPLETED("Completed", "Training has been successfully completed"),
    CANCELLED("Cancelled", "Training has been cancelled"),
    POSTPONED("Postponed", "Training has been postponed to a later date"),
    FAILED("Failed", "Training was not completed successfully");
    
    private final String displayName;
    private final String description;
    
    TrainingStatus(String displayName, String description) {
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