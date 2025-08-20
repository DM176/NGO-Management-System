package com.ngo.management.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Enhanced Donor model with comprehensive donor management features
 */
public class Donor {
    
    // Core donor information
    private String donorId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private LocalDate dateOfBirth;
    private Gender gender;
    
    // Donation preferences
    private DonorType donorType;
    private List<String> preferredProjects;
    private List<String> preferredCategories;
    private String preferredContactMethod;
    private boolean anonymousDonations;
    
    // Financial information
    private List<Donation> donations;
    private double totalDonated;
    private String preferredCurrency;
    private String taxId;
    
    // Communication preferences
    private boolean newsletterSubscription;
    private boolean eventNotifications;
    private String preferredLanguage;
    
    // Metadata
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
    private boolean isActive;
    
    // Constructor
    public Donor(String firstName, String lastName, String email, String phone) {
        this.donorId = generateDonorId();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.donorType = DonorType.INDIVIDUAL;
        this.preferredCurrency = "USD";
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.isActive = true;
        
        // Initialize collections
        this.preferredProjects = new ArrayList<>();
        this.preferredCategories = new ArrayList<>();
        this.donations = new ArrayList<>();
    }
    
    // Generate unique donor ID
    private String generateDonorId() {
        return "DON-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
    
    // Business logic methods
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    public int getAge() {
        if (dateOfBirth == null) return 0;
        return LocalDate.now().getYear() - dateOfBirth.getYear();
    }
    
    public void addDonation(Donation donation) {
        if (donation != null) {
            donations.add(donation);
            totalDonated += donation.getAmount();
            updatedAt = LocalDateTime.now();
        }
    }
    
    public double getAverageDonation() {
        if (donations.isEmpty()) return 0.0;
        return totalDonated / donations.size();
    }
    
    public int getDonationCount() {
        return donations.size();
    }
    
    public LocalDate getLastDonationDate() {
        if (donations.isEmpty()) return null;
        return donations.stream()
            .map(Donation::getDonationDate)
            .max(LocalDate::compareTo)
            .orElse(null);
    }
    
    public boolean isRecurringDonor() {
        return donations.size() > 1;
    }
    
    // Getters and Setters
    public String getDonorId() { return donorId; }
    
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { 
        this.firstName = firstName; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { 
        this.lastName = lastName; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { 
        this.email = email; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { 
        this.phone = phone; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public String getAddress() { return address; }
    public void setAddress(String address) { 
        this.address = address; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { 
        this.dateOfBirth = dateOfBirth; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public Gender getGender() { return gender; }
    public void setGender(Gender gender) { 
        this.gender = gender; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public DonorType getDonorType() { return donorType; }
    public void setDonorType(DonorType donorType) { 
        this.donorType = donorType; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public List<String> getPreferredProjects() { return new ArrayList<>(preferredProjects); }
    
    public List<String> getPreferredCategories() { return new ArrayList<>(preferredCategories); }
    
    public String getPreferredContactMethod() { return preferredContactMethod; }
    public void setPreferredContactMethod(String preferredContactMethod) { 
        this.preferredContactMethod = preferredContactMethod; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public boolean isAnonymousDonations() { return anonymousDonations; }
    public void setAnonymousDonations(boolean anonymousDonations) { 
        this.anonymousDonations = anonymousDonations; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public List<Donation> getDonations() { return new ArrayList<>(donations); }
    
    public double getTotalDonated() { return totalDonated; }
    
    public String getPreferredCurrency() { return preferredCurrency; }
    public void setPreferredCurrency(String preferredCurrency) { 
        this.preferredCurrency = preferredCurrency; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public String getTaxId() { return taxId; }
    public void setTaxId(String taxId) { 
        this.taxId = taxId; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public boolean isNewsletterSubscription() { return newsletterSubscription; }
    public void setNewsletterSubscription(boolean newsletterSubscription) { 
        this.newsletterSubscription = newsletterSubscription; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public boolean isEventNotifications() { return eventNotifications; }
    public void setEventNotifications(boolean eventNotifications) { 
        this.eventNotifications = eventNotifications; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public String getPreferredLanguage() { return preferredLanguage; }
    public void setPreferredLanguage(String preferredLanguage) { 
        this.preferredLanguage = preferredLanguage; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    
    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }
    
    public String getUpdatedBy() { return updatedBy; }
    public void setUpdatedBy(String updatedBy) { this.updatedBy = updatedBy; }
    
    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { 
        this.isActive = active; 
        this.updatedAt = LocalDateTime.now();
    }
    
    @Override
    public String toString() {
        return String.format("Donor[ID=%s, Name=%s %s, Total=%.2f %s]", 
            donorId, firstName, lastName, totalDonated, preferredCurrency);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Donor donor = (Donor) obj;
        return donorId.equals(donor.donorId);
    }
    
    @Override
    public int hashCode() {
        return donorId.hashCode();
    }
} 