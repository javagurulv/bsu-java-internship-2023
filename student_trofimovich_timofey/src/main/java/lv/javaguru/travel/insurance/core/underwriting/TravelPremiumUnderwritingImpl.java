package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.underwriting.calculations.TravelRiskPremiumCalculator;
import lv.javaguru.travel.insurance.dto.RiskPremium;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
class TravelPremiumUnderwritingImpl implements TravelPremiumUnderwriting{
    @Autowired
    private List<TravelRiskPremiumCalculator> premiumCalculationList;

    public TravelPremiumCalculationResult calculatePremium(TravelCalculatePremiumRequest request) {
        List<String> selectedRisks = request.getSelectedRisks();
        BigDecimal sum = BigDecimal.ZERO;
        List<RiskPremium> riskPremiums = new ArrayList<>();
        for (TravelRiskPremiumCalculator calculation : premiumCalculationList) {
            String calculationRiskIc = calculation.getRiskIc();
            if (selectedRisks.contains(calculationRiskIc)) {
                BigDecimal calculatedPremium = calculation.calculatePremium(request);
                RiskPremium riskPremium = new RiskPremium(calculationRiskIc, calculatedPremium);
                riskPremiums.add(riskPremium);
                sum = sum.add(calculatedPremium);
            }
        }
        return new TravelPremiumCalculationResult(sum, riskPremiums);
    }
}
