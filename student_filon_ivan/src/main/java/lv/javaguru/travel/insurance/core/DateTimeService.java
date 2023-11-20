package lv.javaguru.travel.insurance.core;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class DateTimeService {
    public static long getDaysBetween(Date date1, Date date2){
        long difference = date1.getTime() - date2.getTime();
        return TimeUnit.MILLISECONDS.toDays(difference);
    }
}
