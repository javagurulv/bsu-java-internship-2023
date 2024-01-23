package lv.javaguru.travel.insurance.core.underwriting.calculators.cancellation;


import lv.javaguru.travel.insurance.core.api.dto.agreement.AgreementDTO;
import lv.javaguru.travel.insurance.core.domain.TravelCostCoefficient;
import lv.javaguru.travel.insurance.core.repositories.TravelCostCoefficientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelCostCoefficientCalculatorTest {
    @Mock
    private TravelCostCoefficientRepository repository;

    @InjectMocks
    private TravelCostCoefficientCalculator calculator;

    private AgreementDTO agreementDTO;
    private final BigDecimal cost = new BigDecimal("7777.77");
    private final BigDecimal expectedCoefficient = new BigDecimal("100.00");

    @BeforeEach
    void init() {
        agreementDTO = mock(AgreementDTO.class);
        when(agreementDTO.getAgreementPremium()).thenReturn(cost);
    }
    @Test
    void shouldCalculateCoefficient() {
        TravelCostCoefficient travelCostCoefficient = mock(TravelCostCoefficient.class);
        when(travelCostCoefficient.getCoefficient()).thenReturn(expectedCoefficient);
        when(repository.findCoefficient(cost)).thenReturn(Optional.of(travelCostCoefficient));
        BigDecimal coefficient = calculator.getCostCoefficient(agreementDTO);
        assertThat(coefficient).isEqualTo(expectedCoefficient);
    }

    @Test
    void shouldThrowAnExceptionWhenCostCoefficientNotFound() {
        when(repository.findCoefficient(cost)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> calculator.getCostCoefficient(agreementDTO));
        assertThat(exception.getMessage()).isEqualTo("Travel cost coefficient calculator not found for cost: " + cost);
    }
}
