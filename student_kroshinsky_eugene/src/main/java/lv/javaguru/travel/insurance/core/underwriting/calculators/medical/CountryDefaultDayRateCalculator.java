package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class CountryDefaultDayRateCalculator {
    @Autowired
    private CountryDefaultDayRateRepository repository;
    public BigDecimal calculate(TravelCalculatePremiumRequestV1 request) {
        return repository.findByIc(request.getCountry())
                .orElseThrow(()->new RuntimeException("Optional is empty")).getDayRate();
    }
}
