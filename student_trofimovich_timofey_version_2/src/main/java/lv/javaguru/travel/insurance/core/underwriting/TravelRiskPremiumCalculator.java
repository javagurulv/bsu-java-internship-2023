package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.api.dto.agreement.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.person.PersonDTO;

import java.math.BigDecimal;

public interface TravelRiskPremiumCalculator {
    BigDecimal calculatePremium(AgreementDTO agreement, PersonDTO person);

    String getRiskIc();
}
