package com.ngo.management.service;

import com.ngo.management.model.*;
import com.ngo.management.util.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for managing beneficiary (needy) operations
 */
public class NeedyService {
    
    private List<Needy> beneficiaries;
    
    public NeedyService() {
        this.beneficiaries = new ArrayList<>();
        initializeSampleData();
    }
    
    private void initializeSampleData() {
        // Add sample beneficiaries for demonstration
        Needy beneficiary1 = new Needy("Sarah", "Davis", "sarah.davis@email.com", "+1-555-0505");
        beneficiary1.setMonthlyIncome(15000.0);
        beneficiary1.setPrimaryNeed("Education");
        beneficiary1.setUrgencyLevel("High");
        beneficiary1.setOccupation("Student");
        
        Needy beneficiary2 = new Needy("Michael", "Brown", "michael.brown@email.com", "+1-555-0606");
        beneficiary2.setMonthlyIncome(18000.0);
        beneficiary2.setPrimaryNeed("Healthcare");
        beneficiary2.setUrgencyLevel("Medium");
        beneficiary2.setOccupation("Construction Worker");
        
        beneficiaries.add(beneficiary1);
        beneficiaries.add(beneficiary2);
        
        Logger.info("Sample beneficiaries initialized");
    }
    
    // CRUD Operations
    public Needy createBeneficiary(String firstName, String lastName, String email, String phone) {
        try {
            Needy beneficiary = new Needy(firstName, lastName, email, phone);
            beneficiaries.add(beneficiary);
            Logger.info("Beneficiary created: " + beneficiary.getBeneficiaryId());
            return beneficiary;
        } catch (Exception e) {
            Logger.error("Failed to create beneficiary: " + firstName + " " + lastName, e);
            throw new RuntimeException("Failed to create beneficiary", e);
        }
    }
    
    public Optional<Needy> getBeneficiaryById(String beneficiaryId) {
        return beneficiaries.stream()
            .filter(b -> b.getBeneficiaryId().equals(beneficiaryId))
            .findFirst();
    }
    
    public List<Needy> getAllBeneficiaries() {
        return new ArrayList<>(beneficiaries);
    }
    
    public List<Needy> getActiveBeneficiaries() {
        return beneficiaries.stream()
            .filter(Needy::isActive)
            .collect(Collectors.toList());
    }
    
    public List<Needy> getEligibleBeneficiaries() {
        return beneficiaries.stream()
            .filter(Needy::isEligible)
            .collect(Collectors.toList());
    }
    
    public boolean updateBeneficiary(String beneficiaryId, Needy updatedBeneficiary) {
        try {
            Optional<Needy> existingBeneficiary = getBeneficiaryById(beneficiaryId);
            if (existingBeneficiary.isPresent()) {
                Needy beneficiary = existingBeneficiary.get();
                beneficiary.setFirstName(updatedBeneficiary.getFirstName());
                beneficiary.setLastName(updatedBeneficiary.getLastName());
                beneficiary.setEmail(updatedBeneficiary.getEmail());
                beneficiary.setPhone(updatedBeneficiary.getPhone());
                beneficiary.setAddress(updatedBeneficiary.getAddress());
                beneficiary.setMonthlyIncome(updatedBeneficiary.getMonthlyIncome());
                beneficiary.setPrimaryNeed(updatedBeneficiary.getPrimaryNeed());
                beneficiary.setUrgencyLevel(updatedBeneficiary.getUrgencyLevel());
                
                Logger.info("Beneficiary updated: " + beneficiaryId);
                return true;
            }
            return false;
        } catch (Exception e) {
            Logger.error("Failed to update beneficiary: " + beneficiaryId, e);
            return false;
        }
    }
    
    public boolean deleteBeneficiary(String beneficiaryId) {
        try {
            Optional<Needy> beneficiary = getBeneficiaryById(beneficiaryId);
            if (beneficiary.isPresent()) {
                beneficiary.get().setActive(false);
                Logger.info("Beneficiary deactivated: " + beneficiaryId);
                return true;
            }
            return false;
        } catch (Exception e) {
            Logger.error("Failed to delete beneficiary: " + beneficiaryId, e);
            return false;
        }
    }
    
    // Business Logic Methods
    public List<Needy> getBeneficiariesByNeed(String need) {
        if (need == null || need.trim().isEmpty()) {
            return getAllBeneficiaries();
        }
        
        String needTerm = need.toLowerCase();
        return beneficiaries.stream()
            .filter(b -> b.getPrimaryNeed() != null && b.getPrimaryNeed().toLowerCase().contains(needTerm))
            .collect(Collectors.toList());
    }
    
    public List<Needy> getBeneficiariesByUrgency(String urgencyLevel) {
        if (urgencyLevel == null || urgencyLevel.trim().isEmpty()) {
            return getAllBeneficiaries();
        }
        
        String urgencyTerm = urgencyLevel.toLowerCase();
        return beneficiaries.stream()
            .filter(b -> b.getUrgencyLevel() != null && b.getUrgencyLevel().toLowerCase().contains(urgencyTerm))
            .collect(Collectors.toList());
    }
    
