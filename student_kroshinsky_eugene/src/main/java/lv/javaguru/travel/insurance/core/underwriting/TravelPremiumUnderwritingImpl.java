package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.underwriting.calculators.TravelRiskPremiumCalculator;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelRisk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
class TravelPremiumUnderwritingImpl implements TravelPremiumUnderwriting {
    @Autowired private List<TravelRiskPremiumCalculator> calculators;
    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request){
        BigDecimal result = BigDecimal.ZERO;
        List<String> selectedRisks = request.getSelectedRisks();
        for (TravelRiskPremiumCalculator calculator : calculators) {
            if (selectedRisks.contains(calculator.getRiskIc())) {
                result = result.add(calculator.calculatePremium(request));
            }
        }
        return result;
    }
}
