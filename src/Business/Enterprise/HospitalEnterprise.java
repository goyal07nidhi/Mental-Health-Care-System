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
public class HospitalEnterprise extends Enterprise {

    public HospitalEnterprise(String name) {
        super(name, EnterpriseType.Hospital);
    }

    public ArrayList<Role> getSupportedRole() {
        return null;
    }

    @Override
    public ArrayList<Organization.Type> getOrganizationType() {
        ArrayList<Organization.Type> orgType = new ArrayList<>();

        orgType.add(Organization.Type.Doctor);
        orgType.add(Organization.Type.Lab);
        orgType.add(Organization.Type.Pharmacy);

        return orgType;
    }
}
