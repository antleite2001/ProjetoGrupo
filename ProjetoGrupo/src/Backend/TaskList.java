package Backend;

import java.util.ArrayList;
import java.util.Date;


public class TaskList{
     
    
    private int TaskListId; //Generated by RepositorioListaDeTarefas     
    private int ProjectId;
     
    private String Title, Description;
    private int createdBy;

    public TaskList(int projectId, String title, String description, int createdBy, int taskListId  ) {
        this.ProjectId = projectId;
        this.Title = title;
        this.Description = description;
        this.createdBy = createdBy;
        this.TaskListId = taskListId;
         
    }
    
    public int getTaskListId() {
        return TaskListId;
    }

    

    public int getProjectId() {
        return ProjectId;
    }

    
    public String getTitle() {
        return Title;
    }

    
    public String getDescription() {
        return Description;
    }

    
    public int getCreatedBy() {
        return createdBy;
    }

     
   
    
   
}
