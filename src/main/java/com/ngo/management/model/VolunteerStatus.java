package com.ngo.management.model;

/**
 * Enum representing the status of volunteers
 */
public enum VolunteerStatus {
    ACTIVE("Active", "Volunteer is currently active"),
    INACTIVE("Inactive", "Volunteer is currently inactive"),
    SUSPENDED("Suspended", "Volunteer is temporarily suspended"),
    TERMINATED("Terminated", "Volunteer relationship has ended"),
    ON_LEAVE("On Leave", "Volunteer is on temporary leave"),
    TRAINING("In Training", "Volunteer is undergoing training");
    
    private final String displayName;
    private final String description;
    
    VolunteerStatus(String displayName, String description) {
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