package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.domain.AgeCoefficient;
import lv.javaguru.travel.insurance.core.repositories.AgeCoefficientRepository;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CalculateMedicalFindAgeCoefficientTest {
    @Mock
    private DateTimeUtil dateTimeUtilMock;
    @Mock
    private AgeCoefficientRepository ageCoefficientRepositoryMock;
    @Mock
    private AgeCoefficient ageCoefficientMock;
    @InjectMocks
    private Calculate_MEDICAL_FindAgeCoefficient calculator;
    private TravelCalculatePremiumRequest request;

    @BeforeEach
    void setUp() {
        request = new TravelCalculatePremiumRequest();
        request.setPersonBirthDate(Date.from(LocalDate.of(1990, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
    }
    @Test
    void shouldFindCoefficientWhenAgeCoefficientExists() {
        LocalDate currentDate = LocalDate.of(2023, 3, 27);
        int age = 33;
        BigDecimal expectedCoefficient = BigDecimal.valueOf(1.2);

        when(dateTimeUtilMock.getTodaysDateAndTime()).thenReturn(Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        when(ageCoefficientMock.getCoefficient()).thenReturn(expectedCoefficient);
        when(ageCoefficientRepositoryMock.findCoefficient(age)).thenReturn(Optional.of(ageCoefficientMock));

        BigDecimal result = calculator.findAgeCoefficient(request);

        assertEquals(expectedCoefficient, result);
    }
    @Test
    void shouldThrowExceptionWhenAgeCoefficientNotFound() {
        LocalDate currentDate = LocalDate.of(2023, 3, 27);
        int age = 33;

        when(dateTimeUtilMock.getTodaysDateAndTime()).thenReturn(Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        when(ageCoefficientRepositoryMock.findCoefficient(age)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> calculator.findAgeCoefficient(request));

        assertEquals("Age coefficient not found for age = " + age, exception.getMessage());
        verifyNoInteractions(ageCoefficientMock);
    }
}
