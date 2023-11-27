package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
class TravelPremiumUnderwritingImpl implements TravelPremiumUnderwriting{
    @Autowired
    private List<TravelMedicalPremiumCalculation> premiumCalculationList;

    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        List<String> selectedRisks = request.getSelectedRisks();
        BigDecimal sum = BigDecimal.ZERO;
        for (TravelMedicalPremiumCalculation calculation : premiumCalculationList) {
            String calculationRiskIc = calculation.getRiskIc();
            if (selectedRisks.contains(calculationRiskIc)) {
                BigDecimal calculatedPremium = calculation.calculatePremium(request);
                sum = sum.add(calculatedPremium);
            }
        }
        return sum;
    }
}
