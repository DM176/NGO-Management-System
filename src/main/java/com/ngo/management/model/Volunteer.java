package com.ngo.management.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Enhanced Volunteer model with comprehensive volunteer management features
 */
public class Volunteer {
    
    // Core volunteer information
    private String volunteerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private LocalDate dateOfBirth;
    private Gender gender;
    
    // Professional information
    private String occupation;
    private String skills;
    private String education;
    private String experience;
    private VolunteerStatus status;
    private LocalDate joinDate;
    private LocalDate lastActiveDate;
    
    // Availability and preferences
    private List<Availability> availability;
    private List<String> preferredProjects;
    private List<String> preferredLocations;
    private int maxHoursPerWeek;
    
    // Performance and tracking
    private List<VolunteerActivity> activities;
    private List<VolunteerTraining> trainings;
    private List<VolunteerFeedback> feedback;
    private double totalHours;
    private int totalProjects;
    
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
    public Volunteer(String firstName, String lastName, String email, String phone) {
        this.volunteerId = generateVolunteerId();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.status = VolunteerStatus.ACTIVE;
        this.joinDate = LocalDate.now();
        this.lastActiveDate = LocalDate.now();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.isActive = true;
        
        // Initialize collections
        this.availability = new ArrayList<>();
        this.preferredProjects = new ArrayList<>();
        this.preferredLocations = new ArrayList<>();
        this.activities = new ArrayList<>();
        this.trainings = new ArrayList<>();
        this.feedback = new ArrayList<>();
    }
    
    // Generate unique volunteer ID
    private String generateVolunteerId() {
        return "VOL-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
    
    // Business logic methods
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    public int getAge() {
        if (dateOfBirth == null) return 0;
        return LocalDate.now().getYear() - dateOfBirth.getYear();
    }
    
    public boolean isAvailable() {
        return status == VolunteerStatus.ACTIVE && isActive;
    }
    
    public void addActivity(VolunteerActivity activity) {
        if (activity != null) {
            activities.add(activity);
            totalHours += activity.getHours();
            lastActiveDate = LocalDate.now();
            updatedAt = LocalDateTime.now();
        }
    }
    
    public void addTraining(VolunteerTraining training) {
        if (training != null && !trainings.contains(training)) {
            trainings.add(training);
            updatedAt = LocalDateTime.now();
        }
    }
    
    public void addFeedback(VolunteerFeedback feedback) {
        if (feedback != null) {
            this.feedback.add(feedback);
            updatedAt = LocalDateTime.now();
        }
    }
    
    public double getAverageRating() {
        if (feedback.isEmpty()) return 0.0;
        return feedback.stream()
            .mapToDouble(VolunteerFeedback::getRating)
            .average()
            .orElse(0.0);
    }
    
    public void updateStatus(VolunteerStatus newStatus) {
        this.status = newStatus;
        this.updatedAt = LocalDateTime.now();
        
        if (newStatus == VolunteerStatus.INACTIVE) {
            this.lastActiveDate = LocalDate.now();
        }
    }
    
    // Getters and Setters
    public String getVolunteerId() { return volunteerId; }
    
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
    
    public String getOccupation() { return occupation; }
    public void setOccupation(String occupation) { 
        this.occupation = occupation; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public String getSkills() { return skills; }
    public void setSkills(String skills) { 
        this.skills = skills; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public String getEducation() { return education; }
    public void setEducation(String education) { 
        this.education = education; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public String getExperience() { return experience; }
    public void setExperience(String experience) { 
        this.experience = experience; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public VolunteerStatus getStatus() { return status; }
    
    public LocalDate getJoinDate() { return joinDate; }
    
    public LocalDate getLastActiveDate() { return lastActiveDate; }
    
    public List<Availability> getAvailability() { return new ArrayList<>(availability); }
    
    public List<String> getPreferredProjects() { return new ArrayList<>(preferredProjects); }
    
    public List<String> getPreferredLocations() { return new ArrayList<>(preferredLocations); }
    
    public int getMaxHoursPerWeek() { return maxHoursPerWeek; }
    public void setMaxHoursPerWeek(int maxHoursPerWeek) { 
        this.maxHoursPerWeek = maxHoursPerWeek; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public List<VolunteerActivity> getActivities() { return new ArrayList<>(activities); }
    
    public List<VolunteerTraining> getTrainings() { return new ArrayList<>(trainings); }
    
    public List<VolunteerFeedback> getFeedback() { return new ArrayList<>(feedback); }
    
    public double getTotalHours() { return totalHours; }
    
    public int getTotalProjects() { return totalProjects; }
    
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
        return String.format("Volunteer[ID=%s, Name=%s %s, Status=%s]", 
            volunteerId, firstName, lastName, status);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Volunteer volunteer = (Volunteer) obj;
        return volunteerId.equals(volunteer.volunteerId);
    }
    
    @Override
    public int hashCode() {
        return volunteerId.hashCode();
    }
} 