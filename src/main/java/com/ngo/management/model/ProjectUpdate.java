package com.ngo.management.model;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Simplified ProjectUpdate model for tracking project updates
 */
public class ProjectUpdate {
    
    private String updateId;
    private String message;
    private String updatedBy;
    private UpdateType updateType;
    private LocalDateTime timestamp;
    
    public ProjectUpdate(String message, String updatedBy, UpdateType updateType) {
        this.updateId = "UPDATE-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.message = message;
        this.updatedBy = updatedBy;
        this.updateType = updateType;
        this.timestamp = LocalDateTime.now();
    }
    
    // Getters and Setters
    public String getUpdateId() { return updateId; }
    
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    
    public String getUpdatedBy() { return updatedBy; }
    public void setUpdatedBy(String updatedBy) { this.updatedBy = updatedBy; }
    
    public UpdateType getUpdateType() { return updateType; }
    public void setUpdateType(UpdateType updateType) { this.updateType = updateType; }
    
    public LocalDateTime getTimestamp() { return timestamp; }
    
    @Override
    public String toString() {
        return String.format("ProjectUpdate[ID=%s, Message=%s, By=%s, Type=%s]", 
            updateId, message, updatedBy, updateType);
    }
} 