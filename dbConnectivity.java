/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_ooad;

/**
 *
 * @author DELL
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import java.sql.ResultSet;
public class dbConnectivity
{
    
    Connection con;
    Statement stmt;
    
    dbConnectivity() //cons
    {
        try
        {
            String s = "jdbc:sqlserver://localhost:1433;databaseName=medicene";
            con=DriverManager.getConnection(s,"new_user","123");
            stmt = con.createStatement(); 
            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
  
   	public Boolean addProject ( Project p) 
        {
            try
            {
                //int rs=stmt.executeUpdate( "insert Project values ("+ p.ID+",'" + p+"'," + p.medPrice+ "," + p.medQuantity+ ")" );
                return true;
            }
            catch(Exception e)
            {
                 System.out.println(e);
            }
            return false;
        }
//	Boolean Delete_medicene (int id) 
//        {
//            
//        }
//	Boolean decreace_medicene_quan (int id)
//        {   
//        }
//	Boolean increase_medicene_price (int id, int price)  
//        {
//            
//        }
//	List<Medicene> getAllMedicene()
//        {
//            
//        }
}
