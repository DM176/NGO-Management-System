package com.ngo.management.service;

import com.ngo.management.model.*;
import com.ngo.management.util.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for managing volunteer operations
 */
public class VolunteerService {
    
    private List<Volunteer> volunteers;
    
    public VolunteerService() {
        this.volunteers = new ArrayList<>();
        initializeSampleData();
    }
    
    private void initializeSampleData() {
        // Add sample volunteers for demonstration
        Volunteer volunteer1 = new Volunteer("Alice", "Johnson", "alice.johnson@email.com", "+1-555-0303");
        volunteer1.updateStatus(VolunteerStatus.ACTIVE);
        volunteer1.setSkills("Teaching, Communication, Leadership");
        volunteer1.setOccupation("Teacher");
        
        Volunteer volunteer2 = new Volunteer("Bob", "Wilson", "bob.wilson@email.com", "+1-555-0404");
        volunteer2.updateStatus(VolunteerStatus.ACTIVE);
        volunteer2.setSkills("Healthcare, First Aid, Counseling");
        volunteer2.setOccupation("Nurse");
        
        volunteers.add(volunteer1);
        volunteers.add(volunteer2);
        
        Logger.info("Sample volunteers initialized");
    }
    
    // CRUD Operations
    public Volunteer createVolunteer(String firstName, String lastName, String email, String phone) {
        try {
            Volunteer volunteer = new Volunteer(firstName, lastName, email, phone);
            volunteers.add(volunteer);
            Logger.info("Volunteer created: " + volunteer.getVolunteerId());
            return volunteer;
        } catch (Exception e) {
            Logger.error("Failed to create volunteer: " + firstName + " " + lastName, e);
            throw new RuntimeException("Failed to create volunteer", e);
        }
    }
    
    public Optional<Volunteer> getVolunteerById(String volunteerId) {
        return volunteers.stream()
            .filter(v -> v.getVolunteerId().equals(volunteerId))
            .findFirst();
    }
    
    public List<Volunteer> getAllVolunteers() {
        return new ArrayList<>(volunteers);
    }
    
    public List<Volunteer> getActiveVolunteers() {
        return volunteers.stream()
            .filter(Volunteer::isAvailable)
            .collect(Collectors.toList());
    }
    
    public List<Volunteer> getVolunteersByStatus(VolunteerStatus status) {
        return volunteers.stream()
            .filter(v -> v.getStatus() == status)
            .collect(Collectors.toList());
    }
    
    public boolean updateVolunteer(String volunteerId, Volunteer updatedVolunteer) {
        try {
            Optional<Volunteer> existingVolunteer = getVolunteerById(volunteerId);
            if (existingVolunteer.isPresent()) {
                Volunteer volunteer = existingVolunteer.get();
                volunteer.setFirstName(updatedVolunteer.getFirstName());
                volunteer.setLastName(updatedVolunteer.getLastName());
                volunteer.setEmail(updatedVolunteer.getEmail());
                volunteer.setPhone(updatedVolunteer.getPhone());
                volunteer.setAddress(updatedVolunteer.getAddress());
                volunteer.setSkills(updatedVolunteer.getSkills());
                volunteer.setOccupation(updatedVolunteer.getOccupation());
                
                Logger.info("Volunteer updated: " + volunteerId);
                return true;
            }
            return false;
        } catch (Exception e) {
            Logger.error("Failed to update volunteer: " + volunteerId, e);
            return false;
        }
    }
    
    public boolean deleteVolunteer(String volunteerId) {
        try {
            Optional<Volunteer> volunteer = getVolunteerById(volunteerId);
            if (volunteer.isPresent()) {
                volunteer.get().setActive(false);
                Logger.info("Volunteer deactivated: " + volunteerId);
                return true;
            }
            return false;
        } catch (Exception e) {
            Logger.error("Failed to delete volunteer: " + volunteerId, e);
            return false;
        }
    }
    
    // Business Logic Methods
    public List<Volunteer> getTopVolunteers(int limit) {
        return volunteers.stream()
            .sorted((v1, v2) -> Double.compare(v2.getTotalHours(), v1.getTotalHours()))
            .limit(limit)
            .collect(Collectors.toList());
    }
    
