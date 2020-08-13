/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_ooad;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author Sunlight Traders
 */
public class Donor extends Person {
    List<String> pledges;
    List<Item> itemsDonated=new ArrayList<Item>();

    public Donor(int ID, String name, double CNIC) {
        super( name, CNIC);
        
        super.ID=ID;
    }

    public int getID() {
        return ID;
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
    
    public void setID(int ID) {
        this.ID = ID;
    }
    
    public void addGood(String goodName)
    {
        Item i=new Goods(goodName);
        
        itemsDonated.add(i);
    } 
    public void addFund(Long amount)
     {
         Item i=new Fund(amount);
         itemsDonated.add(i);
     } 

    public List<String> getPledges() {
        return pledges;
    }

    public void setPledges(List<String> pledges) {
        this.pledges = pledges;
    }

    public List<Item> getItemsDonated() {
        return itemsDonated;
    }

    public void setItemsDonated(List<Item> itemsDonated) {
        this.itemsDonated = itemsDonated;
    }
    

   
    
    
}
