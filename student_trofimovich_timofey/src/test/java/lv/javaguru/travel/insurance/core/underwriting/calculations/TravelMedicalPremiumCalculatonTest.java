package lv.javaguru.travel.insurance.core.underwriting.calculations;

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
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelMedicalPremiumCalculatonTest {
    @Mock
    DateTimeUtil dateTimeUtil;
    @Mock
    CountryDefaultDayRateRepository rateRepository;
    @Mock
    AgeCoefficientRepository coefficientRepository;
    @InjectMocks TravelMedicalPremiumCalculation medicalPremiumCalculation;

    @Test
    public void shouldReturnCorrectPremium() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        BigDecimal daysCount = BigDecimal.valueOf(10);
        BigDecimal countryDefaultRate = BigDecimal.valueOf(20);
        BigDecimal ageCoefficient = BigDecimal.valueOf(1.2);

        when(dateTimeUtil.getDaysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo())).thenReturn(daysCount.longValue());
        CountryDefaultDayRate countryDefaultDayRate = mock(CountryDefaultDayRate.class);
        when(countryDefaultDayRate.getCountryDefaultDayRate()).thenReturn(countryDefaultRate);
        when(rateRepository.findByCountryIc(request.getCountry())).thenReturn(Optional.of(countryDefaultDayRate));
        when(request.getDateOfBirth()).thenReturn(new Date());
        AgeCoefficient ageCoefficientDomain = mock(AgeCoefficient.class);
        when(ageCoefficientDomain.getCoefficient()).thenReturn(ageCoefficient);
        when(coefficientRepository.findCoefficient(calculateAge(request))).thenReturn(Optional.of(ageCoefficientDomain));

        BigDecimal expectedPremium = countryDefaultRate.multiply(daysCount).multiply(ageCoefficient)
                .setScale(2, RoundingMode.HALF_UP);

        BigDecimal result = medicalPremiumCalculation.calculatePremium(request);

        assertEquals(expectedPremium.stripTrailingZeros(), result.stripTrailingZeros());
    }

    private Integer calculateAge(TravelCalculatePremiumRequest request) {
        LocalDate personBirthDate = toLocalDate(request.getDateOfBirth());
        LocalDate currentDate = toLocalDate(new Date());
        return Period.between(personBirthDate, currentDate).getYears();
    }

    private LocalDate toLocalDate(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }


}