    public List<Volunteer> getVolunteersBySkill(String skill) {
        if (skill == null || skill.trim().isEmpty()) {
            return getAllVolunteers();
        }
        
        String skillTerm = skill.toLowerCase();
        return volunteers.stream()
            .filter(v -> v.getSkills() != null && v.getSkills().toLowerCase().contains(skillTerm))
            .collect(Collectors.toList());
    }
    
    public double getTotalVolunteerHours() {
        return volunteers.stream()
            .mapToDouble(Volunteer::getTotalHours)
            .sum();
    }
    
    public double getAverageVolunteerHours() {
        if (volunteers.isEmpty()) return 0.0;
        return getTotalVolunteerHours() / volunteers.size();
    }
    
    public int getTotalActiveVolunteers() {
        return (int) volunteers.stream()
            .filter(Volunteer::isAvailable)
            .count();
    }
    
    public VolunteerStatistics getVolunteerStatistics() {
        VolunteerStatistics stats = new VolunteerStatistics();
        stats.setTotalVolunteers(volunteers.size());
        stats.setActiveVolunteers(getTotalActiveVolunteers());
        stats.setTotalHours(getTotalVolunteerHours());
        stats.setAverageHours(getAverageVolunteerHours());
        
        return stats;
    }
    
    // Search and Filter Methods
    public List<Volunteer> searchVolunteers(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllVolunteers();
        }
        
        String term = searchTerm.toLowerCase();
        return volunteers.stream()
            .filter(v -> v.getFirstName().toLowerCase().contains(term) ||
                        v.getLastName().toLowerCase().contains(term) ||
                        v.getEmail().toLowerCase().contains(term) ||
                        (v.getSkills() != null && v.getSkills().toLowerCase().contains(term)))
            .collect(Collectors.toList());
    }
    
    public List<Volunteer> getVolunteersByOccupation(String occupation) {
        if (occupation == null || occupation.trim().isEmpty()) {
            return getAllVolunteers();
        }
        
        String occupationTerm = occupation.toLowerCase();
        return volunteers.stream()
            .filter(v -> v.getOccupation() != null && v.getOccupation().toLowerCase().contains(occupationTerm))
            .collect(Collectors.toList());
    }
    
    // Volunteer Management Methods
    public boolean updateVolunteerStatus(String volunteerId, VolunteerStatus newStatus) {
        try {
            Optional<Volunteer> volunteer = getVolunteerById(volunteerId);
            if (volunteer.isPresent()) {
                volunteer.get().updateStatus(newStatus);
                Logger.info("Volunteer status updated: " + volunteerId + " -> " + newStatus);
                return true;
            }
            return false;
        } catch (Exception e) {
            Logger.error("Failed to update volunteer status: " + volunteerId, e);
            return false;
        }
    }
    
    public boolean addVolunteerHours(String volunteerId, double hours, String projectId) {
        try {
            Optional<Volunteer> volunteer = getVolunteerById(volunteerId);
            if (volunteer.isPresent()) {
                // Record volunteer hours as an activity
                volunteer.get().addActivity(new VolunteerActivity(projectId, "Logged volunteer hours", hours));
                Logger.info("Hours added for volunteer: " + volunteerId + ", Hours: " + hours);
                return true;
            }
            return false;
        } catch (Exception e) {
            Logger.error("Failed to add hours for volunteer: " + volunteerId, e);
            return false;
        }
    }
    
    // Inner class for volunteer statistics
    public static class VolunteerStatistics {
        private int totalVolunteers;
        private int activeVolunteers;
        private double totalHours;
        private double averageHours;
        
        // Getters and Setters
        public int getTotalVolunteers() { return totalVolunteers; }
        public void setTotalVolunteers(int totalVolunteers) { this.totalVolunteers = totalVolunteers; }
        
        public int getActiveVolunteers() { return activeVolunteers; }
        public void setActiveVolunteers(int activeVolunteers) { this.activeVolunteers = activeVolunteers; }
        
        public double getTotalHours() { return totalHours; }
        public void setTotalHours(double totalHours) { this.totalHours = totalHours; }
        
        public double getAverageHours() { return averageHours; }
        public void setAverageHours(double averageHours) { this.averageHours = averageHours; }
    }
    
    // SimpleVolunteerActivity no longer needed; using VolunteerActivity model
} 