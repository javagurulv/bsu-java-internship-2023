package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumRequestValidatorTest {
@InjectMocks
    TravelCalculatePremiumRequestValidator requestValidator = new TravelCalculatePremiumRequestValidator();
    @Mock DateTimeService dateTimeService;
    @Mock TravelCalculatePremiumRequest request;

    @Test
    public void responseShouldContainErrorEmptyFirstNameTest() {
        when(dateTimeService.getCurrentDateTime()).thenReturn(createDate("16.11.2023"));
        when(request.getPersonFirstName()).thenReturn("");
        when(request.getPersonLastName()).thenReturn("Frolov");
        when(request.getAgreementDateFrom()).thenReturn(createDate("8.07.2024"));
        when(request.getAgreementDateTo()).thenReturn(createDate("8.08.2024"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "personFirstName");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }
    @Test
    public void responseShouldContainErrorNullFirstNameTest() {
        when(dateTimeService.getCurrentDateTime()).thenReturn(createDate("16.11.2023"));
        when(request.getPersonFirstName()).thenReturn(null);
        when(request.getPersonLastName()).thenReturn("Ignatov");
        when(request.getAgreementDateFrom()).thenReturn(createDate("8.07.2024"));
        when(request.getAgreementDateTo()).thenReturn(createDate("8.08.2024"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "personFirstName");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }
    @Test
    public void responseShouldContainErrorEmptyLastNameTest(){
        when(dateTimeService.getCurrentDateTime()).thenReturn(createDate("16.11.2023"));
        when(request.getPersonFirstName()).thenReturn("Sasha");
        when(request.getPersonLastName()).thenReturn("");
        when(request.getAgreementDateFrom()).thenReturn(createDate("8.07.2024"));
        when(request.getAgreementDateTo()).thenReturn(createDate("8.08.2024"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "personLastName");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }
    @Test
    public void responseShouldContainErrorNullLastNameTest(){
        when(dateTimeService.getCurrentDateTime()).thenReturn(createDate("16.11.2023"));
        when(request.getPersonFirstName()).thenReturn("Nikita");
        when(request.getPersonLastName()).thenReturn(null);
        when(request.getAgreementDateFrom()).thenReturn(createDate("8.07.2024"));
        when(request.getAgreementDateTo()).thenReturn(createDate("8.08.2024"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "personLastName");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }
    @Test
    public void responseShouldContainErrorNullDateFromTest() {
        when(dateTimeService.getCurrentDateTime()).thenReturn(createDate("16.11.2023"));
        when(request.getPersonFirstName()).thenReturn("Misha");
        when(request.getPersonLastName()).thenReturn("Popov");
        when(request.getAgreementDateFrom()).thenReturn(null);
        when(request.getAgreementDateTo()).thenReturn(createDate("8.08.2024"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(),"agreementDateFrom" );
        assertEquals(errors.get(0).getMessage(),"Must not be empty!" );
    }
    @Test
    public void responseShouldContainErrorNullDateToTest() {
        when(dateTimeService.getCurrentDateTime()).thenReturn(createDate("16.11.2023"));
        when(request.getPersonFirstName()).thenReturn("Misha");
        when(request.getPersonLastName()).thenReturn("Popov");
        when(request.getAgreementDateTo()).thenReturn(null);
        when(request.getAgreementDateFrom()).thenReturn(createDate("8.07.2024"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(),"agreementDateTo" );
        assertEquals(errors.get(0).getMessage(),"Must not be empty!" );
    }
    @Test
    public void responseShouldContainDateFromOfFutureTimeTest() {
        when(dateTimeService.getCurrentDateTime()).thenReturn(createDate("16.11.2023"));
        when(request.getPersonFirstName()).thenReturn("Misha");
        when(request.getPersonLastName()).thenReturn("Popov");
        when(request.getAgreementDateFrom()).thenReturn(createDate("8.07.2023"));
        when(request.getAgreementDateTo()).thenReturn(createDate("8.08.2024"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(),"agreementDateFrom" );
        assertEquals(errors.get(0).getMessage(),"agreementDateFrom must be future date" );
    }
    @Test
    public void responseShouldContainErrorDateFromMoreToTest() {
        when(dateTimeService.getCurrentDateTime()).thenReturn(createDate("16.11.2023"));
        when(request.getPersonFirstName()).thenReturn("Misha");
        when(request.getPersonLastName()).thenReturn("Popov");
        when(request.getAgreementDateFrom()).thenReturn(createDate("8.07.2024"));
        when(request.getAgreementDateTo()).thenReturn(createDate("8.08.2023"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 2);
        assertEquals(errors.get(1).getField(),"agreementDateTo" );
        assertEquals(errors.get(1).getMessage(),"agreementDateTo must be future date" );
    }
    @Test
    public void responseNotContainErrorTest() {
        when(dateTimeService.getCurrentDateTime()).thenReturn(createDate("16.11.2023"));
        when(request.getPersonFirstName()).thenReturn("Misha");
        when(request.getPersonLastName()).thenReturn("Popov");
        when(request.getAgreementDateFrom()).thenReturn(createDate("8.07.2024"));
        when(request.getAgreementDateTo()).thenReturn(createDate("8.08.2024"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 0);
    }
    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}