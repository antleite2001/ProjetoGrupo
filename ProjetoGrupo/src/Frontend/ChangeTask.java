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
public class ChangeTask extends javax.swing.JDialog {

    Sistema s;
    TaskPriority SelectedPriority;
    TaskStatus SelectedStatus;
    DefaultTableModel modelTask, modelUsers;

    public ChangeTask(java.awt.Frame parent, boolean modal, Sistema s) {
        super(parent, modal);
        initComponents();
        this.s = s;
        lblDescriptionWarning.setText("");
        lblEndDateWarning.setText("");
        lblStartDateWarning.setText("");
        lblTitleWarning.setText("");
        lbltableTaskWarning.setText("");
        lbltableUsersWarning.setText("");
        
        Validacoes.SetDialogProperties(this, s, 970, 802, "Atualizar Tarefa");

        rbHighPriority.setSelected(false);
        rbMediumPriority.setSelected(false);
        rbLowPriority.setSelected(true);
        SelectedPriority = TaskPriority.LOW;

        rbNotStarted.setSelected(true);
        rbInProgress.setSelected(false);
        rbInProgress.setSelected(false);
        SelectedStatus = TaskStatus.NOTSTARTED;

        //Users table-----------------------------------------------------------
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

        //relate model to table
        tableTasks.setModel(modelTask);
        //----------------------------------------------------------------------

        //EnableBtnUpdateTask();
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

        //Fill table Tasks
        for (Task task : s.getRepositoryTasks().getTasks()) {
            //get only the users related to current project owner
            if (task.getCreatedBy() == s.getCurrentUser().getUserId()) {
                modelTask.addRow(new Object[]{
                    task.getTaskId(),
                    task.getTitle(),
                    task.getDescription()});
            }
        }

        //Events----------------------------------------------------------------
        //jtable selection change event
        tableUsers.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                EnableBtnUpdateTask();

            }
        });

        //jtable selection change event
        tableTasks.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                UpdateControls();
                EnableBtnUpdateTask();
            }
        });

        //Task Title events
        tbTaskTitle.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent arg0) {
                EnableBtnUpdateTask();
            }

            @Override
            public void insertUpdate(DocumentEvent arg0) {
                EnableBtnUpdateTask();
            }

            @Override
            public void removeUpdate(DocumentEvent arg0) {
                EnableBtnUpdateTask();
            }
        });

        //Task Description events
        tbTaskDescription.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent arg0) {
                EnableBtnUpdateTask();
            }

            @Override
            public void insertUpdate(DocumentEvent arg0) {
                EnableBtnUpdateTask();
            }

            @Override
            public void removeUpdate(DocumentEvent arg0) {
                EnableBtnUpdateTask();
            }
        });

        //StartDate Events. Date must be >= current date
        calStartDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EnableBtnUpdateTask();
            }
        });

        //EndDate Events.  
        calEndDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EnableBtnUpdateTask();
            }
        });
    }

    void UpdateControls() {
        int TaskId = (int) tableTasks.getValueAt(tableTasks.getSelectedRow(), 0);
        Task t = s.getRepositoryTasks().getTaskByTaskId(TaskId);

        tbTaskTitle.setText((String) tableTasks.getValueAt(tableTasks.getSelectedRow(), 1));
        tbTaskDescription.setText((String) tableTasks.getValueAt(tableTasks.getSelectedRow(), 2));

        //Select AssignedTo In tableUsers
        for (int i = 0; i < tableUsers.getRowCount(); i++) {
            if ((int) tableUsers.getValueAt(i, 0) == t.getAssignedTo()) {
                tableUsers.setRowSelectionInterval(i, i);
                break;
            }
        }

        //Select Prority
        switch (t.getEnumPriority()) {
            case LOW: {
                rbLowPriority.setSelected(true);
                break;
            }
            case MEDIUM: {
                rbMediumPriority.setSelected(true);
                break;
            }
            case HIGH: {
                rbHighPriority.setSelected(true);
                break;
            }
            default: {

                break;
            }
        }

        //Select Status
        switch (t.getEnumStatus()) {
            case NOTSTARTED: {
                rbNotStarted.setSelected(true);
                break;
            }
            case INPROGRESS: {
                rbInProgress.setSelected(true);
                break;
            }
            case FINISHED: {
                rbFinished.setSelected(true);
                break;
            }
            default: {

                break;
            }
        }
    }

    void EnableBtnUpdateTask() {
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
            lblStartDateWarning.setText("");
        }

        
        
        if (tableUsers.getSelectedRow() == -1) {
            lbltableUsersWarning.setText("Selecione um utilizador para associá-lo a esta tarefa");
            b = false;
        } else {
            lbltableUsersWarning.setText("");
        }

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
        jPanel1 = new javax.swing.JPanel();
        rbLowPriority = new javax.swing.JRadioButton();
        rbMediumPriority = new javax.swing.JRadioButton();
        rbHighPriority = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableUsers = new javax.swing.JTable();
        lbltableUsersWarning = new javax.swing.JLabel();
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
        tableTasks = new javax.swing.JTable();
        lbltableTaskWarning = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        calEndDate = new org.jdesktop.swingx.JXDatePicker();
        lblEndDateWarning = new javax.swing.JLabel();
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
        btnCreateTask.setText("Atualizar Tarefa");
        btnCreateTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateTaskActionPerformed(evt);
            }
        });
        getContentPane().add(btnCreateTask, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 720, 200, 46));

        btnCancel.setBackground(new java.awt.Color(51, 110, 123));
        btnCancel.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(255, 255, 255));
        btnCancel.setText("Cancelar");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 720, 200, 46));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Prioridade"));
        jPanel1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbMediumPriority)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbHighPriority)
                .addContainerGap(197, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbLowPriority)
                    .addComponent(rbMediumPriority)
                    .addComponent(rbHighPriority))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 370, -1));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Atribuir Tarefa ao Utilizador"));
        jPanel3.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        tableUsers.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tableUsers.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(tableUsers);

        lbltableUsersWarning.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbltableUsersWarning.setForeground(new java.awt.Color(255, 0, 0));
        lbltableUsersWarning.setText("lbltableUsersWarning");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 909, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lbltableUsersWarning)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbltableUsersWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, -1, -1));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Data de Início"));
        jPanel2.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

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
                        .addComponent(calStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 209, Short.MAX_VALUE))
                    .addComponent(lblStartDateWarning, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(calStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblStartDateWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 370, -1));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Descrição"));
        jPanel4.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        tbTaskDescription.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
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
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tbTaskDescription)
                    .addComponent(lblDescriptionWarning, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tbTaskDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDescriptionWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 200, 370, 100));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Título"));
        jPanel5.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        tbTaskTitle.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
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
                    .addComponent(lblTitleWarning, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                    .addComponent(tbTaskTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tbTaskTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTitleWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 370, 100));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Selecionar Tarefa"));
        jPanel6.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        tableTasks.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tableTasks.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(tableTasks);

        lbltableTaskWarning.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbltableTaskWarning.setForeground(new java.awt.Color(255, 0, 0));
        lbltableTaskWarning.setText("lbltableTasksWarning");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 909, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lbltableTaskWarning)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbltableTaskWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Data de Fim"));
        jPanel7.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        lblEndDateWarning.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lblEndDateWarning.setForeground(new java.awt.Color(255, 0, 0));
        lblEndDateWarning.setText("lblEndDateWarning");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(calEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEndDateWarning))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(calEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(lblEndDateWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 390, 370, -1));

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Estado"));
        jPanel8.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        btngrpStatus.add(rbNotStarted);
        rbNotStarted.setText("Não Iniciada");

        btngrpStatus.add(rbInProgress);
        rbInProgress.setText("Em Progresso");

        btngrpStatus.add(rbFinished);
        rbFinished.setText("Terminada");

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
                .addContainerGap(91, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbNotStarted)
                    .addComponent(rbInProgress)
                    .addComponent(rbFinished))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 320, 370, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
