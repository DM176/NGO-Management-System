/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_ooad;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author Sunlight Traders
 */
public class Project {
    int ID;
    String projectName;
    List<String> goals=new ArrayList<String>();
    List<Needy> needyPeople=new ArrayList<Needy>();
    List<Volunteer> volunteers=new ArrayList<Volunteer>();
    Volunteer projectManager;
    Team team;
    List<Donor> donors=new ArrayList<Donor>();
    
    
    String planDescription;
    double requiredBudget;

    public Project(int ID, String projectName,double requiredBudget ) {
        this.ID = ID;
        this.projectName = projectName;
        
        this.requiredBudget=requiredBudget;
    }

    public List<Donor> getDonors() {
        return donors;
    }

    public void setDonors(List<Donor> donors) {
        this.donors = donors;
    }
    
    public Volunteer getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(Volunteer projectManager) {
        this.projectManager = projectManager;
    }
    
    public void allocateTeam(Team t)
     {
         team=t;
     }       
    public void addVolunteer(Volunteer v)
    {
        volunteers.add(v);
    }      

    public double getRequiredBudget() {
        return requiredBudget;
    }

    public void setRequiredBudget(double requiredBudget) {
        this.requiredBudget = requiredBudget;
    }
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<String> getGoals() {
        return goals;
    }

    public void setGoals(List<String> goals) {
        this.goals = goals;
    }

    public List<Needy> getNeedy() {
        return needyPeople;
    }

    public void setNeedy(List<Needy> needy) {
        this.needyPeople = needy;
    }

    public String getPlanDescription() {
        return planDescription;
    }

    public void setPlanDescription(String planDescription) {
        this.planDescription = planDescription;
    }
    
    
}

