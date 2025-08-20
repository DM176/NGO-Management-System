package com.ngo.management.service;

import com.ngo.management.model.*;
import com.ngo.management.util.Logger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for managing project operations
 */
public class ProjectService {
    
    private List<Project> projects;
    
    public ProjectService() {
        this.projects = new ArrayList<>();
        initializeSampleData();
    }
    
    private void initializeSampleData() {
        // Add sample projects for demonstration
        Project educationProject = new Project(
            "Education for All", 
            "Providing quality education to underprivileged children", 
            50000.0
        );
        educationProject.setCategory(ProjectCategory.EDUCATION);
        educationProject.setPriority(ProjectPriority.HIGH);
        educationProject.setStartDate(LocalDate.now());
        educationProject.setEndDate(LocalDate.now().plusMonths(12));
        
        Project healthcareProject = new Project(
            "Community Health Initiative", 
            "Improving healthcare access in rural communities", 
            75000.0
        );
        healthcareProject.setCategory(ProjectCategory.HEALTHCARE);
        healthcareProject.setPriority(ProjectPriority.URGENT);
        healthcareProject.setStartDate(LocalDate.now().minusMonths(2));
        healthcareProject.setEndDate(LocalDate.now().plusMonths(10));
        
        projects.add(educationProject);
        projects.add(healthcareProject);
        
        Logger.info("Sample projects initialized");
    }
    
    // CRUD Operations
    public Project createProject(String name, String description, double budget) {
        try {
            Project project = new Project(name, description, budget);
            projects.add(project);
            Logger.info("Project created: " + project.getProjectId());
            return project;
        } catch (Exception e) {
            Logger.error("Failed to create project: " + name, e);
            throw new RuntimeException("Failed to create project", e);
        }
    }
    
    public Optional<Project> getProjectById(String projectId) {
        return projects.stream()
            .filter(p -> p.getProjectId().equals(projectId))
            .findFirst();
    }
    
    public List<Project> getAllProjects() {
        return new ArrayList<>(projects);
    }
    
    public List<Project> getActiveProjects() {
        return projects.stream()
            .filter(Project::isActive)
            .collect(Collectors.toList());
    }
    
    public List<Project> getProjectsByStatus(ProjectStatus status) {
        return projects.stream()
            .filter(p -> p.getStatus() == status)
            .collect(Collectors.toList());
    }
    
    public List<Project> getProjectsByCategory(ProjectCategory category) {
        return projects.stream()
            .filter(p -> p.getCategory() == category)
            .collect(Collectors.toList());
    }
    
    public List<Project> getProjectsByPriority(ProjectPriority priority) {
        return projects.stream()
            .filter(p -> p.getPriority() == priority)
            .collect(Collectors.toList());
    }
    
    public boolean updateProject(String projectId, Project updatedProject) {
        try {
            Optional<Project> existingProject = getProjectById(projectId);
            if (existingProject.isPresent()) {
                Project project = existingProject.get();
                // Update fields
                project.setProjectName(updatedProject.getProjectName());
                project.setDescription(updatedProject.getDescription());
                project.setRequiredBudget(updatedProject.getRequiredBudget());
                project.setCategory(updatedProject.getCategory());
                project.setPriority(updatedProject.getPriority());
                project.setStartDate(updatedProject.getStartDate());
                project.setEndDate(updatedProject.getEndDate());
                
                Logger.info("Project updated: " + projectId);
                return true;
            }
            return false;
        } catch (Exception e) {
            Logger.error("Failed to update project: " + projectId, e);
            return false;
        }
    }
    
    public boolean deleteProject(String projectId) {
        try {
            Optional<Project> project = getProjectById(projectId);
            if (project.isPresent()) {
                project.get().setActive(false);
                Logger.info("Project deactivated: " + projectId);
                return true;
            }
            return false;
        } catch (Exception e) {
            Logger.error("Failed to delete project: " + projectId, e);
            return false;
        }
    }
    
    // Business Logic Methods
    public List<Project> getOverdueProjects() {
        return projects.stream()
            .filter(Project::isDelayed)
            .collect(Collectors.toList());
    }
    
    public List<Project> getOverBudgetProjects() {
        return projects.stream()
            .filter(Project::isOverBudget)
            .collect(Collectors.toList());
    }
    
    public List<Project> getProjectsEndingSoon(int daysThreshold) {
        LocalDate thresholdDate = LocalDate.now().plusDays(daysThreshold);
        return projects.stream()
            .filter(p -> p.getEndDate() != null && p.getEndDate().isBefore(thresholdDate))
            .collect(Collectors.toList());
    }
    
