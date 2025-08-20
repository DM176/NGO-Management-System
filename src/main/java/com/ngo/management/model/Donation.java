package com.ngo.management.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Simplified Donation model for tracking donor contributions
 */
public class Donation {
    
    private String donationId;
    private double amount;
    private String currency;
    private String projectId;
    private DonationType donationType;
    private LocalDate donationDate;
    private String notes;
    private LocalDateTime createdAt;
    private boolean isAnonymous;
    
    public Donation(double amount, String currency, String projectId) {
        this.donationId = "DON-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.amount = amount;
        this.currency = currency;
        this.projectId = projectId;
        this.donationType = DonationType.MONETARY;
        this.donationDate = LocalDate.now();
        this.createdAt = LocalDateTime.now();
        this.isAnonymous = false;
    }
    
    // Getters and Setters
    public String getDonationId() { return donationId; }
    
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    
    public String getProjectId() { return projectId; }
    public void setProjectId(String projectId) { this.projectId = projectId; }
    
    public DonationType getDonationType() { return donationType; }
    public void setDonationType(DonationType donationType) { this.donationType = donationType; }
    
    public LocalDate getDonationDate() { return donationDate; }
    public void setDonationDate(LocalDate donationDate) { this.donationDate = donationDate; }
    
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    
    public boolean isAnonymous() { return isAnonymous; }
    public void setAnonymous(boolean anonymous) { isAnonymous = anonymous; }
    
    @Override
    public String toString() {
        return String.format("Donation[ID=%s, Amount=%.2f %s, Project=%s]", 
            donationId, amount, currency, projectId);
    }
} 