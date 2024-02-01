package lv.javaguru.travel.insurance.core.underwriting.calculators.cancel;


import lv.javaguru.travel.insurance.core.api.dto.AgreementDto;
import lv.javaguru.travel.insurance.core.api.dto.PersonDto;
import lv.javaguru.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
class TravelCancelRiskCalculator implements TravelRiskPremiumCalculator {

    @Autowired private TCTravelCostCoefficientCalculator travelCostCoefficientCalculator;
    @Autowired private TCAgeCoefficientCalculator ageCoefficientCalculator;
    @Autowired private TCCountrySafetyRatingCoefficientCalculator countrySafetyRatingCoefficientCalculator;

    @Override
    public BigDecimal calculatePremium(AgreementDto agreement, PersonDto person) {
        var travelCostCoefficient = travelCostCoefficientCalculator.calculate(person);
        var ageCoefficient = ageCoefficientCalculator.calculate(person);
        var countrySafetyRatingCoefficient = countrySafetyRatingCoefficientCalculator.calculate(agreement);
        return travelCostCoefficient
                .add(ageCoefficient)
                .add(countrySafetyRatingCoefficient)
                .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String getRiskIc() {
        return "TRAVEL_CANCELLATION";
    }

}
