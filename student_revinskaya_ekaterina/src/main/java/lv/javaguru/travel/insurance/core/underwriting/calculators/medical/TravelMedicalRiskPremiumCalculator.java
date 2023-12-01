package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TravelMedicalRiskPremiumCalculator implements TravelRiskPremiumCalculator {
    @Autowired private DayCountCalculator dayCountCalculator;
    @Autowired private CountryDefaultDayPremiumCalculator countryDefaultDayPremiumCalculator;
    @Autowired private AgeCoefficientCalculator ageCoefficientCalculator;

    @Autowired private InsuranceLimitCoefficientCalculator insuranceLimitCoefficientCalculator;
    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequestV1 request) {
        BigDecimal dayCount = dayCountCalculator.calculate(request);
        BigDecimal countryDefaultDayPremium = countryDefaultDayPremiumCalculator.calculate(request);
        BigDecimal ageCoefficient = ageCoefficientCalculator.calculate(request);
        BigDecimal insuranceLimitCoefficient = insuranceLimitCoefficientCalculator.calculate(request);
        return dayCount.multiply(countryDefaultDayPremium).multiply(ageCoefficient).multiply(insuranceLimitCoefficient);
    }

    @Override
    public String getRiskIc() {
        return "TRAVEL_MEDICAL";
    }
}