package lv.javaguru.travel.insurance.utils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class DateTimeService {

    private static LocalDate dateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static BigDecimal getDaysBetween(Date dateFrom, Date dateTo) {
        LocalDate localDateFrom = dateToLocalDate(dateFrom);
        LocalDate localDateTo = dateToLocalDate(dateTo);
        Period period = Period.between(localDateFrom, localDateTo);
        return new BigDecimal(period.getDays());
    }
}
