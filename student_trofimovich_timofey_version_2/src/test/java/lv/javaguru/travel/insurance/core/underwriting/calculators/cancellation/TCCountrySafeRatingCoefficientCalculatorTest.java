package lv.javaguru.travel.insurance.core.underwriting.calculators.cancellation;

import lv.javaguru.travel.insurance.core.api.dto.agreement.AgreementDTO;
import lv.javaguru.travel.insurance.core.domain.TCCountrySafeRatingCoefficient;
import lv.javaguru.travel.insurance.core.repositories.TCCountrySafeRatingCoefficientRepository;
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
public class TCCountrySafeRatingCoefficientCalculatorTest {

    @Mock
    private TCCountrySafeRatingCoefficientRepository repository;
    @InjectMocks
    private TCCountrySafeRatingCoefficientCalculator calculator;

    private AgreementDTO agreementDTO;
    private final String country = "SPAIN";
    private final BigDecimal expectedCoefficient = new BigDecimal("8.00");

    @BeforeEach
    void init() {
        agreementDTO = mock(AgreementDTO.class);
        when(agreementDTO.getCountry()).thenReturn(country);
    }
    @Test
    void shouldCalculateCoefficient() {
        TCCountrySafeRatingCoefficient tcCountrySafeRatingCoefficient = mock(TCCountrySafeRatingCoefficient.class);
        when(tcCountrySafeRatingCoefficient.getCoefficient()).thenReturn(expectedCoefficient);
        when(repository.findByCountryIc(country)).thenReturn(Optional.of(tcCountrySafeRatingCoefficient));
        BigDecimal coefficient = calculator.getCountrySafeRatingCoefficient(agreementDTO);
        assertThat(coefficient).isEqualTo(expectedCoefficient);
    }

    @Test
    void shouldThrowAnExceptionWhenCostCoefficientNotFound() {
        when(repository.findByCountryIc(country)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> calculator.getCountrySafeRatingCoefficient(agreementDTO));
        assertThat(exception.getMessage()).isEqualTo("Country safe rating not found for country: " + country);
    }

}
