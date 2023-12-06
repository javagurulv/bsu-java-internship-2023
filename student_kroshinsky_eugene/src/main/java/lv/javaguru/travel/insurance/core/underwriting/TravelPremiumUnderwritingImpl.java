package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.utils.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class TravelPremiumUnderwritingImpl implements TravelPremiumUnderwriting {
    @Autowired private DateTimeUtil dateTimeDifference;
    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request){
        return dateTimeDifference.calculateDateDifference(request.getAgreementDateFrom(), request.getAgreementDateTo());
    }
}
