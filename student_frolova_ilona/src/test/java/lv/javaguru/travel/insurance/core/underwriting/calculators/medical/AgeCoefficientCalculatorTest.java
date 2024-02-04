package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.domain.AgeCoefficient;
import lv.javaguru.travel.insurance.core.repositories.AgeCoefficientRepository;
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
public class AgeCoefficientCalculatorTest {

    @Mock private DateTimeUtil dateTimeUtil;
    @Mock private AgeCoefficientRepository repository;

    @InjectMocks private AgeCoefficientCalculator calculator;

    @Test
    public void shouldCalculateAgeCoefficientCorrectly() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        AgeCoefficient ageCoefficient = mock(AgeCoefficient.class);

        when(request.getPersonBirthDate()).thenReturn(createDate("01.01.2000"));
        when(dateTimeUtil.getCurrentDateTime()).thenReturn(createDate("01.01.2070"));

        when(repository.findCoefficientByAge(any())).thenReturn(Optional.of(ageCoefficient));
        when(ageCoefficient.getCoefficient()).thenReturn(BigDecimal.valueOf(1.5));

        BigDecimal result = calculator.calculate(request);
        assertEquals(BigDecimal.valueOf(1.5), result);
    }

    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
