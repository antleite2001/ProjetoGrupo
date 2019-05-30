package Backend;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable{

    private int createdBy;
    private int assignedTo;
    private String Title;
    private String Description;
    private TaskPriority enumPriority;
    private Date StartDate;
    private Date EndDate;
    private TaskStatus enumStatus;
    private int TaskId; //Generated by RepositorioTarefa
    private int TaskListId;

    //Construtor
    public Task(int createdBy, int assignedTo, String Title, String Description,
            TaskPriority Priority, TaskStatus Status, Date StartDate, int TaskId, 
            int TaskListId) {
        this.createdBy = createdBy;
        this.assignedTo = assignedTo;
        this.Title = Title;
        this.Description = Description;
        this.enumPriority = Priority;
        this.enumStatus = Status;
        this.StartDate = StartDate;
        this.EndDate = null;
        this.TaskId = TaskId;
        this.TaskListId = TaskListId;
    }

    public int getTaskListId() {
        return TaskListId;
    }

    public TaskPriority getEnumPriority() {
        return enumPriority;
    }

    public TaskStatus getEnumStatus() {
        return enumStatus;
    }

    public int getTaskId() {
        return TaskId;
    }

    //Seletores    
    public int getCreatedBy() {
        return createdBy;
    }

    public int getAssignedTo() {
        return assignedTo;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public TaskPriority getTaskPriority() {
        return enumPriority;
    }

    public TaskStatus getTaskStatus() {
        return enumStatus;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    //Modificadores
    public void setTitle(String Title) {
        this.Title = Title;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setPrioridade(TaskPriority prioridade) {
        this.enumPriority = prioridade;
    }

    public void setEstado(TaskStatus estado) {
        this.enumStatus = estado;
    }

    public void setStartDate(Date StartDate) {
        this.StartDate = StartDate;
    }

    public void setEndDate(Date EndDate) {
        this.EndDate = EndDate;
    }

    //Outros Métodos
    @Override
    public String toString() {

        StringBuilder s = new StringBuilder();

        s.append("Características da Tarefa:");
        s.append("\nCriada por: " + createdBy);
        s.append("\nAtribuída a: " + assignedTo);
        s.append("\nTítulo: " + Title);
        s.append("\nDescrição: " + Description);
        s.append("\nPrioridade: " + enumPriority);
        s.append("\nEstado: " + enumStatus);
        s.append("\nData Prevista: " + StartDate); //VER ISTO FUTURAMENTE
        s.append("\nData Fim: " + EndDate); //VER ISTO FUTURAMENTE

        return s.toString();
    }
}
