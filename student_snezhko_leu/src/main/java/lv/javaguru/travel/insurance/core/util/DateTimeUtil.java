package lv.javaguru.travel.insurance.core.util;

import java.math.BigDecimal;
import java.util.Date;

public class DateTimeUtil {
    public static BigDecimal findDiffBetweenTwoDate(Date date1, Date date2) {
        return BigDecimal.valueOf(date1.getDay() - date2.getDay());
    }
}
