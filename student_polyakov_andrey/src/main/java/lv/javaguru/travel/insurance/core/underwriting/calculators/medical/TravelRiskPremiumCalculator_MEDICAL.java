package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
class TravelRiskPremiumCalculator_MEDICAL implements TravelRiskPremiumCalculator {
    @Autowired
    private Calculate_MEDICAL_CalculateDayCount medical_calculateDayCount;
    @Autowired
    private Calculate_MEDICAL_FindAgeCoefficient medical_FindAgeCoefficient;
    @Autowired
    private Calculate_MEDICAL_FindCountryDefaultDayRate medical_findCountryDefaultDayRate;

    @Override
    public String getRiskIc() {
        return "TRAVEL_MEDICAL";
    }

    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        var daysCount = medical_calculateDayCount.calculateDayCount(request);
        var countryDefaultRate = medical_findCountryDefaultDayRate.findCountryDefaultDayRate(request);
        var ageCoefficient = medical_FindAgeCoefficient.findAgeCoefficient(request);
        return countryDefaultRate
                .multiply(daysCount)
                .multiply(ageCoefficient)
                .setScale(2, RoundingMode.HALF_UP);
    }
}
