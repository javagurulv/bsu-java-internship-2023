package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.repositories.AgeCoefficientRepository;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

import static lv.javaguru.travel.insurance.core.util.DateTimeUtil.findAge;

@Component
class TravelCalculateMedicalAgeCoefficient {
    @Autowired
    private AgeCoefficientRepository acRepository;

    public Double calculatePremium(TravelCalculatePremiumRequest request) {
        return acRepository
                .findByAgeFromAndAgeTo(
                        findAge(
                                new Date(),
                                request.getPersonBirthDate()
                        )
                )
                .get()
                .getCoefficient();
    }
}
