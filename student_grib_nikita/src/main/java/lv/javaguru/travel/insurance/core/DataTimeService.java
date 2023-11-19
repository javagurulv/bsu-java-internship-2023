package lv.javaguru.travel.insurance.core;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;
@Component
public class DataTimeService {
    public static long getDaysBetween(Date dateFrom, Date dateTo){
        long daysDiff = dateTo.getTime() - dateFrom.getTime();
        return TimeUnit.DAYS.convert(daysDiff, TimeUnit.MILLISECONDS);
    }
}
