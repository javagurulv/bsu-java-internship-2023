package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.repositories.AgeCoefficientRepository;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

import static lv.javaguru.travel.insurance.core.util.CheckApplicationPropertiesUtil.checkProperty;
import static lv.javaguru.travel.insurance.core.util.DateTimeUtil.findAge;

@Component
class TravelCalculateMedicalAgeCoefficient {
    @Autowired
    private AgeCoefficientRepository acRepository;

    public Double calculatePremium(TravelCalculatePremiumRequest request) {
        try {
            return checkProperty("medical.risk.age.enabled")
                    ? acRepository
                        .findByAgeFromAndAgeTo(
                                findAge(
                                        new Date(),
                                        request.getPersonBirthDate()
                                )
                        )
                        .get()
                        .getCoefficient()
                    : 1d;
        }
        catch (IOException e) {
            return 1d;
        }
    }
}
