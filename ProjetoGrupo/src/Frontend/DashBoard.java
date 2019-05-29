/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.*;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author VarianInstaller
 */
public class DashBoard extends javax.swing.JDialog {

    Sistema s;
    DefaultTableModel modelTask;

    /**
     * Creates new form DashBoard2
     */
    public DashBoard(java.awt.Frame parent, boolean modal, Sistema s) {
        super(parent, modal);
        initComponents();
        this.s = s;
        Validacoes.SetDialogProperties(this, s, 1200, 500,"DashBoard" );
        
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
        modelTask.addColumn("Prioridade");
        modelTask.addColumn("Estado");
        modelTask.addColumn("Data de Início");
        modelTask.addColumn("Titulo do Projeto");

        //relate model to table
        tableTasks.setModel(modelTask);
        
        displayDelayedTasks();
     
    }

    void displayDelayedTasks()
    {
        modelTask.setRowCount(0);
        ArrayList <Project> pa = new ArrayList <> ();
        for(Project p : s.getRepositoryProjects().getProjects())
        {
             Date currentDate = new Date();
               
            if( currentDate.compareTo(p.getEndDate()) > 0) //currentdate > enddate
            {
                pa.add(p);
            }
        }
       ArrayList <Task> t1 = new ArrayList <> ();
        for (Project p : pa)
        {
            for (TaskList tl1 : s.getRepositoryTaskLists().getListaDeTarefas())
            {
                if (p.getProjectId()==tl1.getProjectId())
                {
                    for (Task t : s.getRepositoryTasks().getTasks())
                    {
                        if (t.getTaskStatus()==TaskStatus.INPROGRESS)
                        {
                            if (!t1.contains(t))
                            {
                                t1.add(t);
                            modelTask.addRow(new Object [] {t.getTaskId(), t.getTitle(), t.getDescription(), t.getTaskPriority(), t.getTaskStatus(), 
                               Validacoes.FormatDate( t.getStartDate()), p.getProjectTitle()});
                            
                            }
                        }
                    }
                }
            }
        }
    }
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableTasks = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuExit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menuSignUpUser = new javax.swing.JMenuItem();
        menuChangeUserName = new javax.swing.JMenuItem();
        menuChangeEmail = new javax.swing.JMenuItem();
        menuChangePassword = new javax.swing.JMenuItem();
        menuAssignUsertoTask = new javax.swing.JMenuItem();
        menuAssociateUserToProject = new javax.swing.JMenuItem();
        menuDisplayUserRelationships = new javax.swing.JMenuItem();
        menuDisplayAllUsers = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        menuCreateProject = new javax.swing.JMenuItem();
        menuDisplayprojects = new javax.swing.JMenuItem();
        menuChangeProjectData = new javax.swing.JMenuItem();
        menuAssociateProjectsToUser = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        menuCreateTaskList = new javax.swing.JMenuItem();
        menuDisplayTaskList = new javax.swing.JMenuItem();
        menuEditTaskList = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        menuCreateTasks = new javax.swing.JMenuItem();
        menuEditTasks = new javax.swing.JMenuItem();
        menuChangeTaskStatus = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        menuFilterByCreatedBy = new javax.swing.JMenuItem();
        menuFilterByAssignedTo = new javax.swing.JMenuItem();
        menuFilterByPriority = new javax.swing.JMenuItem();
        menuFilterByStatus = new javax.swing.JMenuItem();
        menuFilterByEndDate = new javax.swing.JMenuItem();
        menuDisplayDelayedTasks = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setModal(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tarefas Atrasadas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 18))); // NOI18N
        jPanel6.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        tableTasks.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tableTasks.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(tableTasks);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 909, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, 350));

        jMenuBar1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N

        jMenu1.setText("Ficheiro");

        menuExit.setText("Sair");
        menuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuExitActionPerformed(evt);
            }
        });
        jMenu1.add(menuExit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Utilizador");

        menuSignUpUser.setText("Registar");
        menuSignUpUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSignUpUserActionPerformed(evt);
            }
        });
        jMenu2.add(menuSignUpUser);

        menuChangeUserName.setText("Alterar Nome");
        menuChangeUserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuChangeUserNameActionPerformed(evt);
            }
        });
        jMenu2.add(menuChangeUserName);

        menuChangeEmail.setText("Alterar Email");
        menuChangeEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuChangeEmailActionPerformed(evt);
            }
        });
        jMenu2.add(menuChangeEmail);

        menuChangePassword.setText("Alterar Password");
        menuChangePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuChangePasswordActionPerformed(evt);
            }
        });
        jMenu2.add(menuChangePassword);

        menuAssignUsertoTask.setText("Associar Utilizador a Tarefas");
        menuAssignUsertoTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAssignUsertoTaskActionPerformed(evt);
            }
        });
        jMenu2.add(menuAssignUsertoTask);

        menuAssociateUserToProject.setText("Associar Utilizador a Projetos");
        menuAssociateUserToProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAssociateUserToProjectActionPerformed(evt);
            }
        });
        jMenu2.add(menuAssociateUserToProject);

        menuDisplayUserRelationships.setText("Ver Relacionamentos");
        menuDisplayUserRelationships.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuDisplayUserRelationshipsActionPerformed(evt);
            }
        });
        jMenu2.add(menuDisplayUserRelationships);

        menuDisplayAllUsers.setText("Listar Todos");
        menuDisplayAllUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuDisplayAllUsersActionPerformed(evt);
            }
        });
        jMenu2.add(menuDisplayAllUsers);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Projetos");

        menuCreateProject.setText("Criar Projeto");
        menuCreateProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCreateProjectActionPerformed(evt);
            }
        });
        jMenu3.add(menuCreateProject);

        menuDisplayprojects.setText("Mostrar Projectos");
        menuDisplayprojects.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuDisplayprojectsActionPerformed(evt);
            }
        });
        jMenu3.add(menuDisplayprojects);

        menuChangeProjectData.setText("Editar Dados de Projeto");
        menuChangeProjectData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuChangeProjectDataActionPerformed(evt);
            }
        });
        jMenu3.add(menuChangeProjectData);

        menuAssociateProjectsToUser.setText("Associar Utilizador a Projetos");
        menuAssociateProjectsToUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAssociateProjectsToUserActionPerformed(evt);
            }
        });
        jMenu3.add(menuAssociateProjectsToUser);

        jMenuItem1.setText("Listar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Lista de Tarefas");

        menuCreateTaskList.setText("Criar Listas de Tarefa");
        menuCreateTaskList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCreateTaskListActionPerformed(evt);
            }
        });
        jMenu4.add(menuCreateTaskList);

        menuDisplayTaskList.setText("Mostrar Lista de Tarefas");
        menuDisplayTaskList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuDisplayTaskListActionPerformed(evt);
            }
        });
        jMenu4.add(menuDisplayTaskList);

        menuEditTaskList.setText("Editar Lista de Tarefas");
        menuEditTaskList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuEditTaskListActionPerformed(evt);
            }
        });
        jMenu4.add(menuEditTaskList);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("Tarefas");

        menuCreateTasks.setText("Criar Tarefa");
        menuCreateTasks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCreateTasksActionPerformed(evt);
            }
        });
        jMenu5.add(menuCreateTasks);

        menuEditTasks.setText("Editar Tarefas");
        menuEditTasks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuEditTasksActionPerformed(evt);
            }
        });
        jMenu5.add(menuEditTasks);

        menuChangeTaskStatus.setText("Alterar Estado da Tarefa");
        menuChangeTaskStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuChangeTaskStatusActionPerformed(evt);
            }
        });
        jMenu5.add(menuChangeTaskStatus);

        jMenu6.setText("Filtrar");

        menuFilterByCreatedBy.setText("Utilizador que Criou Tarefas");
        menuFilterByCreatedBy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFilterByCreatedByActionPerformed(evt);
            }
        });
        jMenu6.add(menuFilterByCreatedBy);

        menuFilterByAssignedTo.setText("Utilizador Associado");
        menuFilterByAssignedTo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFilterByAssignedToActionPerformed(evt);
            }
        });
        jMenu6.add(menuFilterByAssignedTo);

        menuFilterByPriority.setText("Prioridade");
        menuFilterByPriority.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFilterByPriorityActionPerformed(evt);
            }
        });
        jMenu6.add(menuFilterByPriority);

        menuFilterByStatus.setText("Estado");
        menuFilterByStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFilterByStatusActionPerformed(evt);
            }
        });
        jMenu6.add(menuFilterByStatus);

        menuFilterByEndDate.setText("Data de Fim");
        menuFilterByEndDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFilterByEndDateActionPerformed(evt);
            }
        });
        jMenu6.add(menuFilterByEndDate);

        jMenu5.add(jMenu6);

        menuDisplayDelayedTasks.setText("Mostrar Tarefas Atrasadas");
        menuDisplayDelayedTasks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuDisplayDelayedTasksActionPerformed(evt);
            }
        });
        jMenu5.add(menuDisplayDelayedTasks);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void menuCreateProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCreateProjectActionPerformed

        CreateProject createProject = new CreateProject(null, true, s);
        createProject.setLocationRelativeTo(null);
        createProject.setVisible(true);

    }//GEN-LAST:event_menuCreateProjectActionPerformed

    private void menuChangeUserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuChangeUserNameActionPerformed

        ChangeUserName changeUserName = new ChangeUserName(null, true, s);
        changeUserName.setLocationRelativeTo(null);
        changeUserName.setVisible(true);

        this.setTitle("DashBoard - " + s.getCurrentUser().getUserName() + " (" + s.getCurrentUser().getEmail() + ")");
    }//GEN-LAST:event_menuChangeUserNameActionPerformed

    private void menuChangeEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuChangeEmailActionPerformed
        ChangeUserEmail changeUserEmail = new ChangeUserEmail(null, true, s);
        changeUserEmail.setLocationRelativeTo(null);
        changeUserEmail.setVisible(true);
        this.setTitle("DashBoard - " + s.getCurrentUser().getUserName() + " (" + s.getCurrentUser().getEmail() + ")");
    }//GEN-LAST:event_menuChangeEmailActionPerformed

    private void menuChangePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuChangePasswordActionPerformed
        ChangeUserPassword changeUserPassword = new ChangeUserPassword(null, true, s);
        changeUserPassword.setLocationRelativeTo(null);
        changeUserPassword.setVisible(true);
    }//GEN-LAST:event_menuChangePasswordActionPerformed

    private void menuSignUpUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSignUpUserActionPerformed
        SignUpUser signUpUser = new SignUpUser(null, true, s);
        signUpUser.setLocationRelativeTo(null);
        signUpUser.setVisible(true);
    }//GEN-LAST:event_menuSignUpUserActionPerformed

    private void menuAssignUsertoTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAssignUsertoTaskActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuAssignUsertoTaskActionPerformed

    private void menuDisplayUserRelationshipsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDisplayUserRelationshipsActionPerformed
        DisplayUserRelationship displayUserRelationship = new DisplayUserRelationship(null, true, s);
        displayUserRelationship.setLocationRelativeTo(null);
        displayUserRelationship.setVisible(true);
    }//GEN-LAST:event_menuDisplayUserRelationshipsActionPerformed

    private void menuAssociateUserToProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAssociateUserToProjectActionPerformed
        AssociateUserToProjects associateUserToProject = new AssociateUserToProjects(null, true, s);
        associateUserToProject.setLocationRelativeTo(null);
        associateUserToProject.setVisible(true);
    }//GEN-LAST:event_menuAssociateUserToProjectActionPerformed

    private void menuCreateTasksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCreateTasksActionPerformed
        CreateTask ct = new CreateTask(null, true, s);
        ct.setLocationRelativeTo(null);
        ct.setVisible(true);
    }//GEN-LAST:event_menuCreateTasksActionPerformed

    private void menuDisplayDelayedTasksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDisplayDelayedTasksActionPerformed
