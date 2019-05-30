/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class AssociateUserToProjects extends javax.swing.JDialog {

    Sistema s;
    DefaultTableModel modelProjects, modelUsers;

    //construtor
    public AssociateUserToProjects(java.awt.Frame parent, boolean modal, Sistema s) {
        super(parent, modal);
        initComponents();
        this.s = s;
        Validacoes.SetDialogProperties(this, s, "Associar Utilizador a Projectos");

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
            if (s.getCurrentUser().getUserId() != u.getUserId()) {

                modelUsers.addRow(new Object[]{
                    u.getUserId(),
                    u.getUserName(),
                    u.getEmail()
                });
            }
        }

        //set jtable selection to multiple row selection
        tableProjects.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        //prevent editing of jtable cells
        tableProjects.setDefaultEditor(Object.class, null);

        //create model to handle columns and rows
        modelProjects = new DefaultTableModel();
        modelProjects.addColumn("Id do Projeto");
        modelProjects.addColumn("Título do Projeto");
        modelProjects.addColumn("Descrição do Projeto");

        tableProjects.setModel(modelProjects);

        ArrayList<Project> ProjectsByOwner = new ArrayList<>();

        //Select projects owned by current user (owner of projects)
        ProjectsByOwner = s.getRepositoryProjects().getProjectsByOwner(s.getCurrentUser().getUserId());

        //Add rows to table with projects info
        for (Project p : ProjectsByOwner) {
            modelProjects.addRow(new Object[]{
                p.getProjectId(),
                p.getProjectTitle(),
                p.getProjectDescription()
            });
        }

        EnablebtnAssociateUserToProjects();

        //jtable project selection change event
        tableProjects.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                EnablebtnAssociateUserToProjects();
                if (tableProjects.getSelectedRow() > -1) {

                }
            }
        });

        //jtable user selection change event
        tableUsers.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                EnablebtnAssociateUserToProjects();
                if (tableUsers.getSelectedRow() > -1) {

                }
            }
        });
    }

    void EnablebtnAssociateUserToProjects() {
        boolean b = true;

        if (tableUsers.getSelectedRow() == -1) {
            lbltableUsertWarning.setText("Selecione um utilizador");
            b = false;
        } else {
            lbltableUsertWarning.setText("");
        }

        if (tableProjects.getSelectedRow() == -1) {
            lbltableProjectWarning.setText("Selecione um ou mais projetos para associá-lo a este utilizador");
            b = false;
        } else {
            lbltableProjectWarning.setText("");
        }

        btnAssociateUserToProjects.setEnabled(b);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tableProjects = new javax.swing.JTable();
        lbltableProjectWarning = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableUsers = new javax.swing.JTable();
        lbltableUsertWarning = new javax.swing.JLabel();
        btnExit = new javax.swing.JButton();
        btnAssociateUserToProjects = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Selecionar Projeto (Mantenha Ctrl Premido para Selecionar vários Projetos)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 18))); // NOI18N

        tableProjects.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tableProjects.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tableProjects);

        lbltableProjectWarning.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbltableProjectWarning.setForeground(new java.awt.Color(255, 0, 0));
        lbltableProjectWarning.setText("lbltableProjectWarning");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbltableProjectWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 712, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 184, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addComponent(lbltableProjectWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Selecionar Utilizador", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 18))); // NOI18N

        tableUsers.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tableUsers.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(tableUsers);

        lbltableUsertWarning.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbltableUsertWarning.setForeground(new java.awt.Color(255, 0, 0));
        lbltableUsertWarning.setText("lbltableProjectWarning");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lbltableUsertWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 712, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 185, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(lbltableUsertWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

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

        btnAssociateUserToProjects.setBackground(new java.awt.Color(51, 110, 123));
        btnAssociateUserToProjects.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        btnAssociateUserToProjects.setForeground(new java.awt.Color(255, 255, 255));
        btnAssociateUserToProjects.setText("Associar Utilizador a Projeto(s)");
        btnAssociateUserToProjects.setBorder(null);
        btnAssociateUserToProjects.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAssociateUserToProjectsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAssociateUserToProjects, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAssociateUserToProjects, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnAssociateUserToProjectsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAssociateUserToProjectsActionPerformed

        int UserId = (Integer) tableUsers.getModel().getValueAt(tableUsers.getSelectedRow(), 0);
        int ProjectId;
        for (int selectedRow : tableProjects.getSelectedRows()) {
            ProjectId = (Integer) tableProjects.getModel().getValueAt(selectedRow, 0);
            if (!s.getRepositoryUserProjectsAssociation().exists(UserId, ProjectId)) {
                s.getRepositoryUserProjectsAssociation().addUserProjectsAssociation(UserId, ProjectId, s.getCurrentUser().getUserId());
                //System.out.println("New Association: " +UserId+" " +ProjectId+" " + s.getCurrentUser().getUserId());
            }

        }

        JOptionPane.showMessageDialog(null, "O utilizador foi associado ao projeto com sucesso!");
        this.dispose();
    }//GEN-LAST:event_btnAssociateUserToProjectsActionPerformed

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
    private javax.swing.JButton btnAssociateUserToProjects;
    private javax.swing.JButton btnExit;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbltableProjectWarning;
    private javax.swing.JLabel lbltableUsertWarning;
    private javax.swing.JTable tableProjects;
    private javax.swing.JTable tableUsers;
    // End of variables declaration//GEN-END:variables
}
