package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import lv.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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

    TravelCalculatePremiumRequest request;

    @Test
    public void CalculatorMedicalCddrTest() {
        init(1.1d);
        assertEquals(1.1, calculator.calculatePremium(request));
    }

    private void init(Double countryCoefficient) {
        request = mock(TravelCalculatePremiumRequest.class);
        String country = "COUNTRY";
        when(request.getCountry()).thenReturn(country);

        CountryDefaultDayRate cddrValue = mock(CountryDefaultDayRate.class);
        when(cddrValue.getCountryDefaultDayRate()).thenReturn(countryCoefficient);
        when(cddrValue.getCountryIc()).thenReturn(country);

        when(cddrRepository.findByCountryIc(country)).thenReturn(Optional.of(cddrValue));
    }
}