    public List<Needy> getBeneficiariesByIncomeRange(double minIncome, double maxIncome) {
        return beneficiaries.stream()
            .filter(b -> b.getMonthlyIncome() >= minIncome && b.getMonthlyIncome() <= maxIncome)
            .collect(Collectors.toList());
    }
    
    public List<Needy> getGraduatedBeneficiaries() {
        return beneficiaries.stream()
            .filter(Needy::isGraduated)
            .collect(Collectors.toList());
    }
    
    public double getAverageMonthlyIncome() {
        if (beneficiaries.isEmpty()) return 0.0;
        return beneficiaries.stream()
            .mapToDouble(Needy::getMonthlyIncome)
            .average()
            .orElse(0.0);
    }
    
    public int getTotalActiveBeneficiaries() {
        return (int) beneficiaries.stream()
            .filter(Needy::isActive)
            .count();
    }
    
    public NeedyStatistics getBeneficiaryStatistics() {
        NeedyStatistics stats = new NeedyStatistics();
        stats.setTotalBeneficiaries(beneficiaries.size());
        stats.setActiveBeneficiaries(getTotalActiveBeneficiaries());
        stats.setEligibleBeneficiaries(getEligibleBeneficiaries().size());
        stats.setGraduatedBeneficiaries(getGraduatedBeneficiaries().size());
        stats.setAverageMonthlyIncome(getAverageMonthlyIncome());
        
        return stats;
    }
    
    // Search and Filter Methods
    public List<Needy> searchBeneficiaries(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllBeneficiaries();
        }
        
        String term = searchTerm.toLowerCase();
        return beneficiaries.stream()
            .filter(b -> b.getFirstName().toLowerCase().contains(term) ||
                        b.getLastName().toLowerCase().contains(term) ||
                        b.getEmail().toLowerCase().contains(term) ||
                        (b.getPrimaryNeed() != null && b.getPrimaryNeed().toLowerCase().contains(term)))
            .collect(Collectors.toList());
    }
    
    public List<Needy> getBeneficiariesByOccupation(String occupation) {
        if (occupation == null || occupation.trim().isEmpty()) {
            return getAllBeneficiaries();
        }
        
        String occupationTerm = occupation.toLowerCase();
        return beneficiaries.stream()
            .filter(b -> b.getOccupation() != null && b.getOccupation().toLowerCase().contains(occupationTerm))
            .collect(Collectors.toList());
    }
    
    // Program Management Methods
    public boolean enrollBeneficiaryInProgram(String beneficiaryId, String programName) {
        try {
            Optional<Needy> beneficiary = getBeneficiaryById(beneficiaryId);
            if (beneficiary.isPresent()) {
                beneficiary.get().enrollInProgram(programName);
                Logger.info("Beneficiary enrolled in program: " + beneficiaryId + " -> " + programName);
                return true;
            }
            return false;
        } catch (Exception e) {
            Logger.error("Failed to enroll beneficiary in program: " + beneficiaryId, e);
            return false;
        }
    }
    
    public boolean completeProgram(String beneficiaryId, String programName) {
        try {
            Optional<Needy> beneficiary = getBeneficiaryById(beneficiaryId);
            if (beneficiary.isPresent()) {
                beneficiary.get().completeProgram(programName);
                Logger.info("Program completed for beneficiary: " + beneficiaryId + " -> " + programName);
                return true;
            }
            return false;
        } catch (Exception e) {
            Logger.error("Failed to complete program for beneficiary: " + beneficiaryId, e);
            return false;
        }
    }
    
    public boolean graduateBeneficiary(String beneficiaryId) {
        try {
            Optional<Needy> beneficiary = getBeneficiaryById(beneficiaryId);
            if (beneficiary.isPresent()) {
                beneficiary.get().graduate();
                Logger.info("Beneficiary graduated: " + beneficiaryId);
                return true;
            }
            return false;
        } catch (Exception e) {
            Logger.error("Failed to graduate beneficiary: " + beneficiaryId, e);
            return false;
        }
    }
    
    // Inner class for beneficiary statistics
    public static class NeedyStatistics {
        private int totalBeneficiaries;
        private int activeBeneficiaries;
        private int eligibleBeneficiaries;
        private int graduatedBeneficiaries;
        private double averageMonthlyIncome;
        
        // Getters and Setters
        public int getTotalBeneficiaries() { return totalBeneficiaries; }
        public void setTotalBeneficiaries(int totalBeneficiaries) { this.totalBeneficiaries = totalBeneficiaries; }
        
        public int getActiveBeneficiaries() { return activeBeneficiaries; }
        public void setActiveBeneficiaries(int activeBeneficiaries) { this.activeBeneficiaries = activeBeneficiaries; }
        
        public int getEligibleBeneficiaries() { return eligibleBeneficiaries; }
        public void setEligibleBeneficiaries(int eligibleBeneficiaries) { this.eligibleBeneficiaries = eligibleBeneficiaries; }
        
        public int getGraduatedBeneficiaries() { return graduatedBeneficiaries; }
        public void setGraduatedBeneficiaries(int graduatedBeneficiaries) { this.graduatedBeneficiaries = graduatedBeneficiaries; }
        
        public double getAverageMonthlyIncome() { return averageMonthlyIncome; }
        public void setAverageMonthlyIncome(double averageMonthlyIncome) { this.averageMonthlyIncome = averageMonthlyIncome; }
    }
} 