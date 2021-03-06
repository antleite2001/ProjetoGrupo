package Frontend;

import Backend.*;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class ChangeTaskStatus extends javax.swing.JDialog {

    Sistema s;

    TaskStatus SelectedStatus;
    DefaultTableModel modelTask;
    Integer SelectedTaskId = -1;

    public ChangeTaskStatus(java.awt.Frame parent, boolean modal, Sistema s) {
        super(parent, modal);
        initComponents();
        this.s = s;

        Validacoes.SetDialogProperties(this, s, "Atualizar Estado da Tarefa");

        rbNotStarted.setSelected(false);
        rbInProgress.setSelected(false);
        rbInProgress.setSelected(false);
        SelectedStatus = TaskStatus.NOTSTARTED;

        //-------Tasks table----------------------------------------------------
        //set jtable selection to single row
        tableTasks.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        //prevent editing of jtable cells
        tableTasks.setDefaultEditor(Object.class, null);

        //create model to handle columns and rows
        modelTask = new DefaultTableModel();
        modelTask.addColumn("Id da Tarefa");
        modelTask.addColumn("Título da Tarefa");
        modelTask.addColumn("Descrição da Tarefa");
        modelTask.addColumn("Data de Início");
        modelTask.addColumn("Data de Fim");
        modelTask.addColumn("Estado");
        //relate model to table
        tableTasks.setModel(modelTask);
        //----------------------------------------------------------------------

        //fill tasks table
        ArrayList<Task> tasks = new ArrayList<>(); //remove repeated
        for (Task t : s.getRepositoryTasks().getTasks()) {

            if (!tasks.contains(t) && (t.getAssignedTo() == s.getCurrentUser().getUserId() || t.getCreatedBy() == s.getCurrentUser().getUserId())) {
                tasks.add(t);
                if (t.getEndDate() == null) {
                    modelTask.addRow(new Object[]{
                        t.getTaskId(),
                        t.getTitle(),
                        t.getDescription(),
                        Validacoes.FormatDate(t.getStartDate()),
                        "",
                        t.getTaskStatus()
                    });
                } else {
                    modelTask.addRow(new Object[]{
                        t.getTaskId(),
                        t.getTitle(),
                        t.getDescription(),
                        Validacoes.FormatDate(t.getStartDate()),
                        Validacoes.FormatDate(t.getEndDate()),
                        t.getTaskStatus()
                    });
                }
            }

        }
        EnableBtnUpdateTask();
        //Events----------------------------------------------------------------
        //jtable selection change event

        //jtable selection change event
        tableTasks.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                SelectedTaskId = (int) tableTasks.getValueAt(tableTasks.getSelectedRow(), 0);
                UpdateRadioButtonStatus();
                EnableBtnUpdateTask();

            }
        });

    }

    void UpdateRadioButtonStatus() {
        if (SelectedTaskId >= 0) {
            Task t = s.getRepositoryTasks().getTaskByTaskId(SelectedTaskId);

            //Select Status
            switch (t.getEnumStatus()) {
                case NOTSTARTED: {
                    rbNotStarted.setSelected(true);
                    SelectedStatus = TaskStatus.NOTSTARTED;
                    EnableRadioButtons(true);
                    break;
                }
                case INPROGRESS: {
                    rbInProgress.setSelected(true);
                    SelectedStatus = TaskStatus.INPROGRESS;
                    EnableRadioButtons(true);
                    break;
                }
                case FINISHED: {
                    rbFinished.setSelected(true);
                    SelectedStatus = TaskStatus.FINISHED;
                    EnableRadioButtons(false);
                    break;
                }
                default: {
                    System.out.println("ERROR: Task Status not defined");
                    break;
                }
            }
        }
    }

    void EnableBtnUpdateTask() {
        boolean b = true;
        if (tableTasks.getSelectedRow() == -1) {
            lbltableTaskWarning.setText("Selecione uma tarefa");
            b = false;
        } else {
            lbltableTaskWarning.setText("");
        }

        btnCreateTask.setEnabled(b);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btngrpPriority = new javax.swing.ButtonGroup();
        btngrpStatus = new javax.swing.ButtonGroup();
        btnCreateTask = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableTasks = new javax.swing.JTable();
        lbltableTaskWarning = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        rbNotStarted = new javax.swing.JRadioButton();
        rbInProgress = new javax.swing.JRadioButton();
        rbFinished = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Criar Tarefa");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCreateTask.setBackground(new java.awt.Color(51, 110, 123));
        btnCreateTask.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        btnCreateTask.setForeground(new java.awt.Color(255, 255, 255));
        btnCreateTask.setText("Atualizar Estado da Tarefa");
        btnCreateTask.setBorder(null);
        btnCreateTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateTaskActionPerformed(evt);
            }
        });
        getContentPane().add(btnCreateTask, new org.netbeans.lib.awtextra.AbsoluteConstraints(513, 645, 260, 46));

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
        getContentPane().add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(791, 645, 200, 46));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Selecionar Tarefa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 18))); // NOI18N
        jPanel6.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        tableTasks.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tableTasks.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(tableTasks);

        lbltableTaskWarning.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbltableTaskWarning.setForeground(new java.awt.Color(255, 0, 0));
        lbltableTaskWarning.setText("lbltableTaskWarning");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 949, Short.MAX_VALUE)
                    .addComponent(lbltableTaskWarning, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(lbltableTaskWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 981, -1));

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Estado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 18))); // NOI18N
        jPanel8.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        btngrpStatus.add(rbNotStarted);
        rbNotStarted.setText("Não Iniciada");
        rbNotStarted.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbNotStartedItemStateChanged(evt);
            }
        });

        btngrpStatus.add(rbInProgress);
        rbInProgress.setText("Em Progresso");
        rbInProgress.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbInProgressItemStateChanged(evt);
            }
        });

        btngrpStatus.add(rbFinished);
        rbFinished.setText("Terminada");
        rbFinished.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbFinishedItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbNotStarted)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbInProgress)
                .addGap(4, 4, 4)
                .addComponent(rbFinished)
                .addContainerGap(701, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbNotStarted)
                    .addComponent(rbInProgress)
                    .addComponent(rbFinished))
                .addContainerGap())
        );

        getContentPane().add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 557, 980, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
