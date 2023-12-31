package lv.javaguru.travel.insurance.core.utils;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Date;

@Component
public class DateTimeUtil {
    public BigDecimal calculateDateDifference(Date dateFrom, Date dateTo){
        return new BigDecimal(Duration.between(dateFrom.toInstant(), dateTo.toInstant()).toDays());
    }
}
