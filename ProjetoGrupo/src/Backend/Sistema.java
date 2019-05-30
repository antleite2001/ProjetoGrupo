package Backend;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
 
public class Sistema  implements Serializable{

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

        
       /* //Repository Users
        int JoseAlvaro = this.reporitoryUsers.addUser("JoseAlvaro", "JoseAlvaro@gmail.com", "123456");
        int AntonioSilva = this.reporitoryUsers.addUser("AntonioSilva", "AntonioSilva@gmail.com", "123456");
        int ManuelSilva = this.reporitoryUsers.addUser("ManuelSilva", "ManuelSilva@gmail.com", "123456");
        int InesSilva = this.reporitoryUsers.addUser("InesSilva", "InesSilva@gmail.com", "123456");
        int PedroSilva = this.reporitoryUsers.addUser("PedroSilva", "PedroSilva@gmail.com", "123456");

        //Projects Repository
        int p1JoseAlvaro = 1;
        int p2ManuelSilva = 1;
        int p3JoseAlvaro = 1;

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
        int tasklist1 = repositoryTaskLists.addTaskList(p1JoseAlvaro, "TL1", "TaskDesc1", JoseAlvaro);
        int tasklist2 = repositoryTaskLists.addTaskList(p2ManuelSilva, "TL2", "TaskDesc2", ManuelSilva);
        int tasklist3 = repositoryTaskLists.addTaskList(p1JoseAlvaro, "TL3", "TaskDesc3", JoseAlvaro);
        int tasklist4 = repositoryTaskLists.addTaskList(p2ManuelSilva, "TL4", "TaskDesc4", ManuelSilva);
        int tasklist5 = repositoryTaskLists.addTaskList(p3JoseAlvaro, "TL5", "TaskDesc5", JoseAlvaro);

        //Tasks CreatedBy,   Title,   Description,   priority, Status, 
        //  DataDeInicio,   TaskListId,   assignedTo
        try {
            repositoryTasks.addTask(JoseAlvaro, "Task1", "Task1 description", TaskPriority.LOW, TaskStatus.FINISHED,
                    formatter.parse("1-May-2019"), tasklist1, JoseAlvaro);
            repositoryTasks.getTasks().get(0).setEndDate(formatter.parse("20-May-2019"));
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("SISTEMA-ERROR:" + e.getMessage());
        }

        repositoryTasks.addTask(JoseAlvaro, "Task2", "Task2 description", TaskPriority.MEDIUM, TaskStatus.NOTSTARTED, new Date(System.currentTimeMillis()), tasklist1, JoseAlvaro);
        repositoryTasks.addTask(JoseAlvaro, "Task3", "Task3 description", TaskPriority.HIGH, TaskStatus.NOTSTARTED, new Date(System.currentTimeMillis()), tasklist1, JoseAlvaro);
        repositoryTasks.addTask(JoseAlvaro, "Task4", "Task4 description", TaskPriority.HIGH, TaskStatus.NOTSTARTED, new Date(System.currentTimeMillis()), tasklist1, JoseAlvaro);
        repositoryTasks.addTask(JoseAlvaro, "Task5", "Task5 description", TaskPriority.LOW, TaskStatus.NOTSTARTED, new Date(System.currentTimeMillis()), tasklist1, AntonioSilva);
        repositoryTasks.addTask(JoseAlvaro, "Task6", "Task6 description", TaskPriority.MEDIUM, TaskStatus.NOTSTARTED, new Date(System.currentTimeMillis()), tasklist1, AntonioSilva);
        repositoryTasks.addTask(JoseAlvaro, "Task7", "Task7 description", TaskPriority.HIGH, TaskStatus.NOTSTARTED, new Date(System.currentTimeMillis()), tasklist1, AntonioSilva);
        repositoryTasks.addTask(JoseAlvaro, "Task8", "Task8 description", TaskPriority.HIGH, TaskStatus.NOTSTARTED, new Date(System.currentTimeMillis()), tasklist1, AntonioSilva);
        repositoryTasks.addTask(JoseAlvaro, "Task9", "Task9 description", TaskPriority.HIGH, TaskStatus.NOTSTARTED, new Date(System.currentTimeMillis()), tasklist1, InesSilva);
        repositoryTasks.addTask(JoseAlvaro, "Task10", "Task10 description", TaskPriority.HIGH, TaskStatus.NOTSTARTED, new Date(System.currentTimeMillis()), tasklist1, PedroSilva);
        
        try {
        repositoryTasks.addTask(ManuelSilva, "Task11", "Task11 description", TaskPriority.LOW, TaskStatus.FINISHED, formatter.parse("1-May-2019"), tasklist2, JoseAlvaro);
        repositoryTasks.getTasks().get(10).setEndDate(formatter.parse("21-May-2019"));
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("SISTEMA-ERROR:" + e.getMessage());
        }
        
        
        repositoryTasks.addTask(ManuelSilva, "Task12", "Task12 description", TaskPriority.MEDIUM, TaskStatus.NOTSTARTED, new Date(System.currentTimeMillis()), tasklist2, JoseAlvaro);
        repositoryTasks.addTask(ManuelSilva, "Task13", "Task13 description", TaskPriority.HIGH, TaskStatus.NOTSTARTED, new Date(System.currentTimeMillis()), tasklist2, ManuelSilva);
        repositoryTasks.addTask(ManuelSilva, "Task14", "Task14 description", TaskPriority.HIGH, TaskStatus.NOTSTARTED, new Date(System.currentTimeMillis()), tasklist2, ManuelSilva);
        repositoryTasks.addTask(ManuelSilva, "Task15", "Task15 description", TaskPriority.LOW, TaskStatus.NOTSTARTED, new Date(System.currentTimeMillis()), tasklist2, ManuelSilva);
        repositoryTasks.addTask(ManuelSilva, "Task16", "Task16 description", TaskPriority.MEDIUM, TaskStatus.NOTSTARTED, new Date(System.currentTimeMillis()), tasklist2, ManuelSilva);
        repositoryTasks.addTask(ManuelSilva, "Task17", "Task17 description", TaskPriority.HIGH, TaskStatus.NOTSTARTED, new Date(System.currentTimeMillis()), tasklist2, InesSilva);
        repositoryTasks.addTask(ManuelSilva, "Task18", "Task18 description", TaskPriority.HIGH, TaskStatus.NOTSTARTED, new Date(System.currentTimeMillis()), tasklist2, InesSilva);
        repositoryTasks.addTask(ManuelSilva, "Task19", "Task19 description", TaskPriority.HIGH, TaskStatus.NOTSTARTED, new Date(System.currentTimeMillis()), tasklist2, InesSilva);
        repositoryTasks.addTask(ManuelSilva, "Task20", "Task20 description", TaskPriority.HIGH, TaskStatus.NOTSTARTED, new Date(System.currentTimeMillis()), tasklist2, InesSilva);
        //21
        repositoryTasks.addTask(JoseAlvaro, "Task21", "Task21 description", TaskPriority.LOW, TaskStatus.NOTSTARTED, new Date(System.currentTimeMillis()), tasklist3, JoseAlvaro);
        repositoryTasks.addTask(JoseAlvaro, "Task22", "Task22 description", TaskPriority.MEDIUM, TaskStatus.NOTSTARTED, new Date(System.currentTimeMillis()), tasklist3, InesSilva);
        repositoryTasks.addTask(JoseAlvaro, "Task23", "Task23 description", TaskPriority.HIGH, TaskStatus.NOTSTARTED, new Date(System.currentTimeMillis()), tasklist3, InesSilva);
        repositoryTasks.addTask(JoseAlvaro, "Task24", "Task24 description", TaskPriority.HIGH, TaskStatus.NOTSTARTED, new Date(System.currentTimeMillis()), tasklist3, AntonioSilva);
        repositoryTasks.addTask(JoseAlvaro, "Task25", "Task25 description", TaskPriority.LOW, TaskStatus.NOTSTARTED, new Date(System.currentTimeMillis()), tasklist3, PedroSilva);

        repositoryTasks.addTask(ManuelSilva, "Task26", "Task26 description", TaskPriority.MEDIUM, TaskStatus.NOTSTARTED, new Date(System.currentTimeMillis()), tasklist4, JoseAlvaro);
        repositoryTasks.addTask(ManuelSilva, "Task27", "Task27 description", TaskPriority.HIGH, TaskStatus.NOTSTARTED, new Date(System.currentTimeMillis()), tasklist4, ManuelSilva);
        repositoryTasks.addTask(ManuelSilva, "Task28", "Task28 description", TaskPriority.HIGH, TaskStatus.NOTSTARTED, new Date(System.currentTimeMillis()), tasklist4, InesSilva);
        repositoryTasks.addTask(ManuelSilva, "Task29", "Task29 description", TaskPriority.HIGH, TaskStatus.NOTSTARTED, new Date(System.currentTimeMillis()), tasklist4, InesSilva);
        repositoryTasks.addTask(ManuelSilva, "Task30", "Task30 description", TaskPriority.HIGH, TaskStatus.NOTSTARTED, new Date(System.currentTimeMillis()), tasklist4, InesSilva);

        repositoryTasks.addTask(JoseAlvaro, "Task31", "Task31 description", TaskPriority.HIGH, TaskStatus.NOTSTARTED, new Date(System.currentTimeMillis()), tasklist5, JoseAlvaro);
        repositoryTasks.addTask(JoseAlvaro, "Task32", "Task32 description", TaskPriority.HIGH, TaskStatus.NOTSTARTED, new Date(System.currentTimeMillis()), tasklist5, PedroSilva);
*/
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
