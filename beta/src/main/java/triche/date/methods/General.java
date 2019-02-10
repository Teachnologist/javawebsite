package triche.date.methods;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class General {

    final private String date_format = "yyyy-m-d'T'HH:mm:ss'Z'";

    public Long convertDaystoMilliSeconds(Integer days){
        Long milliseconds = TimeUnit.DAYS.toMillis(days);
        return milliseconds;
    }

    public Long getDateAsMilliseconds(String updated_at) {

        String DateStr=updated_at;
        SimpleDateFormat sdf = new SimpleDateFormat(date_format);
        Long milliseconds = null;
        try {
            Date d = sdf.parse(DateStr);
            Long i = d.getTime();
            milliseconds = i;
        }catch(Exception e){
            System.out.print(e);
        }
        return milliseconds;
    }

    public Long convertMillisecondsToSeconds(Long milliseconds){
        Long seconds = milliseconds/1000;
        return seconds;
    }

    public Long subtractTimeFromDateString(String date, Integer days){
        Long datems = getDateAsMilliseconds(date);
        Long daysms = convertDaystoMilliSeconds(days);
        return datems - daysms;
    }

    public Long subtractMillisecondsFromDate(Date date, Long ms){
        SimpleDateFormat format = new SimpleDateFormat(date_format);
        System.out.println("SHOULD BE FORMATTED STRING "+format.format(date));
        Long datems = getDateAsMilliseconds(format.format(date));
        return datems - ms;
    }

    public Long subtractMillisecondsFromDateString(String date, Long ms){
        Long datems = getDateAsMilliseconds(date);
        return datems - ms;
    }

    public Long subtractMilliseconds(Long ms1, Long ms2){
        return ms1-ms2;
    }

    public Boolean dateIsGreater(Long dateinms1, Long dateinms2){
        Boolean truthy = false;

        if(dateinms1 > dateinms2){
            truthy = true;
        }

        return truthy;
    }
}
