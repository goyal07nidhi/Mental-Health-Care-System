/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Enterprise;

import Business.Organization.Organization;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author Nidhi Goyal
 */
public class NGOEnterprise extends Enterprise{

    public NGOEnterprise(String name) {
        super(name, EnterpriseType.NGO);
    }

    public ArrayList<Role> getSupportedRole() {
        return null;
    }
    
    @Override
    public ArrayList<Organization.Type> getOrganizationType() {
        ArrayList<Organization.Type> orgType = new ArrayList<>();
        orgType.add(Organization.Type.Event);
        orgType.add(Organization.Type.NGOStaff);
        orgType.add(Organization.Type.NGOSupervisor);
        return orgType;
    }
}
