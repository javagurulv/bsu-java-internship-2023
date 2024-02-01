package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CountryDefaultDayPremiumCalculator {
    @Autowired
    private CountryDefaultDayRateRepository countryDefaultDayRateRepository;

    BigDecimal calculate(TravelCalculatePremiumRequestV1 request) {
        return countryDefaultDayRateRepository.findByCountryIc(request.getCountry())
                .orElseThrow(() -> new RuntimeException("default day rate for country " + request.getCountry() + " not found"))
                .getDefaultDayRate();

    }
}
