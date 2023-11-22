package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
class TravelPremiumUnderwritingImpl implements TravelPremiumUnderwriting{

    private DateTimeUtil calculateDate = new DateTimeUtil();
    @Override
    public BigDecimal calculateAgreementPrice(TravelCalculatePremiumRequest request){
        return calculateDate.calculateDiffBetweenDays(request.getAgreementDateFrom(), request.getAgreementDateTo());
    }
}
