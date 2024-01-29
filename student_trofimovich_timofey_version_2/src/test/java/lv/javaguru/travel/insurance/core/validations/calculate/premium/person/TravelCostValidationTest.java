package lv.javaguru.travel.insurance.core.validations.calculate.premium.person;

import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.api.dto.agreement.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.person.PersonDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelCostValidationTest {

    @Mock
    private ValidationErrorFactory factory;
    @InjectMocks
    private TravelCostValidation validation;
    private PersonDTO person;
    private AgreementDTO agreement;

    @BeforeEach
    void init() {
        person = mock(PersonDTO.class);
        agreement = mock(AgreementDTO.class);
    }

    @Test
    void shouldNotReturnErrorWhenCancellationRiskIsNotIncluded() {
        when(agreement.getSelectedRisks()).thenReturn(List.of());
        Optional<ValidationErrorDTO> error = validation.validate(person, agreement);
        assertThat(error).isEmpty();
    }

    @Test
    void shouldReturnEmptyOrNullLimitLevelError() {
        when(agreement.getSelectedRisks()).thenReturn(List.of("TRAVEL_CANCELLATION"));
        when(person.getTravelCost()).thenReturn(null);
        when(factory.buildError("ERROR_CODE_19")).thenReturn(new ValidationErrorDTO());
        assertThat(validation.validate(person, agreement)).isPresent();
    }


    @Test
    void shouldNotReturnError() {
        when(agreement.getSelectedRisks()).thenReturn(List.of("TRAVEL_CANCELLATION"));
        when(person.getTravelCost()).thenReturn(BigDecimal.ONE);
        assertThat(validation.validate(person, agreement)).isEmpty();
    }
}
