package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.domain.calculate.medical.CountryDefaultDayRate;
import lv.javaguru.travel.insurance.core.repositories.calculate.medical.CountryDefaultDayRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
class TravelCalculateMedicalCountryDefaultDayRate implements TravelRiskPremiumCalculatorMedicalComponent{
    @Autowired
    private CountryDefaultDayRateRepository cddrRepository;

    public BigDecimal calculatePremium(AgreementDTO agreement, PersonDTO person) {
        Optional<CountryDefaultDayRate> optional = cddrRepository.findByCountryIc(agreement.getCountry());
        CountryDefaultDayRate cddr = optional.get();
        return cddr.getDefaultDayRate();
    }
}
