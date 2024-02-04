package lv.javaguru.travel.insurance.core.util;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class DateTimeUtil {

    public long getDifferenceInDays(Date date1, Date date2) {
        long difference = date2.getTime() - date1.getTime();
        return TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
    }

    public Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public Date getCurrentDateTime() {
        ZoneId zone = ZoneId.of("Europe/Minsk");
        ZonedDateTime zonedDateTime = ZonedDateTime.now(zone);
        return Date.from(zonedDateTime.toInstant());
    }
}
