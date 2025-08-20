package com.ngo.management.model;

/**
 * Enum representing the possible statuses of a project
 */
public enum ProjectStatus {
    PLANNING("Planning", "Project is in planning phase"),
    ACTIVE("Active", "Project is currently running"),
    ON_HOLD("On Hold", "Project is temporarily suspended"),
    COMPLETED("Completed", "Project has been successfully completed"),
    CANCELLED("Cancelled", "Project has been cancelled"),
    ARCHIVED("Archived", "Project has been archived");
    
    private final String displayName;
    private final String description;
    
    ProjectStatus(String displayName, String description) {
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