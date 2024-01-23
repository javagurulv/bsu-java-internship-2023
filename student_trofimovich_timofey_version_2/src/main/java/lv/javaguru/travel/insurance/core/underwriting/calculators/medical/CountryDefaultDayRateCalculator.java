package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.api.dto.agreement.AgreementDTO;
import lv.javaguru.travel.insurance.core.domain.TMCountryDefaultDayRate;
import lv.javaguru.travel.insurance.core.repositories.TMCountryDefaultDayRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class CountryDefaultDayRateCalculator {
    @Autowired
    private TMCountryDefaultDayRateRepository rateRepository;

     BigDecimal getCountryDefaultDayRate(AgreementDTO agreement) {
        Optional<TMCountryDefaultDayRate> countryRate = rateRepository.findByCountryIc(agreement.getCountry());
        return countryRate.map(TMCountryDefaultDayRate::getCountryDefaultDayRate)
                .orElseThrow(() -> new RuntimeException("Country default day rate not found for country: " + agreement.getCountry()));
    }
}
