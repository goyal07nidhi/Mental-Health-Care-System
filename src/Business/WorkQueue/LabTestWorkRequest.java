/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;

import Business.Patient.Patient;
import java.util.Date;

/**
 *
 * @author Nidhi Goyal
 */
public class LabTestWorkRequest extends WorkRequest {
    private Patient patient;
    private String testResult;
    private Date apptDate;

    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
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
