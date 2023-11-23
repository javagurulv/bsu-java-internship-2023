package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PremiumUnderwritingService {

    @Autowired private DateTimeService dateTimeService;

    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        return new BigDecimal(dateTimeService.getDaysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo()));
    }
}
