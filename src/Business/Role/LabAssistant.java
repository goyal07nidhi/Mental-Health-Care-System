/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Role;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Organization.LabOrganization;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import javax.swing.JPanel;
import userinterface.LabAssistant.LabAssistantWorkAreaJPanel;

/**
 *
 * @author Nidhi Goyal
 */
public class LabAssistant extends Role {
    private int assistantID;
    private String assistantName;
    private static int count;
    private UserAccount ua;
    private String emailId;
    
    public LabAssistant() {
        super(Role.RoleType.LabAssistant);
        count++;  
        assistantID = count;
    }

    public int getAssistantID() {
        return assistantID;
    }
    public void setAssistantID(int assistantID) {
        this.assistantID = assistantID;
    }

    public String getAssistantName() {
        return assistantName;
    }
    public void setAssistantName(String assistantName) {
        this.assistantName = assistantName;
    }

    public static int getCount() {
        return count;
    }
    public static void setCount(int count) {
        LabAssistant.count = count;
    }

    public UserAccount getUa() {
        return ua;
    }
    public void setUa(UserAccount ua) {
        this.ua = ua;
    }

    public String getEmailId() {
        return emailId;
    }
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Override
    public String toString() {
        return assistantName;
    }

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer,
            UserAccount account,
            Organization organization,
            Enterprise enterprise,
            EcoSystem business) {
        return new LabAssistantWorkAreaJPanel(account, (LabOrganization)organization);
    }
}
