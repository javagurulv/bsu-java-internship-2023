package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.underwriting.calculators.TravelRiskPremiumCalculator;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class TravelMedicalCalculator implements TravelRiskPremiumCalculator {
    @Autowired private LimitLevelCalculator limitLevelCalculator;
    @Autowired private AgeCoefficientCalculator ageCoefficientCalculator;
    @Autowired private DayCountCalculator dayCountCalculator;
    @Autowired private CountryDefaultDayRateCalculator countryDefaultDayRateCalculator;
    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        BigDecimal insuranceLimitCoefficient = limitLevelCalculator.calculate(request);
        BigDecimal ageCoefficient = ageCoefficientCalculator.calculate(request);
        BigDecimal countryDefaultDayPremium = countryDefaultDayRateCalculator.calculate(request);
        BigDecimal dayCount = dayCountCalculator.calculate(request);
        return countryDefaultDayPremium.multiply(dayCount)
                .multiply(ageCoefficient).multiply(insuranceLimitCoefficient);
    }
    @Override
    public String getRiskIc() {
        return "TRAVEL_MEDICAL";
    }
}
