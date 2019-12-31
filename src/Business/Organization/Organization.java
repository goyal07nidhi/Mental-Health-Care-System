/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Organization;

import Business.Role.Role;
import Business.UserAccount.UserAccountDirectory;
import Business.WorkQueue.WorkQueue;
import java.util.ArrayList;

/**
 *
 * @author Nidhi Goyal
 */
public abstract class Organization {
    private final String name;
    private final UserAccountDirectory userAccountDirectory;
    private int organizationID;
    private final WorkQueue workQueue;
    private static int counter = 0;
    
    public enum Type {
        Admin("Admin"),
        Doctor("Doctor"),
        Event("Event"),
        Lab("Lab"),
        NGOStaff("NGOStaff"),
        NGOSupervisor("NGOSupervisor"),
        Pharmacy("Pharmacy");

        private final String value;

        private Type(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public Organization(String name) {
        this.name = name;
        userAccountDirectory = new UserAccountDirectory();
        workQueue = new WorkQueue();
        organizationID = counter;
        ++counter;
    }

    public abstract ArrayList<Role.RoleType> getSupportedRole();
    
    public UserAccountDirectory getUserAccountDirectory() {
        return userAccountDirectory;
    }

    public int getOrganizationID() {
        return organizationID;
    }
    
    public String getName() {
        return name;
    }
    
    public WorkQueue getWorkQueue() {
        return this.workQueue;
    }

    @Override
    public String toString() {
        return name;
    }
}
