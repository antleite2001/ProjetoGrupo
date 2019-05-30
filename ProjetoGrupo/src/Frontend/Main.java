package Frontend;

import Backend.Sistema;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;

public class Main {

    static String SERIALIZEFULLPATHFILE = "Sistema.ser";
    static Login login = null;
    static boolean closeApp = false;

    public static void main(String args[]) {

        Sistema s = DesserializeSistema(SERIALIZEFULLPATHFILE);

        login = new Login(null, true, s);

        //Catch Closing of Login to Serialize sistema object
        login.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {                
                SerializeSistema(SERIALIZEFULLPATHFILE, s);
                closeApp = true;
                System.out.println("jdialog window closing event received");
            }
        });

        //Set Nimbus Look and Feel
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

        //Check if a valid registered user was selected
        while(!closeApp) {

            login.setLocationRelativeTo(null);
            login.setVisible(true);

            if ( !closeApp) {
                DashBoard dashboard = new DashBoard(null, true, s);
                dashboard.setLocationRelativeTo(null);
                dashboard.setVisible(true);
            }
        }  
        
        System.out.println("Exiting Application");
    }//Main

    static Sistema DesserializeSistema(String ficheiroOrigem) {
        Sistema s = null;
        File f = new File(ficheiroOrigem);
        if (!f.exists()) {
            s = new Sistema();
            return s;
        }

        try {

            FileInputStream fileStream = new FileInputStream(ficheiroOrigem);

            ObjectInputStream os = new ObjectInputStream(fileStream);

            s = (Sistema) os.readObject();

            os.close();
            return s;
        } catch (Exception i) {

            System.out.println("ERROR DesserializeSistema " + i);
            JOptionPane.showMessageDialog(null, i.getMessage(), "DesserializeSistema", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    static boolean SerializeSistema(String ficheiroDestino, Sistema s) {

        try {
            FileOutputStream fileOut = new FileOutputStream(ficheiroDestino);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(s);
            out.close();
            fileOut.close();
            return true;
        } catch (Exception i) {
            System.out.println("ERROR SerializeSistema " + i);
            JOptionPane.showMessageDialog(null, i.getMessage(), "SerializeSistema", JOptionPane.ERROR_MESSAGE);
        }

        return false;
    }

}
