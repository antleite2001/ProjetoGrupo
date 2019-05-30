/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class FilterByTaskStatus extends javax.swing.JDialog {

    Sistema s;
    DefaultTableModel modelUsers, modelTasksCreatedBy;

    //construtor
    public FilterByTaskStatus(java.awt.Frame parent, boolean modal, Sistema s) {
        super(parent, modal);
        initComponents();
        this.s = s;
        Validacoes.SetDialogProperties(this, s, "Filtrar por Estado");

        //Tasks Assigned ------------------------------------------------------
        //set jtable selection to single row
        tableTasksCreatedBy.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        //prevent editing of jtable cells
        tableTasksCreatedBy.setDefaultEditor(Object.class, null);

        //create model to handle columns and rows
        modelTasksCreatedBy = new DefaultTableModel();
        modelTasksCreatedBy.addColumn("Id da Tarefa");
        modelTasksCreatedBy.addColumn("Título da Tarefa");
        modelTasksCreatedBy.addColumn("Descrição da Tarefa");
        modelTasksCreatedBy.addColumn("Prioridade");
        modelTasksCreatedBy.addColumn("Data de Início");
        modelTasksCreatedBy.addColumn("Data de Fim");
        //relate model to table
        tableTasksCreatedBy.setModel(modelTasksCreatedBy);

        //Events
        cbTaskStatus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FilStatusTable();

            }//void
        });

        FilStatusTable();
    }//constructor

    void FilStatusTable() {
        //Clear table
        modelTasksCreatedBy.setRowCount(0);

        //Display Tasks Created by user
        TaskStatus SelectedStatus = TaskStatus.NOTSTARTED;
        int status =  cbTaskStatus.getSelectedIndex();
        switch (status) {
            case 0: {
                SelectedStatus = TaskStatus.NOTSTARTED;
                break;
            }
            case 1: {
                SelectedStatus = TaskStatus.INPROGRESS;
                break;
            }
            case 2: {
                SelectedStatus = TaskStatus.FINISHED;
                break;
            }
            default: {
                System.out.println("ERROR: Status not defined: " + s);
                break;
            }
        }
  
        ArrayList<Task> tasksByStatus = s.getRepositoryTasks().getTasksByStatus(SelectedStatus);
        for (Task taskByStatus : tasksByStatus) {
            if (taskByStatus.getEndDate() != null) {
                modelTasksCreatedBy.addRow(new Object[]{
                    taskByStatus.getTaskId(),
                    taskByStatus.getTitle(),
                    taskByStatus.getDescription(),
                    taskByStatus.getEnumPriority(),
                    Validacoes.FormatDate(taskByStatus.getStartDate()),
                    Validacoes.FormatDate(taskByStatus.getEndDate())
                });
            } else {
                modelTasksCreatedBy.addRow(new Object[]{
                    taskByStatus.getTaskId(),
                    taskByStatus.getTitle(),
                    taskByStatus.getDescription(),
                    taskByStatus.getEnumPriority(),
                    Validacoes.FormatDate(taskByStatus.getStartDate()),
                    ""
                });
            }

        }//for
    }//void

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableTasksCreatedBy = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        cbTaskStatus = new javax.swing.JComboBox<>();
        btnExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tarefas Selecionadas por Estado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 18))); // NOI18N

        tableTasksCreatedBy.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tableTasksCreatedBy.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tableTasksCreatedBy);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 838, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 108, -1, -1));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Selecionar Estado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 18))); // NOI18N

        cbTaskStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Não Iniciada", "Em Andamento", "Terminada" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(cbTaskStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbTaskStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        btnExit.setBackground(new java.awt.Color(51, 110, 123));
        btnExit.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        btnExit.setForeground(new java.awt.Color(255, 255, 255));
        btnExit.setText("Sair");
        btnExit.setBorder(null);
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        getContentPane().add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(671, 552, 200, 46));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(AssociateUserToProject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(AssociateUserToProject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(AssociateUserToProject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(AssociateUserToProject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                AssociateUserToProject dialog = new AssociateUserToProject(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JComboBox<String> cbTaskStatus;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableTasksCreatedBy;
    // End of variables declaration//GEN-END:variables
}
