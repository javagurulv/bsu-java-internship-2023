package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.api.dto.agreement.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.person.PersonDTO;
import lv.javaguru.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class TravelMedicalPremiumCalculation implements TravelRiskPremiumCalculator {


    @Autowired
    private AgeCoefficientCalculator ageCoefficientCalculator;
    @Autowired
    private CountryDefaultDayRateCalculator countryDefaultDayRateCalculator;
    @Autowired
    private DayCountCalculator dayCountCalculator;
    @Autowired
    private InsuranceLimitCoefficientCalculator limitCoefficientCalculator;

    @Override
    public BigDecimal calculatePremium(AgreementDTO agreement, PersonDTO person) {
        long numberOfDays = dayCountCalculator.getNumberOfDays(agreement);
        BigDecimal countryDefaultDayRate = countryDefaultDayRateCalculator.getCountryDefaultDayRate(agreement);
        BigDecimal ageCoefficient = ageCoefficientCalculator.getAgeCoefficient(person);
        BigDecimal insuranceLimitCoefficient = limitCoefficientCalculator.getInsuranceLimitCoefficient(person);
        return new BigDecimal(numberOfDays)
                .multiply(countryDefaultDayRate)
                .multiply(ageCoefficient)
                .multiply(insuranceLimitCoefficient)
                .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String getRiskIc() {
        return "TRAVEL_MEDICAL";
    }


}
