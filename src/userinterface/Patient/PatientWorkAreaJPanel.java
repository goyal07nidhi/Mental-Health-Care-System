/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.Patient;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Hospital.TestReport;
import Business.Ngo.EventRegistration;
import Business.Organization.DoctorOrganization;
import Business.Organization.EventOrganization;
import Business.Organization.Organization;
import Business.Organization.PharmacyOrganization;
import Business.Patient.Patient;
import Business.Role.EventOrganizer;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.AppointmentWorkRequest;
import Business.WorkQueue.EventWorkRequest;
import Business.WorkQueue.PharmacyWorkRequest;
import Business.WorkQueue.WorkRequest;
import java.awt.CardLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import userinterface.HealthTest.AddictionJPanel;
import userinterface.HealthTest.AnxietyJPanel;
import userinterface.HealthTest.DepressionJPanel;
import userinterface.HealthTest.EatingDisorderJPanel;
import userinterface.HealthTest.SleepDisorderJPanel;
import userinterface.HealthTest.StressJPanel;

/**
 *
 * @author Nidhi Goyal
 */
public class PatientWorkAreaJPanel extends javax.swing.JPanel {

    private final JPanel userContainer;
    private final EcoSystem system;
    private final Patient patient;
    private final ArrayList<Enterprise> hospitalEnt, ngoEnt;

    public PatientWorkAreaJPanel(JPanel userContainer, Patient patient, EcoSystem ecosystem) {
        initComponents();
        this.userContainer = userContainer;
        this.patient = patient;
        this.system = ecosystem;
        nameTxtField.setText(this.patient.getName());
        this.hospitalEnt = system.getEnterprise(patient.getLocation(), Enterprise.EnterpriseType.Hospital);
        this.ngoEnt = system.getEnterprise(patient.getLocation(), Enterprise.EnterpriseType.NGO);

        populateAppointmentHistory();
        populateEventHistory(system.getEventOrganizerByNetwork(patient.getLocation()));
        populateMedicineRequest();
        populateTestHistory();
        
        BufferedImage profileImage = patient.getProfileImage();
        if (profileImage != null) {
            ImageIcon imageIcon = new ImageIcon(profileImage);
            Image image = imageIcon.getImage();
            Image newimg = image.getScaledInstance(300, 200, java.awt.Image.SCALE_SMOOTH); 
            imageIcon = new ImageIcon(newimg);
            imageJLabel.setIcon(imageIcon);
        }
    }

    public void populateEventHistory(ArrayList<UserAccount> eventList) {

        DefaultTableModel model = (DefaultTableModel) eventTbl.getModel();
        model.setRowCount(0);       
        for (Enterprise ent : this.ngoEnt) {
            for (Organization org : ent.getOrganizationDirectory().getOrganizationList()) {
                if (org instanceof EventOrganization) {
                    for (UserAccount ua : org.getUserAccountDirectory().getUserAccountList()) {
                        for (WorkRequest request : org.getWorkQueue().getWorkRequestList()) {
                            EventWorkRequest eventWorkRequest = (EventWorkRequest)request;
                            Object[] row = new Object[7];
                            row[0] = eventWorkRequest.getEventRegistration().getEventName();
                            row[1] = eventWorkRequest.getEventRegistration().getEventLocation();
                            row[2] = eventWorkRequest.getEventRegistration().getEventDate();
                            row[3] = eventWorkRequest.getEventRegistration().getEventDescription();
                            row[4] = eventWorkRequest.getReceiver().getEmployee().getName();
                            row[5] = org;
                            row[6] = ua;
                            model.addRow(row);
                        }
                    }
                }
            }
        }
    }
    public void populateAppointmentHistory() {
        DefaultTableModel model = (DefaultTableModel) patientTbl.getModel();
        model.setRowCount(0);
        
        for (Enterprise ent : this.hospitalEnt) {
            for (Organization org : ent.getOrganizationDirectory().getOrganizationList()) {
                if (org instanceof DoctorOrganization) {
                    for (WorkRequest request : org.getWorkQueue().getWorkRequestList()) {
                        AppointmentWorkRequest apptRequest = (AppointmentWorkRequest)request;
                        if (apptRequest.getAppointment().getPatient().equals(patient)) {
                            Object[] row = new Object[5];
                            row[0] = apptRequest.getAppointment().getAppointmentDate();
                            row[1] = apptRequest.getReceiver().getEmployee().getName();
                            row[2] = apptRequest.getAppointment().getAppointmentReason();
                            row[3] = ent;
                            row[4] = apptRequest.getStatus();

                            model.addRow(row);
                        }
                    }
                }
            }
        }
    }
    
