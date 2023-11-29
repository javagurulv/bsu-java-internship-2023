package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.domain.AgeCoefficient;
import lv.javaguru.travel.insurance.core.repositories.AgeCoefficientRepository;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class AgeCoefficientCalculatorTest {
    @InjectMocks
    private AgeCoefficientCalculator ageCoefficientCalculator;
    @Mock
    private AgeCoefficientRepository ageCoefficientRepository;
    @Mock private DateTimeUtil dateTimeUtil;
    @Mock private TravelCalculatePremiumRequestV1 request;

    @Test
    public void shouldReturn1Test(){
        ReflectionTestUtils.setField(ageCoefficientCalculator, "ageCoefficientEnabled", false);
        assertEquals(ageCoefficientCalculator.calculate(request), BigDecimal.valueOf(1));
    }
    @Test
    public void calculateDayCountTest(){
        Date date =createDate("03.04.2003");
        when(request.getBirthday()).thenReturn(date);
        when(dateTimeUtil.getCurrentDateTime()).thenReturn(createDate("03.04.2023"));
        ReflectionTestUtils.setField(ageCoefficientCalculator, "ageCoefficientEnabled", true);
        AgeCoefficient ageCoefficient = mock(AgeCoefficient.class);
        when(ageCoefficient.getCoefficient()).thenReturn(BigDecimal.valueOf(1.2));
        when(ageCoefficientRepository.findByAge(20)).thenReturn(Optional.of(ageCoefficient));
        assertEquals(ageCoefficientCalculator.calculate(request), BigDecimal.valueOf(1.2));
    }
    @Test
    public void throwExceptionDayCountTest(){
        Date date =createDate("03.04.2033");
        when(request.getBirthday()).thenReturn(date);
        when(dateTimeUtil.getCurrentDateTime()).thenReturn(createDate("03.04.2023"));
        ReflectionTestUtils.setField(ageCoefficientCalculator, "ageCoefficientEnabled", true);
        Throwable thrown = assertThrows(RuntimeException.class, ()->ageCoefficientCalculator.calculate(request));
        assertEquals(thrown.getMessage(),
                "coefficient for person with birthday 03.04.2033 not found");
    }
    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
