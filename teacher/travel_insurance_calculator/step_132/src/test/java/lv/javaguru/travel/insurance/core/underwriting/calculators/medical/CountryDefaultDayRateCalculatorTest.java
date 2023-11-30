package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import lv.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
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
class CountryDefaultDayRateCalculatorTest {

    @Mock private CountryDefaultDayRateRepository countryDefaultDayRateRepository;

    @InjectMocks
    private CountryDefaultDayRateCalculator calculator;

    private AgreementDTO agreement;

    @BeforeEach
    void setUp() {
        agreement = new AgreementDTO();
        agreement.setCountry("US");
    }

    @Test
    void shouldCalculateDayRateWhenCountryDayRateExists() {
        BigDecimal expectedDayRate = BigDecimal.valueOf(10.0);
        CountryDefaultDayRate countryDefaultDayRate = mock(CountryDefaultDayRate.class);
        when(countryDefaultDayRate.getDefaultDayRate()).thenReturn(expectedDayRate);
        when(countryDefaultDayRateRepository.findByCountryIc(agreement.getCountry()))
                .thenReturn(Optional.of(countryDefaultDayRate));
        BigDecimal result = calculator.calculate(agreement);
        assertEquals(expectedDayRate, result);
    }

    @Test
    void shouldThrowExceptionWhenCountryDayRateNotFound() {
        when(countryDefaultDayRateRepository.findByCountryIc(agreement.getCountry())).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> calculator.calculate(agreement));
        assertEquals("Country day rate not found by country id = " + agreement.getCountry(), exception.getMessage());
    }

}