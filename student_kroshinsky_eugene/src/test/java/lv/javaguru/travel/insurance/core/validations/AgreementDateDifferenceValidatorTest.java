package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class AgreementDateDifferenceValidatorTest {
    @Mock private ValidationError expectedError;
    @Mock private ValidationErrorFactory validationErrorFactory;
    @Mock private TravelCalculatePremiumRequestV1 request;
    @InjectMocks private AgreementDateDifferenceValidator validator;
    @Test
    public void injectedRepositoryAreNotNull() {
        assertNotNull(validationErrorFactory);
        assertNotNull(request);
        assertNotNull(validator);
        assertNotNull(expectedError);
    }
    @Test
    void validateNullDateFrom() {
        when(request.getAgreementDateFrom()).thenReturn(null);
        Optional<ValidationError> validationError = validator.validate(request);
        assertTrue(validationError.isEmpty());
    }
    @Test
    void validateNullDateTo() {
        when(request.getAgreementDateTo()).thenReturn(null);
        when(request.getAgreementDateFrom()).thenReturn(createDate("12.11.2030"));
        Optional<ValidationError> validationError = validator.validate(request);
        assertTrue(validationError.isEmpty());
    }
    @Test
    void validateWrongDateDifference() {
        when(request.getAgreementDateFrom()).thenReturn(createDate("30.11.2030"));
        when(request.getAgreementDateTo()).thenReturn(createDate("28.11.2030"));
        when(validationErrorFactory.createValidationError("ERROR_CODE_1")).thenReturn(expectedError);
        Optional<ValidationError> validationError = validator.validate(request);
        assertTrue(validationError.isPresent());
        assertEquals(expectedError, validationError.get());
    }
    @Test
    void validateRightDifference(){
        when(request.getAgreementDateFrom()).thenReturn(createDate("28.11.2030"));
        when(request.getAgreementDateTo()).thenReturn(createDate("30.11.2030"));
        Optional<ValidationError> validationError = validator.validate(request);
        assertTrue(validationError.isEmpty());
    }
    private Date createDate(String s) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(s);
        } catch (ParseException e) {
            throw new RuntimeException();
        }
    }
}