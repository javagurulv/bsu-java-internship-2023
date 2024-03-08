package lv.javaguru.travel.insurance.core.util;

import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class DateTimeUtil {

    public long getDaysBetween(Date date1, Date date2) {
        return TimeUnit.DAYS.convert(date2.getTime() - date1.getTime(), TimeUnit.MILLISECONDS);
    }

    public Date getTodaysDateAndTime() {
        ZoneId zone_id = ZoneId.of("Europe/Minsk");
        ZonedDateTime zonedDateTime = ZonedDateTime.now(zone_id);
        return Date.from(zonedDateTime.toInstant());
    }

}
