package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDto;
import lv.javaguru.travel.insurance.core.domain.TMCountryDefaultDayRate;
import lv.javaguru.travel.insurance.core.repositories.TMCountryDefaultDayRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class TMCountryDefaultDayRateCalculator {

    @Autowired private TMCountryDefaultDayRateRepository countryDefaultDayRateRepository;

    BigDecimal calculate(AgreementDto agreement) {
        return countryDefaultDayRateRepository.findByCountryIc(agreement.getCountry())
                .map(TMCountryDefaultDayRate::getDefaultDayRate)
                .orElseThrow(() -> new RuntimeException("Country day rate not found by country id = " + agreement.getCountry()));
    }

}
