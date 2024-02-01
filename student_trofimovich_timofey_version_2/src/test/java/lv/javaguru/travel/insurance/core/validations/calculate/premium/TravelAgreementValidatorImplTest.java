package lv.javaguru.travel.insurance.core.validations.calculate.premium;

import lv.javaguru.travel.insurance.core.api.dto.agreement.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)


public class TravelAgreementValidatorImplTest {

    @Mock
    private TravelPersonFieldValidator personFieldValidator;

    @Mock
    private TravelAgreementFieldValidator agreementFieldValidator;
    @InjectMocks
    private TravelAgreementValidatorImpl agreementValidatorImpl;

    private AgreementDTO agreement;

    @BeforeEach
    void init() {
        agreement = mock(AgreementDTO.class);
    }

    @Test
    void shouldNotReturnErrors() {

        when(personFieldValidator.validate(agreement)).thenReturn(List.of());
        when(agreementFieldValidator.validate(agreement)).thenReturn(List.of());

        List<ValidationErrorDTO> errors = agreementValidatorImpl.validate(agreement);
        assertThat(errors.size()).isEqualTo(0);
    }

    @Test
    void shouldReturnAgreementFieldErrors() {

        when(agreementFieldValidator.validate(agreement)).thenReturn(List.of(mock(ValidationErrorDTO.class),
                mock(ValidationErrorDTO.class)));
        when(personFieldValidator.validate(agreement)).thenReturn(List.of());

        List<ValidationErrorDTO> errors = agreementValidatorImpl.validate(agreement);
        assertThat(errors.size()).isGreaterThan(0);
        assertThat(errors.size()).isEqualTo(2);
    }

    @Test
    void shouldReturnPersonFieldErrors() {

        when(personFieldValidator.validate(agreement)).thenReturn(List.of(mock(ValidationErrorDTO.class),
                mock(ValidationErrorDTO.class)));
        when(agreementFieldValidator.validate(agreement)).thenReturn(List.of());

        List<ValidationErrorDTO> errors = agreementValidatorImpl.validate(agreement);
        assertThat(errors.size()).isGreaterThan(0);
        assertThat(errors.size()).isEqualTo(2);
    }

    @Test
    void shouldReturnBothAgreementAndPersonErrors() {

        when(personFieldValidator.validate(agreement)).thenReturn(List.of(mock(ValidationErrorDTO.class),
                mock(ValidationErrorDTO.class)));
        when(agreementFieldValidator.validate(agreement)).thenReturn(List.of(mock(ValidationErrorDTO.class),
                mock(ValidationErrorDTO.class)));

        List<ValidationErrorDTO> errors = agreementValidatorImpl.validate(agreement);
        assertThat(errors.size()).isGreaterThan(0);
        assertThat(errors.size()).isEqualTo(4);
    }
}