/*public void addTask(int CreatedBy, String Title, 
    String Description, TaskPriority pt,TaskStatus et,Date DataDeInicio*/
    private void btnCreateTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateTaskActionPerformed
        s.getRepositoryTasks().addTask(s.getCurrentUser().getUserId(), tbTaskTitle.getText(),
                tbTaskDescription.getText(), SelectedPriority, TaskStatus.NOTSTARTED,
                calStartDate.getDate(),
                (int) tableTasks.getModel().getValueAt(tableTasks.getSelectedRow(), 0), (int) tableUsers.getModel().getValueAt(tableUsers.getSelectedRow(), 0));
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
    private javax.swing.ButtonGroup btngrpStatus;
    private org.jdesktop.swingx.JXDatePicker calEndDate;
    private org.jdesktop.swingx.JXDatePicker calStartDate;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblDescriptionWarning;
    private javax.swing.JLabel lblEndDateWarning;
    private javax.swing.JLabel lblStartDateWarning;
    private javax.swing.JLabel lblTitleWarning;
    private javax.swing.JLabel lbltableTaskWarning;
    private javax.swing.JLabel lbltableUsersWarning;
    private javax.swing.JRadioButton rbFinished;
    private javax.swing.JRadioButton rbHighPriority;
    private javax.swing.JRadioButton rbInProgress;
    private javax.swing.JRadioButton rbLowPriority;
    private javax.swing.JRadioButton rbMediumPriority;
    private javax.swing.JRadioButton rbNotStarted;
    private javax.swing.JTable tableTasks;
    private javax.swing.JTable tableUsers;
    private javax.swing.JTextField tbTaskDescription;
    private javax.swing.JTextField tbTaskTitle;
    // End of variables declaration//GEN-END:variables
}
