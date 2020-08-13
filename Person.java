/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_ooad;

import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Sunlight Traders
 */
public class Person {
    int ID;
    String name;
    double CNIC;
    String adress;
    int phoneNo;
    Date dateOfBirth;
    public Person(String name, double CNIC,String adress,int ph,Date dob) {
        
        this.name = name;
        this.adress=adress;
        this.CNIC = CNIC;
        this.dateOfBirth=dob;
        this.phoneNo=ph;
    }
    public Person(String name,double CNIC)
    {
        this.name = name;
        this.CNIC = CNIC;
        
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
    
    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
    
    public void takeInput()
    {
        Scanner input=new Scanner (System.in);
         System.out.println("Enter volunteer name");
         this.name=input.nextLine();
         System.out.println("Enter volunteer cnic");
         this.CNIC =input.nextDouble();
    }   
    
}
