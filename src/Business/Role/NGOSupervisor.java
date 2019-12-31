/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Role;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Enterprise.NGOEnterprise;
import Business.Organization.EventOrganization;
import Business.Organization.Organization;
import Business.Patient.Patient;
import Business.UserAccount.UserAccount;
import javax.swing.JPanel;
import userinterface.NGOSupervisor.NGOSupervisorWorkAreaJPanel;

/**
 *
 * @author Nidhi Goyal
 */
public class NGOSupervisor extends Role {
    private Patient patient;
    public NGOSupervisor() {
        super(Role.RoleType.NGOSupervisor);
        this.patient = patient;
    }

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer,
            UserAccount account,
            Organization organization,
            Enterprise enterprise,
            EcoSystem business) {
        //return new NGOSupervisorWorkAreaJPanel(userProcessContainer,enterprise);
        return new NGOSupervisorWorkAreaJPanel(account,patient, business);
    }

    @Override
    public String toString() {
        return Role.RoleType.NGOSupervisor.getValue();
    }
}
