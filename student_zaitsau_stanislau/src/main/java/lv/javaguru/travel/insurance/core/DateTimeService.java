package lv.javaguru.travel.insurance.core;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateTimeService {
    private static final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public static BigDecimal getDaysDifference(Date start, Date end) {
        long diffInMilliseconds = Math.abs(end.getTime() - start.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMilliseconds, TimeUnit.MILLISECONDS);
        return new BigDecimal(diff);
    }

    public static DateFormat getDateFormat() {
        return dateFormat;
    }
}
