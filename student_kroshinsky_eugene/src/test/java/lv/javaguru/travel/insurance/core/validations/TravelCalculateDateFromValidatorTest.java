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
class TravelCalculateDateFromValidatorTest {
    @Mock
    TravelCalculatePremiumRequest request;
    @InjectMocks
    TravelCalculateDateFromValidator validator;
    @Test
    void validateNullDateFrom() {
        when(request.getAgreementDateFrom()).thenReturn(null);
        Optional<ValidationError> validationError = validator.validate(request);
        assertTrue(validationError.isPresent());
        assertEquals("Must not be empty!", validationError.get().getMessage());
        assertEquals("agreementDateFrom", validationError.get().getField());
    }
    @Test
    void validateNoErrorsDateFrom() {
        when(request.getAgreementDateFrom()).thenReturn(createDate("24.11.2024"));
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