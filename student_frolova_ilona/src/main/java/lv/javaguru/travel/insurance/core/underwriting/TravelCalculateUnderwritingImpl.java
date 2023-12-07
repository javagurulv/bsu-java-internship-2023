package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class TravelCalculateUnderwritingImpl implements TravelCalculateUnderwriting {

    @Autowired
    private DateTimeUtil dateTimeUtil;

    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        return dateTimeUtil.getDifferenceInDays(request.getAgreementDateFrom(), request.getAgreementDateTo());
    }
}
