package lv.javaguru.travel.insurance.core.underwriting.calculations.medical;

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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CountryDefaultDayRateCalculatorTest {
    @Mock
    CountryDefaultDayRateRepository repository;
    @InjectMocks CountryDefaultDayRateCalculator calculator;

    @Test
    void shouldCalculateDayRateWhenCountryDayRateExists() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        BigDecimal expectedDayRate = BigDecimal.valueOf(2.0);
        CountryDefaultDayRate countryDefaultDayRate = mock(CountryDefaultDayRate.class);
        when(countryDefaultDayRate.getCountryDefaultDayRate()).thenReturn(expectedDayRate);
        when(repository.findByCountryIc(request.getCountry()))
                .thenReturn(Optional.of(countryDefaultDayRate));
        BigDecimal result = calculator.getCountryDefaultDayRate(request);
        assertEquals(expectedDayRate, result);
    }

    @Test
    void shouldThrowExceptionWhenCountryDayRateNotFound() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(repository.findByCountryIc(request.getCountry())).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> calculator.getCountryDefaultDayRate(request));
        assertEquals("Country default day rate not found for country: " + request.getCountry(), exception.getMessage());
    }
}
