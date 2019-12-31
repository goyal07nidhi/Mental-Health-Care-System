/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Role;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Enterprise.NGOEnterprise;
import Business.Event.NewEvent;
import Business.Ngo.EventRegistration;
import Business.Organization.NGOStaffOrganization;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import java.util.ArrayList;
import javax.swing.JPanel;
import userinterface.NGOStaff.NGOStaffWorkAreaJPanel;

/**
 *
 * @author Nidhi Goyal
 */
public class NGOStaff extends Role {
    private int ngoStaffID;
    private String ngoStaffName;
    private final ArrayList<EventRegistration> registrationList;
    private static int count;
    private JPanel container;
    public NGOStaff() {
        super(Role.RoleType.NGOStaff);
        registrationList = new ArrayList<>();
    }

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer,
            UserAccount account,
            Organization organization,
            Enterprise enterprise,
            EcoSystem business) {
        //return new NGOStaffWorkAreaJPanel(account,(EventOrganization)organization,business,container);
        return new NGOStaffWorkAreaJPanel(account, (NGOStaffOrganization)organization,(NGOEnterprise)enterprise,business);
    }

    public int getNgoStaffID() {
        return ngoStaffID;
    }

    public void setNgoStaffID(int ngoStaffID) {
        this.ngoStaffID = ngoStaffID;
    }

    public String getNgoStaffName() {
        return ngoStaffName;
    }

    public void setNgoStaffName(String ngoStaffName) {
        this.ngoStaffName = ngoStaffName;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        NGOStaff.count = count;
    }

    public JPanel getContainer() {
        return container;
    }

    public void setContainer(JPanel container) {
        this.container = container;
    }
    
    @Override
    public String toString() {
        return ngoStaffName;
    }

//    @Override
//    public String toString() {
//        return Role.RoleType.NGOStaff.getValue();
//    }
}
