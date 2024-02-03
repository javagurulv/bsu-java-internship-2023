package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import lv.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
class TravelCalculateMedicalCountryDefaultDayRate {
    @Autowired
    private CountryDefaultDayRateRepository cddrRepository;

    public BigDecimal calculatePremium(TravelCalculatePremiumRequestV1 request) {
        Optional<CountryDefaultDayRate> optional = cddrRepository.findByCountryIc(request.getCountry());
        CountryDefaultDayRate cddr = optional.get();
        BigDecimal result = cddr.getCountryDefaultDayRateCoefficient();
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
