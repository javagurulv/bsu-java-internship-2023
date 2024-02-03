package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRisk;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

@Component
public class SelectedRisksPremiumCalculator {
    @Autowired
    private List<TravelRiskPremiumCalculator> calculators;

    public BigDecimal calculatePremiumForOneRisk(String riskIc, TravelCalculatePremiumRequestV1 request) {
        return findCalculatorByIc(riskIc).calculatePremium(request);
    }

    public List<TravelCalculatePremiumRisk> calculatePremiumForAllRisks(TravelCalculatePremiumRequestV1 request) {
        return request.getSelectedRisks().stream()
                .map(riskIc ->
                        new TravelCalculatePremiumRisk(riskIc, calculatePremiumForOneRisk(riskIc, request)))
                .toList();
    }
    public TravelRiskPremiumCalculator findCalculatorByIc(String riskIc) {
        for (TravelRiskPremiumCalculator calculator : calculators) {
            if (calculator.getIc().equals(riskIc)) {
                return calculator;
            }
        }
        throw new RuntimeException("Risk with ic = " + riskIc + " is not supported!");
/*        return calculators.stream()
                .filter(calc -> calc.getIc().equals(riskIc))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Risk with ic = " + riskIc + " is not supported!"));
*/
    }
}
