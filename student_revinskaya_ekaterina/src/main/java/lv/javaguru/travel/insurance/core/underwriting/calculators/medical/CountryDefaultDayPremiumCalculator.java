package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CountryDefaultDayPremiumCalculator {
    @Autowired
    private CountryDefaultDayRateRepository countryDefaultDayRateRepository;

    BigDecimal calculate(TravelCalculatePremiumRequest request) {
        return countryDefaultDayRateRepository.findByCountryIc(request.getCountry())
                .orElseThrow(() -> new RuntimeException("default day rate for country " + request.getCountry() + " not found"))
                .getDefaultDayRate();

    }
}
