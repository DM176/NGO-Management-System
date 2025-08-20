package com.ngo.management.model;

/**
 * Enum representing different types of volunteer activities
 */
public enum ActivityType {
    GENERAL("General", "General volunteer work"),
    TEACHING("Teaching", "Educational activities"),
    HEALTHCARE("Healthcare", "Medical or health-related activities"),
    ADMINISTRATIVE("Administrative", "Office or administrative work"),
    FUNDRAISING("Fundraising", "Fundraising activities"),
    OUTREACH("Outreach", "Community outreach activities"),
    TRAINING("Training", "Training or capacity building"),
    EMERGENCY("Emergency", "Emergency response activities"),
    MAINTENANCE("Maintenance", "Facility or equipment maintenance"),
    TRANSPORTATION("Transportation", "Transportation services");
    
    private final String displayName;
    private final String description;
    
    ActivityType(String displayName, String description) {
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