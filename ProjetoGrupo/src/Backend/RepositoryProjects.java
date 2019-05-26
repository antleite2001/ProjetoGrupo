package Backend;

import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
  

public class RepositoryProjects
{
  
    int nextProjectId = 0;
    
    private ArrayList<Project> Projects;
    
    //construtor
    public RepositoryProjects (){
        this.Projects = new ArrayList<Project>();
    }
    
    //Seletores
    public ArrayList<Project> getProjects(){
        return this.Projects;
    }
    
    //Given an Owner, return a list of projects owned by owner
    public ArrayList<Project> getProjectsByOwner(int Owner)
    {
        ArrayList<Project> projectsByOwner = new ArrayList<Project>();   
        for(Project p : Projects){
            if(p.getProjectOwner()== Owner)
            {
                 projectsByOwner.add(p);
            }
        }
        return projectsByOwner;
    }
    
    //Given an ProjectId, return Project
    public  Project getProjectsById(int ProjectId)
    {          
        for(Project project : Projects){
            if(project.getProjectId()== ProjectId)
            {
                 return project;
            }
        }
        return null;
    }
    
    
    
    
    
    
    //modificadores
         
    public int addProject(int owner, String titulo, String descricao, Date dataInicio, Date dataFim, RepositoryUserProjectsAssociation r){
        //to show getNextprojectid 
        Project p = new Project(owner, titulo, descricao, dataInicio, dataFim, getNextProjectId());
        
        //insert owner into  UserProjectsAssociation table
        r.addUserProjectsAssociation(owner, p.getProjectId(), owner);

        
        //System.out.println ("New Project ID: " + p.getProjectId()+" owner "+owner+ " titulo "+titulo+ " descricao "+descricao +" dataInicio "+dataInicio+ " dataFim" + dataFim);
        Projects.add(p);
        return p.getProjectId(); //return ProjectId
    }
    
    public void removeProjeto(Project projeto){
        Projects.remove(projeto);
    }

    public int getNextProjectId() {
        return ++nextProjectId;
    }
    
    public boolean ProjectExists(String projectTitle)
    {
        for(Project p : Projects){
            if(p.getProjectTitle().replaceAll(" ", "").toUpperCase().equals(projectTitle.replaceAll(" ", "").toUpperCase()))
            {
                return true;
            }
        }
       return false; 
    }
    
    public boolean changeProjectById(int projectId, String newTitle, String newDescription, Date newStartDate, Date newEndDate){
        for(Project p : Projects)
        {
            if(p.getProjectId() == projectId)
            {
                 
                p.setProjectTitle(newTitle);
                p.setProjectDescription(newDescription);
                p.setStartDate(newStartDate);
                p.setEndDate(newEndDate);
                return true;
            }
        }
        return false;
        
        
         
         
    }
    
    //Outros métodos
    
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        
        s.append("Lista Projetos:\n");
        
        for(Project projeto : Projects){
            s.append(projeto.toString());
        }
        
        return s.toString();
    }
    
}
