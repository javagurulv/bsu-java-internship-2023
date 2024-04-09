package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.repositories.AgeCoefficientRepository;
import lv.javaguru.travel.insurance.core.utils.AgeUtil;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class AgeCoefficientCalculator {
    @Value("${age.coefficient.enabled:false}")
    private boolean ageCoefficientEnabled;
    @Autowired
    private AgeUtil ageUtil;
    @Autowired
    private AgeCoefficientRepository repository;
    public BigDecimal calculate(TravelCalculatePremiumRequestV1 request) {
        return (ageCoefficientEnabled)
                ? calculatePresentOptional(request)
                : BigDecimal.ONE;
    }
    private BigDecimal calculatePresentOptional(TravelCalculatePremiumRequestV1 request) {
        return repository.findByAge(ageUtil.calculateAge(request))
                .orElseThrow(()->new RuntimeException("Optional is empty")).getCoefficient();
    }
}