/*public void addTask(int CreatedBy, String Title, 
    String Description, TaskPriority pt,TaskStatus et,Date DataDeInicio*/
    private void btnCreateTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateTaskActionPerformed

        if (SelectedStatus == TaskStatus.FINISHED) {
            Date d = new Date(System.currentTimeMillis());
            s.getRepositoryTasks().updateTaskStatusByIdandDate(SelectedTaskId, SelectedStatus, d);
            tableTasks.setValueAt(Validacoes.FormatDate(d), tableTasks.getSelectedRow(), 4);
            EnableRadioButtons(false);

        } else {
            s.getRepositoryTasks().updateTaskStatusById(SelectedTaskId, SelectedStatus);
            EnableRadioButtons(true);

        }
        modelTask.setValueAt(SelectedStatus, tableTasks.getSelectedRow(), 5);
        JOptionPane.showMessageDialog(null, "O estado da Tarefa foi alterado com sucesso!");
        this.dispose ();
    }//GEN-LAST:event_btnCreateTaskActionPerformed

    void EnableRadioButtons(boolean e) {
        rbNotStarted.setEnabled(e);
        rbInProgress.setEnabled(e);
        rbFinished.setEnabled(e);
    }

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void rbNotStartedItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbNotStartedItemStateChanged
        if (rbNotStarted.isSelected()) {
            SelectedStatus = TaskStatus.NOTSTARTED;
            System.out.println(SelectedStatus);
        }

    }//GEN-LAST:event_rbNotStartedItemStateChanged

    private void rbInProgressItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbInProgressItemStateChanged
        if (rbInProgress.isSelected()) {
            SelectedStatus = TaskStatus.INPROGRESS;
            System.out.println(SelectedStatus);
        }
    }//GEN-LAST:event_rbInProgressItemStateChanged

    private void rbFinishedItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbFinishedItemStateChanged
        if (rbFinished.isSelected()) {
            SelectedStatus = TaskStatus.FINISHED;
            System.out.println(SelectedStatus);
        }
    }//GEN-LAST:event_rbFinishedItemStateChanged

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
//            java.util.logging.Logger.getLogger(CriarTarefa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(CriarTarefa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(CriarTarefa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(CriarTarefa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                CriarTarefa dialog = new CriarTarefa(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnCreateTask;
    private javax.swing.ButtonGroup btngrpPriority;
    private javax.swing.ButtonGroup btngrpStatus;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbltableTaskWarning;
    private javax.swing.JRadioButton rbFinished;
    private javax.swing.JRadioButton rbInProgress;
    private javax.swing.JRadioButton rbNotStarted;
    private javax.swing.JTable tableTasks;
    // End of variables declaration//GEN-END:variables
}
