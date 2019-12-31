/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Role;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Enterprise.HospitalEnterprise;
import Business.Hospital.Appointment;
import Business.Organization.DoctorOrganization;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import java.util.ArrayList;
import javax.swing.JPanel;
import userinterface.Doctor.DoctorWorkAreaJPanel;
/**
 *
 * @author Nidhi Goyal
 */
public class Doctor extends Role {
    private int doctorID;
    private String doctorName;
    private final ArrayList<Appointment> appointmentList;
    private static int count;
    private JPanel container;
    
    public Doctor() {
        super(Role.RoleType.Doctor);
        count++;  
        doctorID = count;
        appointmentList = new ArrayList<>();
    }

    public int getDoctorID() {
        return doctorID;
    }
    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public String getDoctorName() {
        return doctorName;
    }
    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public static int getCount() {
        return count;
    }
    public static void setCount(int count) {
        Doctor.count = count;
    }

    @Override
    public String toString() {
        return doctorName;
    }

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer,
            UserAccount account,
            Organization organization,
            Enterprise enterprise,
            EcoSystem business) {
        return new DoctorWorkAreaJPanel(account, (DoctorOrganization)organization, (HospitalEnterprise)enterprise, business);
    }
}
