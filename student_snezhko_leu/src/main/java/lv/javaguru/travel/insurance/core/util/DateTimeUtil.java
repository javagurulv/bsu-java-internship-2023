package lv.javaguru.travel.insurance.core.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateTimeUtil {
    public static long findDiffBetweenTwoDate(Date date1, Date date2) {
        return TimeUnit.DAYS.convert(date1.getTime() - date2.getTime(), TimeUnit.MILLISECONDS);//date1.getDay() - date2.getDay();
    }
    public static int findAge(Date d1, Date d2) {
        return Math.abs(d1.getYear() - d2.getYear());
    }
}
