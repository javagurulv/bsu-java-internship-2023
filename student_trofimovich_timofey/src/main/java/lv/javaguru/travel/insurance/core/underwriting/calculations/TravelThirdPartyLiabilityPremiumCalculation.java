package lv.javaguru.travel.insurance.core.underwriting.calculations;

import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class TravelThirdPartyLiabilityPremiumCalculation implements TravelRiskPremiumCalculator{
    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequestV1 request) {
        return BigDecimal.ZERO;
    }

    @Override
    public String getRiskIc() {
        return "TRAVEL_THIRD_PARTY_LIABILITY";
    }
}
