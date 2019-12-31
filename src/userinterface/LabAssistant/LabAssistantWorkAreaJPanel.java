/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package userinterface.LabAssistant;

import Business.Organization.LabOrganization;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.LabTestWorkRequest;
import Business.WorkQueue.WorkRequest;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nidhi Goyal
 */
public class LabAssistantWorkAreaJPanel extends javax.swing.JPanel {
    private final UserAccount userAccount;
    private final LabOrganization labOrganization;
    
    public LabAssistantWorkAreaJPanel(UserAccount account, LabOrganization org) {
        initComponents();
        this.userAccount = account;
        this.labOrganization = org;
        labAssistantTxtField.setText(userAccount.getEmployee().getName());
        populateWorkRequestTable();
    }
    
    private void populateWorkRequestTable() {
        DefaultTableModel model = (DefaultTableModel) labTestTbl.getModel();
        model.setRowCount(0);
        
        if (labOrganization.getWorkQueue() == null) {
            System.out.println("WorkQueue is NULL for " + labOrganization.getName());
            return;
        }

        for (WorkRequest request : labOrganization.getWorkQueue().getWorkRequestList()) {
            if (request == null) {
                continue;
            }

            // Do not show requests that are assigned to other lab assistants
            if (request.getReceiver() != null && !request.getReceiver().equals(userAccount)) {
                continue;
            }

            LabTestWorkRequest labRequest = (LabTestWorkRequest)request;
            Object[] row = new Object[4];
            row[0] = labRequest.getPatient().getName();
            row[1] = labRequest.getSender().getEmployee().getName();
            row[2] = labRequest.getMessage();
            row[3] = labRequest;

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

        jLabel1 = new javax.swing.JLabel();
        enterpriseLabel = new javax.swing.JLabel();
        labAssistantTxtField = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        labTestTbl = new javax.swing.JTable();
        processBtn = new javax.swing.JButton();
        assignBtn = new javax.swing.JButton();
        submitBtn = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel1.setText("Lab Assistant Dashboard");

        enterpriseLabel.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        enterpriseLabel.setText("Lab Assistant Name:");

        labAssistantTxtField.setText("<value>");

        labTestTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Patient", "Doctor", "Test", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(labTestTbl);

        processBtn.setText("Process");
        processBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                processBtnActionPerformed(evt);
            }
        });

        assignBtn.setText("Assign To Me");
        assignBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assignBtnActionPerformed(evt);
            }
        });

        submitBtn.setText("Submit");
        submitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(enterpriseLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(labAssistantTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(assignBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(processBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(submitBtn)))
                .addGap(35, 35, 35))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(188, 188, 188))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(enterpriseLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labAssistantTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(processBtn)
                    .addComponent(assignBtn)
                    .addComponent(submitBtn))
                .addGap(263, 263, 263))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void processBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_processBtnActionPerformed
        int row = labTestTbl.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Select a lab test request", "Error Message", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        LabTestWorkRequest request = (LabTestWorkRequest)labTestTbl.getValueAt(row, 3);
        
        if (request.getReceiver() == null) {
            JOptionPane.showMessageDialog(null, "You need to assign the request to yourself before processing", "Error Message", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        request.setStatus("Processing");
        populateWorkRequestTable();
    }//GEN-LAST:event_processBtnActionPerformed

    private void assignBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assignBtnActionPerformed
        int row = labTestTbl.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Select a lab test request", "Error Message", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        LabTestWorkRequest request = (LabTestWorkRequest)labTestTbl.getValueAt(row, 3);
        request.setReceiver(userAccount);
        request.setStatus("Acknowledged");
        populateWorkRequestTable();
    }//GEN-LAST:event_assignBtnActionPerformed

    private void submitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitBtnActionPerformed
        int row = labTestTbl.getSelectedRow();

        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Select a lab test request", "Error Message", JOptionPane.ERROR_MESSAGE);
            return;
        }

        LabTestWorkRequest request = (LabTestWorkRequest)labTestTbl.getValueAt(row, 3);
        if (request.getReceiver() == null) {
            JOptionPane.showMessageDialog(null, "You need to assign the request to yourself before processing", "Error Message", JOptionPane.ERROR_MESSAGE);
            return;
        }

        request.setStatus("Complete");
        request.setResolveDate(new Date());
        populateWorkRequestTable();
    }//GEN-LAST:event_submitBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton assignBtn;
    private javax.swing.JLabel enterpriseLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labAssistantTxtField;
    private javax.swing.JTable labTestTbl;
    private javax.swing.JButton processBtn;
    private javax.swing.JButton submitBtn;
    // End of variables declaration//GEN-END:variables
}
