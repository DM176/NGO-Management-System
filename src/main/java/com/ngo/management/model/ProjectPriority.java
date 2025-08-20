package com.ngo.management.model;

/**
 * Enum representing the priority levels of a project
 */
public enum ProjectPriority {
    LOW("Low", "Low priority project"),
    MEDIUM("Medium", "Medium priority project"),
    HIGH("High", "High priority project"),
    URGENT("Urgent", "Urgent priority project"),
    CRITICAL("Critical", "Critical priority project");
    
    private final String displayName;
    private final String description;
    
    ProjectPriority(String displayName, String description) {
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