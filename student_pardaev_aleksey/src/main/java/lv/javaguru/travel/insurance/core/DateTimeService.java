package lv.javaguru.travel.insurance.core;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.*;
@Component
class DateTimeService {
    BigDecimal getDaysBetweenDates(Date dateFrom, Date dateTo){
        return BigDecimal.valueOf(ChronoUnit.DAYS.between(dateFrom.toInstant(), dateTo.toInstant()));
    }
}
