package lv.javaguru.travel.insurance.core.underwriting;

import lombok.RequiredArgsConstructor;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
class TravelCalculateUnderwritingImpl implements TravelCalculateUnderwriting {

    private final List<TravelRiskPremiumCalculator> riskPremiumCalculators;

    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {

        return riskPremiumCalculators.stream()
                .filter(x -> request.getSelectedRisks().contains(x.getRiskIc()))
                .map(x -> x.calculatePremium(request))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
