/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.*;
import static java.awt.image.ImageObserver.ERROR;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class DisplayAllUsers extends javax.swing.JDialog {

    Sistema s;
    DefaultTableModel modelUsers, modelAssociatedToprojects, modelTasksAssigned;

    //construtor
    public DisplayAllUsers(java.awt.Frame parent, boolean modal, Sistema s) {
        super(parent, modal);
        initComponents();
        this.s = s;
        Validacoes.SetDialogProperties(this, s,  "Listar Todos os Utilizadores");

        //set jtable selection to single row
        tableUsers.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        //prevent editing of jtable cells
        tableUsers.setDefaultEditor(Object.class, null);

        //create model to handle columns and rows
        modelUsers = new DefaultTableModel();
        modelUsers.addColumn("Id do Utilizador");
        modelUsers.addColumn("Nome do Utilizador");
        modelUsers.addColumn("Email do Utilizador");

        //relate model to table
        tableUsers.setModel(modelUsers);

        //Add rows to table with users info
        for (User u : s.getUsersRepository().getUsers()) {
            modelUsers.addRow(new Object[]{
                u.getUserId(),
                u.getUserName(),
                u.getEmail()
            });

        }

        //Projects Associated--------------------------------------------------
        //set jtable selection to single row
        tableProjectsAssociated.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        //prevent editing of jtable cells
        tableProjectsAssociated.setDefaultEditor(Object.class, null);

        //create model to handle columns and rows
        modelAssociatedToprojects = new DefaultTableModel();
        modelAssociatedToprojects.addColumn("Título do Projeto");
        modelAssociatedToprojects.addColumn("Descrição do projeto");
        modelAssociatedToprojects.addColumn("Data de Início");
        modelAssociatedToprojects.addColumn("Data de Fim");
        //relate model to table
        tableProjectsAssociated.setModel(modelAssociatedToprojects);

        //Tasks Assigned ------------------------------------------------------
        //set jtable selection to single row
        tableTasksAssigned.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        //prevent editing of jtable cells
        tableTasksAssigned.setDefaultEditor(Object.class, null);

        //create model to handle columns and rows
        modelTasksAssigned = new DefaultTableModel();
        modelTasksAssigned.addColumn("Título da Tarefa");
        modelTasksAssigned.addColumn("Descrição da Tarefa");
        modelTasksAssigned.addColumn("Prioridade");
        modelTasksAssigned.addColumn("Estado");
        modelTasksAssigned.addColumn("Data de Início");
        modelTasksAssigned.addColumn("Data de Fim");
        //relate model to table
        tableTasksAssigned.setModel(modelTasksAssigned);
//jtable user selection change event
        tableUsers.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {

                //Clear tables
                modelAssociatedToprojects.setRowCount(0);
                modelTasksAssigned.setRowCount(0);

                //Display Associated projects---------------------------------
                int UserId = (Integer) tableUsers.getValueAt(tableUsers.getSelectedRow(), 0);
                ArrayList<UserProjectsAssociation> ProjectsAssociatedToUser = s.getRepositoryUserProjectsAssociation().getUserProjectsAssociation(UserId);
                for (UserProjectsAssociation projectsAssociatedToUser : ProjectsAssociatedToUser) {
                    for (Project p : s.getRepositoryProjects().getProjects()) {
                        if (p.getProjectId() == projectsAssociatedToUser.getProjectId()) {
                            modelAssociatedToprojects.addRow(new Object[]{
                                p.getProjectTitle(),
                                p.getProjectDescription(),
                                Validacoes.FormatDate(p.getStartDate()),
                                Validacoes.FormatDate(p.getEndDate())
                            });
                        }
                    }//for
                }//for
                //------------------------~
                //Tasks Assigned----------------------------------------------
                ArrayList<Task> tasksAssignedToUser = s.getRepositoryTasks().getTasksAssignedToUser(UserId);
                for (Task t1 : tasksAssignedToUser) {

                    if (t1.getEndDate() == null) {
                        modelTasksAssigned.addRow(new Object[]{
                            t1.getTitle(),
                            t1.getDescription(),
                            t1.getTaskPriority(),
                            t1.getTaskStatus(),
                            Validacoes.FormatDate(t1.getStartDate()),
                            ""});
                    } else {
                        modelTasksAssigned.addRow(new Object[]{
                            t1.getTitle(),
                            t1.getDescription(),
                            t1.getTaskPriority(),
                            t1.getTaskStatus(),
                            Validacoes.FormatDate(t1.getStartDate()),
                            Validacoes.FormatDate(t1.getEndDate())});
                    }
                }
                //----------------------
            }
        });
    }//constructor

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
        tableTasksAssigned = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableUsers = new javax.swing.JTable();
        btnExit = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableProjectsAssociated = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Utilizador tem as Tarefas Atribuídas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 18))); // NOI18N

        tableTasksAssigned.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tableTasksAssigned.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tableTasksAssigned);

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 448, -1, -1));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Selecionar Utilizador", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 18))); // NOI18N

        tableUsers.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tableUsers.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(tableUsers);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 838, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 22, Short.MAX_VALUE))
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
        getContentPane().add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(671, 666, 200, 46));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Utilizador está Associados aos projetos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 18))); // NOI18N

        tableProjectsAssociated.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tableProjectsAssociated.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(tableProjectsAssociated);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 838, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, -1));

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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tableProjectsAssociated;
    private javax.swing.JTable tableTasksAssigned;
    private javax.swing.JTable tableUsers;
    // End of variables declaration//GEN-END:variables
}
