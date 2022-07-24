package utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;

public class DateUtils {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

    public static String currentDate(){
        return dateFormat.format(new Date());
    }

    public static String formatStringToDate(String strDate){
        String strDateFormat = strDate.split("T")[0];
        return strDateFormat.replace("-", StringUtils.EMPTY);
    }
}
