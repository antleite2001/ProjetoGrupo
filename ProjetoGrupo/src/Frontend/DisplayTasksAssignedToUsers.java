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

public class DisplayTasksAssignedToUsers extends javax.swing.JDialog {

    Sistema s;
    int selectedprojectid;
    DefaultTableModel modelProjectsOwnedByUser, modelUserIsAssociatedToProjects, modelTasksAssignedToProject;

    public DisplayTasksAssignedToUsers(java.awt.Frame parent, boolean modal, Sistema s) {
        super(parent, modal);
        initComponents();
        this.s = s;
        Validacoes.SetDialogProperties(this, s, "Mostrar Tarefas Associadas a Projetos");
        panelProjectsOwnedByUser.setBorder(javax.swing.BorderFactory.createTitledBorder(s.getCurrentUser().getUserName() + " é Proprietário dos Projetos"));

        panelTasksAssignedTo.setBorder(javax.swing.BorderFactory.createTitledBorder("Tarefas Atribuídas ao Projeto"));


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
        tableTasksAssignedToProject.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        //Auto resize all columns
        tableTasksAssignedToProject.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        //prevent editing of jtable cells
        tableTasksAssignedToProject.setDefaultEditor(Object.class, null);

        //create modelProjectsOwnedByUser to handle columns and rows
        modelTasksAssignedToProject = new DefaultTableModel();
        modelTasksAssignedToProject.addColumn("Id da Tarefa");//0
        modelTasksAssignedToProject.addColumn("Título da Tarefa");//1
        modelTasksAssignedToProject.addColumn("Descrição da Tarefa");//2
        modelTasksAssignedToProject.addColumn("Prioridade");//3
        modelTasksAssignedToProject.addColumn("Status");//4
        modelTasksAssignedToProject.addColumn("Criada Por");//5
        modelTasksAssignedToProject.addColumn("Utilizador Associado");//6
        modelTasksAssignedToProject.addColumn("Data de Início");//7
        modelTasksAssignedToProject.addColumn("Data de Fim");//8
        tableTasksAssignedToProject.setModel(modelTasksAssignedToProject);


        
        
        //Events
        tableProjectsOwnedByUser.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                selectedprojectid=(int) tableProjectsOwnedByUser.getValueAt(tableProjectsOwnedByUser.getSelectedRow(), 0);
                SelectArrayTaskList();
                
                 
            }
        });


    }

     
    void SelectArrayTaskList()
    {
        modelTasksAssignedToProject.setRowCount(0);
        ArrayList <TaskList> tl = new ArrayList<> ();
        for (TaskList tl2 : s.getRepositoryTaskLists().getListaDeTarefas())
        {
            if (selectedprojectid==tl2.getProjectId())
            {
                tl.add(tl2);
            }
        }
        for (TaskList tl3 : tl)
        {
            int tlid=tl3.getTaskListId();
            for (Task t1 : s.getRepositoryTasks().getTasks())
            {
                if (tlid==t1.getTaskListId())
                {
                    if (t1.getEndDate()!=null)
                    {
                        
                   
                    modelTasksAssignedToProject.addRow(new Object []{t1.getTaskId(), t1.getTitle(), t1.getDescription(), t1.getTaskPriority(), t1.getTaskStatus(), 
                       s.getUsersRepository().getUserById(t1.getCreatedBy()).getUserName(), s.getUsersRepository().getUserById(t1.getAssignedTo()).getUserName(), Validacoes.FormatDate( t1.getStartDate()), Validacoes.FormatDate(t1.getEndDate())});
                    }
                    else
                    {
                        modelTasksAssignedToProject.addRow(new Object []{t1.getTaskId(), t1.getTitle(), t1.getDescription(), t1.getTaskPriority(), t1.getTaskStatus(), 
                        s.getUsersRepository().getUserById(t1.getCreatedBy()).getUserName(), s.getUsersRepository().getUserById(t1.getAssignedTo()).getUserName(), Validacoes.FormatDate(t1.getStartDate()), ""});
                    }
                }
            }
        }
        
        
        
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelProjectsOwnedByUser = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableProjectsOwnedByUser = new javax.swing.JTable();
        panelTasksAssignedTo = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tableTasksAssignedToProject = new javax.swing.JTable();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mostrar relacionamentos");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelProjectsOwnedByUser.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "É Proprietário dos Projetos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 18))); // NOI18N

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

        getContentPane().add(panelProjectsOwnedByUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        panelTasksAssignedTo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tarefas associadas ao projeto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 18))); // NOI18N

        tableTasksAssignedToProject.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tableTasksAssignedToProject.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane5.setViewportView(tableTasksAssignedToProject);

        javax.swing.GroupLayout panelTasksAssignedToLayout = new javax.swing.GroupLayout(panelTasksAssignedTo);
        panelTasksAssignedTo.setLayout(panelTasksAssignedToLayout);
        panelTasksAssignedToLayout.setHorizontalGroup(
            panelTasksAssignedToLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTasksAssignedToLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelTasksAssignedToLayout.setVerticalGroup(
            panelTasksAssignedToLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTasksAssignedToLayout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(panelTasksAssignedTo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 218, -1, -1));

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
        getContentPane().add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 430, 200, 46));

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
    private javax.swing.JButton btnCancel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JPanel panelProjectsOwnedByUser;
    private javax.swing.JPanel panelTasksAssignedTo;
    private javax.swing.JTable tableProjectsOwnedByUser;
    private javax.swing.JTable tableTasksAssignedToProject;
    // End of variables declaration//GEN-END:variables
}
