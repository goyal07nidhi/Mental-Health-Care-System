/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Enterprise;

import Business.Organization.Organization;
import Business.Organization.OrganizationDirectory;
import Business.UserAccount.UserAccountDirectory;
import java.util.ArrayList;

/**
 *
 * @author Nidhi Goyal
 */
public abstract class Enterprise {
    private final EnterpriseType enterpriseType;
    private final OrganizationDirectory organizationDirectory;
    private final UserAccountDirectory userAccountDirectory;
    private final String name;
    
    public enum EnterpriseType {
        Hospital("Hospital"),
        NGO("NGO");
        
        private final String value;
        
        private EnterpriseType(String value) {
            this.value = value;
        }
        
        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
    
    public Enterprise(String name, EnterpriseType type) {
        this.enterpriseType = type;
        this.organizationDirectory = new OrganizationDirectory();
        this.userAccountDirectory = new UserAccountDirectory();
        this.name = name;
    }

    public EnterpriseType getEnterpriseType() {
        return enterpriseType;
    }
    
    public OrganizationDirectory getOrganizationDirectory() {
        return organizationDirectory;
    }
    
    public UserAccountDirectory getUserAccountDirectory() {
        return userAccountDirectory;
    }

    public String getName() {
        return this.name;
    }
    
    public Organization getOrganization(Organization.Type orgType) {
        for (Organization org : this.organizationDirectory.getOrganizationList()) {
            if (org.getName().equals(orgType.getValue())) {
                return org;
            }
        }
        
        return null;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
    public abstract ArrayList<Organization.Type> getOrganizationType();
}
