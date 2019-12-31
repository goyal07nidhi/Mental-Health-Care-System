/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.WorkQueue;

import Business.Patient.Patient;
import java.util.Date;

/**
 *
 * @author Nidhi Goyal
 */
public class PharmacyWorkRequest extends WorkRequest {
    private Patient patient;
    private String prescription;
    private Date apptDate;

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }
    
    public Patient getPatient() {
        return this.patient;
    }
    
    public void setPatient(Patient p) {
        this.patient = p;
    }
    
    public void setApptDate(Date date) {
        this.apptDate = date;
    }
    
    public Date getApptDate() {
        return this.apptDate;
    }
}
