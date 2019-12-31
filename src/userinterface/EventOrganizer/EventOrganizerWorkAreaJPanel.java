/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.EventOrganizer;

import Business.EcoSystem;
import Business.Enterprise.NGOEnterprise;
import Business.Ngo.EventRegistration;
import Business.Ngo.PatientEventRegistration;
import Business.Organization.EventOrganization;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.EventWorkRequest;
import Business.WorkQueue.WorkRequest;
import java.awt.CardLayout;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Amruta
 */
public class EventOrganizerWorkAreaJPanel extends javax.swing.JPanel {

    private final EcoSystem system;
    private final EventOrganization eventOrganization;
    private final NGOEnterprise ngoEnterprise;
    private final UserAccount userAccount;

    public EventOrganizerWorkAreaJPanel(UserAccount userAccount,EventOrganization eventOrganization,NGOEnterprise ngoEnterprise, EcoSystem system) {
        initComponents();
        this.ngoEnterprise=ngoEnterprise;
        this.eventOrganization = eventOrganization;
        this.system = system;
        this.userAccount=userAccount;
        eventOrganizerNameTxtField.setText(userAccount.getEmployee().getName());
        populateEventWorkRequestTable();
        //populatePatientRegistrationTable();
    }
     private void populateEventWorkRequestTable() {
         DefaultTableModel model = (DefaultTableModel) eventJTable.getModel();
            model.setRowCount(0);
            if (eventOrganization.getWorkQueue() == null) {
                System.out.println("WorkQueue is NULL for " + eventOrganization.getName());
                return;
            }
            
            //for (WorkRequest request : eventOrganization.getWorkQueue().getWorkRequestList()) {
            for (WorkRequest request : eventOrganization.getWorkQueue().getWorkRequestList()) {
                if (request == null) {
                    continue;
                }
                
                if (request.getReceiver().equals(userAccount)) {
                    //EventOrganizerAssignment event = ((EventOrganizerAssignmentWorkRequest)request).getEventRegistration();
                    EventRegistration event = ((EventWorkRequest)request).getEventRegistration();
                    //System.out.println(event.getPatient());
                    System.out.println(event);
                    Object[] row = new Object[5];
                    row[0]=event.getEventName();
                    row[1]=event.getEventLocation();
                    row[2]=event.getEventDate();
                    row[3]=event.getEventDescription();
                    model.addRow(row);
                }
                for(UserAccount us:system.getUserAccountDirectory().getUserAccountList()){
                    if(us.getEmployee().getName().equals(userAccount.getUsername())){
                        System.out.println(us);
                    }
                }
            }
            
     }
//     private void populatePatientRegistrationTable()
//     {
//         DefaultTableModel model = (DefaultTableModel) patientJTable.getModel();
//            model.setRowCount(0);
//            if (eventOrganization.getWorkQueue() == null) {
//                System.out.println("WorkQueue is NULL for " + eventOrganization.getName());
//                return;
//            }
//            
//            //for (WorkRequest request : eventOrganization.getWorkQueue().getWorkRequestList()) {
//            for (WorkRequest request : eventOrganization.getWorkQueue().getWorkRequestList()) {
//                if (request == null) {
//                    continue;
//                }
//                
//                if (request.getReceiver().equals(userAccount)) {
//                    //EventOrganizerAssignment event = ((EventOrganizerAssignmentWorkRequest)request).getEventRegistration();
//                    PatientEventRegistration event = ((PatientEventWorkRequest)request).getEvents();
//                    //System.out.println(event.getPatient());
//                    System.out.println(event);
//                    Object[] row = new Object[5];
//                    row[0]=event.getEventName();
//                    row[1]=event.getEventLocation();
//                    row[2]=event.getEventDate();
//                    row[3]=event.getEventDescription();
//                    row[4]=event.getPatient().getName();
//                    model.addRow(row);
//                }
//            }
//     }
     
     private void processWorkRequest(String newStatus) {
         int row = eventJTable.getSelectedRow();
         if (row < 0) {
            JOptionPane.showMessageDialog(null, "Select an event", "Error Message", JOptionPane.ERROR_MESSAGE);
            return;
         }
         String appt = (String)(eventJTable.getValueAt(row, 0));
         System.out.println(appt);
         for (WorkRequest request : this.eventOrganization.getWorkQueue().getWorkRequestList()) {
             EventWorkRequest apptRequest = (EventWorkRequest)request;
             
             if (apptRequest.getEventRegistration().getEventName().equals(appt)) {
                 apptRequest.setStatus(newStatus);
                 apptRequest.setResolveDate(new Date());
                 apptRequest.setMessage("Event has been " + newStatus);
             }
         }
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

        jScrollPane1 = new javax.swing.JScrollPane();
        eventJTable = new javax.swing.JTable();
        eventOrganizerNameTxtField = new javax.swing.JLabel();
        enterpriseLabel = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        eventJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Event Name", "Event Location", "Event Date", "Event Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(eventJTable);

        eventOrganizerNameTxtField.setText("<value>");

        enterpriseLabel.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        enterpriseLabel.setText("Event Organizer Name:");

        jButton1.setText("View Registered Event");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(enterpriseLabel)
                        .addGap(39, 39, 39)
                        .addComponent(eventOrganizerNameTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(164, 164, 164)
                        .addComponent(jButton1)))
                .addContainerGap(179, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eventOrganizerNameTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(enterpriseLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jButton1)
                .addContainerGap(100, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel enterpriseLabel;
    private javax.swing.JTable eventJTable;
    private javax.swing.JLabel eventOrganizerNameTxtField;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
