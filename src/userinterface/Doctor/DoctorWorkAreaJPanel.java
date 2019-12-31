/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package userinterface.Doctor;

import Business.EcoSystem;
import Business.Enterprise.HospitalEnterprise;
import Business.Hospital.Appointment;
import Business.Organization.DoctorOrganization;
import Business.Organization.LabOrganization;
import Business.Organization.Organization;
import Business.Organization.PharmacyOrganization;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.AppointmentWorkRequest;
import Business.WorkQueue.LabTestWorkRequest;
import Business.WorkQueue.PharmacyWorkRequest;
import Business.WorkQueue.WorkRequest;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nidhi Goyal
 */
public final class DoctorWorkAreaJPanel extends javax.swing.JPanel {
    private final UserAccount userAccount;
    private final DoctorOrganization doctorOrganization;
    private LabOrganization labOrganization;
    private PharmacyOrganization phmcyOrganization;
    private final EcoSystem system;
    private final HospitalEnterprise enterprise;

    public DoctorWorkAreaJPanel(UserAccount account, DoctorOrganization doctorOrganization, HospitalEnterprise enterprise, EcoSystem business) {
        initComponents();
        this.userAccount = account;
        this.doctorOrganization = doctorOrganization;
        this.enterprise = enterprise;
        this.system = business;

        doctorNameTxtField.setText(userAccount.getEmployee().getName());

        for (Organization org : this.enterprise.getOrganizationDirectory().getOrganizationList()) {
            if (org instanceof LabOrganization) {
                this.labOrganization = (LabOrganization)org;
                break;
            }
        }

        for (Organization org : this.enterprise.getOrganizationDirectory().getOrganizationList()) {
            if (org instanceof PharmacyOrganization) {
                this.phmcyOrganization = (PharmacyOrganization)org;
                break;
            }
        }

        populateApptWorkRequestTable();
        populateLabTestWorkRequestTable();
        populatePharmacyWorkRequestTable();
    }
    
    private void populateApptWorkRequestTable() {
        DefaultTableModel model = (DefaultTableModel) patientRequestTbl.getModel();
        model.setRowCount(0);
        
        if (doctorOrganization.getWorkQueue() == null) {
            System.out.println("WorkQueue is NULL for " + doctorOrganization.getName());
            return;
        }

        for (WorkRequest request : doctorOrganization.getWorkQueue().getWorkRequestList()) {
            if (request == null) {
                continue;
            }

            if (request.getReceiver().equals(userAccount)) {
                Appointment appt = ((AppointmentWorkRequest)request).getAppointment();
                System.out.println("appt"+appt.getPatient().getName());
                Object[] row = new Object[4];
                row[0] = appt.getPatient().getName();
                row[1] = appt.getAppointmentDate();
                row[2] = appt;
                row[3] = request;

                model.addRow(row);
            }
        }
    }
    
    public void populateLabTestWorkRequestTable() {
        DefaultTableModel model = (DefaultTableModel) labStatusJTable.getModel();
        model.setRowCount(0);
        
        if (labOrganization == null) {
            System.out.println("Lab Organization is NULL");
            return;
        }
        
        if (labOrganization.getWorkQueue() == null) {
            System.out.println("WorkQueue is NULL for " + labOrganization.getName());
            return;
        }

        for (WorkRequest request : labOrganization.getWorkQueue().getWorkRequestList()) {
            if (request == null) {
                continue;
            }

            if (request.getSender().getUsername().equals(userAccount.getUsername())) {
                LabTestWorkRequest labRequest = (LabTestWorkRequest)request;
                Object[] row = new Object[4];
                row[0] = labRequest.getPatient().getName();
                row[1] = labRequest.getApptDate();
                row[2] = labRequest.getReceiver() == null ? "" : labRequest.getReceiver().getEmployee().getName();
                row[3] = labRequest;

                model.addRow(row);
            }
        }
    }
    
