package com.ngo.management.model;

/**
 * Enum representing different types of donations
 */
public enum DonationType {
    MONETARY("Monetary", "Cash or bank transfer donations"),
    IN_KIND("In-Kind", "Goods or services donations"),
    EQUIPMENT("Equipment", "Equipment or machinery donations"),
    VEHICLE("Vehicle", "Vehicle donations"),
    PROPERTY("Property", "Real estate or property donations"),
    STOCK("Stock", "Stock or securities donations"),
    LEGACY("Legacy", "Bequest or legacy donations"),
    RECURRING("Recurring", "Regular recurring donations");
    
    private final String displayName;
    private final String description;
    
    DonationType(String displayName, String description) {
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