package lv.javaguru.travel.insurance.core.underwriting.calculators.cancellation;


import lv.javaguru.travel.insurance.core.api.dto.person.PersonDTO;
import lv.javaguru.travel.insurance.core.domain.TCAgeCoefficient;
import lv.javaguru.travel.insurance.core.repositories.TCAgeCoefficientRepository;
import org.junit.jupiter.api.BeforeEach;
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
public class TripCancellationAgeCoefficientCalculatorTest {
    @Mock
    private TCAgeCoefficientRepository repository;

    @InjectMocks
    private TripCancellationAgeCoefficientCalculator calculator;

    private PersonDTO personDTO;
    private final BigDecimal expectedCoefficient = new BigDecimal("30.00");

    @BeforeEach
    void init() {
        personDTO = mock(PersonDTO.class);
        when(personDTO.getPersonBirthDate()).thenReturn(new Date());
    }
    @Test
    void shouldCalculateCoefficient() {
        TCAgeCoefficient tcAgeCoefficient = mock(TCAgeCoefficient.class);
        when(tcAgeCoefficient.getCoefficient()).thenReturn(expectedCoefficient);
        when(repository.findCoefficient(0)).thenReturn(Optional.of(tcAgeCoefficient));
        BigDecimal coefficient = calculator.calculateAgeCoefficient(personDTO);
        assertThat(coefficient).isEqualTo(expectedCoefficient);
    }

    @Test
    void shouldThrowAnExceptionWhenAgeCoefficientNotFound() {
        when(repository.findCoefficient(0)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> calculator.calculateAgeCoefficient(personDTO));
        assertThat(exception.getMessage()).isEqualTo("Age coefficient not found for age: " + 0);
    }
}
