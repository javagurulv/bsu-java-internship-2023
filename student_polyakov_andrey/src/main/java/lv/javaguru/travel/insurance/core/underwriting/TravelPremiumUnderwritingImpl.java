package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.dto.RiskPremium;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
class TravelPremiumUnderwritingImpl implements TravelPremiumUnderwriting {
    @Autowired
    private SelectedRisksPremiumCalculator selectedRisksPremiumCalculator;

    private BigDecimal calculateTotalPremium(List<RiskPremium> riskPremiumList) {
        return riskPremiumList.stream()
                .map(RiskPremium::getPremium)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public TravelPremiumCalculationResult calculatePremium(TravelCalculatePremiumRequest request) {
        List<RiskPremium> riskPremiumList = selectedRisksPremiumCalculator.calculatePremiumForRisks(request);
        BigDecimal totalPremium = calculateTotalPremium(riskPremiumList);
        return new TravelPremiumCalculationResult(totalPremium, riskPremiumList);
    }
}
