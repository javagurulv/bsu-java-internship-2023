package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.repositories.AgeCoefficientRepository;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import static lv.javaguru.travel.insurance.core.util.CheckApplicationPropertiesUtil.checkProperty;
import static lv.javaguru.travel.insurance.core.util.DateTimeUtil.findAge;

@Component
class TravelCalculateMedicalAgeCoefficient {
    @Autowired
    private AgeCoefficientRepository acRepository;

    public BigDecimal calculatePremium(TravelCalculatePremiumRequestV1 request) {
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
                    : BigDecimal.ONE;
        }
        catch (IOException e) {
            return BigDecimal.ONE;
        }
    }
}
