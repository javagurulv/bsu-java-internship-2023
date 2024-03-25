package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import lv.javaguru.travel.insurance.core.domain.LimitLevel;
import lv.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
class CountryDefaultDayRateCalculator {
    @Autowired
    private CountryDefaultDayRateRepository repository;
    public BigDecimal calculate(TravelCalculatePremiumRequest request) {
        return repository.findByIc(request.getCountry())
                .orElseThrow(()->new RuntimeException("Optional is empty")).getDayRate();
    }
}
