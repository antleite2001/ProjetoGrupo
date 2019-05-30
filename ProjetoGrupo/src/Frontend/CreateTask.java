//https://www.youtube.com/watch?v=F_6lUrruyXA
package Frontend;

import Backend.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author VarianInstaller
 */
public class CreateTask extends javax.swing.JDialog {

    Sistema s;
    TaskPriority SelectedPriority;
    DefaultTableModel modelTaskLists, modelUsers;

    public CreateTask(java.awt.Frame parent, boolean modal, Sistema s) {
        super(parent, modal);
        initComponents();
        this.s = s;
        Validacoes.SetDialogProperties(this, s,  "Criar Tarefa");
        Validacoes.FormatCalendar(calStartDate);
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
        
        
        //-------TaskList table start
        //set jtable selection to single row
        tableTaskLists.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        //prevent editing of jtable cells
        tableTaskLists.setDefaultEditor(Object.class, null);

        //create model to handle columns and rows
        modelTaskLists = new DefaultTableModel();
        modelTaskLists.addColumn("Id da Lista de Tarefas");
        modelTaskLists.addColumn("Título da Lista de Tarefas");
        modelTaskLists.addColumn("Descrição da Lista de Tarefas");

        //relate model to table
        tableTaskLists.setModel(modelTaskLists);
        //-------

        rbHighPriority.setSelected(false);
        rbMediumPriority.setSelected(false);
        rbLowPriority.setSelected(true);
        SelectedPriority = TaskPriority.LOW;
        EnableBtnCreateTask();

        //Fill table Users
        ArrayList<User> u1 = new ArrayList<>(); //keep a list uf associated users so that it will not be repeated in the users table
        //Scan all associated users to projects
        for (UserProjectsAssociation u : s.getRepositoryUserProjectsAssociation().getUserProjectsAssociations()) {
            //get only the users related to current project owner
            if (u.getProjectOwner() == s.getCurrentUser().getUserId()) {
                //get user data
                User u2 = s.getUsersRepository().getUserById(u.getUserId());
                if (!u1.contains(u2)) {
                    u1.add(u2);
                    //display user data
                    modelUsers.addRow(new Object[]{u2.getUserId(), u2.getUserName(), u2.getEmail()});
                }
            }
        }

        
        
        //Fill table TaslLists
         //Scan all tasllists associated with current user
        for (TaskList tl : s.getRepositoryTaskLists().getListaDeTarefas()) {
            
            //System.out.println("TaskList Created by " + tl.getCreatedBy());
            //get only the users related to current project owner
            if (tl.getOwnerId() == s.getCurrentUser().getUserId()) {
                //get tasklist data
                //User u2 = s.getUsersRepository().getUserById(u.getUserId());
                //if (!u1.contains(u2)) {
                   // u1.add(u2);
                    //display user data
                    modelTaskLists.addRow(new Object[]{tl.getTaskListId(), tl.getTitle(), tl.getDescription()});
                //}
            }
        }
        
        
        //Events----------------------------------------------------------------
        
        //jtable selection change event
        tableUsers.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                EnableBtnCreateTask();
                 
            }
        });
        
        
        
        //jtable selection change event
        tableTaskLists.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                EnableBtnCreateTask();
            }
        });
        
        
        //Task Title events
        tbTaskTitle.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent arg0) {
                EnableBtnCreateTask();
            }

            @Override
            public void insertUpdate(DocumentEvent arg0) {
                EnableBtnCreateTask();
            }

            @Override
            public void removeUpdate(DocumentEvent arg0) {
                EnableBtnCreateTask();
            }
        });

        //Task Description events
        tbTaskDescription.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent arg0) {
                EnableBtnCreateTask();
            }

            @Override
            public void insertUpdate(DocumentEvent arg0) {
                EnableBtnCreateTask();
            }

            @Override
            public void removeUpdate(DocumentEvent arg0) {
                EnableBtnCreateTask();
            }
        });

        //StartDate Events. Date must be >= current date
        calStartDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EnableBtnCreateTask();
            }
        });

    }

    void EnableBtnCreateTask() {
        boolean b = true;
        if (tbTaskTitle.getText().isBlank()) {
            lblTitleWarning.setText("Introduza o título da tarefa");
            b = false;
        } else {
            lblTitleWarning.setText("");
        }

        //Task description cannot be null
        if (tbTaskDescription.getText().isBlank()) {
            lblDescriptionWarning.setText("Introduza uma descrição para a tarefa");
            b = false;
        } else {
            lblDescriptionWarning.setText("");
        }

        //StartDate cannot be in the past
        long d1 = 1 + (calStartDate.getDateInMillis() / (1000 * 60 * 60 * 24));
        //System.out.println(d1);
        long d2 = System.currentTimeMillis() / (1000 * 60 * 60 * 24);
        //System.out.println(d2);
        if (d1 < d2) {
            lblStartDateWarning.setText("Data de início da tarefa deve ser igual ou superior à data atual");
            b = false;
        } else {
            lblStartDateWarning.setText(" ");
        }
        
         if (tableUsers.getSelectedRow() == -1) {
            lbltableUsertWarning.setText("Selecione um utilizador para associá-lo a esta tarefa");
            b = false;
        } else {
            lbltableUsertWarning.setText("");
        }
         
         
         if (tableTaskLists.getSelectedRow() == -1) {
            lbltableTaslListWarning.setText("Selecione uma  Lista de tarefas para associá-la a esta tarefa");
            b = false;
        } else {
            lbltableTaslListWarning.setText("");
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
        btnCreateTask = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        rbLowPriority = new javax.swing.JRadioButton();
        rbMediumPriority = new javax.swing.JRadioButton();
        rbHighPriority = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableUsers = new javax.swing.JTable();
        lbltableUsertWarning = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        calStartDate = new org.jdesktop.swingx.JXDatePicker();
        lblStartDateWarning = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        tbTaskDescription = new javax.swing.JTextField();
        lblDescriptionWarning = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        tbTaskTitle = new javax.swing.JTextField();
        lblTitleWarning = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableTaskLists = new javax.swing.JTable();
        lbltableTaslListWarning = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Criar Tarefa");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCreateTask.setBackground(new java.awt.Color(51, 110, 123));
        btnCreateTask.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        btnCreateTask.setForeground(new java.awt.Color(255, 255, 255));
        btnCreateTask.setText("CriarTarefa");
        btnCreateTask.setBorder(null);
        btnCreateTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateTaskActionPerformed(evt);
            }
        });
        getContentPane().add(btnCreateTask, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 710, 200, 46));

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
        getContentPane().add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 710, 200, 46));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Atribuir Tarefa ao Utilizador", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 18))); // NOI18N

        btngrpPriority.add(rbLowPriority);
        rbLowPriority.setText("Baixa");

        btngrpPriority.add(rbMediumPriority);
        rbMediumPriority.setText("Média");

        btngrpPriority.add(rbHighPriority);
        rbHighPriority.setText("Alta");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbLowPriority)
                .addGap(34, 34, 34)
                .addComponent(rbMediumPriority)
                .addGap(43, 43, 43)
                .addComponent(rbHighPriority)
                .addContainerGap(234, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbLowPriority)
                    .addComponent(rbMediumPriority)
                    .addComponent(rbHighPriority))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 480, 100));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Atribuir Tarefa ao Utilizador", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 18))); // NOI18N

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
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 929, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lbltableUsertWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 919, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbltableUsertWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 248, 961, -1));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data de Início", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 18))); // NOI18N

        lblStartDateWarning.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lblStartDateWarning.setForeground(new java.awt.Color(255, 0, 0));
        lblStartDateWarning.setText("lblStartDateWarning");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(calStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lblStartDateWarning, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(calStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblStartDateWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 140, 480, 100));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Descrição", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 18))); // NOI18N

        tbTaskDescription.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblDescriptionWarning.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lblDescriptionWarning.setForeground(new java.awt.Color(255, 0, 0));
        lblDescriptionWarning.setText("lblDescriptionWarning");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tbTaskDescription, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblDescriptionWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tbTaskDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDescriptionWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(496, 10, -1, 110));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Atribuir Tarefa ao Utilizador", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 18))); // NOI18N

        tbTaskTitle.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tbTaskTitle.setPreferredSize(new java.awt.Dimension(30, 20));

        lblTitleWarning.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lblTitleWarning.setForeground(new java.awt.Color(255, 0, 0));
        lblTitleWarning.setText("lblTitleWarning");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tbTaskTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTitleWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tbTaskTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTitleWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 110));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Atribuir Tarefa à Lista de Tarefas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 18))); // NOI18N

        tableTaskLists.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tableTaskLists.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(tableTaskLists);

        lbltableTaslListWarning.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbltableTaslListWarning.setForeground(new java.awt.Color(255, 0, 0));
        lbltableTaslListWarning.setText("lbltableTaslListWarning");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 929, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lbltableTaslListWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 712, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbltableTaslListWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, 961, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
