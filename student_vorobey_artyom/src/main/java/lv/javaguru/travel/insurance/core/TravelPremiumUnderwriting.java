package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.core.util.DateTimeService;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TravelPremiumUnderwriting {

    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        return new BigDecimal(DateTimeService.daysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo()));
    }
}
