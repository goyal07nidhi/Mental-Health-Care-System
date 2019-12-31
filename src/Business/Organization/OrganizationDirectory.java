/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Organization;

import java.util.ArrayList;

/**
 *
 * @author Nidhi Goyal
 */
public class OrganizationDirectory {
    private ArrayList<Organization> organizationList;

    public OrganizationDirectory() {
        organizationList = new ArrayList();
    }

    public ArrayList<Organization> getOrganizationList() {
        return organizationList;
    }
    
    public Organization createOrganization(Organization.Type type) {
        Organization organization = null;
        
        if (type.getValue().equals(Organization.Type.Doctor.getValue())) {
            organization = new DoctorOrganization();
            organizationList.add(organization);
        }
        else if (type.getValue().equals(Organization.Type.Event.getValue())) {
            organization = new EventOrganization();
            organizationList.add(organization);
        }
        else if (type.getValue().equals(Organization.Type.Lab.getValue())) {
            organization = new LabOrganization();
            organizationList.add(organization);
        }
        else if (type.getValue().equals(Organization.Type.NGOStaff.getValue())) {
            organization = new NGOStaffOrganization();
            organizationList.add(organization);
        }
        else if (type.getValue().equals(Organization.Type.NGOSupervisor.getValue())) {
            organization = new NGOSupervisorOrganization();
            organizationList.add(organization);
        }
        else if (type.getValue().equals(Organization.Type.Pharmacy.getValue())) {
            organization = new PharmacyOrganization();
            organizationList.add(organization);
        }
        
        return organization;
    }
    
    public boolean isOrganizationCreated(Organization.Type orgType) {
        for (Organization org : this.organizationList) {
            if (org.getName().equals(orgType.getValue())) {
                return true;
            }
        }

        return false;
    }
}
