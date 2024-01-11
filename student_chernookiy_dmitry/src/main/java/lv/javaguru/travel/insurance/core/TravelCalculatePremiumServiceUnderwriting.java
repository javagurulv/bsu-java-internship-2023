package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class TravelCalculatePremiumServiceUnderwriting {

    public BigDecimal calculatePremium(Date dateFrom, Date dateTo) {
        LocalDate localDateFrom = dateToLocalDate(dateFrom);
        LocalDate localDateTo = dateToLocalDate(dateTo);
        Period period = Period.between(localDateFrom, localDateTo);
        return new BigDecimal(period.getDays());
    }

    private static LocalDate dateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
