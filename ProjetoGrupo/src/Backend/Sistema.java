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

        int userid1 = this.reporitoryUsers.addUser("José Álvaro", "josealvaro@gmail.com", "123456");
        int userid2 = this.reporitoryUsers.addUser("Antonio Leite", "antonioleite@gmail.com", "123456");
        int userid3 = this.reporitoryUsers.addUser("Paula Leite", "paulaleite@gmail.com", "123456");

        //Projects Repository
        int p1 = 1;
        int p2 = 1;
        int p3 = 1;
        int p4 = 1;
        int p5 = 1;
        int p6 = 1;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        try {
            p1 = repositoryProjects.addProject(userid1, "P1 jose alvaro", "P1", 
                    formatter.parse("22-May-2019"),
                    formatter.parse("23-May-2019"), RepositoryUserProjectsAssociation);
            p2 = repositoryProjects.addProject(userid1, "P2 jose alvaro", "P2", 
                    formatter.parse("24-May-2019"), formatter.parse("25-May-2019"),
                    RepositoryUserProjectsAssociation);
            p3 = repositoryProjects.addProject(userid1, "P3 jose alvaro", "P3", 
                    formatter.parse("26-May-2019"), formatter.parse("27-May-2019"),
                    RepositoryUserProjectsAssociation);

            p4 = repositoryProjects.addProject(userid2, "P1 antonio leite", "P1", 
                    formatter.parse("22-May-2019"), formatter.parse("22-May-2019"),
                    RepositoryUserProjectsAssociation);
            p5 = repositoryProjects.addProject(userid2, "P2 antonio leite", "P2", 
                    formatter.parse("22-May-2019"), formatter.parse("22-May-2019"),
                    RepositoryUserProjectsAssociation);
            p6 = repositoryProjects.addProject(userid2, "P3 antonio leite", "P3", 
                    formatter.parse("22-May-2019"), formatter.parse("22-May-2019"),
                    RepositoryUserProjectsAssociation);

        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("SISTEMA-ERROR:" + e.getMessage());
        }

        //task List repository
        //ProjectId, String Title, String Description, int CreatedBy
       int taskList1= repositoryTaskLists.addTaskList(p1, "TL1", "TaskDesc1", userid1);
        repositoryTaskLists.addTaskList(p1, "TL2", "TaskDesc2", userid1);

        repositoryTaskLists.addTaskList(p2, "TL3", "TaskDesc3", userid1);
        repositoryTaskLists.addTaskList(p3, "TL4", "TaskDesc4", userid1);
        repositoryTaskLists.addTaskList(p2, "TL5", "TaskDesc5", userid1);

        repositoryTaskLists.addTaskList(p1, "TL6", "TaskDesc6", userid1);
        repositoryTaskLists.addTaskList(p1, "TL7", "TaskDesc7", userid1);
        repositoryTaskLists.addTaskList(p1, "TL8", "TaskDesc8", userid1);
        
        //Tasks
        repositoryTasks.addTask(userid1, "Task1", "Task1 description", TaskPriority.LOW, TaskStatus.FINISHED, new Date(System.currentTimeMillis()),taskList1); 
        ////UserId, ProjectId, ProjectOwner
        //RepositoryUserProjectsAssociation.addUserProjectsAssociation(userid2, p1, userid1);
        //RepositoryUserProjectsAssociation.addUserProjectsAssociation(userid3, p2, userid1);
        //RepositoryUserProjectsAssociation.addUserProjectsAssociation(userid2, p3, userid1);
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
