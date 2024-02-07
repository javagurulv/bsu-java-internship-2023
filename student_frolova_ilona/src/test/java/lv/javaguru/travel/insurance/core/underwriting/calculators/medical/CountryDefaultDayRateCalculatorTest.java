package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import lv.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CountryDefaultDayRateCalculatorTest {
    @Mock private CountryDefaultDayRateRepository repository;

    @InjectMocks private CountryDefaultDayRateCalculator calculator;

    @Test
    public void shouldCalculateDayRateCorrectly() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getCountry()).thenReturn("SPAIN");

        CountryDefaultDayRate dayRate = mock(CountryDefaultDayRate.class);
        when(dayRate.getDefaultDayRate()).thenReturn(BigDecimal.valueOf(2.5));

        when(repository.findByCountryIc(any())).thenReturn(Optional.of(dayRate));

        BigDecimal result = calculator.calculate(request);
        assertEquals(BigDecimal.valueOf(2.5), result);
    }
}