    private void populateMedicineRequest() {
        DefaultTableModel model = (DefaultTableModel) medicineTbl.getModel();
        model.setRowCount(0);
        
        for (Enterprise ent : this.hospitalEnt) {
            for (Organization org : ent.getOrganizationDirectory().getOrganizationList()) {
                if (org instanceof PharmacyOrganization) {
                    for (WorkRequest request : org.getWorkQueue().getWorkRequestList()) {
                        PharmacyWorkRequest phmcyRequest = (PharmacyWorkRequest)request;
                        if (phmcyRequest.getPatient().equals(patient)) {
                            Object[] row = new Object[5];
                            row[0] = phmcyRequest.getApptDate();
                            row[1] = ent.getName();
                            row[2] = phmcyRequest.getSender().getEmployee().getName();
                            row[3] = phmcyRequest.getReceiver() == null ? "" : phmcyRequest.getReceiver().getEmployee().getName();
                            row[4] = phmcyRequest;

                            model.addRow(row);
                        }
                    }
                }
            }
        }
    }
    
    public void populateTestHistory() {
        DefaultTableModel model = (DefaultTableModel) testTbl.getModel();
        model.setRowCount(0);
        
        if (this.patient.getTestReportHistory() == null) {
            return;
        }
        
        for (TestReport report : this.patient.getTestReportHistory()) {
            Object[] row = new Object[4];
            row[0] = report.getTestDate();
            row[1] = report.getTestName();
            row[2] = report.getScore();
            row[3] = report.getRecommendation();
            model.addRow(row);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        patientNameLabel = new javax.swing.JLabel();
        nameTxtField = new javax.swing.JLabel();
        testBtn = new javax.swing.JButton();
        bookApptBtn = new javax.swing.JButton();
        problemComboBox = new javax.swing.JComboBox();
        eventRegistration = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        patientTbl = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        medicineTbl = new javax.swing.JTable();
        collectBtn = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        eventTbl = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        testTbl = new javax.swing.JTable();
        profileBtn = new javax.swing.JButton();
        imageJLabel = new javax.swing.JLabel();
        eventFeedbackButton = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel1.setText("Dashboard");

        patientNameLabel.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        patientNameLabel.setText("Patient Name:");

        testBtn.setText("Take a test");
        testBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testBtnActionPerformed(evt);
            }
        });

        bookApptBtn.setText("Book Appointment");
        bookApptBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookApptBtnActionPerformed(evt);
            }
        });

        problemComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select a Problem", "Alcohol/Drug Addiction", "Anxiety", "Depression", "Eating Disorder", "Sleep Disorder", "Stress" }));
        problemComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                problemComboBoxActionPerformed(evt);
            }
        });

        eventRegistration.setText("View Event Details");
        eventRegistration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eventRegistrationActionPerformed(evt);
            }
        });

        patientTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Appointment Date", "Doctor", "Appointment Reason", "Hospital", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(patientTbl);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 83, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Appointments", jPanel2);

        medicineTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Appointment Date", "Hospital", "Doctor", "Pharmacist", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(medicineTbl);
        if (medicineTbl.getColumnModel().getColumnCount() > 0) {
            medicineTbl.getColumnModel().getColumn(0).setResizable(false);
            medicineTbl.getColumnModel().getColumn(4).setResizable(false);
        }

        collectBtn.setText("Collect");
        collectBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                collectBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(collectBtn))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(collectBtn)
                .addGap(28, 28, 28))
        );

        jTabbedPane1.addTab("Prescriptions", jPanel3);

        eventTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Event Name", "Event Location", "Event Date", "Event Description", "EventOrganizer", "Organization", "User"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        eventTbl.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(eventTbl);
        if (eventTbl.getColumnModel().getColumnCount() > 0) {
            eventTbl.getColumnModel().getColumn(5).setMinWidth(1);
            eventTbl.getColumnModel().getColumn(5).setPreferredWidth(1);
            eventTbl.getColumnModel().getColumn(5).setMaxWidth(1);
            eventTbl.getColumnModel().getColumn(6).setMinWidth(1);
            eventTbl.getColumnModel().getColumn(6).setPreferredWidth(1);
            eventTbl.getColumnModel().getColumn(6).setMaxWidth(1);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 73, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Events", jPanel1);

        testTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Test Date", "Name", "Score", "Recommendation"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(testTbl);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 106, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tests", jPanel4);

        profileBtn.setText("View Profile");
        profileBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileBtnActionPerformed(evt);
            }
        });

        eventFeedbackButton.setText("Event Feedback");
        eventFeedbackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eventFeedbackButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(336, 336, 336)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(patientNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nameTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(imageJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 251, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(profileBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                    .addComponent(bookApptBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(eventRegistration, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                    .addComponent(testBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(problemComboBox, 0, 0, Short.MAX_VALUE)
                    .addComponent(eventFeedbackButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))
                .addGap(69, 69, 69))
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(patientNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(imageJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(problemComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nameTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(testBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bookApptBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(eventRegistration)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(profileBtn)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eventFeedbackButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bookApptBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookApptBtnActionPerformed
        CardLayout layout = (CardLayout)userContainer.getLayout();
        userContainer.add("Patient Book Appointment", new PatientBookAppointmentJPanel(userContainer, patient, system));
        layout.next(userContainer);
    }//GEN-LAST:event_bookApptBtnActionPerformed

    private void problemComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_problemComboBoxActionPerformed

    }//GEN-LAST:event_problemComboBoxActionPerformed

    private void testBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testBtnActionPerformed
        int selectedIndex = problemComboBox.getSelectedIndex();
        if (selectedIndex == 0) {
            JOptionPane.showMessageDialog(null, "Select a problem", "Error Message", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String problem = (String)problemComboBox.getSelectedItem();
        
        CardLayout layout = (CardLayout)userContainer.getLayout();

        switch (problem) {
            case "Alcohol/Drug Addiction":
                userContainer.add("Alcohol/Drug Test", new AddictionJPanel(userContainer, patient, system));
                break;
            case "Anxiety":
                userContainer.add("Anxiety Test", new AnxietyJPanel(userContainer, patient, system));
                break;
            case "Depression":
                userContainer.add("Depression Test", new DepressionJPanel(userContainer, patient, system));
                break;
            case "Eating Disorder":
                userContainer.add("Eating Disorder Test", new EatingDisorderJPanel(userContainer, patient, system));
                break;
            case "Sleep Disorder":
                userContainer.add("Sleep Disorder Test", new SleepDisorderJPanel(userContainer, patient, system));
                break;
            case "Stress":
                userContainer.add("Stress Test", new StressJPanel(userContainer, patient, system));
                break;
            default:
                return;
        }
        layout.next(userContainer);
    }//GEN-LAST:event_testBtnActionPerformed

    private void eventRegistrationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eventRegistrationActionPerformed
          
        int selectedRow = eventTbl.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Select an event to view", "Error Message", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
            String name = (String) eventTbl.getValueAt(selectedRow, 0);
            String location = (String) eventTbl.getValueAt(selectedRow, 1);
            Date date = (Date) eventTbl.getValueAt(selectedRow, 2);
            String description = (String)eventTbl.getValueAt(selectedRow, 3);
            String eventOrganizer = (String)eventTbl.getValueAt(selectedRow, 4);

            CardLayout layout = (CardLayout)userContainer.getLayout();

            userContainer.add("Patient Event Registration", new PatientEventRegistrationJPanel(userContainer,name,date,location,description,eventOrganizer));

            layout.next(userContainer); 
        
    }//GEN-LAST:event_eventRegistrationActionPerformed

    private void collectBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_collectBtnActionPerformed

        int row = medicineTbl.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Select a prescription for pickup", "Error Message", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        PharmacyWorkRequest request = (PharmacyWorkRequest)medicineTbl.getValueAt(row, 4);

        if (request.getStatus().startsWith("Picked up on")) {
            JOptionPane.showMessageDialog(null, "Prescription already picked up", "Info Message", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (!request.getStatus().equals("Ready For Pickup")) {
            JOptionPane.showMessageDialog(null, "Prescription not ready for pick up", "Error Message", JOptionPane.ERROR_MESSAGE);
            return;
        }

        request.setResolveDate(new Date());
        request.setStatus("Picked up on " + request.getResolveDate());
        populateMedicineRequest();
    }//GEN-LAST:event_collectBtnActionPerformed

    private void profileBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileBtnActionPerformed
        CardLayout layout = (CardLayout)userContainer.getLayout();
        userContainer.add("Patient Profile", new PatientViewProfileJPanel(userContainer, patient, system));
        layout.next(userContainer);
    }//GEN-LAST:event_profileBtnActionPerformed

    private void eventFeedbackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eventFeedbackButtonActionPerformed
        // TODO add your handling code here:
        CardLayout layout = (CardLayout)userContainer.getLayout();
        userContainer.add("Patient Book Appointment", new PatientFeedbackJPanel(userContainer, patient, system));
        layout.next(userContainer);
    }//GEN-LAST:event_eventFeedbackButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bookApptBtn;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton collectBtn;
    private javax.swing.JButton eventFeedbackButton;
    private javax.swing.JButton eventRegistration;
    private javax.swing.JTable eventTbl;
    private javax.swing.JLabel imageJLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable medicineTbl;
    private javax.swing.JLabel nameTxtField;
    private javax.swing.JLabel patientNameLabel;
    private javax.swing.JTable patientTbl;
    private javax.swing.JComboBox problemComboBox;
    private javax.swing.JButton profileBtn;
    private javax.swing.JButton testBtn;
    private javax.swing.JTable testTbl;
    // End of variables declaration//GEN-END:variables
}
