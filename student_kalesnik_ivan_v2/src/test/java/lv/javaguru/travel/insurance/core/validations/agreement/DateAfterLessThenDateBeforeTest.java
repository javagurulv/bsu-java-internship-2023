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

class DateAfterLessThenDateBeforeTest {

    @InjectMocks
    private DateAfterLessThenDateBefore validation;

    @Mock
    private ValidationErrorFactory errorFactory;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnErrorWhenDateFromIsAfterDateTo() {
        AgreementDto request = new AgreementDto();
        request.setAgreementDateFrom(new Date(2025, 1, 10));
        request.setAgreementDateTo(new Date(2025, 1, 1));
        when(errorFactory.buildError("ERROR_CODE_5")).thenReturn(new ValidationErrorDto());

        Optional<ValidationErrorDto> errorOpt = validation.validate(request);

        assertTrue(errorOpt.isPresent());
    }

    @Test
    void shouldReturnErrorWhenDateFromIsEqualsDateTo() {
        AgreementDto request = new AgreementDto();
        request.setAgreementDateFrom(new Date(2025, 1, 1));
        request.setAgreementDateTo(new Date(2025, 1, 1));
        when(errorFactory.buildError("ERROR_CODE_5")).thenReturn(new ValidationErrorDto());

        Optional<ValidationErrorDto> errorOpt = validation.validate(request);

        assertTrue(errorOpt.isPresent());
    }

    @Test
    void shouldNotReturnErrorWhenDateFromIsLessDateTo() {
        AgreementDto request = new AgreementDto();
        request.setAgreementDateFrom(new Date(2025, 1, 1));
        request.setAgreementDateTo(new Date(2025, 1, 10));

        Optional<ValidationErrorDto> errorOpt = validation.validate(request);

        assertFalse(errorOpt.isPresent());
    }
}