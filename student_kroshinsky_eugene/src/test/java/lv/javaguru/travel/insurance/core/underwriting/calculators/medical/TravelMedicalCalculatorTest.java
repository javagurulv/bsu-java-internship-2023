package lv.javaguru.travel.insurance.core.underwriting.calculators;

import lv.javaguru.travel.insurance.core.domain.AgeCoefficient;
import lv.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import lv.javaguru.travel.insurance.core.repositories.AgeCoefficientRepository;
import lv.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import lv.javaguru.travel.insurance.core.underwriting.calculators.medical.TravelMedicalCalculator;
import lv.javaguru.travel.insurance.core.utils.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TravelMedicalCalculatorTest {
    @Mock private AgeCoefficientRepository ageCoefficientRepository;
    @Mock private AgeCoefficient ageCoefficient;
    @Mock private DateTimeUtil dateTimeDifference;
    @Mock private CountryDefaultDayRateRepository repository;
    @Mock private CountryDefaultDayRate countryDefaultDayRate;
    @Mock private TravelCalculatePremiumRequest request;
    @InjectMocks private TravelMedicalCalculator travelMedicalCalculator;
    @Test
    public void injectedRepositoryAreNotNull() {
        assertNotNull(dateTimeDifference);
        assertNotNull(request);
        assertNotNull(repository);
        assertNotNull(travelMedicalCalculator);
        assertNotNull(countryDefaultDayRate);
    }
/*    @Test
    void calculatePremiumTest1() {
        when(request.getCountry()).thenReturn("Latvia");
        when(repository.findByIc(anyString())).thenReturn(Optional.of(countryDefaultDayRate));
        when(ageCoefficientRepository.findByAge(anyInt())).thenReturn(Optional.of(ageCoefficient));
        when(ageCoefficient.getCoefficient()).thenReturn(BigDecimal.valueOf(2));
        when(countryDefaultDayRate.getDayRate()).thenReturn(new BigDecimal(10));
        when(dateTimeDifference.calculateDateDifference(any(),any())).thenReturn(new BigDecimal(4));
        BigDecimal result = travelMedicalCalculator.calculatePremium(request);
        assertEquals(new BigDecimal(80), result);
    }
    @Test
    void calculatePremiumTest2() {
        when(request.getCountry()).thenReturn("Spain");
        when(repository.findByIc(anyString())).thenReturn(Optional.of(countryDefaultDayRate));
        when(countryDefaultDayRate.getDayRate()).thenReturn(new BigDecimal(2.5));
        when(dateTimeDifference.calculateDateDifference(any(),any())).thenReturn(new BigDecimal(4));
        BigDecimal result = travelMedicalCalculator.calculatePremium(request).setScale(0);
        assertEquals(new BigDecimal(10), result);
    }*/
}