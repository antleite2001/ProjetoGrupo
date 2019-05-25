package Backend;

import java.util.ArrayList;
import java.util.Date;
  

public class ToBeDeleted1
{
  
    int nextprojectid = 0;
    
    private ArrayList<Project> Projetos;
    
    //construtor
    public ToBeDeleted1 (){
        this.Projetos = new ArrayList<Project>();
    }
    
    //Seletores
    public ArrayList<Project> getListaProjetos(){
        return this.Projetos;
    }
    
    //modificadores
    public void addProjeto(int owner, String titulo, String descricao, Date dataInicio, Date dataFim){
        //to show getNextprojectid 
        Project p = new Project(owner, titulo, descricao, dataInicio, dataFim, getNextprojectid());
        System.out.println ("New Project ID: " + p.getProjectId());
        Projetos.add(p);
        
    }
    
    public void removeProjeto(Project projeto){
        Projetos.remove(projeto);
    }

    public int getNextprojectid() {
        return ++nextprojectid;
    }
    
    public boolean ProjectExists(String projectTitle)
    {
        for(Project p : Projetos){
            if(p.getProjectTitle().replaceAll(" ", "").toUpperCase().equals(projectTitle.replaceAll(" ", "").toUpperCase()))
            {
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
        
        for(Project projeto : Projetos){
            s.append(projeto.toString());
        }
        
        return s.toString();
    }
    
}
