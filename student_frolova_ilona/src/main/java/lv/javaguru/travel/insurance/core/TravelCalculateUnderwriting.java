package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TravelCalculateUnderwriting {

    @Autowired
    private DateTimeUtil dateTimeUtil;

    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        return dateTimeUtil.getDifferenceInDays(request.getAgreementDateFrom(), request.getAgreementDateTo());
    }
}
