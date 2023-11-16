package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TravelCalculatePremiumRequestValidatorTest {
    private  TravelCalculatePremiumRequest request;

    private List<ValidationError> validationErrorList;
    private TravelCalculatePremiumRequestValidator validator = new TravelCalculatePremiumRequestValidator();

    @Test
    public void validateTestNotEmpty(){
        request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Eugene");
        when(request.getPersonLastName()).thenReturn("Kroshinsky");
        when(request.getAgreementDateFrom()).thenReturn(new Date(2023, 11, 14));
        when(request.getAgreementDateTo()).thenReturn(new Date(2023, 11, 16));

        validationErrorList = validator.validate(request);
        assertTrue(validationErrorList.isEmpty());
        assertEquals(validationErrorList.size(), 0);
    }
    @Test
    public void validateTestNullFirstName(){
        request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn(null);
        when(request.getPersonLastName()).thenReturn("Kroshinsky");
        when(request.getAgreementDateFrom()).thenReturn(new Date(2023, 11, 14));
        when(request.getAgreementDateTo()).thenReturn(new Date(2023, 11, 16));

        validationErrorList = validator.validate(request);
        assertFalse(validationErrorList.isEmpty());
        assertEquals(validationErrorList.size(), 1);
        assertEquals(validationErrorList.get(0).getMessage(), "Must not be empty!");
        assertEquals(validationErrorList.get(0).getField(), "personFirstName");
    }
    @Test
    public void validateTestEmptyFirstName(){
        request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("");
        when(request.getPersonLastName()).thenReturn("Kroshinsky");
        when(request.getAgreementDateFrom()).thenReturn(new Date(2023, 11, 14));
        when(request.getAgreementDateTo()).thenReturn(new Date(2023, 11, 16));

        validationErrorList = validator.validate(request);
        assertFalse(validationErrorList.isEmpty());
        assertEquals(validationErrorList.size(), 1);
        assertEquals(validationErrorList.get(0).getMessage(), "Must not be empty!");
        assertEquals(validationErrorList.get(0).getField(), "personFirstName");
    }
    @Test
    public void validateTestNullLastName(){
        request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Eugene");
        when(request.getPersonLastName()).thenReturn(null);
        when(request.getAgreementDateFrom()).thenReturn(new Date(2023, 11, 14));
        when(request.getAgreementDateTo()).thenReturn(new Date(2023, 11, 16));

        validationErrorList = validator.validate(request);
        assertFalse(validationErrorList.isEmpty());
        assertEquals(validationErrorList.size(), 1);
        assertEquals(validationErrorList.get(0).getMessage(), "Must not be empty!");
        assertEquals(validationErrorList.get(0).getField(), "personLastName");
    }
    @Test
    public void validateTestEmptyLastName(){
        request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Eugene");
        when(request.getPersonLastName()).thenReturn("");
        when(request.getAgreementDateFrom()).thenReturn(new Date(2023, 11, 14));
        when(request.getAgreementDateTo()).thenReturn(new Date(2023, 11, 16));

        validationErrorList = validator.validate(request);
        assertFalse(validationErrorList.isEmpty());
        assertEquals(validationErrorList.size(), 1);
        assertEquals(validationErrorList.get(0).getMessage(), "Must not be empty!");
        assertEquals(validationErrorList.get(0).getField(), "personLastName");
    }
    @Test
    public void validateTestEmptyLastNameAndFirstName(){
        request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("");
        when(request.getPersonLastName()).thenReturn("");
        when(request.getAgreementDateFrom()).thenReturn(new Date(2023, 11, 14));
        when(request.getAgreementDateTo()).thenReturn(new Date(2023, 11, 16));

        validationErrorList = validator.validate(request);
        assertFalse(validationErrorList.isEmpty());
        assertEquals(validationErrorList.size(), 2);
        assertEquals(validationErrorList.get(0).getMessage(), "Must not be empty!");
        assertEquals(validationErrorList.get(0).getField(), "personFirstName");
        assertEquals(validationErrorList.get(1).getMessage(), "Must not be empty!");
        assertEquals(validationErrorList.get(1).getField(), "personLastName");
    }
    @Test
    public void validateTestNullLastNameAndFirstName(){
        request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn(null);
        when(request.getPersonLastName()).thenReturn(null);
        when(request.getAgreementDateFrom()).thenReturn(new Date(2023, 11, 14));
        when(request.getAgreementDateTo()).thenReturn(new Date(2023, 11, 16));

        validationErrorList = validator.validate(request);
        assertFalse(validationErrorList.isEmpty());
        assertEquals(validationErrorList.size(), 2);
        assertEquals(validationErrorList.get(0).getMessage(), "Must not be empty!");
        assertEquals(validationErrorList.get(0).getField(), "personFirstName");
        assertEquals(validationErrorList.get(1).getMessage(), "Must not be empty!");
        assertEquals(validationErrorList.get(1).getField(), "personLastName");
    }
    @Test
    public void validateTestNullDateFromError(){
        request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Eugene");
        when(request.getPersonLastName()).thenReturn("Kroshinsky");
        when(request.getAgreementDateFrom()).thenReturn(null);
        when(request.getAgreementDateTo()).thenReturn(new Date(2023, 11, 16));

        validationErrorList = validator.validate(request);
        assertFalse(validationErrorList.isEmpty());
        assertEquals(validationErrorList.size(), 1);
        assertEquals(validationErrorList.get(0).getMessage(), "Must not be empty!");
        assertEquals(validationErrorList.get(0).getField(), "agreementDateFrom");
    }
    @Test
    public void validateTestNullDateToError(){
        request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Eugene");
        when(request.getPersonLastName()).thenReturn("Kroshinsky");
        when(request.getAgreementDateFrom()).thenReturn(new Date(2023, 11, 16));
        when(request.getAgreementDateTo()).thenReturn(null);

        validationErrorList = validator.validate(request);
        assertFalse(validationErrorList.isEmpty());
        assertEquals(validationErrorList.size(), 1);
        assertEquals(validationErrorList.get(0).getMessage(), "Must not be empty!");
        assertEquals(validationErrorList.get(0).getField(), "agreementDateTo");
    }

    @Test
    public void validateTestWrongDateDifference(){
        request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Eugene");
        when(request.getPersonLastName()).thenReturn("Kroshinsky");
        when(request.getAgreementDateFrom()).thenReturn(new Date(2023, 11, 16));
        when(request.getAgreementDateTo()).thenReturn(new Date(2023, 11, 1));

        validationErrorList = validator.validate(request);
        assertFalse(validationErrorList.isEmpty());
        assertEquals(validationErrorList.size(), 1);
        assertEquals(validationErrorList.get(0).getMessage(), "DateTo must be greater than DateFrom");
        assertEquals(validationErrorList.get(0).getField(), "agreementDateDifference");
    }
}