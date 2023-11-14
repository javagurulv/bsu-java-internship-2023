package lv.javaguru.travel.insurance.core;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CalculatorDate {
    BigDecimal calculateDiffBetweenDays(Date date1, Date date2) {
        return BigDecimal.valueOf(TimeUnit.MILLISECONDS.toDays(date2.getTime() - date1.getTime()));
    }
}
