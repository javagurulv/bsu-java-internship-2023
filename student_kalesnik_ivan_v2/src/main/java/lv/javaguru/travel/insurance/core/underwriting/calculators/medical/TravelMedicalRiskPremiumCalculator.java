package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDto;
import lv.javaguru.travel.insurance.core.api.dto.PersonDto;
import lv.javaguru.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
class TravelMedicalRiskPremiumCalculator implements TravelRiskPremiumCalculator {

    @Autowired
    public TMDayCountCalculator dayCountCalculator;
    @Autowired
    public TMCountryDefaultDayRateCalculator countryDefaultDayRateCalculator;
    @Autowired
    public TMAgeCoefficientCalculator ageCoefficientCalculator;
    @Autowired
    public TMRiskLimitLevelCalculator riskLimitLevelCalculator;

    @Override
    public BigDecimal calculatePremium(AgreementDto request, PersonDto person) {
        var daysCount = dayCountCalculator.calculate(request);
        var countryDefaultRate = countryDefaultDayRateCalculator.calculate(request);
        var ageCoefficient = ageCoefficientCalculator.calculate(person);
        var riskLimitLevel = riskLimitLevelCalculator.calculate(person);
        return countryDefaultRate
                .multiply(daysCount)
                .multiply(ageCoefficient)
                .multiply(riskLimitLevel)
                .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String getRiskIc() {
        return "TRAVEL_MEDICAL";
    }

}
