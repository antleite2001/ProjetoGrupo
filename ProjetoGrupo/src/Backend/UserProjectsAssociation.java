/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

public class UserProjectsAssociation {

    private int UserProjectAssociationId, UserId, ProjectId, ProjectOwner;

//construtor
    public UserProjectsAssociation(int UserId, int ProjectId, int UserProjectAssociationId, int ProjectOwner) {

        this.UserProjectAssociationId = UserProjectAssociationId;
        this.UserId = UserId;
        this.ProjectId = ProjectId;
        this.ProjectOwner = ProjectOwner;
    }

    public int getUserProjectAssociationId() {
        return UserProjectAssociationId;
    }

    public int getProjectOwner() {
        return ProjectOwner;
    }

    public int getUserId() {
        return UserId;
    }

    public int getProjectId() {
        return ProjectId;
    }
}
