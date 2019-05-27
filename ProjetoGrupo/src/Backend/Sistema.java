package Backend;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class Sistema {

    private RepositoryProjects repositoryProjects;
    private RepositoryUsers reporitoryUsers;
    private User currentUser = null;
    private RepositoryTasks repositoryTasks;
    private RepositoryTaskLists repositoryTaskLists;
    private RepositoryUserProjectsAssociation RepositoryUserProjectsAssociation;

    public Sistema() {

        //Users
        this.reporitoryUsers = new RepositoryUsers();
        this.repositoryProjects = new RepositoryProjects();
        this.repositoryTaskLists = new RepositoryTaskLists();
        this.repositoryTasks = new RepositoryTasks();
        this.RepositoryUserProjectsAssociation = new RepositoryUserProjectsAssociation();

        int JoseAlvaro = this.reporitoryUsers.addUser("JoseAlvaro", "JoseAlvaro@gmail.com", "123456");
        int AntonioSilva = this.reporitoryUsers.addUser("AntonioSilva", "AntonioSilva@gmail.com", "123456");
        int ManuelSilva = this.reporitoryUsers.addUser("ManuelSilva", "ManuelSilva@gmail.com", "123456");
        int InesSilva = this.reporitoryUsers.addUser("InesSilva", "InesSilva@gmail.com", "123456");
        int PedroSilva = this.reporitoryUsers.addUser("PedroSilva", "PedroSilva@gmail.com", "123456");

//Projects Repository
        int p1JoseAlvaro = 1;
        int p2ManuelSilva = 1;
        int p3JoseAlvaro = 1;
        /*int p4 = 1;
        int p5 = 1;
        int p6 = 1;*/
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        try {
            p1JoseAlvaro = repositoryProjects.addProject(JoseAlvaro, "P1", "P1",
                    formatter.parse("22-May-2019"),
                    formatter.parse("23-May-2019"),
                    RepositoryUserProjectsAssociation);
            p2ManuelSilva = repositoryProjects.addProject(ManuelSilva, "P2", "P2",
                    formatter.parse("24-May-2019"),
                    formatter.parse("25-May-2019"),
                    RepositoryUserProjectsAssociation);
            p3JoseAlvaro = repositoryProjects.addProject(JoseAlvaro, "P3", "P3",
                    formatter.parse("26-May-2019"),
                    formatter.parse("27-May-2019"),
                    RepositoryUserProjectsAssociation);

            /* p4 = repositoryProjects.addProject(userid2, "P1 antonio leite", "P1",
                    formatter.parse("22-May-2019"), formatter.parse("22-May-2019"),
                    RepositoryUserProjectsAssociation);
            p5 = repositoryProjects.addProject(userid2, "P2 antonio leite", "P2",
                    formatter.parse("22-May-2019"), formatter.parse("22-May-2019"),
                    RepositoryUserProjectsAssociation);
            p6 = repositoryProjects.addProject(userid2, "P3 antonio leite", "P3",
                    formatter.parse("22-May-2019"), formatter.parse("22-May-2019"),
                    RepositoryUserProjectsAssociation);*/
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("SISTEMA-ERROR:" + e.getMessage());
        }

        
        //Associations Users Project                                 userid, projectid projectOwner
        RepositoryUserProjectsAssociation.addUserProjectsAssociation(AntonioSilva, p1JoseAlvaro, JoseAlvaro);
        RepositoryUserProjectsAssociation.addUserProjectsAssociation(InesSilva, p2ManuelSilva, ManuelSilva);
        RepositoryUserProjectsAssociation.addUserProjectsAssociation(InesSilva, p1JoseAlvaro, JoseAlvaro);
        RepositoryUserProjectsAssociation.addUserProjectsAssociation(JoseAlvaro, p2ManuelSilva, ManuelSilva);
        RepositoryUserProjectsAssociation.addUserProjectsAssociation(PedroSilva, p1JoseAlvaro, JoseAlvaro);
        RepositoryUserProjectsAssociation.addUserProjectsAssociation(PedroSilva, p3JoseAlvaro, JoseAlvaro);
        
        
        //task List repository
        //ProjectId, String Title, String Description, int CreatedBy
        repositoryTaskLists.addTaskList(p1JoseAlvaro, "TL1", "TaskDesc1", JoseAlvaro);
        repositoryTaskLists.addTaskList(p2ManuelSilva, "TL2", "TaskDesc2", ManuelSilva);
        repositoryTaskLists.addTaskList(p1JoseAlvaro, "TL3", "TaskDesc3", JoseAlvaro);
        
        
        repositoryTaskLists.addTaskList(p2ManuelSilva, "TL4", "TaskDesc4", ManuelSilva);
        repositoryTaskLists.addTaskList(p3JoseAlvaro, "TL5", "TaskDesc5", JoseAlvaro);

        
/*
        //Tasks CreatedBy,   Title,   Description,   priority, Status, 
        //  DataDeInicio,   TaskListId,   assignedTo
        repositoryTasks.addTask(userid1, "Task1", "Task1 description", TaskPriority.LOW, TaskStatus.NOTSTARTED, new Date(System.currentTimeMillis()), taskList1, userid1);
        repositoryTasks.addTask(userid1, "Task2", "Task3 description", TaskPriority.MEDIUM, TaskStatus.INPROGRESS, new Date(System.currentTimeMillis()), taskList1, userid1);
        repositoryTasks.addTask(userid1, "Task3", "Task3 description", TaskPriority.HIGH, TaskStatus.FINISHED, new Date(System.currentTimeMillis()), taskList1, userid1);
        repositoryTasks.addTask(userid1, "Task4", "Task4 description", 
                TaskPriority.HIGH, 
                TaskStatus.FINISHED, 
                new Date(System.currentTimeMillis()), 
                taskList1, 
                userid2);*/
    }

    public RepositoryTasks getRepositoryTasks() {
        return repositoryTasks;
    }

    public RepositoryTaskLists getRepositoryTaskLists() {
        return repositoryTaskLists;
    }

    public RepositoryProjects getRepositoryProjects() {
        return repositoryProjects;
    }

    public RepositoryUsers getUsersRepository() {
        return reporitoryUsers;
    }

    public void setCurrentUser(User CurrentUser) {
        this.currentUser = CurrentUser;
    }

    public User getCurrentUser() {
        return currentUser;

    }

    public RepositoryUserProjectsAssociation getRepositoryUserProjectsAssociation() {
        return RepositoryUserProjectsAssociation;
    }

}
