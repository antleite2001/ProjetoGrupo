package Backend;

import java.util.ArrayList;
import javax.swing.*;

public class ToBeDeleted2 
{
    private ArrayList<User> Utilizadores;

    int nextuserid=0;
    //Construtor
    public ToBeDeleted2 (){
        this.Utilizadores = new ArrayList<User>();
    }
    public boolean UserExists(String UserEmail)
    {
        for(User u : Utilizadores){
            if(u.getEmail().toUpperCase().equals(UserEmail.toUpperCase()))
            {
                return true;
            }
        }
       return false; 
    }
    //modificadores
    //adicionar utilizador caso este ainda nao exista na lista de utilizadores
    public boolean addUtilizador(String nome, String email, String password){                      
        User u = new User(nome, email, password, getNextuserid());
        System.out.println ("New User : " + u.getUserName() + "  " +u .getEmail()+"  "+ u.getUserId());
        Utilizadores.add(u);       
        return true;
    }
    
    public boolean removeUtilizador(String email, ArrayList<Task> Tarefas, ArrayList<Project> Projetos, User u1 ){
        //verifica se o utilizador existe
        boolean utilizadorexiste=false; 
        
        
        //utilizadorexiste=true e i contem o indice do utilizador na lista
        utilizadorexiste=false;
        String s="";
        
      for (Project p : Projetos)          
      {
         if(p.getProjectOwner()==u1.getUserId())
         {
             s=s+p.getProjectTitle()+" ";
             utilizadorexiste=true;
         }
      }
      if (utilizadorexiste)
      {
      JOptionPane.showMessageDialog(null, "O utilizador "+email+" não pode ser removido pois é owner do(s) projeto(s) "+s);
      return false;
      }
      
      //verificar se o utilizador está com alguma tarefa atribuida
      utilizadorexiste=false;
     s="";
        
      for (Task t : Tarefas)          
      {
         if(t.getAssignedTo()==u1.getUserId())
         {
             s=s+t.getTitle()+" ";
             utilizadorexiste=true;
         }
      }
      if (utilizadorexiste)
      {
      JOptionPane.showMessageDialog(null, "O utilizador "+email+" não pode ser removido pois tem tarefa(s) atribuida(s):  "+s);
      return false;
      }
      
      
      
       Utilizadores.remove(u1);
        return true;
    }
    // modifica o utilizador (nome e/ou password e/ou email)
    // verifica se o email que se pretende alterar ainda não está registado
    public boolean changeuser(User u1, String newname, String newpassword, String newemail)
            {
                
                if (!u1.getEmail().toUpperCase().equals(newemail.toUpperCase()))
                {
                    for (User u : Utilizadores)
        {
            if (u.getEmail().toUpperCase().equals(newemail.toUpperCase()))
            {
                JOptionPane.showMessageDialog(null, "O utilizador "+newemail+" ja está registado. ");
                return false;
            }
                }
                  u1.setUserName(newname);
                u1.setUserPassword(newpassword);  
                u1.setUserEmail(newemail);
            }
                else
                {
                    u1.setUserName(newname);
                    u1.setUserPassword(newpassword); 
                }
                return true;
            }
    
    public int getNextuserid() {
        
        return ++nextuserid;
    }

    
    
    
    //Outros métodos
    public ArrayList<User> getUtilizadores(){
        return this.Utilizadores;
    }
    
    
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        
        s.append("Utilizadores\n");
        
        for(User utilizador : Utilizadores)
        {
            s.append(utilizador.toString());
        }
        
        return s.toString();
    }

}

