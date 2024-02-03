package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import lv.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import lv.javaguru.travel.insurance.core.utils.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CountryDefaultDayRateCalculatorTest {
    @Mock private TravelCalculatePremiumRequest request;
    @Mock private CountryDefaultDayRateRepository repository;
    @Mock private CountryDefaultDayRate countryDefaultDayRate;
    @InjectMocks private CountryDefaultDayRateCalculator calculator;
    @Test
    void injectedObjectsAreNotNull() {
        assertNotNull(calculator);
        assertNotNull(request);
        assertNotNull(repository);
        assertNotNull(countryDefaultDayRate);
    }
    @Test
    void calculateTest() {
        when(request.getCountry()).thenReturn("country");
        when(repository.findByIc(anyString())).thenReturn(Optional.of(countryDefaultDayRate));
        BigDecimal expectedResult = BigDecimal.valueOf(1);
        when(countryDefaultDayRate.getDayRate()).thenReturn(expectedResult);

        BigDecimal result = calculator.calculate(request);
        assertEquals(expectedResult, result);
    }

}