/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business;

import Business.Enterprise.Enterprise;
import Business.Event.EventDirectory;
import Business.Event.NewEvent;
import Business.Network.Network;
import Business.Organization.DoctorOrganization;
import Business.Organization.EventOrganization;
import Business.Organization.Organization;
import Business.Patient.PatientDirectory;
import Business.Role.Role;
import Business.Role.SystemAdmin;
import Business.UserAccount.UserAccount;
import Business.UserAccount.UserAccountDirectory;
import java.util.ArrayList;

/**
 *
 * @author Nidhi Goyal
 */
public class EcoSystem {
    private static EcoSystem business;
    private ArrayList<Network> networkList;
    private final UserAccountDirectory userAccountDirectory;
    private final PatientDirectory patientDirectory;
 
    private EcoSystem() {
        this.networkList = new ArrayList<>();
        this.userAccountDirectory = new UserAccountDirectory();
        this.patientDirectory = new PatientDirectory();
    }

    public static EcoSystem getBusiness() {
        return business;
    }

    public static void setBusiness(EcoSystem business) {
        EcoSystem.business = business;
    }

    public static EcoSystem getInstance() {
        if(business == null) {
            business = new EcoSystem();
        }
        return business;
    }
    
    public Network createAndAddNetwork() {
        Network network = new Network();
        networkList.add(network);
        return network;
    }

    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roleList = new ArrayList<Role>();
        roleList.add(new SystemAdmin());
        return roleList;
    }

    public UserAccountDirectory getUserAccountDirectory() {
        return this.userAccountDirectory;
    }

    public ArrayList<Network> getNetworkList() {
        return networkList;
    }

    public void setNetworkList(ArrayList<Network> networkList) {
        this.networkList = networkList;
    }
    
    public boolean isUserNameUnique(String username) {
        // 1. check if username in ecosystem directory
        if (!this.userAccountDirectory.checkIfUsernameIsUnique(username)) {
            return false;
        }
        // 2. check across all networks
        for (Network nw : this.networkList) {
            for (Enterprise e: nw.getEnterpriseDirectory().getEnterpriseList()) {
                if (!e.getUserAccountDirectory().checkIfUsernameIsUnique(username)) {
                    return false;
                }
            }
        }
        
        return true;
    }

    public Network getNetwork(String networkName) {
        for (Network n : this.networkList) {
            if (n.getName().equalsIgnoreCase(networkName)) {
                return n;
            }
        }
        
        return null;
    }
    public Enterprise getEnt(String networkName,Enterprise.EnterpriseType entType)
    {
        Network net = getNetwork(networkName);
        for (Enterprise ent : net.getEnterpriseDirectory().getEnterpriseList()) {
            if (ent.getEnterpriseType().getValue().equals(entType.getValue())) {
                return ent;
            }
        }
        return null;
    }
    public ArrayList<Enterprise> getEnterprise(String networkName, Enterprise.EnterpriseType entType) {
        Network net = getNetwork(networkName);
        ArrayList<Enterprise> entList = new ArrayList<>();

        if (net == null) {
            return entList;
        }
        
        for (Enterprise ent : net.getEnterpriseDirectory().getEnterpriseList()) {
            if (ent.getEnterpriseType().getValue().equals(entType.getValue())) {
                entList.add(ent);
            }
        }
        
        return entList;
    }
    
    public ArrayList<UserAccount> getDoctorsByNetwork(String networkName) {
        ArrayList<Enterprise> entList = getEnterprise(networkName, Enterprise.EnterpriseType.Hospital);
        ArrayList<UserAccount> doctorList = new ArrayList<>();
        
        for (Enterprise ent : entList) {
            for (Organization org : ent.getOrganizationDirectory().getOrganizationList()) {
                if (org instanceof DoctorOrganization) {
                    for (UserAccount ua : org.getUserAccountDirectory().getUserAccountList()) {
                        if (ua.getRole().getRoleType() == Role.RoleType.Doctor) {
                            doctorList.add(ua);
                        }
                    }
                }
            }
        }
        
        return doctorList;
    }
    public ArrayList<UserAccount> getEventOrganizerByNetwork(String networkName) {
        ArrayList<Enterprise> entList = getEnterprise(networkName, Enterprise.EnterpriseType.NGO);
        ArrayList<UserAccount> eventList = new ArrayList<>();

        for (Enterprise ent : entList) {
            for (Organization org : ent.getOrganizationDirectory().getOrganizationList()) {
                if (org instanceof EventOrganization) {
                    for (UserAccount ua : org.getUserAccountDirectory().getUserAccountList()) {
                        if (ua.getRole().getRoleType() == Role.RoleType.EventOrganizer) {
                            eventList.add(ua);
                        }
                    }
                }
            }
        }
        
        return eventList;
    }
}
