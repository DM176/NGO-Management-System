package com.ngo.management.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Simplified SupportRecord model for tracking support provided to beneficiaries
 */
public class SupportRecord {
    
    private String recordId;
    private String beneficiaryId;
    private String supportType;
    private String description;
    private double amount;
    private String currency;
    private LocalDate supportDate;
    private String providedBy;
    private LocalDateTime createdAt;
    
    public SupportRecord(String beneficiaryId, String supportType, String description) {
        this.recordId = "SUP-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.beneficiaryId = beneficiaryId;
        this.supportType = supportType;
        this.description = description;
        this.supportDate = LocalDate.now();
        this.createdAt = LocalDateTime.now();
        this.currency = "USD";
    }
    
    // Getters and Setters
    public String getRecordId() { return recordId; }
    
    public String getBeneficiaryId() { return beneficiaryId; }
    public void setBeneficiaryId(String beneficiaryId) { this.beneficiaryId = beneficiaryId; }
    
    public String getSupportType() { return supportType; }
    public void setSupportType(String supportType) { this.supportType = supportType; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    
    public LocalDate getSupportDate() { return supportDate; }
    public void setSupportDate(LocalDate supportDate) { this.supportDate = supportDate; }
    
    public String getProvidedBy() { return providedBy; }
    public void setProvidedBy(String providedBy) { this.providedBy = providedBy; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    
    @Override
    public String toString() {
        return String.format("SupportRecord[ID=%s, Beneficiary=%s, Type=%s, Amount=%.2f %s]", 
            recordId, beneficiaryId, supportType, amount, currency);
    }
} 