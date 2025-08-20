package com.ngo.management.model;

/**
 * Enum representing different types of feedback
 */
public enum FeedbackType {
    GENERAL("General", "General feedback about the program"),
    PROJECT("Project", "Feedback specific to a project"),
    TRAINING("Training", "Feedback about training programs"),
    SUPPORT("Support", "Feedback about support received"),
    FACILITIES("Facilities", "Feedback about facilities and resources"),
    COMMUNICATION("Communication", "Feedback about communication"),
    LEADERSHIP("Leadership", "Feedback about leadership and management"),
    SAFETY("Safety", "Feedback about safety and security"),
    RECOGNITION("Recognition", "Feedback about recognition and appreciation"),
    SUGGESTION("Suggestion", "Suggestions for improvement");
    
    private final String displayName;
    private final String description;
    
    FeedbackType(String displayName, String description) {
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