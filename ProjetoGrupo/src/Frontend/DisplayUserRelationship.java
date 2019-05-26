/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.Project;
import Backend.Sistema;
import Backend.Task;
import Backend.TaskList;
import Backend.UserProjectsAssociation;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class DisplayUserRelationship extends javax.swing.JDialog {

    Sistema s;
    DefaultTableModel modelProjectsOwnedByUser, modelUserIsAssociatedToProjects, modelTasksAssignedToUser;

    public DisplayUserRelationship(java.awt.Frame parent, boolean modal, Sistema s) {
        super(parent, modal);
        initComponents();
        this.s = s;
        this.setTitle("Mostrar Relacionamentos - " + s.getCurrentUser().getUserName() + " (" + s.getCurrentUser().getEmail() + ")");
        panelProjectsOwnedByUser.setBorder(javax.swing.BorderFactory.createTitledBorder(s.getCurrentUser().getUserName() + " é Proprietário dos Projetos"));

        panelTasksAssignedTo.setBorder(javax.swing.BorderFactory.createTitledBorder("Tarefas Atribuídas a " + s.getCurrentUser().getUserName()));

        panelUserIsAssociatedToProjects.setBorder(javax.swing.BorderFactory.createTitledBorder(s.getCurrentUser().getUserName() + " está Associado aos Projetos"));

        //set jtable selection to single row------------------------------------------------------------
        tableProjectsOwnedByUser.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        //prevent editing of jtable cells
        tableProjectsOwnedByUser.setDefaultEditor(Object.class, null);

        //create modelProjectsOwnedByUser to handle columns and rows
        modelProjectsOwnedByUser = new DefaultTableModel();
        modelProjectsOwnedByUser.addColumn("Id do Projeto");
        modelProjectsOwnedByUser.addColumn("Título do Projeto");
        modelProjectsOwnedByUser.addColumn("Descrição do Projeto");
        tableProjectsOwnedByUser.setModel(modelProjectsOwnedByUser);

        ArrayList<Project> ProjectsByOwner = new ArrayList<>();

        //Select projects owned by current user (owner of projects)
        //Only owners can create Task Lista and Tasks. See Specification
        ProjectsByOwner = s.getRepositoryProjects().getProjectsByOwner(s.getCurrentUser().getUserId());

        //Add rows to table with projects info
        for (Project p : ProjectsByOwner) {
            modelProjectsOwnedByUser.addRow(new Object[]{
                p.getProjectId(),
                p.getProjectTitle(),
                p.getProjectDescription()
            });

        }

        //set jtable selection to single row------------------------------------------------------------
        tableUserIsAssociatedToProjects.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        //prevent editing of jtable cells
        tableUserIsAssociatedToProjects.setDefaultEditor(Object.class, null);

        //create modelProjectsOwnedByUser to handle columns and rows
        modelUserIsAssociatedToProjects = new DefaultTableModel();
        modelUserIsAssociatedToProjects.addColumn("Id do Projeto");
        modelUserIsAssociatedToProjects.addColumn("Título do Projeto");
        modelUserIsAssociatedToProjects.addColumn("Descrição do Projeto");
        tableUserIsAssociatedToProjects.setModel(modelUserIsAssociatedToProjects);

        ArrayList<UserProjectsAssociation> ProjectsAssociatedToUser = new ArrayList<>();

        //Select projects owned by current user (owner of projects)
        //Only owners can create Task Lista and Tasks. See Specification
        ProjectsAssociatedToUser = s.getRepositoryUserProjectsAssociation().getUserProjectsAssociation(s.getCurrentUser().getUserId());

        //Add rows to table with projects info
        for (UserProjectsAssociation p : ProjectsAssociatedToUser) {
            Project project = s.getRepositoryProjects().getProjectsById(p.getProjectId());
            modelUserIsAssociatedToProjects.addRow(new Object[]{
                project.getProjectId(),
                project.getProjectTitle(),
                project.getProjectDescription()
            });

        }

        //set jtable selection to single row------------------------------------------------------------
        tableTasksAssignedTo.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        //Auto resize all columns
        tableTasksAssignedTo.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        //prevent editing of jtable cells
        tableTasksAssignedTo.setDefaultEditor(Object.class, null);

        //create modelProjectsOwnedByUser to handle columns and rows
        modelTasksAssignedToUser = new DefaultTableModel();
        modelTasksAssignedToUser.addColumn("Id da Tarefa");//0
        modelTasksAssignedToUser.addColumn("Título da Tarefa");//1
        modelTasksAssignedToUser.addColumn("Descrição da Tarefa");//2
        modelTasksAssignedToUser.addColumn("Prioridade");//3
        modelTasksAssignedToUser.addColumn("Status");//4
        modelTasksAssignedToUser.addColumn("Criada Por");//5
        modelTasksAssignedToUser.addColumn("Data de Início");//6
        modelTasksAssignedToUser.addColumn("Data de Fim");//7
        modelTasksAssignedToUser.addColumn("Id Lista de Tarefas");//8
        tableTasksAssignedTo.setModel(modelTasksAssignedToUser);

        ArrayList<Task> taskAssignedToUser = new ArrayList<>();

        //Select projects owned by current user (owner of projects)
        //Only owners can create Task Lista and Tasks. See Specification
        taskAssignedToUser = s.getRepositoryTasks().getTasksAssignedToUser(s.getCurrentUser().getUserId());

        //Add rows to table with projects info
        for (Task t : taskAssignedToUser) {
            //
            TaskList tl = s.getRepositoryTaskLists().getTaskListByTaskId(t.getTaskListId());
            

            if (t.getEndDate() == null) {
                modelTasksAssignedToUser.addRow(new Object[]{
                    t.getTaskId(),
                    t.getTitle(),
                    t.getDescription(),
                    t.getTaskPriority(),
                    t.getTaskStatus(),
                    t.getCreatedBy(),
                    Validacoes.FormatDate(t.getStartDate()),
                    "",
                    tl.getTaskListId()

                });
            } else {
                modelTasksAssignedToUser.addRow(new Object[]{
                    t.getTaskId(),
                    t.getTitle(),
                    t.getDescription(),
                    t.getTaskPriority(),
                    t.getTaskStatus(),
                    t.getCreatedBy(),
                    Validacoes.FormatDate(t.getStartDate()),
                    Validacoes.FormatDate(t.getEndDate()),
                    tl.getTaskListId()
                });

            }

        }
        
        //Events
        tableTasksAssignedTo.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                SelectProject();
                 
            }
        });


    }

     
    void SelectProject()
    {
        int TaskListId = (int)tableTasksAssignedTo.getValueAt(tableTasksAssignedTo.getSelectedRow() ,8);
        int SelectedProjectId=0;
        for(TaskList tl: s.getRepositoryTaskLists().getListaDeTarefas())
        {
            if(tl.getProjectId()==TaskListId)
            {
                SelectedProjectId = tl.getProjectId();
                break;
            }
        }
        
        Project project = s.getRepositoryProjects().getProjectsById(SelectedProjectId);
            //Find Project associated to TaskList

            for (int i = 0; i < tableUserIsAssociatedToProjects.getRowCount(); i++) {
                if ((int) tableUserIsAssociatedToProjects.getValueAt(i, 0) == project.getProjectId()) {
                    tableUserIsAssociatedToProjects.setRowSelectionInterval(i, i);
                    break;
                }
            }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelProjectsOwnedByUser = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableProjectsOwnedByUser = new javax.swing.JTable();
        panelUserIsAssociatedToProjects = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableUserIsAssociatedToProjects = new javax.swing.JTable();
        panelTasksAssignedTo = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tableTasksAssignedTo = new javax.swing.JTable();
        btnStatistics = new javax.swing.JButton();
        btnStatistics1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mostrar relacionamentos");

        panelProjectsOwnedByUser.setBorder(javax.swing.BorderFactory.createTitledBorder("É Proprietário dos Projetos"));

        tableProjectsOwnedByUser.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tableProjectsOwnedByUser.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tableProjectsOwnedByUser);

        javax.swing.GroupLayout panelProjectsOwnedByUserLayout = new javax.swing.GroupLayout(panelProjectsOwnedByUser);
        panelProjectsOwnedByUser.setLayout(panelProjectsOwnedByUserLayout);
        panelProjectsOwnedByUserLayout.setHorizontalGroup(
            panelProjectsOwnedByUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProjectsOwnedByUserLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelProjectsOwnedByUserLayout.setVerticalGroup(
            panelProjectsOwnedByUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProjectsOwnedByUserLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelUserIsAssociatedToProjects.setBorder(javax.swing.BorderFactory.createTitledBorder("Está Associado aos Projetos"));

        tableUserIsAssociatedToProjects.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tableUserIsAssociatedToProjects.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(tableUserIsAssociatedToProjects);

        javax.swing.GroupLayout panelUserIsAssociatedToProjectsLayout = new javax.swing.GroupLayout(panelUserIsAssociatedToProjects);
        panelUserIsAssociatedToProjects.setLayout(panelUserIsAssociatedToProjectsLayout);
        panelUserIsAssociatedToProjectsLayout.setHorizontalGroup(
            panelUserIsAssociatedToProjectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUserIsAssociatedToProjectsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelUserIsAssociatedToProjectsLayout.setVerticalGroup(
            panelUserIsAssociatedToProjectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUserIsAssociatedToProjectsLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelTasksAssignedTo.setBorder(javax.swing.BorderFactory.createTitledBorder("Tem as Tarefas Atribuídas"));

        tableTasksAssignedTo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tableTasksAssignedTo.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane5.setViewportView(tableTasksAssignedTo);

        javax.swing.GroupLayout panelTasksAssignedToLayout = new javax.swing.GroupLayout(panelTasksAssignedTo);
        panelTasksAssignedTo.setLayout(panelTasksAssignedToLayout);
        panelTasksAssignedToLayout.setHorizontalGroup(
            panelTasksAssignedToLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTasksAssignedToLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5)
                .addContainerGap())
        );
        panelTasksAssignedToLayout.setVerticalGroup(
            panelTasksAssignedToLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTasksAssignedToLayout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnStatistics.setText("TaskLists");
        btnStatistics.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStatisticsActionPerformed(evt);
            }
        });

        btnStatistics1.setText("Tasks");
        btnStatistics1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStatistics1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(btnStatistics)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnStatistics1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelProjectsOwnedByUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelUserIsAssociatedToProjects, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelTasksAssignedTo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(228, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelProjectsOwnedByUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelUserIsAssociatedToProjects, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelTasksAssignedTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStatistics, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnStatistics1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(89, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStatistics1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatistics1ActionPerformed
        //tasks
//        for (Task t : s.getRepositoryTasks().getTasks()) {
//            System.out.println(
//                    t.getTaskId() + "  "
//                    + t.getTitle() + "  "
//                    + t.getDescription() + "  "
//                    + t.getTaskPriority() + "  "
//                    + t.getTaskStatus() + "  "
//                    + t.getCreatedBy() + "  "
//                    + t.getStartDate() + "  "
//                    + t.getEndDate());
//        }
    }//GEN-LAST:event_btnStatistics1ActionPerformed

    private void btnStatisticsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatisticsActionPerformed
        //TaskLists
    }//GEN-LAST:event_btnStatisticsActionPerformed

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
//            java.util.logging.Logger.getLogger(DisplayUserRelationship.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(DisplayUserRelationship.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(DisplayUserRelationship.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(DisplayUserRelationship.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                DisplayUserRelationship dialog = new DisplayUserRelationship(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnStatistics;
    private javax.swing.JButton btnStatistics1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JPanel panelProjectsOwnedByUser;
    private javax.swing.JPanel panelTasksAssignedTo;
    private javax.swing.JPanel panelUserIsAssociatedToProjects;
    private javax.swing.JTable tableProjectsOwnedByUser;
    private javax.swing.JTable tableTasksAssignedTo;
    private javax.swing.JTable tableUserIsAssociatedToProjects;
    // End of variables declaration//GEN-END:variables
}
