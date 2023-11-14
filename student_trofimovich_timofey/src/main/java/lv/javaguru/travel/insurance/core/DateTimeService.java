package lv.javaguru.travel.insurance.core;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class DateTimeService {
    static long getDaysBetween(Date dateFrom, Date dateTo) {
        return TimeUnit.DAYS.convert(dateTo.getTime() - dateFrom.getTime(), TimeUnit.MILLISECONDS);
    }
}
