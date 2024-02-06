package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.utils.AgeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculateAgeValidatorTest {
    @Mock
    private ValidationError expectedError;
    @Mock
    private AgeUtil ageUtil;
    @Mock
    private ValidationErrorFactory validationErrorFactory;
    @Mock
    private TravelCalculatePremiumRequest request;
    @InjectMocks
    private TravelCalculateAgeValidator validator;
    @Test
    public void injectedRepositoryAreNotNull() {
        assertNotNull(validationErrorFactory);
        assertNotNull(request);
        assertNotNull(validator);
    }

    @Test
    void validateNoErrors() {
        ReflectionTestUtils.setField(validator, "ageCoefficientEnabled", true);
        when(request.getDateOfBirth()).thenReturn(createDate("12.11.2020"));
        Optional<ValidationError> validationError = validator.validate(request);
        assertTrue(validationError.isEmpty());
    }
    @Test
    void validateNegativeAge() {
        ReflectionTestUtils.setField(validator, "ageCoefficientEnabled", true);
        when(request.getDateOfBirth()).thenReturn(createDate("28.11.1900"));
        when(ageUtil.calculateAge(request)).thenReturn(-5);
        when(validationErrorFactory.createValidationError("ERROR_CODE_11")).thenReturn(expectedError);
        Optional<ValidationError> validationError = validator.validate(request);
        assertTrue(validationError.isPresent());
        assertEquals(expectedError, validationError.get());
    }
    @Test
    void validateNegativeRiskEnable() {
        ReflectionTestUtils.setField(validator, "ageCoefficientEnabled", false);

        Optional<ValidationError> validationError = validator.validate(request);
        assertTrue(validationError.isEmpty());
    }
    @Test
    void validateToBigAge(){
        ReflectionTestUtils.setField(validator, "ageCoefficientEnabled", true);
        when(request.getDateOfBirth()).thenReturn(createDate("28.11.1900"));
        when(ageUtil.calculateAge(request)).thenReturn(195);
        when(validationErrorFactory.createValidationError("ERROR_CODE_11")).thenReturn(expectedError);
        Optional<ValidationError> validationError = validator.validate(request);
        assertTrue(validationError.isPresent());
        assertEquals(expectedError, validationError.get());
    }
    private Date createDate(String s) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(s);
        } catch (ParseException e) {
            throw new RuntimeException();
        }
    }
}