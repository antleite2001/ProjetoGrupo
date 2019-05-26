package Backend;

import java.util.ArrayList;
import javax.swing.*;

public class RepositoryUsers {

    private ArrayList<User> Users;

    int nextuserid = 0;

    //Construtor
    public RepositoryUsers() {
        this.Users = new ArrayList<User>();
    }

    public boolean UserExists(String UserEmail) {
        if (UserEmail.isBlank()) {
            return false;
        } else {
            for (User u : Users) {
                if (u.getEmail().toUpperCase().equals(UserEmail.toUpperCase())) {
                    return true;
                }
            }
        }
        return false;
    }

    //modificadores
    //adicionar utilizador caso este ainda nao exista na lista de utilizadores
    public int addUser(String nome, String email, String password) {
        User u = new User(nome, email, password, getNextuserid());
        //System.out.println("New User : " + u.getUserName() + "  " + u.getEmail() + "  " + u.getUserId());
        Users.add(u);
        return u.getUserId();
    }

    public void changeUserNameById(int userId, String newName) {
        for (User u : Users) {
            if (u.getUserId() == userId) {
                u.setUserName(newName);
                //System.out.println("New User Name: " + u.getUserName() + "  " + u.getEmail() + "  " + u.getUserId());
                return;
            }
        }
    }

    public void changeUserPasswordById(int userId, String newPassword) {
        for (User u : Users) {
            if (u.getUserId() == userId) {
                u.setUserPassword(newPassword);
                //System.out.println("New User Password: " + u.getUserName() + "  " + u.getEmail() + "  " + u.getUserPassword() + " " + u.getUserId());
                return;
            }
        }
    }
    public void changeUserEmailById(int userId, String newEmail) {
        for (User u : Users) {
            if (u.getUserId() == userId) {
                u.setUserEmail(newEmail);
                //System.out.println("New User Email: " + u.getUserName() + "  " + u.getEmail() + "  " + u.getUserId());
                return;
            }
        }
    }
    
    
    public User getUserById(int userid) {
        for (User u : Users) {
            if (u.getUserId() == userid) {
                return u;
            }
        }
        return null;
    }

    public boolean removeUser(int UserId, ArrayList<Task> Tasks, ArrayList<TaskList> TaskLists, ArrayList<Project> Projetos) {
        //Get user that has UserId
        User user = null;

        for (User u : Users) {
            if (u.getUserId() == UserId) {
                user = u;
                break;
            }
        }

        boolean userExists = false; //Check if userid is owner of some project or is assigned to some task
        String s = ""; //collect projects Title owned by user

        //Check if user is owner of some project
        for (Project p : Projetos) {
            if (p.getProjectOwner() == UserId) {
                s = s + p.getProjectTitle() + " ";
                userExists = true;
            }
        }
        if (userExists) {
            JOptionPane.showMessageDialog(null, "O utilizador " + user.getUserName() + " (" + user.getEmail() + ") não pode ser removido pois é owner do(s) projeto(s) " + s);
            return false;
        }

        //Check if owner has some tasl assigned
        userExists = false;
        s = "";

        for (Task t : Tasks) {
            if (t.getAssignedTo() == UserId) {
                s = s + t.getTitle() + " ";
                userExists = true;

            }
        }
        if (userExists) {
            JOptionPane.showMessageDialog(null, "O utilizador " + user.getUserName() + " (" + user.getEmail() + ") não pode ser removido pois tem tarefa(s) atribuida(s):  " + s);
            return false;
        }

        //Check if owner created some TaskList
        userExists = false;
        s = "";

        for (TaskList tl : TaskLists) {
            if (tl.getCreatedBy() == UserId) {
                s = s + tl.getTitle() + " ";
                userExists = true;

            }
        }
        if (userExists) {
            JOptionPane.showMessageDialog(null, "O utilizador " + user.getUserName() + " (" + user.getEmail() + ") não pode ser removido pois criou as Listas de Tarefas:  " + s);
            return false;
        }
        Users.remove(user);
        return true;
    }

    // modifica o utilizador (nome e/ou password e/ou email)
    // verifica se o email que se pretende alterar ainda não está registado
    public boolean changeuser(User u1, String newname, String newpassword, String newemail) {

        if (!u1.getEmail().toUpperCase().equals(newemail.toUpperCase())) {
            for (User u : Users) {
                if (u.getEmail().toUpperCase().equals(newemail.toUpperCase())) {
                    JOptionPane.showMessageDialog(null, "O utilizador " + newemail + " ja está registado. ");
                    return false;
                }
            }
            u1.setUserName(newname);
            u1.setUserPassword(newpassword);
            u1.setUserEmail(newemail);
        } else {
            u1.setUserName(newname);
            u1.setUserPassword(newpassword);
        }
        return true;
    }

    public int getNextuserid() {

        return ++nextuserid;
    }

    //Outros métodos
    public ArrayList<User> getUsers() {
        return this.Users;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        s.append("Utilizadores\n");

        for (User utilizador : Users) {
            s.append(utilizador.toString());
        }

        return s.toString();
    }

}
