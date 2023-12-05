package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class TravelMedicalRiskPremiumCalculator implements TravelRiskPremiumCalculator {
    @Autowired private DayCountCalculator dayCountCalculator;
    @Autowired private CountryDefaultDayPremiumCalculator countryDefaultDayPremiumCalculator;
    @Autowired private AgeCoefficientCalculator ageCoefficientCalculator;

    @Autowired private InsuranceLimitCoefficientCalculator insuranceLimitCoefficientCalculator;
    @Override
    public BigDecimal calculatePremium(AgreementDTO agreement, PersonDTO person) {
        BigDecimal dayCount = dayCountCalculator.calculate(agreement);
        BigDecimal countryDefaultDayPremium = countryDefaultDayPremiumCalculator.calculate(agreement);
        BigDecimal ageCoefficient = ageCoefficientCalculator.calculate(person);
        BigDecimal insuranceLimitCoefficient = insuranceLimitCoefficientCalculator.calculate(person);
        return dayCount
                .multiply(countryDefaultDayPremium)
                .multiply(ageCoefficient)
                .multiply(insuranceLimitCoefficient)
                .setScale(2, RoundingMode.HALF_UP);

    }

    @Override
    public String getRiskIc() {
        return "TRAVEL_MEDICAL";
    }
}