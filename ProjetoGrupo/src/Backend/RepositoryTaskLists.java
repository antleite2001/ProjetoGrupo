/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author VarianInstaller
 */
public class RepositoryTaskLists implements Serializable{
    int nextTaskListid = 0;  
    private ArrayList<TaskList> ListaDeTarefas;
    
    
    //Construtor
    public RepositoryTaskLists(){        
        this.ListaDeTarefas = new ArrayList< >();
    }
    public ArrayList <TaskList> getTaskListsbyOwner(int ownerid){
        ArrayList <TaskList> lt = new ArrayList <>();
      
        for (TaskList t : ListaDeTarefas)
        {
            if (t.getOwnerId()==ownerid)
            {
                lt.add(t);
            }
        }
        return lt;
    }
   
    public void updatebytasklistid (int tasklistid, String newtitle, String newdescription)
    {
        for (TaskList tl : ListaDeTarefas)
        {
            if (tl.getTaskListId()==tasklistid)
            {
                tl.setTitle(newtitle);
                tl.setDescription(newdescription);
                return;
            }
        }
    }

    public TaskList getTaskListByTaskListId(int TaskListId)
   {
    for(TaskList tl:ListaDeTarefas)  
    {
        if(tl.getTaskListId()==TaskListId)
        {
            return tl;
        }
    }
    return null;
   }
   
    public int addTaskList(int ProjectId, String Title, String Description, int CreatedBy  ){
        TaskList tl = new TaskList( ProjectId,  Title, Description, CreatedBy, getNextTaskListid() );
        //System.out.println ("New ListaDeTarefa ID: " + tl.getTaskListId());
        ListaDeTarefas.add(tl);
        return tl.getTaskListId();
    }

    public ArrayList<TaskList> getListaDeTarefas() {
        return ListaDeTarefas;
    }

     public void removeListaDeTarefa(TaskList listadetarefa){
        ListaDeTarefas.remove(listadetarefa);
    }
    
    public int getNextTaskListid() {
        return ++nextTaskListid;
    }
    @Override
    public String toString(){
        
        StringBuilder s = new StringBuilder();
        s.append("Lista de Tarefas:\n");
        
        for(TaskList listadetarefa : ListaDeTarefas){
            s.append(listadetarefa.toString());
        }
        
        return s.toString();
    }
}