/*public void addTask(int CreatedBy, String Title, 
    String Description, TaskPriority pt,TaskStatus et,Date DataDeInicio*/
    private void btnCreateTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateTaskActionPerformed
        s.getRepositoryTasks().addTask(s.getCurrentUser().getUserId(), tbTaskTitle.getText(),
                tbTaskDescription.getText(), SelectedPriority, TaskStatus.NOTSTARTED, 
                calStartDate.getDate(),
                (int)tableTaskLists.getModel().getValueAt(tableTaskLists.getSelectedRow(), 0), (int)tableUsers.getModel().getValueAt(tableUsers.getSelectedRow(),0));
        JOptionPane.showMessageDialog(null, "A tarefa " + tbTaskTitle.getText() + " foi criada com sucesso!");
        this.dispose();

    }//GEN-LAST:event_btnCreateTaskActionPerformed

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
    private org.jdesktop.swingx.JXDatePicker calStartDate;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblDescriptionWarning;
    private javax.swing.JLabel lblStartDateWarning;
    private javax.swing.JLabel lblTitleWarning;
    private javax.swing.JLabel lbltableTaslListWarning;
    private javax.swing.JLabel lbltableUsertWarning;
    private javax.swing.JRadioButton rbHighPriority;
    private javax.swing.JRadioButton rbLowPriority;
    private javax.swing.JRadioButton rbMediumPriority;
    private javax.swing.JTable tableTaskLists;
    private javax.swing.JTable tableUsers;
    private javax.swing.JTextField tbTaskDescription;
    private javax.swing.JTextField tbTaskTitle;
    // End of variables declaration//GEN-END:variables
}
