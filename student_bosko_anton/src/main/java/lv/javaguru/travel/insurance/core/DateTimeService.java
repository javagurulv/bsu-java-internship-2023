package lv.javaguru.travel.insurance.core;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

@Component
public class DateTimeService {

    public Date createDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public long getDaysBetween(Date date1, Date date2) {
        long diff = date2.getTime() - date1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
}
