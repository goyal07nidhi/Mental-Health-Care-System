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
import Business.UserAccount.UserAccount;
import javax.swing.JPanel;
import userinterface.EventOrganizer.EventOrganizerWorkAreaJPanel;

/**
 *
 * @author Nidhi Goyal
 */
public class EventOrganizer extends Role {
    public EventOrganizer() {
        super(Role.RoleType.EventOrganizer);
    }

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer,
            UserAccount account,
            Organization organization,
            Enterprise enterprise,
            EcoSystem business) {
        return new EventOrganizerWorkAreaJPanel(account, (EventOrganization)organization,(NGOEnterprise)enterprise,business);
    }

    @Override
    public String toString() {
        return Role.RoleType.EventOrganizer.getValue();
    }
}
