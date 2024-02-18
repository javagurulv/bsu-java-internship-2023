package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class TravelRiskPremiumCalculatorMedical implements TravelRiskPremiumCalculator {

    @Autowired
    private List<TravelRiskPremiumCalculatorMedicalComponent> calculators;

    @Override
    public BigDecimal calculatePremium(AgreementDTO agreement, PersonDTO person) {
        BigDecimal result = BigDecimal.ONE;
        for (TravelRiskPremiumCalculatorMedicalComponent c : calculators) {
            result = result.multiply(c.calculatePremium(agreement, person));
        }

        return result;

    }
    @Override
    public String getIc() {
        return "TRAVEL_MEDICAL";
    }
}
