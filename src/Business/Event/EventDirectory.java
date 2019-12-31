/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Event;

import java.util.ArrayList;

/**
 *
 * @author Amruta
 */
public class EventDirectory {
    private ArrayList<NewEvent> eventList;
    
    public EventDirectory() {
        eventList = new ArrayList<>();
    }
    
    public EventDirectory(ArrayList<NewEvent> eventList) {
            eventList = new ArrayList<>();
    }
    
    public NewEvent addEvent(String name,String location,String date,String description) {
        NewEvent e = new NewEvent();
        e.setName(name);
        e.setLocation(location);
        e.setDate(date);
        e.setDescription(description);
        eventList.add(e);
        System.out.println(e);
        return e;
    }
    public ArrayList<NewEvent> getEventList() {
        return eventList;
    }

    public void setAirlinerList(ArrayList<NewEvent> eventList) {
        this.eventList=eventList;
    }
}
