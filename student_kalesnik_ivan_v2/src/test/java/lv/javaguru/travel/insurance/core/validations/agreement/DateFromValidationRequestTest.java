package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDto;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDto;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DateFromValidationRequestTest {

    @InjectMocks
    private DateFromValidationRequest validation;

    @Mock
    private ValidationErrorFactory errorFactory;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnErrorWhenDateFromIsNull() {
        AgreementDto request = new AgreementDto();
        request.setAgreementDateFrom(null);
        when(errorFactory.buildError("ERROR_CODE_2")).thenReturn(new ValidationErrorDto());

        Optional<ValidationErrorDto> errorOpt = validation.validate(request);

        assertTrue(errorOpt.isPresent());
    }

    @Test
    void shouldNotReturnErrorWhenDateFromIsNotNull() {
        AgreementDto request = new AgreementDto();
        request.setAgreementDateFrom(new Date());

        Optional<ValidationErrorDto> errorOpt = validation.validate(request);

        assertFalse(errorOpt.isPresent());
    }
}