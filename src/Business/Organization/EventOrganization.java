/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Organization;

import Business.Event.EventDirectory;
import Business.Role.EventOrganizer;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author Nidhi Goyal
 */
public class EventOrganization extends Organization {
    private final EventDirectory eventDirectory;

    public EventOrganization() {
        super(Type.Event.getValue());
        this.eventDirectory = new EventDirectory();
    }
    
    public EventDirectory getEventDirectory() {
        return this.eventDirectory;
    }
    
    @Override
    public ArrayList<Role.RoleType> getSupportedRole() {
        ArrayList<Role.RoleType> roles = new ArrayList();
        roles.add(Role.RoleType.EventOrganizer);
        return roles;
    }
}
