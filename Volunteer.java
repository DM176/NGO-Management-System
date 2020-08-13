/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_ooad;
import java.time.LocalTime;
import java.util.Scanner;
/**
 *
 * @author Sunlight Traders
 */
public class Volunteer extends Person{
    int hours;
    int start;
    int end;

    
    public Volunteer( String name, double CNIC,int hours, int start, int end) {
        super(name, CNIC);
        this.hours = hours;
        this.start = start;
        this.end = end;
    }

    public Volunteer( String name, double CNIC) {
        super(name, CNIC);
        this.hours=0;
        this.start=0;
        this.end=0;
    }
    public void takeInput()
     {
         Scanner input=new Scanner (System.in);
         super.takeInput();
         System.out.println("Enter total hours volunteer will work");
         this.hours=input.nextInt();
         System.out.println("Enter volunteer's working hours start time in 24 hours format");
         this.start=input.nextInt();
         System.out.println("Enter volunteer's working hours end time in 24 hours format");
         this.end=input.nextInt();
    }       

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
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
    
    
}
