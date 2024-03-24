package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import lv.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CalculateMedicalFindCountryDefaultDayRateTest {
    @Mock
    private CountryDefaultDayRateRepository countryDefaultDayRateRepositoryMock;
    @Mock
    private CountryDefaultDayRate countryDefaultDayRateMock;
    @InjectMocks
    private Calculate_MEDICAL_FindCountryDefaultDayRate calculator;
    private TravelCalculatePremiumRequest request;

    @BeforeEach
    void setUp() {
        request = new TravelCalculatePremiumRequest();
        request.setCountry("US");
    }
    @Test
    void shouldCalculateDayRateWhenCountryDayRateExists() {
        BigDecimal expectedDayRate = BigDecimal.valueOf(10.0);
        when(countryDefaultDayRateMock.getDefaultDayRate()).thenReturn(expectedDayRate);
        when(countryDefaultDayRateRepositoryMock.findByCountryIc(request.getCountry())).thenReturn(Optional.of(countryDefaultDayRateMock));

        BigDecimal result = calculator.findCountryDefaultDayRate(request);

        assertEquals(expectedDayRate, result);
    }
    @Test
    void shouldThrowExceptionWhenCountryDayRateNotFound() {
        when(countryDefaultDayRateRepositoryMock.findByCountryIc(request.getCountry())).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> calculator.findCountryDefaultDayRate(request));

        assertEquals("Country day rate not found by country id = " + request.getCountry(), exception.getMessage());
        verifyNoInteractions(countryDefaultDayRateMock);
    }
}
