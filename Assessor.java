/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_ooad;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sunlight Traders
 */
public class Assessor extends Person {
    
    List<Needy> needy=new ArrayList<Needy>();
    public Assessor(String name, double CNIC) {
        super(name, CNIC);
    }
    public void conductInterview(Needy needy)
    {
        this.needy.add(needy);
        boolean passed=false;
        if(passed)
        {
            needy.passInterview();
        }
        else 
        {
            needy.failInterview();
        }
    }
    
}
