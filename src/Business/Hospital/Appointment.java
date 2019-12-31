/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Hospital;

import Business.Patient.Patient;
import Business.Role.Doctor;
import java.util.*;
/**
 *
 * @author Nidhi Goyal
 */

public class Appointment {
    private int appointmentId;
    private Date appointmentDate;
    private Patient patient;
    private Doctor doctor;
    private String appointmentReason;
    private static int count;
    
    public Appointment() {
        appointmentId = ++count; 
    }

    public int getAppointmentId() {
        return appointmentId;
    }
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }
    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentReason() {
        return appointmentReason;
    }
    public void setAppointmentReason(String appointmentReason) {
        this.appointmentReason = appointmentReason;
    }

    public Patient getPatient() {
        return patient;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public static int getCount() {
        return count;
    }
    public static void setCount(int count) {
        Appointment.count = count;
    }
    
    @Override
    public String toString() {
        return this.appointmentReason;
    }
}
