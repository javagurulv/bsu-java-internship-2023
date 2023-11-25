package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.repositories.AgeCoefficientRepository;
import lv.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import lv.javaguru.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

@Component
public class TravelMedicalRiskPremiumCalculator implements TravelRiskPremiumCalculator {
    @Autowired private DayCountCalculator dayCountCalculator;
    @Autowired private CountryDefaultDayPremiumCalculator countryDefaultDayPremiumCalculator;
    @Autowired private AgeCoefficientCalculator ageCoefficientCalculator;
    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        BigDecimal dayCount = dayCountCalculator.calculate(request);
        BigDecimal countryDefaultDayPremium = countryDefaultDayPremiumCalculator.calculate(request);
        BigDecimal ageCoefficient = ageCoefficientCalculator.calculate(request);
        return dayCount.multiply(countryDefaultDayPremium).multiply(ageCoefficient);
    }

    @Override
    public String getRiskIc() {
        return "TRAVEL_MEDICAL";
    }
}