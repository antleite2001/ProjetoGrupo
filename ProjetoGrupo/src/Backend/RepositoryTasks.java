package Backend;

import Frontend.Validacoes;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import org.jdesktop.swingx.JXDatePicker;

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

    public ArrayList<Task> getTasksCreatedBy(int CreatedBy) {
        ArrayList<Task> tasksCreatedBy = new ArrayList<>();
        for (Task t : Tasks) {
            if (t.getCreatedBy() == CreatedBy) {
                tasksCreatedBy.add(t);
            }
        }
        return tasksCreatedBy;
    }

    public ArrayList<Task> getTasksByPriority(TaskPriority taskPriority) {
        ArrayList<Task> tasksPriority = new ArrayList<>();
        for (Task t : Tasks) {
            if (t.getTaskPriority() == taskPriority) {
                tasksPriority.add(t);
            }
        }
        return tasksPriority;
    }

    public ArrayList<Task> getTasksByStatus(TaskStatus taskStatus) {
        ArrayList<Task> tasksStatus = new ArrayList<>();
        for (Task t : Tasks) {
            if (t.getTaskStatus() == taskStatus) {
                tasksStatus.add(t);
            }
        }
        return tasksStatus;
    }

    public ArrayList<Task> getTasksByEndDate(JXDatePicker endDate) {
        String selectedEndDateDays = Validacoes.FormatDate(endDate.getDate());
        ArrayList<Task> tasksEndDate = new ArrayList<>();
        for (Task t : Tasks) {
            if (t.getEndDate() != null) {
                if (selectedEndDateDays.equals(Validacoes.FormatDate(t.getEndDate()))) {
                    tasksEndDate.add(t);
                }
            }
        }
        return tasksEndDate;
    }

    public boolean updateTaskStatusById(int TaskId, TaskStatus newTaskStatus) {
        for (Task t : Tasks) {
            if (t.getTaskId() == TaskId) {
                t.setEstado(newTaskStatus);

                return true;
            }
        }
        return false;
    }

    public int addTask(int CreatedBy, String Title, String Description, TaskPriority pt, TaskStatus et, Date DataDeInicio, int TaskListId, int assignedTo) {
        Task t = new Task(CreatedBy, assignedTo, Title, Description, pt, et, DataDeInicio, getNextTaskid(), TaskListId);
        Tasks.add(t);
        return t.getTaskId();
    }

    public int getNextTaskid() {
        return ++nextTaskid;
    }

    public void removeTask(Task tarefa) {
        Tasks.remove(tarefa);
    }

    public void updateTaskStatusByIdandDate(int TaskId, TaskStatus newTaskStatus, Date d) {
        for (Task t : Tasks) {
            if (t.getTaskId() == TaskId) {
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
