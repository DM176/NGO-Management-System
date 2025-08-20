package com.ngo.management.model;

/**
 * Enum representing the type of project updates
 */
public enum UpdateType {
    STATUS_CHANGE("Status Change", "Project status has been updated"),
    MILESTONE_UPDATE("Milestone Update", "Project milestone has been updated"),
    BUDGET_UPDATE("Budget Update", "Project budget has been modified"),
    TEAM_UPDATE("Team Update", "Project team has been modified"),
    SCHEDULE_UPDATE("Schedule Update", "Project schedule has been modified"),
    GENERAL_UPDATE("General Update", "General project information update"),
    COMMENT("Comment", "User comment or note"),
    DOCUMENT_ADDED("Document Added", "New document has been added"),
    DOCUMENT_UPDATED("Document Updated", "Existing document has been modified");
    
    private final String displayName;
    private final String description;
    
    UpdateType(String displayName, String description) {
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