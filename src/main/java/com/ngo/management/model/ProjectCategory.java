package com.ngo.management.model;

/**
 * Enum representing the different categories of NGO projects
 */
public enum ProjectCategory {
    EDUCATION("Education", "Educational programs and initiatives"),
    HEALTHCARE("Healthcare", "Health-related programs and services"),
    POVERTY_ALLEVIATION("Poverty Alleviation", "Programs to reduce poverty"),
    ENVIRONMENT("Environment", "Environmental conservation and protection"),
    HUMAN_RIGHTS("Human Rights", "Human rights advocacy and protection"),
    DISASTER_RELIEF("Disaster Relief", "Emergency response and disaster relief"),
    COMMUNITY_DEVELOPMENT("Community Development", "Community building and development"),
    WOMEN_EMPOWERMENT("Women Empowerment", "Programs supporting women's rights"),
    CHILD_WELFARE("Child Welfare", "Programs for children's well-being"),
    ELDERLY_CARE("Elderly Care", "Programs for senior citizens"),
    DISABILITY_SUPPORT("Disability Support", "Programs for people with disabilities"),
    AGRICULTURE("Agriculture", "Agricultural development programs"),
    WATER_SANITATION("Water & Sanitation", "Clean water and sanitation programs"),
    OTHER("Other", "Other project categories");
    
    private final String displayName;
    private final String description;
    
    ProjectCategory(String displayName, String description) {
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