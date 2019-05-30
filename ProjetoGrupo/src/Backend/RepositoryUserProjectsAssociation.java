/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import java.io.Serializable;
import java.util.ArrayList;


public class RepositoryUserProjectsAssociation implements Serializable{
   int  nextUserProjectsAssociation = 0;

    
    
    private ArrayList<UserProjectsAssociation> UserProjectsAssociations;
    
    //construtor
    public RepositoryUserProjectsAssociation (){
        this.UserProjectsAssociations = new ArrayList<>();
    }

    public ArrayList<UserProjectsAssociation> getUserProjectsAssociations() {
        return UserProjectsAssociations;
    }
    
    //UserId, ProjectId, ProjectOwner
     public int addUserProjectsAssociation(int userid, int projectid, int projectOwner){
         UserProjectsAssociation p = new UserProjectsAssociation (userid, projectid, getNextUserProjectsAssociation(), projectOwner);
         UserProjectsAssociations.add(p);
        //System.out.println("NEW ASSOCIATION userid "+userid+ " projectid "+ projectid+" projectOwner "+ projectOwner + " p.getUserProjectAssociationId() " + p.getUserProjectAssociationId());
        return p.getProjectId();
    }
 
//Given an UserId, return a list of projects to which user is associated to
    public ArrayList<UserProjectsAssociation> getUserProjectsAssociation(int UserId)
    {
        ArrayList<UserProjectsAssociation> ProjectsAssociatedToUser = new ArrayList< >();   
        for(UserProjectsAssociation p : UserProjectsAssociations){
            if(p.getUserId()== UserId)
            {
                 ProjectsAssociatedToUser.add(p);
            }
        }
        return ProjectsAssociatedToUser;
    }
    
    public int getNextUserProjectsAssociation() {
        return ++nextUserProjectsAssociation;
    }
    
    public boolean exists(int userid, int projectid)
    {
        for (UserProjectsAssociation p : UserProjectsAssociations)
        {
        if (p.getUserId()==userid && p.getProjectId()==projectid)
        {
            return true;
        }
        }
        return false;
    }
}
