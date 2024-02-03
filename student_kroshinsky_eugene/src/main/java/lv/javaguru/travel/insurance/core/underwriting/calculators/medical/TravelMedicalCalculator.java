package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import lv.javaguru.travel.insurance.core.repositories.AgeCoefficientRepository;
import lv.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import lv.javaguru.travel.insurance.core.underwriting.calculators.TravelRiskPremiumCalculator;
import lv.javaguru.travel.insurance.core.utils.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Component
class TravelMedicalCalculator implements TravelRiskPremiumCalculator {
    @Autowired private AgeCoefficientCalculator ageCoefficientCalculator;
    @Autowired private DayCountCalculator dayCountCalculator;
    @Autowired private CountryDefaultDayRateCalculator countryDefaultDayRateCalculator;
    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        BigDecimal ageCoefficient = ageCoefficientCalculator.calculate(request);
        BigDecimal countryDefaultDayPremium = countryDefaultDayRateCalculator.calculate(request);
        BigDecimal dayCount = dayCountCalculator.calculate(request);
        return countryDefaultDayPremium.multiply(dayCount.multiply(ageCoefficient));
    }
    @Override
    public String getRiskIc() {
        return "TRAVEL_MEDICAL";
    }
}
