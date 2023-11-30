package lv.javaguru.travel.insurance.core;

import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
class DateTimeService {

    long getDaysBetween(Date date1, Date date2) {
        return TimeUnit.DAYS.convert(date2.getTime() - date1.getTime(), TimeUnit.MILLISECONDS);
    }

}
