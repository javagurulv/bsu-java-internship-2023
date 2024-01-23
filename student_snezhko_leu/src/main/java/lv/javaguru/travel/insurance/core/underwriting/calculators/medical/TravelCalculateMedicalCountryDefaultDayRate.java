package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class TravelCalculateMedicalCountryDefaultDayRate {
    @Autowired
    private CountryDefaultDayRateRepository cddrRepository;

    public Double calculatePremium(TravelCalculatePremiumRequest request) {
        return cddrRepository
                .findByCountryIc(
                        request.getCountry()
                )
                .get()
                .getCountryDefaultDayRate();
    }
}
