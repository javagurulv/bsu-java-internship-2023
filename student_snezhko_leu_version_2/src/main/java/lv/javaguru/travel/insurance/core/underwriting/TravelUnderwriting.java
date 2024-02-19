package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;

import java.math.BigDecimal;

public interface TravelUnderwriting {
    public TravelPremiumCalculationResult calculatePremium(AgreementDTO agreement, PersonDTO person);//TravelCalculatePremiumResponse response);
    //public List<TravelRiskPremiumCalculator> getRiskCalculators();
}
