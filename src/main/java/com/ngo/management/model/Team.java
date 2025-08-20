package com.ngo.management.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Simplified Team model for managing project teams
 */
public class Team {
    
    private String teamId;
    private String teamName;
    private String description;
    private List<Volunteer> members;
    private Volunteer teamLeader;
    private LocalDateTime createdAt;
    private boolean isActive;
    
    public Team(String teamName, String description) {
        this.teamId = "TEAM-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.teamName = teamName;
        this.description = description;
        this.members = new ArrayList<>();
        this.createdAt = LocalDateTime.now();
        this.isActive = true;
    }
    
    // Getters and Setters
    public String getTeamId() { return teamId; }
    
    public String getTeamName() { return teamName; }
    public void setTeamName(String teamName) { this.teamName = teamName; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public List<Volunteer> getMembers() { return new ArrayList<>(members); }
    
    public Volunteer getTeamLeader() { return teamLeader; }
    public void setTeamLeader(Volunteer teamLeader) { this.teamLeader = teamLeader; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    
    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }
    
    public void addMember(Volunteer volunteer) {
        if (volunteer != null && !members.contains(volunteer)) {
            members.add(volunteer);
        }
    }
    
    public void removeMember(Volunteer volunteer) {
        members.remove(volunteer);
    }
    
    public int getMemberCount() {
        return members.size();
    }
    
    @Override
    public String toString() {
        return String.format("Team[ID=%s, Name=%s, Members=%d]", teamId, teamName, getMemberCount());
    }
} 