    private void populatePharmacyWorkRequestTable() {
        DefaultTableModel model = (DefaultTableModel) pharmacyTbl.getModel();
        model.setRowCount(0);
        
        if (phmcyOrganization == null) {
            System.out.println("Pharmacy Organization is NULL");
            return;
        }

        if (phmcyOrganization.getWorkQueue() == null) {
            System.out.println("WorkQueue is NULL for " + phmcyOrganization.getName());
            return;
        }

        for (WorkRequest request : phmcyOrganization.getWorkQueue().getWorkRequestList()) {
            if (request == null) {
                continue;
            }

            if (request.getSender().getUsername().equals(userAccount.getUsername())) {
                PharmacyWorkRequest phmcyRequest = (PharmacyWorkRequest)request;
                Object[] row = new Object[4];
                row[0] = phmcyRequest.getPatient().getName();
                row[1] = phmcyRequest.getApptDate();
                row[2] = phmcyRequest.getReceiver() == null ? "" : phmcyRequest.getReceiver().getEmployee().getName();
                row[3] = phmcyRequest;

                model.addRow(row);
            }
        }
    }

    private void processApptWorkRequest(String newStatus) {
        int row = patientRequestTbl.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Select an appointment", "Error Message", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        AppointmentWorkRequest apptRequest = (AppointmentWorkRequest)(patientRequestTbl.getValueAt(row, 3));
        
        if (apptRequest.getStatus().equals(newStatus)) {
            JOptionPane.showMessageDialog(null, "Appointment already in " + newStatus + " status", "Error Message", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (apptRequest.getStatus().equals("Finished")) {
            JOptionPane.showMessageDialog(null, "Cannot modify finished appointment", "Error Message", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if ((apptRequest.getStatus().equals("Confirmed") || apptRequest.getStatus().equals("Declined")) &&
                (newStatus.equals("Confirmed") || newStatus.equals("Declined"))) {
            JOptionPane.showMessageDialog(null, "Cannot change confirmed/declined status", "Error Message", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (apptRequest.getStatus().equals("Pending Confirmation") && !newStatus.equals("Confirmed") && !newStatus.equals("Declined")) {
            JOptionPane.showMessageDialog(null, "You need to first confirm the appointment", "Error Message", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!newStatus.equals("Confirmed") && !newStatus.equals("Declined") && 
                apptRequest.getAppointment().getAppointmentDate().compareTo(new Date()) > 0) {
            JOptionPane.showMessageDialog(null, "Cannot modify future appointment", "Error Message", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (newStatus.equals("In Progress") && isAnotherApptInProgress()) {
            JOptionPane.showMessageDialog(null, "Cannot start new appointment until existing is finished", "Error Message", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else if (newStatus.equals("Finished")) {
            LabTestWorkRequest labRequest = getLabRequestFromApptDate(apptRequest.getAppointment().getAppointmentDate());
            if (labRequest != null && !labRequest.getStatus().equals("Complete")) {
                JOptionPane.showMessageDialog(null, "Cannot complete appointment while lab test in progress", "Error Message", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        apptRequest.setStatus(newStatus);
        apptRequest.setResolveDate(new Date());
        apptRequest.setMessage("Your appointment has been " + newStatus);

        populateApptWorkRequestTable();
    }
    
    private boolean isAnotherApptInProgress() {
        for (int i = 0; i < patientRequestTbl.getRowCount(); ++i) {
            AppointmentWorkRequest request = (AppointmentWorkRequest)patientRequestTbl.getValueAt(i, 3);
            if (request.getStatus().equals("In Progress")) {
                return true;
            }
        }
        return false;
    }
    
    private LabTestWorkRequest getLabRequestFromApptDate(Date date) {
        for (int i = 0; i < labStatusJTable.getRowCount(); ++i) {
            LabTestWorkRequest request = (LabTestWorkRequest)labStatusJTable.getValueAt(i, 3);
            Date apptDate = request.getApptDate();
            if (apptDate.equals(date)) {
                return request;
            }
        }
        return null;
    }
    
    private PharmacyWorkRequest getPharmacyRequestFromApptDate(Date date) {
        for (int i = 0; i < pharmacyTbl.getRowCount(); ++i) {
            PharmacyWorkRequest request = (PharmacyWorkRequest)pharmacyTbl.getValueAt(i, 3);
            Date apptDate = request.getApptDate();
            if (apptDate.equals(date)) {
                return request;
            }
        }
        return null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        enterpriseLabel = new javax.swing.JLabel();
        doctorNameTxtField = new javax.swing.JLabel();
        labTestBtn = new javax.swing.JButton();
        medicineBtn = new javax.swing.JButton();
        confirmApptBtn = new javax.swing.JButton();
        declineApptBtn = new javax.swing.JButton();
        doctorjTabbedPane = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        patientRequestTbl = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        labStatusJTable = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        pharmacyTbl = new javax.swing.JTable();
        finishApptBtn = new javax.swing.JButton();
        startApptBtn = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel1.setText("DOCTOR DASHBOARD");

        enterpriseLabel.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        enterpriseLabel.setText("Doctor Name:");

        doctorNameTxtField.setText("<value>");

        labTestBtn.setText("Request Lab Test");
        labTestBtn.setMaximumSize(new java.awt.Dimension(190, 30));
        labTestBtn.setMinimumSize(new java.awt.Dimension(190, 30));
        labTestBtn.setPreferredSize(new java.awt.Dimension(190, 30));
        labTestBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                labTestBtnActionPerformed(evt);
            }
        });

        medicineBtn.setText("Prescribe Medicine");
        medicineBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                medicineBtnActionPerformed(evt);
            }
        });

        confirmApptBtn.setText("Confirm Appointment");
        confirmApptBtn.setMaximumSize(new java.awt.Dimension(190, 30));
        confirmApptBtn.setMinimumSize(new java.awt.Dimension(190, 30));
        confirmApptBtn.setPreferredSize(new java.awt.Dimension(190, 30));
        confirmApptBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmApptBtnActionPerformed(evt);
            }
        });

        declineApptBtn.setText("Decline Appointment");
        declineApptBtn.setMaximumSize(new java.awt.Dimension(190, 30));
        declineApptBtn.setMinimumSize(new java.awt.Dimension(190, 30));
        declineApptBtn.setPreferredSize(new java.awt.Dimension(190, 30));
        declineApptBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                declineApptBtnActionPerformed(evt);
            }
        });

        patientRequestTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Patient", "AppointmentDate", "Reason", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(patientRequestTbl);

        doctorjTabbedPane.addTab("Appointment History", jScrollPane1);

        labStatusJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Patient", "Appointment Date", "LabAssistant", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class
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
        jScrollPane2.setViewportView(labStatusJTable);
        if (labStatusJTable.getColumnModel().getColumnCount() > 0) {
            labStatusJTable.getColumnModel().getColumn(0).setResizable(false);
            labStatusJTable.getColumnModel().getColumn(2).setResizable(false);
            labStatusJTable.getColumnModel().getColumn(3).setResizable(false);
        }

        doctorjTabbedPane.addTab("Lab Test History", jScrollPane2);

        pharmacyTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Patient", "Appointment Date", "Pharmacy", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(pharmacyTbl);

        doctorjTabbedPane.addTab("Medicine History", jScrollPane3);

        finishApptBtn.setText("Finish Appointment");
        finishApptBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finishApptBtnActionPerformed(evt);
            }
        });

