package lv.javaguru.travel.insurance.rest;

import java.math.BigDecimal;
import java.util.Date;

public class DateService {
    public static BigDecimal findDiffBetweenTwoDate(Date date1, Date date2) {
        return BigDecimal.valueOf(date1.getTime() - date2.getTime());
    }
}
