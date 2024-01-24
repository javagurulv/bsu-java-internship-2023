package lv.javaguru.travel.insurance.core.validations.calculate.premium.agreement;

import lv.javaguru.travel.insurance.core.api.dto.agreement.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import lv.javaguru.travel.insurance.core.validations.calculate.premium.agreement.DateFromValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class DateFromValidationTest {
    @Mock
    private ValidationErrorFactory factory;
    @InjectMocks
    private DateFromValidation validation;
    private AgreementDTO agreement;

    @BeforeEach
    void init() {
        agreement = mock(AgreementDTO.class);
    }

    @ParameterizedTest
    @NullSource
    void shouldReturnErrorWhenAgreementDateFromIsNull(Date date) {
        when(agreement.getAgreementDateFrom()).thenReturn(date);
        when(factory.buildError("ERROR_CODE_3")).thenReturn(new ValidationErrorDTO("ERROR_CODE_3", "Date from field must not be empty!"));
        Optional<ValidationErrorDTO> validationError = validation.validate(agreement);
        assertThat(validationError).isPresent();
        assertThat(validationError.get().getErrorCode()).isEqualTo("ERROR_CODE_3");
        assertThat(validationError.get().getDescription()).isEqualTo("Date from field must not be empty!");
    }

    @Test
    void shouldNotReturnError() {
        when(agreement.getAgreementDateFrom()).thenReturn(new Date());
        Optional<ValidationErrorDTO> validationError = validation.validate(agreement);
        assertThat(validationError).isEmpty();
    }
}