//        DisplayDelayedTasks displayDelayedTasks = new DisplayDelayedTasks(null, true, s);
//        displayDelayedTasks.setLocationRelativeTo(null);
//        displayDelayedTasks.setVisible(true);
    }//GEN-LAST:event_menuDisplayDelayedTasksActionPerformed

    private void menuEditTasksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuEditTasksActionPerformed
        ChangeTask changeTask = new ChangeTask(null, true, s);
        changeTask.setLocationRelativeTo(null);
        changeTask.setVisible(true);
    }//GEN-LAST:event_menuEditTasksActionPerformed

    private void menuCreateTaskListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCreateTaskListActionPerformed
        CreateTaskLists createTaskList = new CreateTaskLists(null, true, s);
        createTaskList.setLocationRelativeTo(null);
        createTaskList.setVisible(true);
    }//GEN-LAST:event_menuCreateTaskListActionPerformed

    private void menuDisplayprojectsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDisplayprojectsActionPerformed
        DisplayProjects displayProjects = new DisplayProjects(null, true, s);
        displayProjects.setLocationRelativeTo(null);
        displayProjects.setVisible(true);
    }//GEN-LAST:event_menuDisplayprojectsActionPerformed

    private void menuChangeProjectDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuChangeProjectDataActionPerformed
        ChangeProjectData changeProjectData = new ChangeProjectData(null, true, s);
        changeProjectData.setLocationRelativeTo(null);
        changeProjectData.setVisible(true);
    }//GEN-LAST:event_menuChangeProjectDataActionPerformed

    private void menuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_menuExitActionPerformed

    private void menuEditTaskListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuEditTaskListActionPerformed
        ChangeTaskList changeTaskList = new ChangeTaskList(null, true, s);
        changeTaskList.setLocationRelativeTo(null);
        changeTaskList.setVisible(true);
    }//GEN-LAST:event_menuEditTaskListActionPerformed

    private void menuDisplayTaskListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDisplayTaskListActionPerformed
        DisplayTaskList displaytasklist = new DisplayTaskList(null, true, s);
        displaytasklist.setLocationRelativeTo(null);
        displaytasklist.setVisible(true);
    }//GEN-LAST:event_menuDisplayTaskListActionPerformed

    private void menuDisplayAllUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDisplayAllUsersActionPerformed
        DisplayAllUsers displayAllUsers = new DisplayAllUsers(null, true, s);
        displayAllUsers.setLocationRelativeTo(null);
        displayAllUsers.setVisible(true);
    }//GEN-LAST:event_menuDisplayAllUsersActionPerformed

    private void menuAssociateProjectsToUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAssociateProjectsToUserActionPerformed
        AssociateUserToProjects associateUserToProject = new AssociateUserToProjects(null, true, s);
        associateUserToProject.setLocationRelativeTo(null);
        associateUserToProject.setVisible(true);
    }//GEN-LAST:event_menuAssociateProjectsToUserActionPerformed

    private void menuChangeTaskStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuChangeTaskStatusActionPerformed
        ChangeTaskStatus changeTaskStatus = new ChangeTaskStatus(null, true, s);
        changeTaskStatus.setLocationRelativeTo(null);
        changeTaskStatus.setVisible(true);
        displayDelayedTasks();
    }//GEN-LAST:event_menuChangeTaskStatusActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void menuFilterByCreatedByActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFilterByCreatedByActionPerformed
        FilterByTaskCreatedBy filterByTaskCreatedBy = new FilterByTaskCreatedBy(null, true, s);
        filterByTaskCreatedBy.setLocationRelativeTo(null);
        filterByTaskCreatedBy.setVisible(true);
    }//GEN-LAST:event_menuFilterByCreatedByActionPerformed

    private void menuFilterByAssignedToActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFilterByAssignedToActionPerformed
        FilterByTaskAssignedTo filterByTaskAssignedTo = new FilterByTaskAssignedTo(null, true, s);
        filterByTaskAssignedTo.setLocationRelativeTo(null);
        filterByTaskAssignedTo.setVisible(true);
                                                 
    }//GEN-LAST:event_menuFilterByAssignedToActionPerformed

    private void menuFilterByPriorityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFilterByPriorityActionPerformed
        FilterByTaskPriority filterByTaskPriority = new FilterByTaskPriority(null, true, s);
        filterByTaskPriority.setLocationRelativeTo(null);
        filterByTaskPriority.setVisible(true);
    }//GEN-LAST:event_menuFilterByPriorityActionPerformed

    private void menuFilterByStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFilterByStatusActionPerformed
        FilterByTaskStatus filterByTaskStatus = new FilterByTaskStatus(null, true, s);
        filterByTaskStatus.setLocationRelativeTo(null);
        filterByTaskStatus.setVisible(true);
    }//GEN-LAST:event_menuFilterByStatusActionPerformed

    private void menuFilterByEndDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFilterByEndDateActionPerformed
        FilterByTaskEndDate filterByTaskEndDate = new FilterByTaskEndDate(null, true, s);
        filterByTaskEndDate.setLocationRelativeTo(null);
        filterByTaskEndDate.setVisible(true);
    }//GEN-LAST:event_menuFilterByEndDateActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JMenuItem menuAssignUsertoTask;
    private javax.swing.JMenuItem menuAssociateProjectsToUser;
    private javax.swing.JMenuItem menuAssociateUserToProject;
    private javax.swing.JMenuItem menuChangeEmail;
    private javax.swing.JMenuItem menuChangePassword;
    private javax.swing.JMenuItem menuChangeProjectData;
    private javax.swing.JMenuItem menuChangeTaskStatus;
    private javax.swing.JMenuItem menuChangeUserName;
    private javax.swing.JMenuItem menuCreateProject;
    private javax.swing.JMenuItem menuCreateTaskList;
    private javax.swing.JMenuItem menuCreateTasks;
    private javax.swing.JMenuItem menuDisplayAllUsers;
    private javax.swing.JMenuItem menuDisplayDelayedTasks;
    private javax.swing.JMenuItem menuDisplayTaskList;
    private javax.swing.JMenuItem menuDisplayUserRelationships;
    private javax.swing.JMenuItem menuDisplayprojects;
    private javax.swing.JMenuItem menuEditTaskList;
    private javax.swing.JMenuItem menuEditTasks;
    private javax.swing.JMenuItem menuExit;
    private javax.swing.JMenuItem menuFilterByAssignedTo;
    private javax.swing.JMenuItem menuFilterByCreatedBy;
    private javax.swing.JMenuItem menuFilterByEndDate;
    private javax.swing.JMenuItem menuFilterByPriority;
    private javax.swing.JMenuItem menuFilterByStatus;
    private javax.swing.JMenuItem menuSignUpUser;
    private javax.swing.JTable tableTasks;
    // End of variables declaration//GEN-END:variables
}
