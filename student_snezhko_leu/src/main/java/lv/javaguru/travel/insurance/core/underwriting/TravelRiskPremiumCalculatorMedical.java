package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static lv.javaguru.travel.insurance.core.util.DateTimeUtil.findDiffBetweenTwoDate;

@Component
public class TravelRiskPremiumCalculatorMedical implements TravelRiskPremiumCalculator{
    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        return findDiffBetweenTwoDate(request.getAgreementDateTo(), request.getAgreementDateFrom());
    }

    @Override
    public String getIc() {
        return "TRAVEL_MEDICAL";
    }
}
