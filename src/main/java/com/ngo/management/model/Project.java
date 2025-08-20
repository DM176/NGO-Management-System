package com.ngo.management.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Enhanced Project model with comprehensive project management features
 */
public class Project {
    
    // Core project information
    private String projectId;
    private String projectName;
    private String description;
    private ProjectStatus status;
    private ProjectPriority priority;
    private ProjectCategory category;
    
    // Financial information
    private double requiredBudget;
    private double allocatedBudget;
    private double spentBudget;
    private String currency;
    
    // Timeline information
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate actualStartDate;
    private LocalDate actualEndDate;
    private int estimatedDuration; // in days
    
    // Team and resources
    private List<Volunteer> volunteers;
    private Volunteer projectManager;
    private Team assignedTeam;
    private List<Donor> donors;
    
    // Beneficiaries and goals
    private List<Needy> beneficiaries;
    private List<ProjectGoal> goals;
    private List<ProjectMilestone> milestones;
    
    // Documentation and tracking
    private String planDescription;
    private String riskAssessment;
    private String successMetrics;
    private List<ProjectDocument> documents;
    private List<ProjectUpdate> updates;
    
    // Metadata
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
    private boolean isActive;
    
    // Constructor
    public Project(String projectName, String description, double requiredBudget) {
        this.projectId = generateProjectId();
        this.projectName = projectName;
        this.description = description;
        this.requiredBudget = requiredBudget;
        this.status = ProjectStatus.PLANNING;
        this.priority = ProjectPriority.MEDIUM;
        this.category = ProjectCategory.EDUCATION;
        this.currency = "USD";
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.isActive = true;
        
        // Initialize collections
        this.volunteers = new ArrayList<>();
        this.donors = new ArrayList<>();
        this.beneficiaries = new ArrayList<>();
        this.goals = new ArrayList<>();
        this.milestones = new ArrayList<>();
        this.documents = new ArrayList<>();
        this.updates = new ArrayList<>();
    }
    
