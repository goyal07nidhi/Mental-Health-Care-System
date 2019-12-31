/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Ngo;

import Business.Patient.Patient;
import Business.Role.EventOrganizer;
import java.util.Date;

/**
 *
 * @author Amruta
 */
public class PatientEventRegistration {
    private String eventName;
    private String eventLocation;
    private Date eventDate;
    private String eventDescription;
    private static int count;
    private EventOrganizer eventOrganizer;
    private Patient patient;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public EventOrganizer getEventOrganizer() {
        return eventOrganizer;
    }

    public void setEventOrganizer(EventOrganizer eventOrganizer) {
        this.eventOrganizer = eventOrganizer;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        PatientEventRegistration.count = count;
    }
    @Override
    public String toString() {
        return this.eventName;
    }
}
