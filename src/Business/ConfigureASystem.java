/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business;

import Business.Employee.Employee;
import Business.Role.SystemAdmin;

/**
 *
 * @author Nidhi Goyal
 */
public class ConfigureASystem {
    public static EcoSystem configure() {
        
        EcoSystem system = EcoSystem.getInstance();
        
        //Create a network
        //create an enterprise
        //initialize some organizations
        //have some employees 
        //create user account
        
        
        Employee employee = new Employee("sysadmin");
        
        system.getUserAccountDirectory().createUserAccount("sysadmin", "sysadmin", employee, new SystemAdmin());
        
        return system;
    }
    
}
