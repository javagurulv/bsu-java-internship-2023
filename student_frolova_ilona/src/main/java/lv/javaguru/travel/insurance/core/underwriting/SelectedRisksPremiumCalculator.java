package lv.javaguru.travel.insurance.core.underwriting;

import lombok.RequiredArgsConstructor;
import lv.javaguru.travel.insurance.dto.RiskPremium;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
class SelectedRisksPremiumCalculator {

    private final List<TravelRiskPremiumCalculator> riskPremiumCalculators;

    List<RiskPremium> calculatePremiumForAllRisks(TravelCalculatePremiumRequest request) {
        return request.getSelectedRisks().stream()
                .map(riskIc -> new RiskPremium(riskIc, calculatePremiumForRisk(riskIc, request)))
                .toList();
    }

    private BigDecimal calculatePremiumForRisk(String riskIc, TravelCalculatePremiumRequest request) {
        return findRiskPremiumCalculator(riskIc).calculatePremium(request);
    }

    private TravelRiskPremiumCalculator findRiskPremiumCalculator(String riskIc) {
        return riskPremiumCalculators.stream()
                .filter(riskCalculator -> riskCalculator.getRiskIc().equals(riskIc))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Not supported riskIc = " + riskIc));
    }

}
