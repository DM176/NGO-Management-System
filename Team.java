/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_ooad;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Sunlight Traders
 */
public class Team {
    int teamID;
    String teamName;
    Volunteer teamLeader;
    List<Volunteer> volunteers=new ArrayList<Volunteer>();

    public Team(int teamID, String teamName, Volunteer teamLeader) {
        this.teamID = teamID;
        this.teamName = teamName;
        this.teamLeader = teamLeader;
    }
    public void EditTeamLead()
    {
         Scanner input=new Scanner(System.in);
         int i=-1;
         
         System.out.println("If you want to change Team Leader Press 1 ");
         System.out.println("If you want to edit Team Leader Press 2 ");
         if(i==1)
         {
             System.out.println("Press 1 if you want to add a new volunteer to be team leader");
             System.out.println("Press 2 if you want to select leader from volunteers ");
             if(i==1)
             {
                 
                 System.out.println("Enter volunteer name");
                 String name=input.nextLine();
                 System.out.println("Enter volunteer cnic");
                 double CNIC =input.nextDouble();
                 Volunteer v=new Volunteer(name,CNIC);  //not getting id add in organization only
                 v.takeInput();
                 this.teamLeader=v;
             }  
             else if(i==2)
             {
                  System.out.println("Enter volunteer id ");
                  i=input.nextInt();
                  for(int j=0;j<volunteers.size();j++)
                  {
                      if(volunteers.get(j).getID()==i)
                      {
                          this.teamLeader=volunteers.get(j);
                          System.out.println("Volunteer successfully added");
                      } 
                  }    
             }   
         }      
     }
    public void editVolunteer()
    {
        Scanner input=new Scanner(System.in);
        System.out.println("Press 1 to add a volunteer");
        System.out.println("Press 2 to remove a volunteer");
        System.out.println("Press 3 to replace a volunteer");
        int i=input.nextInt();
        if(i==1)
        {
            System.out.println("Enter volunteer details");
            System.out.println("Enter volunteer name");
                 String name=input.nextLine();
                 System.out.println("Enter volunteer cnic");
                 double CNIC =input.nextDouble();
            Volunteer v=new Volunteer(name,CNIC);  //not getting id add in organization only
            v.takeInput();
            volunteers.add(v);
            
            
        }
        else if(i==2)
        {
            System.out.println("Enter volunteer id to be removed");
            i=input.nextInt();
            boolean found=false;
                 for(int j=0;j<volunteers.size();j++)
                {
                    if(volunteers.get(j).getID()==j)
                      {
                         System.out.println("Volunteer successfully removed");
                         j=volunteers.size();
                      }  
                }
                 if(!found)
                      {
                          System.out.println("Volunteer not found");
                      }
        }
        else if(i==3)
        {
              System.out.println("Enter volunteer details");
            System.out.println("Enter volunteer name");
                 String name=input.nextLine();
                 System.out.println("Enter volunteer cnic");
                 double CNIC =input.nextDouble();
            Volunteer v=new Volunteer(name,CNIC);   //not getting id add in organization only
            v.takeInput();
            System.out.println("Enter volunteer id to be replaced with new volunteer");
            i=input.nextInt();
            boolean found=false;
                 for(int j=0;j<volunteers.size();j++)
                {
                    if(volunteers.get(j).getID()==j)
                      {
                         System.out.println("Volunteer successfully replaced");
                         volunteers.set(j, v);
                         j=volunteers.size();
                      }  
                }
                 if(!found)
                      {
                          System.out.println("Volunteer not found");
                      }
        }
        
        
    }       
    public void addVolunteer(Volunteer v)
    {
        volunteers.add(v);
    }  
    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Volunteer getTeamLeader() {
        return teamLeader;
    }

    public void setTeamLeader(Volunteer teamLeader) {
        this.teamLeader = teamLeader;
    }

    public List<Volunteer> getVolunteers() {
        return volunteers;
    }

    public void setVolunteers(List<Volunteer> volunteers) {
        this.volunteers = volunteers;
    }
    
    
}
