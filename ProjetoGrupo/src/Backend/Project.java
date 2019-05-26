package Backend;

import java.util.Calendar;
import java.util.Date;

public class Project 
{
    private int ProjetoID;
    private int ProjectOwner;
    private String ProjectTitle, ProjectDescription;

    
    private Date StartDate, EndDate;
    private ProjectStatus enumprojeto; 
    
    
   //construtor
    public Project (int owner, String titulo, String descricao, Date dataInicio, Date dataFim, int projetoID){
       
       this.ProjectOwner = owner;
       this.ProjectTitle = titulo;
       this.ProjectDescription = descricao;
       this.StartDate = dataInicio;
       this.EndDate = dataFim;
     
       this.enumprojeto = ProjectStatus.NOTSTARTED;
       this.ProjetoID = projetoID;
       //System.out.println("Project StartDate " + this.StartDate + "  EndDate " +dataFim);
    }

    public int getProjectId() {
        return ProjetoID;
    }
   
    //seletores
    
    public int getProjectOwner(){
        return ProjectOwner;
    }
    
    public String getProjectTitle(){
        return ProjectTitle;
    }
    
    public String getProjectDescription(){
        return ProjectDescription;
    }
    
    public Date getStartDate(){
        return StartDate;
    }
    
    public Date getEndDate(){
        return EndDate;
    }

    public ProjectStatus getEnumprojeto() {
        
        return enumprojeto;
    }
    
    
   
    
    
    //modificadores
    public void setProjectTitle(String ProjectTitle) {
        this.ProjectTitle = ProjectTitle;
    }

    public void setProjectDescription(String ProjectDescription) {
        this.ProjectDescription = ProjectDescription;
    }

    public void setStartDate(Date StartDate) {
        this.StartDate = StartDate;
    }

    public void setEndDate(Date EndDate) {
        this.EndDate = EndDate;
    }
   

    public void setEnumprojeto(ProjectStatus enumprojeto) {
        this.enumprojeto = enumprojeto;
    }
    
    
    
    
    //Outros Métodos
    @Override
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        
        s.append("Características do projeto:");
        s.append("\nOwner: " + ProjectOwner);
        s.append("\nTitulo: " + ProjectTitle);
        s.append("\nDescrição: " + ProjectDescription);
        s.append("\nData de início: " + StartDate); 
        s.append("\nData de Fim: " + EndDate); 
        s.append("\nEstado: " + enumprojeto);
        
        
        
        return s.toString();
    }
}
