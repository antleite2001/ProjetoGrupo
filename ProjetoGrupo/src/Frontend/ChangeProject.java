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

public class ChangeProject extends javax.swing.JDialog {

    Sistema s;
    DefaultTableModel model;

    public ChangeProject(java.awt.Frame parent, boolean modal, Sistema s) {
        super(parent, modal);
        initComponents();
        this.s = s;
        this.setTitle("Alterar dados de Projeto - " + s.getCurrentUser().getUserName() + " (" + s.getCurrentUser().getEmail() + ")");
        Validacoes.FormatCalendar(calStartDate);
        Validacoes.FormatCalendar(calEndDate);
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
        //System.out.println((Date) tableProjects.getModel().getValueAt(tableProjects.getSelectedRow(), 5));
        //System.out.println((Date) tableProjects.getModel().getValueAt(tableProjects.getSelectedRow(), 6));
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
        lbltableProjectWarning = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableProjects = new javax.swing.JTable();
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

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Editar Título", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 18))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbltableProjectWarning.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbltableProjectWarning.setForeground(new java.awt.Color(255, 0, 0));
        lbltableProjectWarning.setText("lbltableProjectWarning");
        jPanel2.add(lbltableProjectWarning, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 730, 20));

        tableProjects.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tableProjects.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tableProjects);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 730, 140));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Editar Título", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 18))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbProjectTitle.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        tbProjectTitle.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        tbProjectTitle.setPreferredSize(new java.awt.Dimension(30, 20));
        jPanel4.add(tbProjectTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 730, 30));

        lblTitleWarning.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lblTitleWarning.setForeground(new java.awt.Color(255, 0, 0));
        lblTitleWarning.setText("lblTitleWarning");
        jPanel4.add(lblTitleWarning, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 588, 20));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Editar Descrição", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 18))); // NOI18N
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbProjectDescription.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        tbProjectDescription.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel5.add(tbProjectDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 730, 30));

        lblDescriptionWarning.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lblDescriptionWarning.setForeground(new java.awt.Color(255, 0, 0));
        lblDescriptionWarning.setText("lblDescriptionWarning");
        jPanel5.add(lblDescriptionWarning, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 570, 20));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Editar Data de Início", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 18))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(calStartDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 230, -1));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Editar Data de Fim", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 18))); // NOI18N
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel6.add(calEndDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 220, -1));

        lblEndDateWarning.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lblEndDateWarning.setForeground(new java.awt.Color(255, 0, 0));
        lblEndDateWarning.setText("lblDateWarning");
        lblEndDateWarning.setMinimumSize(new java.awt.Dimension(209, 20));
        jPanel6.add(lblEndDateWarning, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 574, 20));

        btnUpdateProjectData.setBackground(new java.awt.Color(51, 110, 123));
        btnUpdateProjectData.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        btnUpdateProjectData.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdateProjectData.setText("Atualizar Dados de Projeto");
        btnUpdateProjectData.setBorder(null);
        btnUpdateProjectData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateProjectDataActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(349, 349, 349)
                            .addComponent(btnUpdateProjectData, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnUpdateProjectData, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

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
