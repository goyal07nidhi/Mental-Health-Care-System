/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Organization;

import Business.Role.Doctor;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author Nidhi Goyal
 */
public class DoctorOrganization extends Organization {

    public DoctorOrganization() {
        super(Organization.Type.Doctor.getValue());
    }
    
    @Override
    public ArrayList<Role.RoleType> getSupportedRole() {
        ArrayList<Role.RoleType> roles = new ArrayList();
        roles.add(Role.RoleType.Doctor);
        return roles;
    }
}
