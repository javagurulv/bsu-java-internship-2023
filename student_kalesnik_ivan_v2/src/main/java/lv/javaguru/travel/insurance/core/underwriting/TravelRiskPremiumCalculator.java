package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDto;
import lv.javaguru.travel.insurance.core.api.dto.PersonDto;

import java.math.BigDecimal;

public interface TravelRiskPremiumCalculator {
    BigDecimal calculatePremium(AgreementDto agreement, PersonDto person);
    String getRiskIc();
}
