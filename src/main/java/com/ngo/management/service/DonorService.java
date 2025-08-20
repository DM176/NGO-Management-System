package com.ngo.management.service;

import com.ngo.management.model.*;
import com.ngo.management.util.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for managing donor operations
 */
public class DonorService {
    
    private List<Donor> donors;
    
    public DonorService() {
        this.donors = new ArrayList<>();
        initializeSampleData();
    }
    
    private void initializeSampleData() {
        // Add sample donors for demonstration
        Donor individualDonor = new Donor("John", "Smith", "john.smith@email.com", "+1-555-0101");
        individualDonor.setDonorType(DonorType.INDIVIDUAL);
        individualDonor.setAddress("123 Main St, City, State 12345");
        
        Donor corporateDonor = new Donor("ABC Corp", "Foundation", "foundation@abccorp.com", "+1-555-0202");
        corporateDonor.setDonorType(DonorType.CORPORATE);
        corporateDonor.setAddress("456 Business Ave, Corporate City, State 67890");
        
        donors.add(individualDonor);
        donors.add(corporateDonor);
        
        Logger.info("Sample donors initialized");
    }
    
    // CRUD Operations
    public Donor createDonor(String firstName, String lastName, String email, String phone) {
        try {
            Donor donor = new Donor(firstName, lastName, email, phone);
            donors.add(donor);
            Logger.info("Donor created: " + donor.getDonorId());
            return donor;
        } catch (Exception e) {
            Logger.error("Failed to create donor: " + firstName + " " + lastName, e);
            throw new RuntimeException("Failed to create donor", e);
        }
    }
    
    public Optional<Donor> getDonorById(String donorId) {
        return donors.stream()
            .filter(d -> d.getDonorId().equals(donorId))
            .findFirst();
    }
    
    public List<Donor> getAllDonors() {
        return new ArrayList<>(donors);
    }
    
    public List<Donor> getActiveDonors() {
        return donors.stream()
            .filter(Donor::isActive)
            .collect(Collectors.toList());
    }
    
    public List<Donor> getDonorsByType(DonorType donorType) {
        return donors.stream()
            .filter(d -> d.getDonorType() == donorType)
            .collect(Collectors.toList());
    }
    
    public boolean updateDonor(String donorId, Donor updatedDonor) {
        try {
            Optional<Donor> existingDonor = getDonorById(donorId);
            if (existingDonor.isPresent()) {
                Donor donor = existingDonor.get();
                donor.setFirstName(updatedDonor.getFirstName());
                donor.setLastName(updatedDonor.getLastName());
                donor.setEmail(updatedDonor.getEmail());
                donor.setPhone(updatedDonor.getPhone());
                donor.setAddress(updatedDonor.getAddress());
                donor.setDonorType(updatedDonor.getDonorType());
                
                Logger.info("Donor updated: " + donorId);
                return true;
            }
            return false;
        } catch (Exception e) {
            Logger.error("Failed to update donor: " + donorId, e);
            return false;
        }
    }
    
    public boolean deleteDonor(String donorId) {
        try {
            Optional<Donor> donor = getDonorById(donorId);
            if (donor.isPresent()) {
                donor.get().setActive(false);
                Logger.info("Donor deactivated: " + donorId);
                return true;
            }
            return false;
        } catch (Exception e) {
            Logger.error("Failed to delete donor: " + donorId, e);
            return false;
        }
    }
    
    // Business Logic Methods
    public List<Donor> getTopDonors(int limit) {
        return donors.stream()
            .sorted((d1, d2) -> Double.compare(d2.getTotalDonated(), d1.getTotalDonated()))
            .limit(limit)
            .collect(Collectors.toList());
    }
    
    public List<Donor> getRecurringDonors() {
        return donors.stream()
            .filter(Donor::isRecurringDonor)
            .collect(Collectors.toList());
    }
    
    public double getTotalDonations() {
        return donors.stream()
            .mapToDouble(Donor::getTotalDonated)
            .sum();
    }
    
    public double getAverageDonation() {
        if (donors.isEmpty()) return 0.0;
        return getTotalDonations() / donors.size();
    }
    
    public int getTotalActiveDonors() {
        return (int) donors.stream()
            .filter(Donor::isActive)
            .count();
    }
    
    public DonorStatistics getDonorStatistics() {
        DonorStatistics stats = new DonorStatistics();
        stats.setTotalDonors(donors.size());
        stats.setActiveDonors(getTotalActiveDonors());
        stats.setTotalDonations(getTotalDonations());
        stats.setAverageDonation(getAverageDonation());
        stats.setRecurringDonors(getRecurringDonors().size());
        
        return stats;
    }
    
    // Search and Filter Methods
    public List<Donor> searchDonors(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllDonors();
        }
        
        String term = searchTerm.toLowerCase();
        return donors.stream()
            .filter(d -> d.getFirstName().toLowerCase().contains(term) ||
                        d.getLastName().toLowerCase().contains(term) ||
                        d.getEmail().toLowerCase().contains(term))
            .collect(Collectors.toList());
    }
    
    public List<Donor> getDonorsByDonationRange(double minAmount, double maxAmount) {
        return donors.stream()
            .filter(d -> d.getTotalDonated() >= minAmount && d.getTotalDonated() <= maxAmount)
            .collect(Collectors.toList());
    }
    
    // Donation Management Methods
    public boolean addDonation(String donorId, double amount, String projectId) {
        try {
            Optional<Donor> donor = getDonorById(donorId);
            if (donor.isPresent()) {
                // Record donation using the model Donation
                donor.get().addDonation(new Donation(amount, "USD", projectId));
                Logger.info("Donation added for donor: " + donorId + ", Amount: " + amount);
                return true;
            }
            return false;
        } catch (Exception e) {
            Logger.error("Failed to add donation for donor: " + donorId, e);
            return false;
        }
    }
    
    // Inner class for donor statistics
    public static class DonorStatistics {
        private int totalDonors;
        private int activeDonors;
        private double totalDonations;
        private double averageDonation;
        private int recurringDonors;
        
        // Getters and Setters
        public int getTotalDonors() { return totalDonors; }
        public void setTotalDonors(int totalDonors) { this.totalDonors = totalDonors; }
        
        public int getActiveDonors() { return activeDonors; }
        public void setActiveDonors(int activeDonors) { this.activeDonors = activeDonors; }
        
        public double getTotalDonations() { return totalDonations; }
        public void setTotalDonations(double totalDonations) { this.totalDonations = totalDonations; }
        
        public double getAverageDonation() { return averageDonation; }
        public void setAverageDonation(double averageDonation) { this.averageDonation = averageDonation; }
        
        public int getRecurringDonors() { return recurringDonors; }
        public void setRecurringDonors(int recurringDonors) { this.recurringDonors = recurringDonors; }
    }
    
    // Removed legacy SimpleDonation; using model Donation instead
} 