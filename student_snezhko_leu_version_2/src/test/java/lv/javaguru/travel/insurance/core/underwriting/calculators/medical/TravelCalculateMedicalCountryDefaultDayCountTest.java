package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import lv.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TravelCalculateMedicalCountryDefaultDayCountTest {
    @InjectMocks
    private TravelCalculateMedicalCountryDefaultDayRate calculator = new TravelCalculateMedicalCountryDefaultDayRate();

    @Mock
    private CountryDefaultDayRateRepository cddrRepository = mock(CountryDefaultDayRateRepository.class);

    private AgreementDTO agreement;
    private PersonDTO person;

    @Test
    public void CalculatorMedicalCddrTest() {
        init(BigDecimal.valueOf(1.1));
        assertEquals(BigDecimal.valueOf(1.1), calculator.calculatePremium(agreement, person));
    }

    private void init(BigDecimal countryCoefficient) {
        agreement = mock(AgreementDTO.class);
        person = mock(PersonDTO.class);

        String country = "COUNTRY";
        when(agreement.getCountry()).thenReturn(country);

        CountryDefaultDayRate cddrValue = mock(CountryDefaultDayRate.class);
        when(cddrValue.getDefaultDayRate()).thenReturn(countryCoefficient);
        when(cddrValue.getCountryIc()).thenReturn(country);

        when(cddrRepository.findByCountryIc(country)).thenReturn(Optional.of(cddrValue));
    }
}
