/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Role;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Organization.Organization;
import Business.Organization.PharmacyOrganization;
import Business.UserAccount.UserAccount;
import javax.swing.JPanel;
import userinterface.Pharmacist.PharmacistWorkAreaJPanel;

/**
 *
 * @author Nidhi Goyal
 */
public class Pharmacist extends Role {
    public Pharmacist() {
        super(Role.RoleType.Pharmacist);
    }

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer,
            UserAccount account,
            Organization organization,
            Enterprise enterprise,
            EcoSystem business) {
        return new PharmacistWorkAreaJPanel(account, (PharmacyOrganization)organization, business);
    }

    @Override
    public String toString() {
        return Role.RoleType.Pharmacist.getValue();
    }
}
