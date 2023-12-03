package lv.javaguru.travel.insurance.core.underwriting.calculations.medical;
import lv.javaguru.travel.insurance.core.underwriting.calculations.TravelRiskPremiumCalculator;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
class TravelMedicalPremiumCalculation implements TravelRiskPremiumCalculator {


    @Autowired private AgeCoefficientCalculator ageCoefficientCalculator;
    @Autowired private CountryDefaultDayRateCalculator countryDefaultDayRateCalculator;
    @Autowired private DayCountCalculator dayCountCalculator;
    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        long numberOfDays = dayCountCalculator.getNumberOfDays(request);
        BigDecimal countryDefaultDayRate = countryDefaultDayRateCalculator.getCountryDefaultDayRate(request);
        BigDecimal ageCoefficient = ageCoefficientCalculator.getAgeCoefficient(request);
        return new BigDecimal(numberOfDays)
                .multiply(countryDefaultDayRate)
                .multiply(ageCoefficient)
                .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String getRiskIc() {
       return "TRAVEL_MEDICAL";
    }


}
