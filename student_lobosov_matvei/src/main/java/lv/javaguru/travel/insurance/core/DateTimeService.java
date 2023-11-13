package lv.javaguru.travel.insurance.core;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.math.BigDecimal;
public class DateTimeService {
    public static BigDecimal getDaysBetween(Date dateFrom, Date dateTo){
        long temp = dateFrom.getTime() - dateTo.getTime();
        return new BigDecimal(TimeUnit.DAYS.convert(temp, TimeUnit.MILLISECONDS));
    }
}
