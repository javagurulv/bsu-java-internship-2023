package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class SelectedRisksPremiumCalculator {
    @Autowired
    private List<TravelRiskPremiumCalculator> riskPremiumCalculators;

     List<RiskDTO> calculateSelectedRisksPremium(AgreementDTO agreement, PersonDTO person) {
        return agreement.getSelectedRisks().stream()
                .map(this::getCalculatorByIc)
                .map(calculator -> new RiskDTO(calculator.getRiskIc(), calculator.calculatePremium(agreement, person)))
                .toList();
    }

    private TravelRiskPremiumCalculator getCalculatorByIc(String riskIc) {
        return riskPremiumCalculators.stream()
                .filter(calculator -> calculator.getRiskIc().equals(riskIc))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("risk with riskIc " + riskIc + " not supported by system"));
    }
}
