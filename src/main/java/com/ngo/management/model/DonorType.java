package com.ngo.management.model;

/**
 * Enum representing different types of donors
 */
public enum DonorType {
    INDIVIDUAL("Individual", "Individual person"),
    CORPORATE("Corporate", "Business or corporation"),
    FOUNDATION("Foundation", "Charitable foundation"),
    GOVERNMENT("Government", "Government agency"),
    INTERNATIONAL("International", "International organization"),
    ANONYMOUS("Anonymous", "Anonymous donor");
    
    private final String displayName;
    private final String description;
    
    DonorType(String displayName, String description) {
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