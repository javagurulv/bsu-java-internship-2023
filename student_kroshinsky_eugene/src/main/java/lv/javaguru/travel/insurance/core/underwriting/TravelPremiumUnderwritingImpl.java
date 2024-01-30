package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.utils.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
class TravelPremiumUnderwritingImpl implements TravelPremiumUnderwriting {
    @Autowired private List<TravelRiskPremiumCalculator> calculators;
    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request){
        BigDecimal result = BigDecimal.ZERO;
        for (TravelRiskPremiumCalculator calculator : calculators) {
            result = result.add(calculator.calculatePremium(request));
        }
        return result;
    }
}
