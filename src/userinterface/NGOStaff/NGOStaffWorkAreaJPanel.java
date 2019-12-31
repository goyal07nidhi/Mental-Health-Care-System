/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.NGOStaff;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Enterprise.NGOEnterprise;
import Business.Network.Network;
import Business.Ngo.EventRegistration;
import Business.Organization.EventOrganization;
import Business.Organization.NGOStaffOrganization;
import Business.Organization.Organization;
import Business.Patient.Patient;
import Business.Role.Role;
import Business.Role.EventOrganizer;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.EventWorkRequest;
import Business.WorkQueue.WorkRequest;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Amruta
 */
public class NGOStaffWorkAreaJPanel extends javax.swing.JPanel {

    /**
     * Creates new form NGOStaffWorkAreaJPanel
     */

    private final EcoSystem system;
    private final NGOStaffOrganization ngoStaffOrg;
    private final NGOEnterprise ngoEnterprise;
    private Organization org;
    private final UserAccount userAccount;
    private ArrayList<Enterprise> ngoEnt;
    public NGOStaffWorkAreaJPanel(UserAccount userAccount,NGOStaffOrganization ngoStaffOrg,NGOEnterprise ngoEnterprise, EcoSystem system)
    {
        initComponents();
        this.ngoEnterprise=ngoEnterprise;
        this.ngoStaffOrg = ngoStaffOrg;
        this.system = system;
        this.userAccount=userAccount;
        this.org=org;
        eventOrganizerNameTxtField.setText(userAccount.getEmployee().getName());
        this.ngoEnt = ngoEnt;
        populateEventOrgTable();
        populateEventWorkRequestTable();
    }
   
    
    private void populateEventWorkRequestTable() {
         DefaultTableModel model = (DefaultTableModel) eventJTable.getModel();
            model.setRowCount(0);
            if (ngoStaffOrg.getWorkQueue() == null) {
                System.out.println("WorkQueue is NULL for " + ngoStaffOrg.getName());
                return;
            }
            
            for (WorkRequest request : ngoStaffOrg.getWorkQueue().getWorkRequestList()) {
                if (request == null) {
                    continue;
                }
                if (request.getReceiver().getUsername().equals(userAccount.getUsername())) {
                    EventRegistration event = ((EventWorkRequest)request).getEventRegistration();
                    Object[] row = new Object[6];
                    row[0]=event.getEventName();
                    row[1]=event.getEventLocation();
                    row[2]=event.getEventDate();
                    row[3]=event.getEventDescription();
                    row[4]=request;
                    row[5]=org;
                    model.addRow(row);
                }
                
            }
            
     }
     private void populateEventOrgTable()
    {
        DefaultTableModel model = (DefaultTableModel) eventOrgTbl.getModel();
        model.setRowCount(0);
        for (Network network : system.getNetworkList())
        {
            String networkName=network.getName();
            ngoEnt=system.getEnterprise(networkName, Enterprise.EnterpriseType.NGO);
            for (Enterprise ent : this.ngoEnt) {
                for (Organization org : ent.getOrganizationDirectory().getOrganizationList()) {
                    if (org instanceof EventOrganization) {
                        for (UserAccount ua : org.getUserAccountDirectory().getUserAccountList()) {
                            if (ua.getRole().getRoleType() == Role.RoleType.EventOrganizer) {
                                Object[] row = new Object[3];
                                row[0] = ua;
                                row[1] = network.getName();
                                row[2] = org;
                                model.addRow(row);
                            }
                        }
                    }
                }
            }
        }
    }


     private void processWorkRequest(String newStatus) {
         int row = eventJTable.getSelectedRow();
         int data=eventOrgTbl.getSelectedRow();
         if (row < 0) {
            JOptionPane.showMessageDialog(null, "Select an event", "Error Message", JOptionPane.ERROR_MESSAGE);
            return;
         }
         if (data < 0) {
            JOptionPane.showMessageDialog(null, "Select an event Organizer", "Error Message", JOptionPane.ERROR_MESSAGE);
            return;
         }

         String eventName = (String)(eventJTable.getValueAt(row, 0));
         String eventLocation = (String)(eventJTable.getValueAt(row, 1));
         Date eventDate = (Date)(eventJTable.getValueAt(row, 2));
         String eventDescription = (String)(eventJTable.getValueAt(row, 3));
         EventWorkRequest eventStatus=(EventWorkRequest)(eventJTable.getValueAt(row, 4));
         
         
         UserAccount account = (UserAccount)eventOrgTbl.getValueAt(data, 0);
         EventOrganization org = (EventOrganization)(eventOrgTbl.getValueAt(data, 2));
           EventRegistration event=new EventRegistration();
           event.setEventOrganizer((EventOrganizer)account.getRole());
           event.setEventName(eventName);
           event.setEventDate(eventDate);
           event.setEventLocation(eventLocation);
           event.setEventDescription(eventDescription);
           EventWorkRequest apptRequest = new EventWorkRequest(event);
            apptRequest.setStatus(newStatus);
            apptRequest.setReceiver(account);
            org.getWorkQueue().getWorkRequestList().add(apptRequest);
            JOptionPane.showMessageDialog(null, "Event has been assigned successfully to Event organizer", "Info Message", JOptionPane.INFORMATION_MESSAGE);

            populateEventWorkRequestTable();
         
     }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        eventOrganizerNameTxtField = new javax.swing.JLabel();
        enterpriseLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        eventJTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        eventOrgTbl = new javax.swing.JTable();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        eventOrganizerNameTxtField.setText("<value>");

        enterpriseLabel.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        enterpriseLabel.setText("NGO Staff Name:");

        eventJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Event Name", "Event Location", "Event Date", "Event Description", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(eventJTable);

        jButton1.setText("Assign");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Select Event Organizer to assign an event:");

        eventOrgTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Event Organizer", "Network", "Organization"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        eventOrgTbl.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(eventOrgTbl);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(enterpriseLabel)
                                .addGap(39, 39, 39)
                                .addComponent(eventOrganizerNameTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jButton1)))
                .addContainerGap(174, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eventOrganizerNameTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(enterpriseLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(119, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //        container.add("Event Registration", new NewEventJPanel(container, eventOrg, system));
        //        CardLayout layout = (CardLayout)container.getLayout();
        //        layout.next(container);
        processWorkRequest("Created");
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel enterpriseLabel;
    private javax.swing.JTable eventJTable;
    private javax.swing.JTable eventOrgTbl;
    private javax.swing.JLabel eventOrganizerNameTxtField;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
