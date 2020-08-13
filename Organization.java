/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_ooad;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Sunlight Traders
 */
public class Organization {
    public static Organization instance;
    String name;
    List<Project> projects=new ArrayList<Project>();
    List<Team> teams=new ArrayList<Team>();
    List<Donor> donors=new ArrayList<Donor>();
    List<Assessor> assessors=new ArrayList<Assessor>();
    List<Needy> needyPeople=new ArrayList<Needy>();
    List<Needy>applicants=new ArrayList<Needy>();
    int TotalVolunteers;
    
    int projectId;
    int teamId;
    int DonorId;
    

    public Organization () {
        name = "FAST NGO";
        projectId=1;
        teamId=1;
        DonorId=1;
    }
    public static Organization getInstance()
        {
             if(instance==null)
             {
                 instance=new Organization();
             }
             return instance;
        }   
    public void enterApplicationInformation(String name, double CNIC,String adress,int ph,Date dob)
    {
        Needy needy=new Needy(name,CNIC,adress,ph,dob);
        applicants.add(needy);
    }
    public void shortListApplicant()
    {
        Needy needy;
        boolean shortListed=false;
        for(int i=0;i<applicants.size() ;i++)
        {
            needy=applicants.get(i);
            if(needy.getInterviewsPassed()>=3 && needy.getInterviewsFailed()<=1 && needy.getHouseIncome()<=20000)
            {   
                    applicants.remove(i);
                    needyPeople.add(needy);      
            }
            else
            {
                System.out.println(needy.getName()+" is not shortListed");
            }
        }
    }
    public void searchDonor(Donor donor)
    {
        List<Item> itemsDonated;
        boolean found=false;
        int totalDonations=0;
        int index=0;
        for(int i=0;i<donors.size() && !found;i++)
        {
            
           if(donors.get(i).getID()==donor.getID())
           {
               found=true;
               index=i;
               itemsDonated=donors.get(i).getItemsDonated();
               totalDonations=itemsDonated.size();
           }
        }
        
        List<Donor> donorsOfProject;
        List<Needy> needy;
        int totalProjects=0;
        int view=0;
        for(int j=0;j<projects.size();j++)
        {
            found=false;
            donorsOfProject=projects.get(j).getDonors();
             for(int i=0;i<donorsOfProject.size() && !found;i++)
             {
                 if(donorsOfProject.get(i).getID()==donor.getID())
                 {
                     found=true;
                     totalProjects++;
                     System.out.println(projects.get(j).getProjectName());
                     if(view==1)  //if uer want to see beneficiary of a project
                     {
                         needy=projects.get(j).getNeedy();
                         for(int k=0;k<needy.size();k++)
                         {
                             System.out.println(needy.get(k).getName());
                         }
                     }
                 }
             }
        }
        System.out.println("Total Projects: "+totalProjects);
        System.out.println("Total Donations: "+totalDonations);
        
    }
    public void viewBeneficiariesOfProject(Project p)
    {
        boolean found=false;
        List<Needy> beneficiaries;
        for(int i=0;i<projects.size() && !found;i++)
        {
            if(projects.get(i).ID==p.getID())
            {
                found=true;
                beneficiaries=projects.get(i).getNeedy();
                for(int j=0;j<beneficiaries.size();j++)
                {
                    System.out.println(beneficiaries.get(j).getName());
                }
            }
        }
        
    }
    public void searchBeneficiary(Needy needy)
    {
        List<Project> projects=needy.getProjects();
        List<Item> items=needy.getItems();
        for(int i=0;i<projects.size();i++)
        {
            
        }
        for(int j=0;j<items.size();j++)
        {
        
        }
    }
    public void addProject(String name,Volunteer volunteer,double requiredBudget)
    {
        Scanner input=new Scanner(System.in);
        Project p=new Project(projectId,name,requiredBudget);
        p.addVolunteer(volunteer);
        System.out.println("Enter projectId of team initiating the project ");
        int teamId=-1;
            teamId =input.nextInt();
        
        boolean found=false;
        for(int i=0;i<teams.size();i++)
        {
            if(teamId==teams.get(i).getTeamID())
            {
                found=true;
                p.allocateTeam(teams.get(i));
            }  
        }
        if(!found)
        {
            System.out.println("No such team found");
        }
        projects.add(p);
    }
    public void allocateTeam(Project p)
    {
        System.out.println("Enter projectId of team initiating the project ");
        int teamId=-1;
        //teamId =input.nextInt();
        
        boolean found=false;
        for(int i=0;i<teams.size();i++)
        {
            if(teamId==teams.get(i).getTeamID())
            {
                found=true;
                p.allocateTeam(teams.get(i));
            }  
        }
        if(!found)
        {
            System.out.println("No such team found");
        }
    }
    public void EditTeam()
     {
        Scanner input=new Scanner(System.in);
        int INPUT=-1;
        System.out.println("Enter team id which you are going to edit");
        INPUT=input.nextInt();
        boolean found=false;
        for(int i=0;i<teams.size() && !found;i++)
        {
            if(INPUT==teams.get(i).getTeamID())
            {
                System.out.println("Enter 1 if you want to edit Team Lead");
                if(INPUT==1)
                {
                    teams.get(i).EditTeamLead();
                } 
                else if(INPUT==2)
                {
                    teams.get(i).editVolunteer();
                }   
                i=teams.size();
                found=true;
            }
        }
        if(!found)
        {
            System.out.println("Team not found");
        }
     }       
    
    public void addDonor()
    {
                Scanner input =new Scanner(System.in);
                System.out.println("Enter Donors name");
                 String name=input.nextLine();
                 System.out.println("Enter volunteer cnic");
                 double CNIC =input.nextDouble();
                Donor d=new Donor(DonorId,name,CNIC);
                long fund=123123;
                d.addFund( fund);
                d.addGood("pants");
                donors.add(d);
                
    }
    public Project SearchProject(String name)
      {
          for(int i=0;i<projects.size();i++)
            {
                if(projects.get(i).getProjectName().equals(name))
                  {
                      return projects.get(i);
                  }
                else if(projects.get(i).getProjectManager().equals(name))
                  {
                      return projects.get(i);
                  }  
            }
          return null;
      }       
    public void addTeam(int teamId,String teamName,Volunteer teamLeader)
      {
          Team t=new Team(teamId,teamName,teamLeader);
           teams.add(t);
      }  
    
    
    
    
}
