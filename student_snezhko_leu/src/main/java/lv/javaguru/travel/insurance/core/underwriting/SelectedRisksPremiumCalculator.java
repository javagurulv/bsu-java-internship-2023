package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRisk;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SelectedRisksPremiumCalculator {
    @Autowired
    private List<TravelRiskPremiumCalculator> calculators;

    public BigDecimal calculatePremiumForOneRisk(String riskIc, TravelCalculatePremiumRequest request) {
        return findCalculatorByIc(riskIc).calculatePremium(request);
    }

    public List<TravelCalculatePremiumRisk> calculatePremiumForAllRisks(TravelCalculatePremiumRequest request) {
        return request.getSelected_risks().stream()
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
