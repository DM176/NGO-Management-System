/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_ooad;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Sunlight Traders
 */
public class Needy extends Person {
    int houseIncome;
    int interviewsPassed; 
    int interviewsFailed;
    List<Project> projects=new ArrayList<Project>();
    List <Item> items=new ArrayList<Item>();
    public Needy(String name, double CNIC,String adress,int ph,Date dob) {
        super(name, CNIC,adress,ph,dob);
        interviewsPassed=0;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
    
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
    
    public int getInterviewsPassed() {
        return interviewsPassed;
    }

    public void setInterviewsPassed(int interviewsPassed) {
        this.interviewsPassed = interviewsPassed;
    }

    public int getInterviewsFailed() {
        return interviewsFailed;
    }

    public void setInterviewsFailed(int interviewsFailed) {
        this.interviewsFailed = interviewsFailed;
    }
    
    public void passInterview()
    {
        interviewsPassed++;
    }
    public void failInterview()
    {
        interviewsFailed--;
    }

    public int getHouseIncome() {
        return houseIncome;
    }

    public void setHouseIncome(int houseIncome) {
        this.houseIncome = houseIncome;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCNIC() {
        return CNIC;
    }

    public void setCNIC(double CNIC) {
        this.CNIC = CNIC;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(int phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    
    
    
}
