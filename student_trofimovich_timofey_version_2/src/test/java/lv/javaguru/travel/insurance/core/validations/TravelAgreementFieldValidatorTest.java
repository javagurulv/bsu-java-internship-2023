package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.api.dto.agreement.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.agreement.TravelAgreementFieldValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelAgreementFieldValidatorTest {
    @InjectMocks
    private TravelAgreementFieldValidator agreementFieldValidator;

    private TravelAgreementFieldValidation agreementFieldValidation1;
    private TravelAgreementFieldValidation agreementFieldValidation2;

    @BeforeEach
    void init() {
        agreementFieldValidation1 = mock(TravelAgreementFieldValidation.class);
        agreementFieldValidation2 = mock(TravelAgreementFieldValidation.class);
    }


    @Test
    void shouldNotReturnErrors() {

        AgreementDTO agreementDTO = mock(AgreementDTO.class);

        when(agreementFieldValidation1.validate(agreementDTO)).thenReturn(Optional.empty());
        when(agreementFieldValidation1.validateList(agreementDTO)).thenReturn(List.of());

        when(agreementFieldValidation2.validate(agreementDTO)).thenReturn(Optional.empty());
        when(agreementFieldValidation2.validateList(agreementDTO)).thenReturn(List.of());


        List<TravelAgreementFieldValidation> agreementValidationsList = List.of(agreementFieldValidation1, agreementFieldValidation2);
        ReflectionTestUtils.setField(agreementFieldValidator, "agreementValidations", agreementValidationsList);

        List<ValidationErrorDTO> errors = agreementFieldValidator.validate(agreementDTO);
        assertThat(errors.size()).isEqualTo(0);
    }

    @Test
    void shouldReturnSingleAgreementErrors() {

        AgreementDTO agreementDTO = mock(AgreementDTO.class);

        when(agreementFieldValidation1.validate(agreementDTO)).thenReturn(Optional.of(mock(ValidationErrorDTO.class)));
        when(agreementFieldValidation2.validate(agreementDTO)).thenReturn(Optional.of(mock(ValidationErrorDTO.class)));

        List<TravelAgreementFieldValidation> agreementValidationsList = List.of(agreementFieldValidation1, agreementFieldValidation2);
        ReflectionTestUtils.setField(agreementFieldValidator, "agreementValidations", agreementValidationsList);

        List<ValidationErrorDTO> errors = agreementFieldValidator.validate(agreementDTO);
        assertThat(errors.size()).isGreaterThan(0);
        assertThat(errors.size()).isEqualTo(2);
    }

    @Test
    void shouldReturnAgreementListErrors() {

        AgreementDTO agreementDTO = mock(AgreementDTO.class);


        when(agreementFieldValidation1.validate(agreementDTO)).thenReturn(Optional.empty());
        when(agreementFieldValidation1.validateList(agreementDTO)).thenReturn(List.of(mock(ValidationErrorDTO.class), mock(ValidationErrorDTO.class)));
        when(agreementFieldValidation2.validate(agreementDTO)).thenReturn(Optional.empty());
        when(agreementFieldValidation2.validateList(agreementDTO)).thenReturn(List.of(mock(ValidationErrorDTO.class), mock(ValidationErrorDTO.class)));

        List<TravelAgreementFieldValidation> agreementValidationsList = List.of(agreementFieldValidation1, agreementFieldValidation2);
        ReflectionTestUtils.setField(agreementFieldValidator, "agreementValidations", agreementValidationsList);

        List<ValidationErrorDTO> errors = agreementFieldValidator.validate(agreementDTO);
        assertThat(errors.size()).isGreaterThan(0);
        assertThat(errors.size()).isEqualTo(4);
    }
}
