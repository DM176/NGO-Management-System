package com.ngo.management.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Simplified VolunteerTraining model for tracking volunteer training
 */
public class VolunteerTraining {
    
    private String trainingId;
    private String trainingName;
    private String description;
    private LocalDate trainingDate;
    private double duration; // in hours
    private TrainingStatus status;
    private String certificateId;
    private LocalDateTime createdAt;
    
    public VolunteerTraining(String trainingName, String description, LocalDate trainingDate, double duration) {
        this.trainingId = "TRAIN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.trainingName = trainingName;
        this.description = description;
        this.trainingDate = trainingDate;
        this.duration = duration;
        this.status = TrainingStatus.PLANNED;
        this.createdAt = LocalDateTime.now();
    }
    
    // Getters and Setters
    public String getTrainingId() { return trainingId; }
    
    public String getTrainingName() { return trainingName; }
    public void setTrainingName(String trainingName) { this.trainingName = trainingName; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public LocalDate getTrainingDate() { return trainingDate; }
    public void setTrainingDate(LocalDate trainingDate) { this.trainingDate = trainingDate; }
    
    public double getDuration() { return duration; }
    public void setDuration(double duration) { this.duration = duration; }
    
    public TrainingStatus getStatus() { return status; }
    public void setStatus(TrainingStatus status) { this.status = status; }
    
    public String getCertificateId() { return certificateId; }
    public void setCertificateId(String certificateId) { this.certificateId = certificateId; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    
    public boolean isCompleted() {
        return status == TrainingStatus.COMPLETED;
    }
    
    public boolean isOverdue() {
        return trainingDate != null && LocalDate.now().isAfter(trainingDate) && status != TrainingStatus.COMPLETED;
    }
    
    @Override
    public String toString() {
        return String.format("VolunteerTraining[ID=%s, Name=%s, Status=%s, Date=%s]", 
            trainingId, trainingName, status, trainingDate);
    }
} 