package Frontend;

import Backend.Sistema;
import java.io.*;

public class Main  {

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        Sistema s = new Sistema();

        
        Login login = null;

        //Check if a valid registered user was selected
        do {
            login = new Login(null, true, s);
            login.setLocationRelativeTo(null);
            login.setVisible(true);
            if (s.getCurrentUser() != null) {
                DashBoard dashboard = new DashBoard(null, true, s);
                dashboard.setLocationRelativeTo(null);
                dashboard.setVisible(true);
                
            }
        } while (s.getCurrentUser() != null);
        
        

    }
    

}
