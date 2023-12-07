package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDto;
import lv.javaguru.travel.insurance.core.api.dto.PersonDto;
import lv.javaguru.travel.insurance.core.api.dto.RiskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
class TravelPremiumUnderwritingImpl implements TravelPremiumUnderwriting {

    @Autowired private SelectedRiskPremiumCalculator selectedRisksPremiumCalculator;

    @Override
    public TravelPremiumCalculationResult calculatePremium(AgreementDto agreement, PersonDto person) {
        List<RiskDto> riskPremiums = calculateSelectedRisksPremium(agreement, person);
        BigDecimal totalPremium = calculateTotalPremium(riskPremiums);
        return new TravelPremiumCalculationResult(totalPremium, riskPremiums);
    }

    private List<RiskDto> calculateSelectedRisksPremium(AgreementDto request, PersonDto person) {
        return selectedRisksPremiumCalculator.calculatePremiumForAllRisks(request, person);
    }

    private static BigDecimal calculateTotalPremium(List<RiskDto> riskPremiums) {
        return riskPremiums.stream()
                .map(RiskDto::getPremium)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
