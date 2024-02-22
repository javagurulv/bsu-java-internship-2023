package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.util.DateTimeService;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class TravelPremiumUnderwritingImpl implements TravelPremiumUnderwriting{

    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        return new BigDecimal(DateTimeService.daysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo()));
    }
}
