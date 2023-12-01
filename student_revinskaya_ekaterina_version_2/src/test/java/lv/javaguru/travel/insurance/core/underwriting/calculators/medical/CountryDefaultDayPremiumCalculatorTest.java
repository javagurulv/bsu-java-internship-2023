package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import lv.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
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
public class CountryDefaultDayPremiumCalculatorTest {
    @InjectMocks
    private CountryDefaultDayPremiumCalculator defaultDayPremiumCalculator;
    @Mock
    private CountryDefaultDayRateRepository countryDefaultDayRateRepository;
    @Mock private AgreementDTO agreementDTO;
    @Test
    public void calculateDayCountTest(){
        when(agreementDTO.getCountry()).thenReturn("SPAIN");
        CountryDefaultDayRate countryDefaultDayRate = mock(CountryDefaultDayRate.class);
        when(countryDefaultDayRate.getDefaultDayRate()).thenReturn(BigDecimal.valueOf(1.2));
        when(countryDefaultDayRateRepository.findByCountryIc("SPAIN")).thenReturn(Optional.of(countryDefaultDayRate));
        assertEquals(defaultDayPremiumCalculator.calculate(agreementDTO), BigDecimal.valueOf(1.2));
    }
    @Test
    public void throwExceptionCountryDefaultDayRateTest(){
        when(agreementDTO.getCountry()).thenReturn("FAKE_COUNTRY");
        when(countryDefaultDayRateRepository.findByCountryIc("FAKE_COUNTRY")).thenReturn(Optional.empty());
        Throwable thrown = assertThrows(RuntimeException.class, ()->defaultDayPremiumCalculator.calculate(agreementDTO));
        assertEquals(thrown.getMessage(), "default day rate for country FAKE_COUNTRY not found");
    }
}
