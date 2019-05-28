package Backend;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class RepositoryTasks {

    int nextTaskid = 0;
    private ArrayList<Task> Tasks;

    public RepositoryTasks() {
        this.Tasks = new ArrayList<Task>();
    }

    //Seletores     
    public ArrayList<Task> getTasks() {
        return this.Tasks;
    }

    public Task getTaskByTaskId(int TaskId) {
        for (Task t : Tasks) {
            if (t.getTaskId() == TaskId) {
                return t;
            }
        }
        return null;
    }

    public ArrayList<Task> getTasksAssignedToUser(int AssignedTo) {
        ArrayList<Task> tasksAssignedToUserId = new ArrayList<>();
        for (Task t : Tasks) {
            if (t.getAssignedTo() == AssignedTo) {
                tasksAssignedToUserId.add(t);
            }
        }
        return tasksAssignedToUserId;
    }

    
    public boolean updateTaskStatusById(int TaskId, TaskStatus newTaskStatus )
    {
        for(Task t : Tasks)
        {
            if(t.getTaskId()==TaskId)
            {
                t.setEstado(newTaskStatus);
                 
                return true;
            }
        }
        return false;
    }
            
    public void addTask(int CreatedBy, String Title, String Description, TaskPriority pt, TaskStatus et, Date DataDeInicio, int TaskListId, int assignedTo) {
        Task t = new Task(CreatedBy, assignedTo, Title, Description, pt, et, DataDeInicio, getNextTaskid(), TaskListId);
        System.out.println("New Task ID: " + t.getTaskId());
        Tasks.add(t);
    }

    public int getNextTaskid() {
        return ++nextTaskid;
    }

    public void removeTask(Task tarefa) {
        Tasks.remove(tarefa);
    }
    
    public void updateTaskStatusByIdandDate
    (int TaskId, TaskStatus newTaskStatus, Date d)
    {
        for(Task t : Tasks)
        {
            if(t.getTaskId()==TaskId)
            {
                t.setEstado(newTaskStatus);
                t.setEndDate(d);
                
            }
        }
    }
    


    @Override
    public String toString() {

        StringBuilder s = new StringBuilder();
        s.append("Tarefas:\n");

        for (Task tarefa : Tasks) {
            s.append(tarefa.toString());
        }

        return s.toString();
    }
}
