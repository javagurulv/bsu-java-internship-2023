package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.utils.DateTimeService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@Component
public class TravelCalculatePremiumServiceUnderwriting {

    public BigDecimal calculatePremium(Date dateFrom, Date dateTo) {
        return DateTimeService.getDaysBetween(dateFrom, dateTo);
    }

}
