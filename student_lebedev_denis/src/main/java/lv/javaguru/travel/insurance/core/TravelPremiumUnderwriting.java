package lv.javaguru.travel.insurance.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TravelPremiumUnderwriting {
    @Autowired
    private DateTimeService dateTimeService;

    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        long daysBetween = dateTimeService.getDays(request.getAgreementDateFrom(), request.getAgreementDateTo());
        return new BigDecimal(daysBetween);
    }
}
