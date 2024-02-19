package lv.javaguru.travel.insurance.core.util;

import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class DateTimeService {
    public static long daysBetween (Date firstDate, Date secondDate) {
        if (firstDate != null && secondDate != null)
            return ChronoUnit.DAYS.between(firstDate.toInstant(), secondDate.toInstant());
        else
            return 0L;
    }

    public static Date createDateFromString (String stringDate) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            return dateFormat.parse(stringDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