        startApptBtn.setText("Start Appointment");
        startApptBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startApptBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(enterpriseLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(doctorNameTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(264, 264, 264))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(74, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(doctorjTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(declineApptBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(confirmApptBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(finishApptBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                            .addComponent(startApptBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(medicineBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labTestBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(93, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(enterpriseLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(doctorNameTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(doctorjTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmApptBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labTestBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(startApptBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(medicineBtn)
                    .addComponent(declineApptBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(finishApptBtn))
                .addContainerGap(177, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void labTestBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_labTestBtnActionPerformed
        int row = patientRequestTbl.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Select an appointment for lab test", "Error Message", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Appointment appt = (Appointment)(patientRequestTbl.getValueAt(row, 2));

        AppointmentWorkRequest apptRequest = (AppointmentWorkRequest)(patientRequestTbl.getValueAt(row, 3));
        
        if (apptRequest.getStatus().equals("Declined") || apptRequest.getStatus().equals("Pending Confirmation")) {
            JOptionPane.showMessageDialog(null, "Lab test available only for confirmed appointments", "Error Message", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (getLabRequestFromApptDate(appt.getAppointmentDate()) != null) {
            JOptionPane.showMessageDialog(null, "Lab test for this appt already sent", "Error Message", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Date now = new Date();
        if (appt.getAppointmentDate().compareTo(now) > 0) {
            JOptionPane.showMessageDialog(null, "Cannot send lab request for a future appointment", "Error Message", JOptionPane.ERROR_MESSAGE);
            return;
        }

        LabTestWorkRequest request = new LabTestWorkRequest();
        request.setSender(userAccount);
        request.setStatus("Pending");
        request.setApptDate(appt.getAppointmentDate());
        request.setPatient(appt.getPatient());
        request.setMessage(appt.getAppointmentReason());

        this.labOrganization.getWorkQueue().getWorkRequestList().add(request);
        
        JOptionPane.showMessageDialog(null, "Lab Test request sent", "Success Message", JOptionPane.INFORMATION_MESSAGE);

        populateLabTestWorkRequestTable();
    }//GEN-LAST:event_labTestBtnActionPerformed

    private void medicineBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_medicineBtnActionPerformed
        int row = patientRequestTbl.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Select an appointment for prescription", "Error Message", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Appointment appt = (Appointment)(patientRequestTbl.getValueAt(row, 2));
        
        AppointmentWorkRequest apptRequest = (AppointmentWorkRequest)(patientRequestTbl.getValueAt(row, 3));
        
        if (apptRequest.getStatus().equals("Declined") || apptRequest.getStatus().equals("Pending Confirmation")) {
            JOptionPane.showMessageDialog(null, "Prescription can be sent only for confirmed/finished appointments", "Error Message", JOptionPane.ERROR_MESSAGE);
            return;
        }

        PharmacyWorkRequest request = getPharmacyRequestFromApptDate(appt.getAppointmentDate());
        if (request != null) {
            JOptionPane.showMessageDialog(null, "Pharmacy request for this appt already sent", "Error Message", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Date now = new Date();
        if (appt.getAppointmentDate().compareTo(now) > 0) {
            JOptionPane.showMessageDialog(null, "Cannot send medicine request for a future appointment", "Error Message", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        LabTestWorkRequest labRequest = getLabRequestFromApptDate(appt.getAppointmentDate());
        if (labRequest != null && !labRequest.getStatus().equals("Complete")) {
            JOptionPane.showMessageDialog(null, "Cannot send medicine request while lab test in progress", "Error Message", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        PharmacyWorkRequest phmcyRequest = new PharmacyWorkRequest();
        phmcyRequest.setSender(userAccount);
        phmcyRequest.setPatient(appt.getPatient());
        phmcyRequest.setStatus("Pending");
        phmcyRequest.setApptDate(appt.getAppointmentDate());

        this.phmcyOrganization.getWorkQueue().getWorkRequestList().add(phmcyRequest);
        JOptionPane.showMessageDialog(null, "Medicine request sent", "Success Message", JOptionPane.INFORMATION_MESSAGE);

        populatePharmacyWorkRequestTable();
    }//GEN-LAST:event_medicineBtnActionPerformed

    private void confirmApptBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmApptBtnActionPerformed
        processApptWorkRequest("Confirmed");
    }//GEN-LAST:event_confirmApptBtnActionPerformed

    private void declineApptBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_declineApptBtnActionPerformed
        processApptWorkRequest("Declined");
    }//GEN-LAST:event_declineApptBtnActionPerformed

    private void finishApptBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finishApptBtnActionPerformed
        processApptWorkRequest("Finished");
    }//GEN-LAST:event_finishApptBtnActionPerformed

    private void startApptBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startApptBtnActionPerformed
        processApptWorkRequest("In Progress");
    }//GEN-LAST:event_startApptBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton confirmApptBtn;
    private javax.swing.JButton declineApptBtn;
    private javax.swing.JLabel doctorNameTxtField;
    private javax.swing.JTabbedPane doctorjTabbedPane;
    private javax.swing.JLabel enterpriseLabel;
    private javax.swing.JButton finishApptBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable labStatusJTable;
    private javax.swing.JButton labTestBtn;
    private javax.swing.JButton medicineBtn;
    private javax.swing.JTable patientRequestTbl;
    private javax.swing.JTable pharmacyTbl;
    private javax.swing.JButton startApptBtn;
    // End of variables declaration//GEN-END:variables
    
}
