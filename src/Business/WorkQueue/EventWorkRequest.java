/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;

import Business.Ngo.EventRegistration;

/**
 *
 * @author Amruta
 */
public class EventWorkRequest extends WorkRequest {
    private final EventRegistration event;
    
    public EventWorkRequest(EventRegistration event) {
        super();
        this.event = event;
    }
    public EventRegistration getEventRegistration() {
        return this.event;
    }
    
}
