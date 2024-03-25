package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.domain.AgeCoefficient;
import lv.javaguru.travel.insurance.core.repositories.AgeCoefficientRepository;
import lv.javaguru.travel.insurance.core.utils.AgeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
class AgeCoefficientCalculator {
    @Value("${age.coefficient.enabled:false}")
    private boolean ageCoefficientEnabled;
    @Autowired
    private AgeUtil ageUtil;
    @Autowired
    private AgeCoefficientRepository repository;
    public BigDecimal calculate(TravelCalculatePremiumRequest request) {
        return (ageCoefficientEnabled)
                ? calculatePresentOptional(request)
                : BigDecimal.ONE;
    }
    private BigDecimal calculatePresentOptional(TravelCalculatePremiumRequest request) {
        return repository.findByAge(ageUtil.calculateAge(request))
                .orElseThrow(()->new RuntimeException("Optional is empty")).getCoefficient();
    }
}
