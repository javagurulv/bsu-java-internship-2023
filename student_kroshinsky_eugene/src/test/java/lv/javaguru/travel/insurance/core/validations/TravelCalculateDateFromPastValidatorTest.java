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
class TravelCalculateDateFromPastValidatorTest {
    @Mock
    PropertyReader propertyReader;
    @Mock
    TravelCalculatePremiumRequest request;
    @InjectMocks
    TravelCalculateDateFromPastValidator validator;
    @Test
    void validateNullDateFromPast() {
        when(request.getAgreementDateFrom()).thenReturn(null);
        Optional<ValidationError> validationError = validator.validate(request);
        assertTrue(validationError.isEmpty());
    }
    @Test
    void validateDateFromPast() {
        when(request.getAgreementDateFrom()).thenReturn(createDate("15.11.2023"));
        when(propertyReader.getProperty("ERROR_CODE_2")).thenReturn("Date from past!");
        Optional<ValidationError> validationError = validator.validate(request);
        assertTrue(validationError.isPresent());
        assertEquals("Date from past!", validationError.get().getDescription());
        assertEquals("ERROR_CODE_2", validationError.get().getErrorCode());
    }
    @Test
    void validateNoErrorsDateFromPast() {
        when(request.getAgreementDateFrom()).thenReturn(createDate("30.11.2024"));
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