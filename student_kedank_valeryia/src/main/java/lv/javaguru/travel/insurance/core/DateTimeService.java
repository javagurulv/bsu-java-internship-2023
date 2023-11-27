package lv.javaguru.travel.insurance.core;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class DateTimeService {
    long getDaysBetween(Date date1, Date date2){
        long temp = date2.getTime() - date1.getTime();
        return TimeUnit.DAYS.convert(temp, TimeUnit.MILLISECONDS);
    }
}