    public double getTotalBudgetAllocated() {
        return projects.stream()
            .mapToDouble(Project::getAllocatedBudget)
            .sum();
    }
    
    public double getTotalBudgetSpent() {
        return projects.stream()
            .mapToDouble(Project::getSpentBudget)
            .sum();
    }
    
    public int getTotalActiveProjects() {
        return (int) projects.stream()
            .filter(Project::isActive)
            .count();
    }
    
    public ProjectStatistics getProjectStatistics() {
        ProjectStatistics stats = new ProjectStatistics();
        stats.setTotalProjects(projects.size());
        stats.setActiveProjects(getTotalActiveProjects());
        stats.setTotalBudgetAllocated(getTotalBudgetAllocated());
        stats.setTotalBudgetSpent(getTotalBudgetSpent());
        stats.setOverdueProjects(getOverdueProjects().size());
        stats.setOverBudgetProjects(getOverBudgetProjects().size());
        
        return stats;
    }
    
    // Search and Filter Methods
    public List<Project> searchProjects(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllProjects();
        }
        
        String term = searchTerm.toLowerCase();
        return projects.stream()
            .filter(p -> p.getProjectName().toLowerCase().contains(term) ||
                        p.getDescription().toLowerCase().contains(term))
            .collect(Collectors.toList());
    }
    
    public List<Project> getProjectsByDateRange(LocalDate startDate, LocalDate endDate) {
        return projects.stream()
            .filter(p -> p.getStartDate() != null && p.getEndDate() != null)
            .filter(p -> !p.getStartDate().isAfter(endDate) && !p.getEndDate().isBefore(startDate))
            .collect(Collectors.toList());
    }
    
    // Project Management Methods
    public boolean assignProjectManager(String projectId, Volunteer projectManager) {
        try {
            Optional<Project> project = getProjectById(projectId);
            if (project.isPresent()) {
                project.get().setProjectManager(projectManager);
                Logger.info("Project manager assigned to project: " + projectId);
                return true;
            }
            return false;
        } catch (Exception e) {
            Logger.error("Failed to assign project manager to project: " + projectId, e);
            return false;
        }
    }
    
    public boolean addVolunteerToProject(String projectId, Volunteer volunteer) {
        try {
            Optional<Project> project = getProjectById(projectId);
            if (project.isPresent()) {
                project.get().addVolunteer(volunteer);
                Logger.info("Volunteer added to project: " + projectId);
                return true;
            }
            return false;
        } catch (Exception e) {
            Logger.error("Failed to add volunteer to project: " + projectId, e);
            return false;
        }
    }
    
    public boolean updateProjectStatus(String projectId, ProjectStatus newStatus) {
        try {
            Optional<Project> project = getProjectById(projectId);
            if (project.isPresent()) {
                project.get().updateStatus(newStatus);
                Logger.info("Project status updated: " + projectId + " -> " + newStatus);
                return true;
            }
            return false;
        } catch (Exception e) {
            Logger.error("Failed to update project status: " + projectId, e);
            return false;
        }
    }
    
    // Inner class for project statistics
    public static class ProjectStatistics {
        private int totalProjects;
        private int activeProjects;
        private double totalBudgetAllocated;
        private double totalBudgetSpent;
        private int overdueProjects;
        private int overBudgetProjects;
        
        // Getters and Setters
        public int getTotalProjects() { return totalProjects; }
        public void setTotalProjects(int totalProjects) { this.totalProjects = totalProjects; }
        
        public int getActiveProjects() { return activeProjects; }
        public void setActiveProjects(int activeProjects) { this.activeProjects = activeProjects; }
        
        public double getTotalBudgetAllocated() { return totalBudgetAllocated; }
        public void setTotalBudgetAllocated(double totalBudgetAllocated) { this.totalBudgetAllocated = totalBudgetAllocated; }
        
        public double getTotalBudgetSpent() { return totalBudgetSpent; }
        public void setTotalBudgetSpent(double totalBudgetSpent) { this.totalBudgetSpent = totalBudgetSpent; }
        
        public int getOverdueProjects() { return overdueProjects; }
        public void setOverdueProjects(int overdueProjects) { this.overdueProjects = overdueProjects; }
        
        public int getOverBudgetProjects() { return overBudgetProjects; }
        public void setOverBudgetProjects(int overBudgetProjects) { this.overBudgetProjects = overBudgetProjects; }
    }
} 