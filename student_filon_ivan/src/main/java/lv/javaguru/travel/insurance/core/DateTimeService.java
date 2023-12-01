package lv.javaguru.travel.insurance.core;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class DateTimeService {
    public long getDaysBetween(Date date1, Date date2){
        long difference = date2.getTime() - date1.getTime();
        return TimeUnit.MILLISECONDS.toDays(difference);
    }
}
