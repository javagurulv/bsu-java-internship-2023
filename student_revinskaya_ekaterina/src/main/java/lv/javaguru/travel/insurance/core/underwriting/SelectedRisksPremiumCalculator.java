package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelRisk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
class SelectedRisksPremiumCalculator {
    @Autowired
    private List<TravelRiskPremiumCalculator> riskPremiumCalculators;

    List<TravelRisk> calculateSelectedRisksPremium(TravelCalculatePremiumRequest request) {
        return request.getSelected_risks().stream()
                .map(this::getCalculatorByIc)
                .map(calculator -> new TravelRisk(calculator.getRiskIc(), calculator.calculatePremium(request)))
                .toList();
    }

    private TravelRiskPremiumCalculator getCalculatorByIc(String riskIc) {
        return riskPremiumCalculators.stream()
                .filter(calculator -> calculator.getRiskIc().equals(riskIc))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("risk with riskIc " + riskIc + " not supported by system"));
    }
}
