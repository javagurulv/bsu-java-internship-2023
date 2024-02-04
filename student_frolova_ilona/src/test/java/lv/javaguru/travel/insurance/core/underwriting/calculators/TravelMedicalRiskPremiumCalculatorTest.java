package lv.javaguru.travel.insurance.core.underwriting.calculators;

import lv.javaguru.travel.insurance.core.domain.AgeCoefficient;
import lv.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import lv.javaguru.travel.insurance.core.repositories.AgeCoefficientRepository;
import lv.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelMedicalRiskPremiumCalculatorTest {

    @Mock
    private DateTimeUtil dateTimeUtil;
    @Mock private CountryDefaultDayRateRepository countryDefaultDayRateRepository;

    @Mock private AgeCoefficientRepository ageCoefficientRepository;

    @InjectMocks
    private TravelMedicalRiskPremiumCalculator calculator;

    @Test
    public void shouldCalculatePremium() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getCountry()).thenReturn("SPAIN");
        when(request.getPersonBirthDate()).thenReturn(createDate("01.01.1980"));

        when(dateTimeUtil.getDifferenceInDays(any(), any())).thenReturn(BigDecimal.valueOf(2));
        when(dateTimeUtil.getCurrentDateTime()).thenReturn(createDate("04.02.2024"));

        CountryDefaultDayRate countryDefaultDayRate = mock(CountryDefaultDayRate.class);
        when(countryDefaultDayRate.getDefaultDayRate()).thenReturn(BigDecimal.TEN);

        AgeCoefficient ageCoefficient = mock(AgeCoefficient.class);
        when(ageCoefficient.getCoefficient()).thenReturn(BigDecimal.valueOf(1.20));

        when(countryDefaultDayRateRepository.findByCountryIc("SPAIN")).thenReturn(Optional.of(countryDefaultDayRate));
        when(ageCoefficientRepository.findCoefficientByAge(any())).thenReturn(Optional.of(ageCoefficient));

        BigDecimal premium = calculator.calculatePremium(request);
        assertEquals(premium.stripTrailingZeros(),
                new BigDecimal("24").stripTrailingZeros());
    }

    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
