package lv.javaguru.travel.insurance.core;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateTimeService {
    public Date getCurrentDateTime() {
        ZoneId zone = ZoneId.of("Europe/Riga");
        return Date.from(ZonedDateTime.now(zone).toInstant());
    }
    BigDecimal calculateDiffBetweenDays(Date date1, Date date2) {
        return BigDecimal.valueOf(TimeUnit.MILLISECONDS.toDays(date2.getTime() - date1.getTime()));
    }
}
