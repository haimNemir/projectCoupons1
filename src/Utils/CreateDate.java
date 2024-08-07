package Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CreateDate {

    /**
     * get int of: year, month, day.
     * Return Date with format "yyyy-MM-dd"
     */
    public static Date getDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day); // שנה, חודש (יולי), יום
        return calendar.getTime();
    }

    /**
     * @param stringDate get String with the pattern - "yyyy-MM-dd".
     * @return object Date with the entered date.
     */
    public static Date getDateFromString(String stringDate) {
        // יצירת אובייקט SimpleDateFormat עם הפורמט המתאים
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(stringDate);// המרת המחרוזת לאובייקט Date
            System.out.println();
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}