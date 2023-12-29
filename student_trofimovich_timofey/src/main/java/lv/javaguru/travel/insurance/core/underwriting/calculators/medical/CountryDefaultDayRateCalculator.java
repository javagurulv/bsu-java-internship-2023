package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import lv.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
class CountryDefaultDayRateCalculator {
    @Autowired
    private CountryDefaultDayRateRepository rateRepository;
     BigDecimal getCountryDefaultDayRate(TravelCalculatePremiumRequestV1 request) {
        Optional<CountryDefaultDayRate> countryRate = rateRepository.findByCountryIc(request.getCountry());
        return countryRate.map(CountryDefaultDayRate::getCountryDefaultDayRate)
                .orElseThrow(() -> new RuntimeException("Country default day rate not found for country: " + request.getCountry()));
    }
}
