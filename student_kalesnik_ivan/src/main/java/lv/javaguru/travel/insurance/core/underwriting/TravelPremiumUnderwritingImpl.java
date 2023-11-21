package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.validation.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public
class TravelPremiumUnderwritingImpl implements TravelPremiumUnderwriting {

    @Autowired private DateTimeUtil dateTimeService;

    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        return new BigDecimal(dateTimeService.getDaysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo()));
    }

}
