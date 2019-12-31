/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Patient;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Hospital.Appointment;
import Business.Role.Doctor;
import Business.Hospital.TestReport;
import Business.Organization.Organization;
import Business.Role.Role;
import Business.UserAccount.UserAccount;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;
import userinterface.Patient.PatientWorkAreaJPanel;

/**
 *
 * @author Nidhi Goyal
 */
public class Patient extends Role {
    private int id;
    private String userName;
    private String name;
    private int age;
    private String gender;
    private float height;
    private float weight;
    private float bmi;
    private String emailId;
    private static int count = 0;
    private BufferedImage profileImage;
    private String location;
    private ArrayList<Appointment> appointmentList;
    private final ArrayList<TestReport> testReportHistory;
   
    public Patient() {
        super(Role.RoleType.Patient);
        count++;
        id = count;
        appointmentList = new ArrayList<>();
        testReportHistory = new ArrayList<>();
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public float getHeight() {
        return height;
    }
    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }
    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getBmi() {
        return bmi;
    }
    public void setBmi(float bmi) {
        this.bmi = bmi;
    }

    public String getEmailId() {
        return emailId;
    }
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public static int getCount() {
        return count;
    }
    public static void setCount(int count) {
        Patient.count = count;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public ArrayList<Appointment> getAppointmentList() {
        return appointmentList;
    }
    public void setAppointmentList(ArrayList<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    public ArrayList<TestReport> getTestReportHistory() {
        return testReportHistory;
    }
    public void addTestReport(TestReport testReport) {
        this.testReportHistory.add(testReport);
    } 
    
    public BufferedImage getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(BufferedImage profileImage) {
        this.profileImage = profileImage;
    }
    
    @Override
    public JPanel createWorkArea(JPanel userProcessContainer,
            UserAccount account,
            Organization organization,
            Enterprise enterprise,
            EcoSystem ecoSystem) {
        return new PatientWorkAreaJPanel(userProcessContainer, this, ecoSystem);
    }

    @Override
    public String toString() {
        return Role.RoleType.Patient.getValue();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
}
