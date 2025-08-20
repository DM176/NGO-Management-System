package com.ngo.management.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Enhanced Needy model for managing beneficiaries of NGO programs
 */
public class Needy {
    
    // Core beneficiary information
    private String beneficiaryId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private LocalDate dateOfBirth;
    private Gender gender;
    
    // Financial and social information
    private double monthlyIncome;
    private String occupation;
    private String education;
    private String familySize;
    private String maritalStatus;
    private List<String> dependents;
    
    // Needs assessment
    private List<String> needs;
    private String primaryNeed;
    private String urgencyLevel;
    private String assessmentNotes;
    private LocalDate assessmentDate;
    
    // Program participation
    private List<String> enrolledPrograms;
    private List<String> completedPrograms;
    private LocalDate firstEnrollmentDate;
    private String currentStatus;
    
    // Support and progress
    private List<SupportRecord> supportRecords;
    private String progressNotes;
    private String successMetrics;
    private boolean isGraduated;
    private LocalDate graduationDate;
    
    // Emergency contact
    private String emergencyContactName;
    private String emergencyContactPhone;
    private String emergencyContactRelationship;
    
    // Metadata
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
    private boolean isActive;
    
    // Constructor
    public Needy(String firstName, String lastName, String email, String phone) {
        this.beneficiaryId = generateBeneficiaryId();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.currentStatus = "REGISTERED";
        this.firstEnrollmentDate = LocalDate.now();
        this.assessmentDate = LocalDate.now();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.isActive = true;
        
        // Initialize collections
        this.needs = new ArrayList<>();
        this.dependents = new ArrayList<>();
        this.enrolledPrograms = new ArrayList<>();
        this.completedPrograms = new ArrayList<>();
        this.supportRecords = new ArrayList<>();
    }
    
    // Generate unique beneficiary ID
    private String generateBeneficiaryId() {
        return "BEN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
    
    // Business logic methods
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    public int getAge() {
        if (dateOfBirth == null) return 0;
        return LocalDate.now().getYear() - dateOfBirth.getYear();
    }
    
    public boolean isEligible() {
        return monthlyIncome <= 20000 && isActive;
    }
    
    public void addNeed(String need) {
        if (need != null && !needs.contains(need)) {
            needs.add(need);
            updatedAt = LocalDateTime.now();
        }
    }
    
    public void enrollInProgram(String programName) {
        if (programName != null && !enrolledPrograms.contains(programName)) {
            enrolledPrograms.add(programName);
            updatedAt = LocalDateTime.now();
        }
    }
    
    public void completeProgram(String programName) {
        if (enrolledPrograms.remove(programName)) {
            completedPrograms.add(programName);
            updatedAt = LocalDateTime.now();
        }
    }
    
    public void addSupportRecord(SupportRecord record) {
        if (record != null) {
            supportRecords.add(record);
            updatedAt = LocalDateTime.now();
        }
    }
    
    public void graduate() {
        this.isGraduated = true;
        this.graduationDate = LocalDate.now();
        this.currentStatus = "GRADUATED";
        this.updatedAt = LocalDateTime.now();
    }
    
    public int getTotalPrograms() {
        return enrolledPrograms.size() + completedPrograms.size();
    }
    
    public double getSuccessRate() {
        if (getTotalPrograms() == 0) return 0.0;
        return (double) completedPrograms.size() / getTotalPrograms() * 100;
    }
    
    // Getters and Setters
    public String getBeneficiaryId() { return beneficiaryId; }
    
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
    
    public double getMonthlyIncome() { return monthlyIncome; }
    public void setMonthlyIncome(double monthlyIncome) { 
        this.monthlyIncome = monthlyIncome; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public String getOccupation() { return occupation; }
    public void setOccupation(String occupation) { 
        this.occupation = occupation; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public String getEducation() { return education; }
    public void setEducation(String education) { 
        this.education = education; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public String getFamilySize() { return familySize; }
    public void setFamilySize(String familySize) { 
        this.familySize = familySize; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public String getMaritalStatus() { return maritalStatus; }
    public void setMaritalStatus(String maritalStatus) { 
        this.maritalStatus = maritalStatus; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public List<String> getDependents() { return new ArrayList<>(dependents); }
    
    public List<String> getNeeds() { return new ArrayList<>(needs); }
    
    public String getPrimaryNeed() { return primaryNeed; }
    public void setPrimaryNeed(String primaryNeed) { 
        this.primaryNeed = primaryNeed; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public String getUrgencyLevel() { return urgencyLevel; }
    public void setUrgencyLevel(String urgencyLevel) { 
        this.urgencyLevel = urgencyLevel; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public String getAssessmentNotes() { return assessmentNotes; }
    public void setAssessmentNotes(String assessmentNotes) { 
        this.assessmentNotes = assessmentNotes; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public LocalDate getAssessmentDate() { return assessmentDate; }
    
    public List<String> getEnrolledPrograms() { return new ArrayList<>(enrolledPrograms); }
    
    public List<String> getCompletedPrograms() { return new ArrayList<>(completedPrograms); }
    
    public LocalDate getFirstEnrollmentDate() { return firstEnrollmentDate; }
    
    public String getCurrentStatus() { return currentStatus; }
    public void setCurrentStatus(String currentStatus) { 
        this.currentStatus = currentStatus; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public List<SupportRecord> getSupportRecords() { return new ArrayList<>(supportRecords); }
    
    public String getProgressNotes() { return progressNotes; }
    public void setProgressNotes(String progressNotes) { 
        this.progressNotes = progressNotes; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public String getSuccessMetrics() { return successMetrics; }
    public void setSuccessMetrics(String successMetrics) { 
        this.successMetrics = successMetrics; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public boolean isGraduated() { return isGraduated; }
    
    public LocalDate getGraduationDate() { return graduationDate; }
    
    public String getEmergencyContactName() { return emergencyContactName; }
    public void setEmergencyContactName(String emergencyContactName) { 
        this.emergencyContactName = emergencyContactName; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public String getEmergencyContactPhone() { return emergencyContactPhone; }
    public void setEmergencyContactPhone(String emergencyContactPhone) { 
        this.emergencyContactPhone = emergencyContactPhone; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public String getEmergencyContactRelationship() { return emergencyContactRelationship; }
    public void setEmergencyContactRelationship(String emergencyContactRelationship) { 
        this.emergencyContactRelationship = emergencyContactRelationship; 
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
        return String.format("Needy[ID=%s, Name=%s %s, Status=%s, Income=%.2f]", 
            beneficiaryId, firstName, lastName, currentStatus, monthlyIncome);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Needy needy = (Needy) obj;
        return beneficiaryId.equals(needy.beneficiaryId);
    }
    
    @Override
    public int hashCode() {
        return beneficiaryId.hashCode();
    }
} 