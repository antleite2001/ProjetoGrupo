/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.Project;
import Backend.Sistema;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class ChangeProjectData extends javax.swing.JDialog {

    Sistema s;
    DefaultTableModel model;

    public ChangeProjectData(java.awt.Frame parent, boolean modal, Sistema s) {
        super(parent, modal);
        initComponents();
        this.s = s;
        this.setTitle("Alterar dados de Projeto - " + s.getCurrentUser().getUserName() + " (" + s.getCurrentUser().getEmail() + ")");

//set jtable selection to single row
        tableProjects.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        //prevent editing of jtable cells
        tableProjects.setDefaultEditor(Object.class, null);

        //create model to handle columns and rows
        model = new DefaultTableModel();
        model.addColumn("Id do Projeto");//0
        model.addColumn("Título do Projeto");//1
        model.addColumn("Descrição do Projeto");//2
        model.addColumn("Data de Início");//3
        model.addColumn("Data de Fim");//4
        model.addColumn("Data de Início NotFormatted");//5
        model.addColumn("Data de Fim Not Formatted");//6
        tableProjects.setModel(model);
        //Do not change the order 6,5
        tableProjects.removeColumn(tableProjects.getColumnModel().getColumn(6));
        tableProjects.removeColumn(tableProjects.getColumnModel().getColumn(5));
        ArrayList<Project> ProjectsByOwner = new ArrayList<>();

        //Select projects owned by current user (owner of projects)
        //Only owners can create Task Lista and Tasks. See Specification
        ProjectsByOwner = s.getRepositoryProjects().getProjectsByOwner(s.getCurrentUser().getUserId());

        //Add rows to table with projects info
        for (Project p : ProjectsByOwner) {
            model.addRow(new Object[]{
                p.getProjectId(),
                p.getProjectTitle(),//1
                p.getProjectDescription(),//2
                Validacoes.FormatDate(p.getStartDate()),//3
                Validacoes.FormatDate(p.getEndDate()),//4
                p.getStartDate(),//5
                p.getEndDate()//6
            });

        }
        EnablebtnUpdateProjectData();

        //jtable selection change event
        tableProjects.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                UpdateProjectData();

            }
        });

        tbProjectDescription.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent arg0) {
                EnablebtnUpdateProjectData();
            }

            @Override
            public void insertUpdate(DocumentEvent arg0) {
                EnablebtnUpdateProjectData();
            }

            @Override
            public void removeUpdate(DocumentEvent arg0) {
                EnablebtnUpdateProjectData();
            }
        });

        tbProjectTitle.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent arg0) {
                EnablebtnUpdateProjectData();
            }

            @Override
            public void insertUpdate(DocumentEvent arg0) {
                EnablebtnUpdateProjectData();
            }

            @Override
            public void removeUpdate(DocumentEvent arg0) {
                EnablebtnUpdateProjectData();
            }

        });

        calStartDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EnablebtnUpdateProjectData();
            }
        });

        calEndDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EnablebtnUpdateProjectData();
            }
        });

    }

    void UpdateProjectData() {
        tbProjectTitle.setText((String) tableProjects.getModel().getValueAt(tableProjects.getSelectedRow(), 1));
        tbProjectDescription.setText((String) tableProjects.getModel().getValueAt(tableProjects.getSelectedRow(), 2));
        System.out.println((Date) tableProjects.getModel().getValueAt(tableProjects.getSelectedRow(), 5));
        System.out.println((Date) tableProjects.getModel().getValueAt(tableProjects.getSelectedRow(), 6));
        calStartDate.setDate((Date) tableProjects.getModel().getValueAt(tableProjects.getSelectedRow(), 5));
        calEndDate.setDate((Date) tableProjects.getModel().getValueAt(tableProjects.getSelectedRow(), 6));
    }

    void EnablebtnUpdateProjectData() {
        boolean b = true;

        //Project Title
        if (tbProjectTitle.getText().isBlank()) {
            b = false;
            lblTitleWarning.setText("Introduza um título para o projeto");
        } else {
            lblTitleWarning.setText("");
        }

        //Project description
        if (tbProjectDescription.getText().isBlank()) {
            b = false;
            lblDescriptionWarning.setText("Introduza uma descrição para o projeto");
        } else {
            lblDescriptionWarning.setText("");
        }

        long startdate = 1 + (calStartDate.getDateInMillis() / (1000 * 60 * 60 * 24));

        long enddate = 1 + (calEndDate.getDateInMillis() / (1000 * 60 * 60 * 24));

//        long currentdate = System.currentTimeMillis() / (1000 * 60 * 60 * 24);
//        if (startdate < currentdate) {
//            b = false;
//            lblStartDateWarning.setText("Data de início do projeto deve ser igual ou superior à data atual");
//        } else {
//            lblStartDateWarning.setText("");
//        }
        if (enddate < startdate) {

            b = false;
            lblEndDateWarning.setText("Data de fim do projeto deve ser igual ou superior à data de início");
        } else {
            lblEndDateWarning.setText("");
        }

        if (tableProjects.getSelectedRow() == -1) {
            lbltableProjectWarning.setText("Selecione um projeto para edição");
            b = false;
        } else {
            lbltableProjectWarning.setText("");
        }

        btnUpdateProjectData.setEnabled(b);
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
        jPanel4 = new javax.swing.JPanel();
        tbProjectTitle = new javax.swing.JTextField();
        lblTitleWarning = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        tbProjectDescription = new javax.swing.JTextField();
        lblDescriptionWarning = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        calStartDate = new org.jdesktop.swingx.JXDatePicker();
        jPanel6 = new javax.swing.JPanel();
        calEndDate = new org.jdesktop.swingx.JXDatePicker();
        lblEndDateWarning = new javax.swing.JLabel();
        btnUpdateProjectData = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Projetos"));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tableProjects.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tableProjects.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tableProjects);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 16, 740, 150));

        lbltableProjectWarning.setText("lbltableProjectWarning");
        jPanel2.add(lbltableProjectWarning, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 730, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 780, 210));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Editar Título"));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbProjectTitle.setPreferredSize(new java.awt.Dimension(30, 20));
        jPanel4.add(tbProjectTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 574, 30));

        lblTitleWarning.setText("lblTitleWarning");
        jPanel4.add(lblTitleWarning, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 63, 588, 39));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 620, -1));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Editar Descrição"));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel5.add(tbProjectDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 16, 574, 30));

        lblDescriptionWarning.setText("lblDescriptionWarning");
        jPanel5.add(lblDescriptionWarning, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 591, 39));

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, -1, 107));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Editar Data de Início"));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(calStartDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 27, -1, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 620, 70));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Editar Data de Fim"));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel6.add(calEndDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 27, 220, -1));

        lblEndDateWarning.setText("lblDateWarning");
        lblEndDateWarning.setMinimumSize(new java.awt.Dimension(209, 20));
        jPanel6.add(lblEndDateWarning, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 574, 35));

        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 560, 620, -1));

        btnUpdateProjectData.setText("Atualizar Dados de Projeto");
        btnUpdateProjectData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateProjectDataActionPerformed(evt);
            }
        });
        getContentPane().add(btnUpdateProjectData, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 650, -1, 40));

        btnExit.setText("Sair");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        getContentPane().add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(283, 650, 170, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateProjectDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateProjectDataActionPerformed
        //collect old and new data
        String oldTitle = (String) tableProjects.getModel().getValueAt(tableProjects.getSelectedRow(), 1);
        String newTitle = tbProjectTitle.getText();
        String newDescription = tbProjectDescription.getText();
        Date newStartDate = calStartDate.getDate();
        Date newEndDate = calEndDate.getDate();
        
        //Change data in model
        s.getRepositoryProjects().changeProjectById((int) tableProjects.getModel().getValueAt(tableProjects.getSelectedRow(), 0),
                newTitle, newDescription, newStartDate, newEndDate);

        //change data im table
        tableProjects.getModel().setValueAt(newTitle, tableProjects.getSelectedRow(), 1);
        tableProjects.getModel().setValueAt(newDescription, tableProjects.getSelectedRow(), 2);
        tableProjects.getModel().setValueAt(Validacoes.FormatDate(newStartDate), tableProjects.getSelectedRow(), 3);
        tableProjects.getModel().setValueAt(Validacoes.FormatDate(newEndDate), tableProjects.getSelectedRow(), 4);
        tableProjects.getModel().setValueAt(newStartDate, tableProjects.getSelectedRow(), 5);
        tableProjects.getModel().setValueAt(newEndDate, tableProjects.getSelectedRow(), 6);

        //display message
        JOptionPane.showMessageDialog(null, "O projeto '" + oldTitle + "' foi alterado com sucesso para\nTítulo: " + newTitle
                + "\nDescrição: " + newDescription
                + "\nData de Início: " + Validacoes.FormatDate(newStartDate)
                + "\nData de Fim: " + Validacoes.FormatDate(newEndDate));

    }//GEN-LAST:event_btnUpdateProjectDataActionPerformed

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
//            java.util.logging.Logger.getLogger(ChangeProjectData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ChangeProjectData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ChangeProjectData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ChangeProjectData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                ChangeProjectData dialog = new ChangeProjectData(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnUpdateProjectData;
    private org.jdesktop.swingx.JXDatePicker calEndDate;
    private org.jdesktop.swingx.JXDatePicker calStartDate;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDescriptionWarning;
    private javax.swing.JLabel lblEndDateWarning;
    private javax.swing.JLabel lblTitleWarning;
    private javax.swing.JLabel lbltableProjectWarning;
    private javax.swing.JTable tableProjects;
    private javax.swing.JTextField tbProjectDescription;
    private javax.swing.JTextField tbProjectTitle;
    // End of variables declaration//GEN-END:variables
}
