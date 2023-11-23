package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
class TravelPremiumUnderwritingImpl implements TravelPremiumUnderwriting {
    @Autowired
    List<TravelRiskPremiumCalculator> riskPremiumCalculators;
    @Autowired
    private DateTimeUtil calculateDate;

    @Override
    public BigDecimal calculateAgreementPrice(TravelCalculatePremiumRequest request) {
        return riskPremiumCalculators.stream()
                .filter(risk->request.getSelected_risks().contains(risk.getRiskIc()))
                .map(risk->risk.calculatePremium(request))
                .reduce(BigDecimal::add).get();
    }
}
