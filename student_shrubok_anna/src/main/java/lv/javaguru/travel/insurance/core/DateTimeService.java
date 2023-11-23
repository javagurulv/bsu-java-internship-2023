package lv.javaguru.travel.insurance.core;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

@Component
public class DateTimeService {

    long getDaysBetween(Date date1, Date date2)
    {
        return TimeUnit.DAYS.convert(date1.getTime()-date2.getTime(),TimeUnit.MILLISECONDS);
    }

}
