package lv.javaguru.travel.insurance.core;

import java.time.temporal.ChronoUnit;
import java.util.Date;

public class datesFunctions {
    public static long daysBetween (Date firstDate, Date secondDate) {
        return ChronoUnit.DAYS.between(firstDate.toInstant(), secondDate.toInstant());
    }
}
