package lv.javaguru.travel.insurance.core.underwriting.calculators;

import lv.javaguru.travel.insurance.core.api.dto.agreement.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.person.PersonDTO;
import lv.javaguru.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class TravelThirdPartyLiabilityPremiumCalculation implements TravelRiskPremiumCalculator {
    @Override
    public BigDecimal calculatePremium(AgreementDTO agreement, PersonDTO person) {
        return BigDecimal.ZERO;
    }


    @Override
    public String getRiskIc() {
        return "TRAVEL_THIRD_PARTY_LIABILITY";
    }
}
