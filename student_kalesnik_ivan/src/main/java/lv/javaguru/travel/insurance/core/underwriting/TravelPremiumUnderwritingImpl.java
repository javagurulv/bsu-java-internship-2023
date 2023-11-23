package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.validation.RiskPremium;
import lv.javaguru.travel.insurance.validation.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public
class TravelPremiumUnderwritingImpl implements TravelPremiumUnderwriting {

    @Autowired private SelectedRiskPremiumCalculator selectedRisksPremiumCalculator;

    @Override
    public TravelPremiumCalculationResult calculatePremium(TravelCalculatePremiumRequest request) {
        List<RiskPremium> riskPremiums = calculateSelectedRisksPremium(request);
        BigDecimal totalPremium = calculateTotalPremium(riskPremiums);
        return new TravelPremiumCalculationResult(totalPremium, riskPremiums);
    }

    private List<RiskPremium> calculateSelectedRisksPremium(TravelCalculatePremiumRequest request) {
        return selectedRisksPremiumCalculator.calculatePremiumForAllRisks(request);
    }

    private static BigDecimal calculateTotalPremium(List<RiskPremium> riskPremiums) {
        return riskPremiums.stream()
                .map(RiskPremium::getPremium)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
