package lv.javaguru.travel.insurance.core.underwriting.calculations.medical;

import lv.javaguru.travel.insurance.core.domain.AgeCoefficient;
import lv.javaguru.travel.insurance.core.repositories.AgeCoefficientRepository;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AgeCoefficientCalculatorTest {
    @Mock
    AgeCoefficientRepository repository;
    @InjectMocks
    AgeCoefficientCalculator ageCoefficientCalculator;
    @Test
    void getCorrectAgeCoefficient() {
        BigDecimal expectedCoefficient = BigDecimal.valueOf(1.2);
        int age = 0;
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getDateOfBirth()).thenReturn(new Date());
        AgeCoefficient ageCoefficient = mock(AgeCoefficient.class);
        when(ageCoefficient.getCoefficient()).thenReturn(expectedCoefficient);
        when(repository.findCoefficient(age)).thenReturn(Optional.of(ageCoefficient));
        assertThat(ageCoefficientCalculator.getAgeCoefficient(request)).isEqualTo(expectedCoefficient);
    }
    @Test
    void shouldThrowAnExceptionWhenAgeCoefficientNotFound() {
        int age = 0;
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getDateOfBirth()).thenReturn(new Date());
        when(repository.findCoefficient(age)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> ageCoefficientCalculator.getAgeCoefficient(request));
        assertThat(exception.getMessage()).isEqualTo("Age coefficient not found for age: " + age);
    }

}
