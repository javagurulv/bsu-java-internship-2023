package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.core.validations.TravelRequestPersonFirstNameValidation;
import lv.javaguru.travel.insurance.core.validations.TravelRequestValidation;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumRequestValidatorTest {
@InjectMocks
    TravelCalculatePremiumRequestValidator requestValidator = new TravelCalculatePremiumRequestValidator();
    @Mock TravelCalculatePremiumRequest request;

    @Test
    public void responseShouldContainErrorTest() {
        TravelRequestValidation requestValidation1 = mock(TravelRequestValidation.class);
        when(requestValidation1.validate(request)).thenReturn(Optional.of(new ValidationError()));
        TravelRequestValidation requestValidation2 = mock(TravelRequestValidation.class);
        when(requestValidation2.validate(request)).thenReturn(Optional.of(new ValidationError()));
        requestValidator.travelRequestValidations = List.of(requestValidation1, requestValidation2);
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 2);
    }


    @Test
    public void responseNotContainErrorTest() {
        TravelRequestValidation requestValidation1 = mock(TravelRequestValidation.class);
        when(requestValidation1.validate(request)).thenReturn(Optional.empty());
        TravelRequestValidation requestValidation2 = mock(TravelRequestValidation.class);
        when(requestValidation2.validate(request)).thenReturn(Optional.empty());
        requestValidator.travelRequestValidations = List.of(requestValidation1, requestValidation2);
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 0);
    }

}