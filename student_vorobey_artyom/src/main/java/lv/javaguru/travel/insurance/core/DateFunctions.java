package lv.javaguru.travel.insurance.core;

import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class DateFunctions {
    public static long daysBetween (Date firstDate, Date secondDate) {
        if (firstDate != null && secondDate != null)
            return ChronoUnit.DAYS.between(secondDate.toInstant(), firstDate.toInstant());
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
