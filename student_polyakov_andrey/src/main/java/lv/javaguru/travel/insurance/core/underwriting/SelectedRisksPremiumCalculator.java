package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.dto.RiskPremium;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class SelectedRisksPremiumCalculator {
    @Autowired
    private List<TravelRiskPremiumCalculator> travelRiskPremiumCalculatorList;

    private TravelRiskPremiumCalculator findRiskPremiumCalculator(String ic) {
        return travelRiskPremiumCalculatorList.stream()
                .filter(calc -> calc.getRiskIc().equals(ic))
                .findFirst()
                .orElseThrow( () -> new RuntimeException("Not supported riskIc = " + ic));
    }
    private BigDecimal calculateByRisk(TravelCalculatePremiumRequest request, String ic) {
        return findRiskPremiumCalculator(ic).calculatePremium(request);
    }

    public List<RiskPremium> calculatePremiumForRisks(TravelCalculatePremiumRequest request) {
        return request.getSelected_risks().stream()
                .map(ic -> new RiskPremium(ic, calculateByRisk(request, ic)))
                .toList();
    }
}
