package Backend;

import java.util.ArrayList;
import java.util.Date;

public class RepositoryTasks {

    int nextTaskid = 0;
    private ArrayList<Task> Tasks;

    public RepositoryTasks() {
        this.Tasks = new ArrayList<Task>();
    }

    //Seletores     
    public ArrayList<Task> getTarefa() {
        return this.Tasks;
    }

    //Modificadores
    public ArrayList<Task> getTasksAssignedToUser(int AssignedTo) {
        ArrayList<Task> tasksAssignedToUserId = new ArrayList<>();
        for (Task t : Tasks) {
            if (t.getAssignedTo() == AssignedTo) {
                tasksAssignedToUserId.add(t);
            }
        }
        return tasksAssignedToUserId;
    }

    public void addTask(int CreatedBy, String Title, String Description, TaskPriority pt, TaskStatus et, Date DataDeInicio, int TaskListId, int assignedTo) {
        //to show getNextprojectid 
        Task t = new Task(CreatedBy, assignedTo, Title, Description, pt, et, DataDeInicio, getNextTaskid(),TaskListId);
        System.out.println("New Project ID: " + t.getTaskId());
        Tasks.add(t);
    }

    public int getNextTaskid() {
        return ++nextTaskid;
    }

    public void removeTarefa(Task tarefa) {
        Tasks.remove(tarefa);
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
