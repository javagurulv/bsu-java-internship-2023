package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class TravelCalculatePremiumRequestValidatorTest {
    @Mock private  TravelCalculatePremiumRequest request;
    private TravelCalculatePremiumRequestValidator validator = new TravelCalculatePremiumRequestValidator();

    private Date createDate(String s) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(s);
        } catch (ParseException e) {
            throw new RuntimeException();
        }
    }

    @Test
    public void validateTestNotEmpty() {
        when(request.getPersonFirstName()).thenReturn("Eugene");
        when(request.getPersonLastName()).thenReturn("Kroshinsky");
        when(request.getAgreementDateFrom()).thenReturn(createDate("14.01.2023"));
        when(request.getAgreementDateTo()).thenReturn(createDate("16.01.2023"));

        List<ValidationError> validationErrorList = validator.validate(request);
        assertTrue(validationErrorList.isEmpty());
        assertEquals(validationErrorList.size(), 0);
    }
    @Test
    public void validateTestNullFirstName(){
        when(request.getPersonFirstName()).thenReturn(null);
        when(request.getPersonLastName()).thenReturn("Kroshinsky");
        when(request.getAgreementDateFrom()).thenReturn(createDate("14.01.2023"));
        when(request.getAgreementDateTo()).thenReturn(createDate("16.01.2023"));

        List<ValidationError> validationErrorList = validator.validate(request);
        assertFalse(validationErrorList.isEmpty());
        assertEquals(validationErrorList.size(), 1);
        assertEquals(validationErrorList.get(0).getMessage(), "Must not be empty!");
        assertEquals(validationErrorList.get(0).getField(), "personFirstName");
    }
    @Test
    public void validateTestEmptyFirstName(){
        when(request.getPersonFirstName()).thenReturn("");
        when(request.getPersonLastName()).thenReturn("Kroshinsky");
        when(request.getAgreementDateFrom()).thenReturn(createDate("14.01.2023"));
        when(request.getAgreementDateTo()).thenReturn(createDate("16.01.2023"));

        List<ValidationError> validationErrorList  = validator.validate(request);
        assertFalse(validationErrorList.isEmpty());
        assertEquals(validationErrorList.size(), 1);
        assertEquals(validationErrorList.get(0).getMessage(), "Must not be empty!");
        assertEquals(validationErrorList.get(0).getField(), "personFirstName");
    }
    @Test
    public void validateTestNullLastName(){
        when(request.getPersonFirstName()).thenReturn("Eugene");
        when(request.getPersonLastName()).thenReturn(null);
        when(request.getAgreementDateFrom()).thenReturn(createDate("14.01.2023"));
        when(request.getAgreementDateTo()).thenReturn(createDate("16.01.2023"));

        List<ValidationError> validationErrorList = validationErrorList = validator.validate(request);
        assertFalse(validationErrorList.isEmpty());
        assertEquals(validationErrorList.size(), 1);
        assertEquals(validationErrorList.get(0).getMessage(), "Must not be empty!");
        assertEquals(validationErrorList.get(0).getField(), "personLastName");
    }
    @Test
    public void validateTestEmptyLastName(){
        when(request.getPersonFirstName()).thenReturn("Eugene");
        when(request.getPersonLastName()).thenReturn("");
        when(request.getAgreementDateFrom()).thenReturn(createDate("14.01.2023"));
        when(request.getAgreementDateTo()).thenReturn(createDate("16.01.2023"));

        List<ValidationError> validationErrorList = validationErrorList = validator.validate(request);
        assertFalse(validationErrorList.isEmpty());
        assertEquals(validationErrorList.size(), 1);
        assertEquals(validationErrorList.get(0).getMessage(), "Must not be empty!");
        assertEquals(validationErrorList.get(0).getField(), "personLastName");
    }
    @Test
    public void validateTestEmptyLastNameAndFirstName(){
        when(request.getPersonFirstName()).thenReturn("");
        when(request.getPersonLastName()).thenReturn("");
        when(request.getAgreementDateFrom()).thenReturn(createDate("14.01.2023"));
        when(request.getAgreementDateTo()).thenReturn(createDate("16.01.2023"));

        List<ValidationError> validationErrorList = validator.validate(request);
        assertFalse(validationErrorList.isEmpty());
        assertEquals(validationErrorList.size(), 2);
        assertEquals(validationErrorList.get(0).getMessage(), "Must not be empty!");
        assertEquals(validationErrorList.get(0).getField(), "personFirstName");
        assertEquals(validationErrorList.get(1).getMessage(), "Must not be empty!");
        assertEquals(validationErrorList.get(1).getField(), "personLastName");
    }
    @Test
    public void validateTestNullLastNameAndFirstName(){
        when(request.getPersonFirstName()).thenReturn(null);
        when(request.getPersonLastName()).thenReturn(null);
        when(request.getAgreementDateFrom()).thenReturn(createDate("14.01.2023"));
        when(request.getAgreementDateTo()).thenReturn(createDate("16.01.2023"));

        List<ValidationError> validationErrorList = validator.validate(request);
        assertFalse(validationErrorList.isEmpty());
        assertEquals(validationErrorList.size(), 2);
        assertEquals(validationErrorList.get(0).getMessage(), "Must not be empty!");
        assertEquals(validationErrorList.get(0).getField(), "personFirstName");
        assertEquals(validationErrorList.get(1).getMessage(), "Must not be empty!");
        assertEquals(validationErrorList.get(1).getField(), "personLastName");
    }
    @Test
    public void validateTestNullDateFromError(){
        when(request.getPersonFirstName()).thenReturn("Eugene");
        when(request.getPersonLastName()).thenReturn("Kroshinsky");
        when(request.getAgreementDateFrom()).thenReturn(null);
        when(request.getAgreementDateTo()).thenReturn(createDate("16.01.2023"));

        List<ValidationError> validationErrorList = validator.validate(request);
        assertFalse(validationErrorList.isEmpty());
        assertEquals(validationErrorList.size(), 1);
        assertEquals(validationErrorList.get(0).getMessage(), "Must not be empty!");
        assertEquals(validationErrorList.get(0).getField(), "agreementDateFrom");
    }
    @Test
    public void validateTestNullDateToError(){
        when(request.getPersonFirstName()).thenReturn("Eugene");
        when(request.getPersonLastName()).thenReturn("Kroshinsky");
        when(request.getAgreementDateFrom()).thenReturn(createDate("16.01.2023"));
        when(request.getAgreementDateTo()).thenReturn(null);

        List<ValidationError> validationErrorList = validator.validate(request);
        assertFalse(validationErrorList.isEmpty());
        assertEquals(validationErrorList.size(), 1);
        assertEquals(validationErrorList.get(0).getMessage(), "Must not be empty!");
        assertEquals(validationErrorList.get(0).getField(), "agreementDateTo");
    }

    @Test
    public void validateTestWrongDateDifference(){
        when(request.getPersonFirstName()).thenReturn("Eugene");
        when(request.getPersonLastName()).thenReturn("Kroshinsky");
        when(request.getAgreementDateFrom()).thenReturn(createDate("14.01.2023"));
        when(request.getAgreementDateTo()).thenReturn(createDate("1.01.2023"));

        List<ValidationError>  validationErrorList = validator.validate(request);
        assertFalse(validationErrorList.isEmpty());
        assertEquals(validationErrorList.size(), 1);
        assertEquals(validationErrorList.get(0).getMessage(), "DateTo must be greater than DateFrom");
        assertEquals(validationErrorList.get(0).getField(), "agreementDateDifference");
    }
}