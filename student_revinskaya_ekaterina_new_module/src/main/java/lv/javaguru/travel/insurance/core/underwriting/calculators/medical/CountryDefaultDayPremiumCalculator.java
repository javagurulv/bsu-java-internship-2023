package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CountryDefaultDayPremiumCalculator {
    @Autowired
    private CountryDefaultDayRateRepository countryDefaultDayRateRepository;

    BigDecimal calculate(AgreementDTO agreement) {
        return countryDefaultDayRateRepository.findByCountryIc(agreement.getCountry())
                .orElseThrow(() -> new RuntimeException("default day rate for country " + agreement.getCountry() + " not found"))
                .getDefaultDayRate();

    }
}
