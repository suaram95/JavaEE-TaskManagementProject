package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static final SimpleDateFormat SDF=new SimpleDateFormat("yyyy-MM-dd");

    public static String getStringFromDate(Date date){
        return SDF.format(date);
    }

    public static Date getDateFromString(String date){
        try {
            return SDF.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
