package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lombok.RequiredArgsConstructor;
import lv.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import lv.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import lv.javaguru.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@RequiredArgsConstructor
class TravelMedicalRiskPremiumCalculator implements TravelRiskPremiumCalculator {

    private final DayCountCalculator dayCountCalculator;
    private final CountryDefaultDayRateCalculator countryDefaultDayRateCalculator;
    private final AgeCoefficientCalculator ageCoefficientCalculator;
    private final MedicalRiskLimitLevelCalculator limitLevelCalculator;

    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {

        BigDecimal dayCount = dayCountCalculator.calculate(request);
        BigDecimal dayRate = countryDefaultDayRateCalculator.calculate(request);
        BigDecimal ageCoefficient = ageCoefficientCalculator.calculate(request);
        BigDecimal limitLevelCoefficient = limitLevelCalculator.calculate(request);

        return dayRate.multiply(dayCount)
                .multiply(ageCoefficient)
                .multiply(limitLevelCoefficient)
                .setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public String getRiskIc() {
        return "TRAVEL_MEDICAL";
    }
}
