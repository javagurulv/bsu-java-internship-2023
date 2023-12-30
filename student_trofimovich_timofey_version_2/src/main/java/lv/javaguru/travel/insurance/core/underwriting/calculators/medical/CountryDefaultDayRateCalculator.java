package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import lv.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class CountryDefaultDayRateCalculator {
    @Autowired
    private CountryDefaultDayRateRepository rateRepository;

    public BigDecimal getCountryDefaultDayRate(AgreementDTO agreement) {
        Optional<CountryDefaultDayRate> countryRate = rateRepository.findByCountryIc(agreement.getCountry());
        return countryRate.map(CountryDefaultDayRate::getCountryDefaultDayRate)
                .orElseThrow(() -> new RuntimeException("Country default day rate not found for country: " + agreement.getCountry()));
    }
}
