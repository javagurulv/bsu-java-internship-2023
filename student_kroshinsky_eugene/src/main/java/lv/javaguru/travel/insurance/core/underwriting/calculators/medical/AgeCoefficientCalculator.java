package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.repositories.AgeCoefficientRepository;
import lv.javaguru.travel.insurance.core.utils.AgeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class AgeCoefficientCalculator {
    @Autowired
    private AgeUtil ageUtil;
    @Autowired
    private AgeCoefficientRepository ageCoefficientRepository;
    public BigDecimal calculate(TravelCalculatePremiumRequest request) {
        int age = ageUtil.calculateAge(request);
        return ageCoefficientRepository.findByAge(age).get().getCoefficient();
    }
}
