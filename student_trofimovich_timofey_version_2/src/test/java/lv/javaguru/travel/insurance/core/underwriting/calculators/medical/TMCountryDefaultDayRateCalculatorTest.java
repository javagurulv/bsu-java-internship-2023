package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.api.dto.agreement.AgreementDTO;
import lv.javaguru.travel.insurance.core.domain.TMCountryDefaultDayRate;
import lv.javaguru.travel.insurance.core.repositories.TMCountryDefaultDayRateRepository;
import org.junit.jupiter.api.BeforeEach;
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
public class TMCountryDefaultDayRateCalculatorTest {
    @Mock
    private TMCountryDefaultDayRateRepository repository;
    @InjectMocks
    private CountryDefaultDayRateCalculator calculator;
    private AgreementDTO agreement;

    @BeforeEach
    void init() {
        agreement = mock(AgreementDTO.class);
    }

    @Test
    void shouldCalculateDayRateWhenCountryDayRateExists() {
        BigDecimal expectedDayRate = BigDecimal.valueOf(2.0);
        TMCountryDefaultDayRate TMCountryDefaultDayRate = mock(TMCountryDefaultDayRate.class);
        when(TMCountryDefaultDayRate.getCountryDefaultDayRate()).thenReturn(expectedDayRate);
        when(repository.findByCountryIc(agreement.getCountry()))
                .thenReturn(Optional.of(TMCountryDefaultDayRate));
        BigDecimal result = calculator.getCountryDefaultDayRate(agreement);
        assertEquals(expectedDayRate, result);
    }

    @Test
    void shouldThrowExceptionWhenCountryDayRateNotFound() {
        when(repository.findByCountryIc(agreement.getCountry())).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> calculator.getCountryDefaultDayRate(agreement));
        assertEquals("Country default day rate not found for country: " + agreement.getCountry(), exception.getMessage());
    }
}
