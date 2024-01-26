package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import lv.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class TravelCalculateMedicalCountryDefaultDayRate {
    @Autowired
    private CountryDefaultDayRateRepository cddrRepository;

    public Double calculatePremium(TravelCalculatePremiumRequest request) {
        Optional<CountryDefaultDayRate> optional = cddrRepository.findByCountryIc(request.getCountry());
        CountryDefaultDayRate cddr = optional.get();
        Double result = cddr.getCountryDefaultDayRateCoefficient();
        return result;
        /*return cddrRepository
                .findByCountryIc(
                        request.getCountry()
                )
                .get()
                .getCountryDefaultDayRate();

         */
    }
}
