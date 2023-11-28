package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.validation.RiskPremium;
import lv.javaguru.travel.insurance.validation.v1.TravelCalculatePremiumRequestV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
@Component
public class SelectedRiskPremiumCalculator {
    @Autowired
    private List<TravelRiskPremiumCalculator> riskPremiumCalculators;

    public List<RiskPremium> calculatePremiumForAllRisks(TravelCalculatePremiumRequestV1 request) {
        return request.getSelected_risks().stream()
                .map(riskIc -> new RiskPremium(riskIc, calculatePremiumForRisk(riskIc, request)))
                .toList();
    }

    private BigDecimal calculatePremiumForRisk(String riskIc, TravelCalculatePremiumRequestV1 request) {
        return findRiskPremiumCalculator(riskIc).calculatePremium(request);
    }

    private TravelRiskPremiumCalculator findRiskPremiumCalculator(String riskIc) {
        return riskPremiumCalculators.stream()
                .filter(riskCalculator -> riskCalculator.getRiskIc().equals(riskIc))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Not supported riskIc = " + riskIc));
    }
}
