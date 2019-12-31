/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.WorkQueue;

import Business.Hospital.Appointment;

/**
 *
 * @author Nidhi Goyal
 */
public class AppointmentWorkRequest extends WorkRequest {
    private final Appointment appt;
    
    public AppointmentWorkRequest(Appointment appt) {
        super();
        this.appt = appt;
    }
    
    public Appointment getAppointment() {
        return this.appt;
    }
}
