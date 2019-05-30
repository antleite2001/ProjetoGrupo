/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

 
public class DisplayTaskList extends javax.swing.JDialog {

    Sistema s;

    DefaultTableModel modelTaskList, modelProjectAssigned, modelTasksAssigned;

    /**
     * Creates new form CriarListaDeTarefas
     */
    public DisplayTaskList(java.awt.Frame parent, boolean modal, Sistema s) {
        super(parent, modal);
        initComponents();
        this.s = s;
        Validacoes.SetDialogProperties(this, s, "Mostrar Lista de Tarefas");

        //set jtable selection to single row
        tableTaskList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        //prevent editing of jtable cells
        tableTaskList.setDefaultEditor(Object.class, null);

        //create modelTaskList to handle columns and rows
        modelTaskList = new DefaultTableModel();
        modelTaskList.addColumn("Id da Lista de Tarefas");
        modelTaskList.addColumn("Título da Lista de Tarefas");
        modelTaskList.addColumn("Descrição da Lista de Tarefas");
        modelTaskList.addColumn("ID do Projeto");
        tableTaskList.setModel(modelTaskList);

        for (TaskList tl : s.getRepositoryTaskLists().getListaDeTarefas()) {
            if (tl.getOwnerId() == s.getCurrentUser().getUserId()) {
                modelTaskList.addRow(new Object[]{tl.getTaskListId(), tl.getTitle(), tl.getDescription(), tl.getProjectId()});
            }
        }

        //set jtable selection to single row
        tableProjectsAssigned.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        //prevent editing of jtable cells
        tableProjectsAssigned.setDefaultEditor(Object.class, null);

        //create modelTaskList to handle columns and rows
        modelProjectAssigned = new DefaultTableModel();
        modelProjectAssigned.addColumn("Id do Projeto");
        modelProjectAssigned.addColumn("Título do Projeto");
        modelProjectAssigned.addColumn("Descrição do Projeto");
        modelProjectAssigned.addColumn("Data de Início");
        modelProjectAssigned.addColumn("Data de Fim");
        //modelProjectAssigned.addColumn("Data de Início NotFormatted"); 
        //modelProjectAssigned.addColumn("Data de Fim Not Formatted"); 
        tableProjectsAssigned.setModel(modelProjectAssigned);

        //set jtable selection to single row
        tableTasksAssigned.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        //prevent editing of jtable cells
        tableTasksAssigned.setDefaultEditor(Object.class, null);

        //create modelTaskList to handle columns and rows
        modelTasksAssigned = new DefaultTableModel();
        modelTasksAssigned.addColumn("Id da Tarefa");
        modelTasksAssigned.addColumn("Título da Tarefa");
        modelTasksAssigned.addColumn("Descrição da Tarefa");
        modelTasksAssigned.addColumn("Prioridade");
        modelTasksAssigned.addColumn("Estado");
        modelTasksAssigned.addColumn("Data de Início");
        modelTasksAssigned.addColumn("Data de Fim");
        tableTasksAssigned.setModel(modelTasksAssigned);

        /* */
//Listeners for events
        //jtable selection change event
        tableTaskList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent event) {
                modelProjectAssigned.setRowCount(0);
                Project ProjectsById = s.getRepositoryProjects().getProjectsById((Integer) tableTaskList.getValueAt(tableTaskList.getSelectedRow(), 3));

                //Add rows to table with projects info
                modelProjectAssigned.addRow(new Object[]{
                    ProjectsById.getProjectId(),
                    ProjectsById.getProjectTitle(),
                    ProjectsById.getProjectDescription(),
                    Validacoes.FormatDate(ProjectsById.getStartDate()),
                    Validacoes.FormatDate(ProjectsById.getEndDate())
                });

                
                modelTasksAssigned.setRowCount(0);
                int tasklistid = (Integer) tableTaskList.getValueAt(tableTaskList.getSelectedRow(), 0);
                for (Task t : s.getRepositoryTasks().getTasks()) {
                    if (tasklistid == t.getTaskListId()) {

                        if (t.getEndDate() != null) {
                            modelTasksAssigned.addRow(new Object[]{
                                t.getTaskId(),
                                t.getTitle(),
                                t.getDescription(),
                                t.getTaskPriority(),
                                t.getTaskStatus(),
                                Validacoes.FormatDate(t.getStartDate()),
                                Validacoes.FormatDate(t.getEndDate())

                            });
                        } else {
                            modelTasksAssigned.addRow(new Object[]{
                                t.getTaskId(),
                                t.getTitle(),
                                t.getDescription(),
                                t.getTaskPriority(),
                                t.getTaskStatus(),
                                Validacoes.FormatDate(t.getStartDate()),
                                ""
                            });
                        }
                    }
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCancel = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableTasksAssigned = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableTaskList = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableProjectsAssigned = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Criar lista de tarefas");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCancel.setBackground(new java.awt.Color(51, 110, 123));
        btnCancel.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(255, 255, 255));
        btnCancel.setText("Sair");
        btnCancel.setBorder(null);
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 510, 200, 46));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tarefas Associadas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 18))); // NOI18N

        tableTasksAssigned.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tableTasksAssigned.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(tableTasksAssigned);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 998, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 17, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 1030, 130));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista de Tarefas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 18))); // NOI18N

        tableTaskList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tableTaskList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tableTaskList);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 998, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1030, 180));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Projeto Associado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 18))); // NOI18N

        tableProjectsAssigned.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tableProjectsAssigned.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(tableProjectsAssigned);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 998, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 18, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 1030, 130));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

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
//            java.util.logging.Logger.getLogger(CriarListaDeTarefas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(CriarListaDeTarefas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(CriarListaDeTarefas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(CriarListaDeTarefas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                CriarListaDeTarefas dialog = new CriarListaDeTarefas(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnCancel;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tableProjectsAssigned;
    private javax.swing.JTable tableTaskList;
    private javax.swing.JTable tableTasksAssigned;
    // End of variables declaration//GEN-END:variables
}
