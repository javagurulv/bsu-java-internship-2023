package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class CountryDefaultDayRateCalculator {
    @Autowired
    private CountryDefaultDayRateRepository countryDefaultDayRateRepository;
    public BigDecimal calculate(TravelCalculatePremiumRequest request) {
        String country = request.getCountry();
        return countryDefaultDayRateRepository.findByIc(country).get().getDayRate();
    }
}
