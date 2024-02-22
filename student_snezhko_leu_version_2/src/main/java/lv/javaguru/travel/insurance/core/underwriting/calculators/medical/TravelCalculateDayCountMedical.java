package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;

import java.math.BigDecimal;

@Component
class TravelCalculateDayCountMedical implements TravelRiskPremiumCalculatorMedicalComponent {
    @Autowired
    private DateTimeUtil dateTimeUtil;

    public BigDecimal calculatePremium(AgreementDTO agreement, PersonDTO person) {
        return BigDecimal.valueOf(dateTimeUtil.getDaysBetween(agreement.getAgreementDateTo(), agreement.getAgreementDateFrom())).abs();
    }
}
