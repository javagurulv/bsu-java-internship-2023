package lv.javaguru.travel.insurance.core;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;


@Component
public class DateTimeService {
    long getDaysBetween(Date dateFrom, Date dateTo) {
        return TimeUnit.DAYS.convert(dateTo.getTime() - dateFrom.getTime(), TimeUnit.MILLISECONDS);
    }
}