    // Generate unique project ID
    private String generateProjectId() {
        return "PRJ-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
    
    // Business logic methods
    public double getBudgetUtilization() {
        if (allocatedBudget == 0) return 0;
        return (spentBudget / allocatedBudget) * 100;
    }
    
    public double getBudgetRemaining() {
        return allocatedBudget - spentBudget;
    }
    
    public boolean isOverBudget() {
        return spentBudget > allocatedBudget;
    }
    
    public boolean isDelayed() {
        if (endDate == null || actualEndDate == null) return false;
        return actualEndDate.isAfter(endDate);
    }
    
    public int getDaysRemaining() {
        if (endDate == null) return 0;
        LocalDate today = LocalDate.now();
        if (today.isAfter(endDate)) return 0;
        return (int) java.time.temporal.ChronoUnit.DAYS.between(today, endDate);
    }
    
    public double getCompletionPercentage() {
        if (milestones.isEmpty()) return 0;
        long completedMilestones = milestones.stream()
            .filter(m -> m.getStatus() == MilestoneStatus.COMPLETED)
            .count();
        return (double) completedMilestones / milestones.size() * 100;
    }
    
    public void addVolunteer(Volunteer volunteer) {
        if (volunteer != null && !volunteers.contains(volunteer)) {
            volunteers.add(volunteer);
            updatedAt = LocalDateTime.now();
        }
    }
    
    public void removeVolunteer(Volunteer volunteer) {
        if (volunteers.remove(volunteer)) {
            updatedAt = LocalDateTime.now();
        }
    }
    
    public void addDonor(Donor donor) {
        if (donor != null && !donors.contains(donor)) {
            donors.add(donor);
            updatedAt = LocalDateTime.now();
        }
    }
    
    public void addGoal(ProjectGoal goal) {
        if (goal != null && !goals.contains(goal)) {
            goals.add(goal);
            updatedAt = LocalDateTime.now();
        }
    }
    
    public void addMilestone(ProjectMilestone milestone) {
        if (milestone != null && !milestones.contains(milestone)) {
            milestones.add(milestone);
            updatedAt = LocalDateTime.now();
        }
    }
    
    public void updateStatus(ProjectStatus newStatus) {
        this.status = newStatus;
        this.updatedAt = LocalDateTime.now();
        
        // Add status update to project updates
        ProjectUpdate update = new ProjectUpdate(
            "Project status changed to: " + newStatus,
            "System",
            UpdateType.STATUS_CHANGE
        );
        updates.add(update);
    }
    
    // Getters and Setters
    public String getProjectId() { return projectId; }
    
    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { 
        this.projectName = projectName; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { 
        this.description = description; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public ProjectStatus getStatus() { return status; }
    
    public ProjectPriority getPriority() { return priority; }
    public void setPriority(ProjectPriority priority) { 
        this.priority = priority; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public ProjectCategory getCategory() { return category; }
    public void setCategory(ProjectCategory category) { 
        this.category = category; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public double getRequiredBudget() { return requiredBudget; }
    public void setRequiredBudget(double requiredBudget) { 
        this.requiredBudget = requiredBudget; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public double getAllocatedBudget() { return allocatedBudget; }
    public void setAllocatedBudget(double allocatedBudget) { 
        this.allocatedBudget = allocatedBudget; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public double getSpentBudget() { return spentBudget; }
    public void setSpentBudget(double spentBudget) { 
        this.spentBudget = spentBudget; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { 
        this.currency = currency; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { 
        this.startDate = startDate; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { 
        this.endDate = endDate; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public LocalDate getActualStartDate() { return actualStartDate; }
    public void setActualStartDate(LocalDate actualStartDate) { 
        this.actualStartDate = actualStartDate; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public LocalDate getActualEndDate() { return actualEndDate; }
    public void setActualEndDate(LocalDate actualEndDate) { 
        this.actualEndDate = actualEndDate; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public int getEstimatedDuration() { return estimatedDuration; }
    public void setEstimatedDuration(int estimatedDuration) { 
        this.estimatedDuration = estimatedDuration; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public List<Volunteer> getVolunteers() { return new ArrayList<>(volunteers); }
    
    public Volunteer getProjectManager() { return projectManager; }
    public void setProjectManager(Volunteer projectManager) { 
        this.projectManager = projectManager; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public Team getAssignedTeam() { return assignedTeam; }
    public void setAssignedTeam(Team assignedTeam) { 
        this.assignedTeam = assignedTeam; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public List<Donor> getDonors() { return new ArrayList<>(donors); }
    
    public List<Needy> getBeneficiaries() { return new ArrayList<>(beneficiaries); }
    
    public List<ProjectGoal> getGoals() { return new ArrayList<>(goals); }
    
    public List<ProjectMilestone> getMilestones() { return new ArrayList<>(milestones); }
    
    public String getPlanDescription() { return planDescription; }
    public void setPlanDescription(String planDescription) { 
        this.planDescription = planDescription; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public String getRiskAssessment() { return riskAssessment; }
    public void setRiskAssessment(String riskAssessment) { 
        this.riskAssessment = riskAssessment; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public String getSuccessMetrics() { return successMetrics; }
    public void setSuccessMetrics(String successMetrics) { 
        this.successMetrics = successMetrics; 
        this.updatedAt = LocalDateTime.now();
    }
    
    public List<ProjectDocument> getDocuments() { return new ArrayList<>(documents); }
    
    public List<ProjectUpdate> getUpdates() { return new ArrayList<>(updates); }
    
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
        return String.format("Project[ID=%s, Name=%s, Status=%s, Budget=%.2f %s]", 
            projectId, projectName, status, requiredBudget, currency);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Project project = (Project) obj;
        return projectId.equals(project.projectId);
    }
    
    @Override
    public int hashCode() {
        return projectId.hashCode();
    }
} 