package Frontend;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validacoes {

    public static boolean validaEmail(String email) {

        Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
    
    static DateFormat dateFormat = new SimpleDateFormat("E dd/MM/yyyy");

    public static String FormatDate(Date date) {
        return dateFormat.format(date);
    }

}
