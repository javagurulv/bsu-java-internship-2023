package lv.javaguru.travel.insurance.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TravelPremiumUnderwriting {
    @Autowired
    private DateTimeService timeService;

    BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        return BigDecimal.valueOf(timeService.getDaysBetween(request.getAgreementDateFrom(),
                request.getAgreementDateTo()));
    }
}
