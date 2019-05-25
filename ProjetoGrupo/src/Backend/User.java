package Backend;

import java.util.ArrayList;

public class User {

    private String UserName, UserEmail, UserPassword;
    private int UserId;

    //Construtor
    public User(String userName, String userEmail, String userPassword, int userId) {
        this.UserName = userName;
        this.UserEmail = userEmail;
        this.UserPassword = userPassword;
        this.UserId = userId;

    }

    //Seletores
    public String getUserName() {
        return UserName;
    }

    public String getEmail() {
        return UserEmail;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public int getUserId() {
        return UserId;
    }

    //modificadores
    public void setUserName(String userName) {
        this.UserName = userName;
    }

    public void setUserEmail(String userEmail) {
        this.UserEmail = userEmail;
    }

    public void setUserPassword(String userPassword) {
        this.UserPassword = userPassword;
    }

    //Outros métodos
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        s.append("Utilizador: ");
        s.append("\nNome: " + UserName);
        s.append("\nemail: " + UserEmail);
        s.append("\nPassword: " + UserPassword);
        s.append("\nuserid: " + UserId);

        return s.toString();
    }
}
