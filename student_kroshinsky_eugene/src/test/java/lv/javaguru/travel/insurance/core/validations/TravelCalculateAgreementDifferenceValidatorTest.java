package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
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
class TravelCalculateAgreementDifferenceValidatorTest {
    @Mock
    ValidationErrorFactory validationErrorFactory;
    @Mock
    TravelCalculatePremiumRequest request;
    @InjectMocks
    TravelCalculateAgreementDifferenceValidator validator;
    @Test
    public void injectedRepositoryAreNotNull() {
        assertNotNull(validationErrorFactory);
        assertNotNull(request);
        assertNotNull(validator);
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
        when(request.getAgreementDateFrom()).thenReturn(createDate("12.11.2025"));
        Optional<ValidationError> validationError = validator.validate(request);
        assertTrue(validationError.isEmpty());
    }
    @Test
    void validateWrongDifference() {
        when(request.getAgreementDateFrom()).thenReturn(createDate("30.11.2024"));
        when(request.getAgreementDateTo()).thenReturn(createDate("28.11.2024"));
        ValidationError expectedError = new ValidationError("ERROR_CODE", "Description");
        when(validationErrorFactory.createValidationError("ERROR_CODE_1")).thenReturn(expectedError);
        Optional<ValidationError> validationError = validator.validate(request);
        assertTrue(validationError.isPresent());
        assertEquals("Description", validationError.get().getDescription());
        assertEquals("ERROR_CODE", validationError.get().getErrorCode());
    }
    @Test
    void validateRightDifference(){
        when(request.getAgreementDateFrom()).thenReturn(createDate("28.11.2024"));
        when(request.getAgreementDateTo()).thenReturn(createDate("30.11.2024"));
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