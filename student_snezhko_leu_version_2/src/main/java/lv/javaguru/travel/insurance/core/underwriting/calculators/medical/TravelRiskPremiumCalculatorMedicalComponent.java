package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;

import java.math.BigDecimal;

public interface TravelRiskPremiumCalculatorMedicalComponent {
    public BigDecimal calculatePremium(AgreementDTO agreement, PersonDTO person);
}
