package Frontend;

import Backend.Sistema;
import java.awt.Dimension;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JDialog;
import org.jdesktop.swingx.JXDatePicker;

public class Validacoes {
 
    static DateFormat dateFormat = new SimpleDateFormat("E dd/MM/yyyy");

    public static boolean validaEmail(String email) {

        Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

   
    public static void FormatCalendar(JXDatePicker cal) {
        cal.getEditor().setEditable(false);
        cal.setFormats(new String[]{"E dd/MM/yyyy"});
    }

    public static String FormatDate(Date date) {
        return dateFormat.format(date);
    }

    public static void SetDialogProperties(JDialog dialog, Sistema s, int width, int height, String menuText) {
        dialog.setSize(new Dimension(width, height));
        dialog.setResizable(false);
        dialog.setTitle(menuText + " - " + s.getCurrentUser().getUserName() + " (" + s.getCurrentUser().getEmail() + ")");

    }

    public static void print(Object s) {
        System.out.println(s);
    }
}
