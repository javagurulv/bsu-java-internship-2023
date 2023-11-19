package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;
import java.util.List;

public class TravelCalculatePremiumRequestValidatorTest {
    private TravelCalculatePremiumRequestValidator validator = new TravelCalculatePremiumRequestValidator();

    @Test
    public void personFirstNameShouldNotBeEmpty() {
        TravelCalculatePremiumRequest request = Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getPersonFirstName()).thenReturn("");
        Mockito.when(request.getPersonLastName()).thenReturn("Pupkin");
        Mockito.when(request.getAgreementDateFrom()).thenReturn(new Date());
        List<ValidationError> error = validator.validate(request);
        Assertions.assertFalse(error.isEmpty());
        Assertions.assertEquals(error.size(), 1);
        Assertions.assertEquals(error.get(0).getField(), "personFirstName");
        Assertions.assertEquals(error.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void personFirstNameShouldNotBeNull() {
        TravelCalculatePremiumRequest request = Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getPersonFirstName()).thenReturn(null);
        Mockito.when(request.getPersonLastName()).thenReturn("Pupkin");
        Mockito.when(request.getAgreementDateFrom()).thenReturn(new Date());
        List<ValidationError> error = validator.validate(request);
        Assertions.assertFalse(error.isEmpty());
        Assertions.assertEquals(error.size(), 1);
        Assertions.assertEquals(error.get(0).getField(), "personFirstName");
        Assertions.assertEquals(error.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldNotReturnErrorIfPersonIsPresent() {
        TravelCalculatePremiumRequest request = Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getPersonFirstName()).thenReturn("Vasya");
        Mockito.when(request.getPersonLastName()).thenReturn("Pupkin");
        Mockito.when(request.getAgreementDateFrom()).thenReturn(new Date());
        List<ValidationError> error = validator.validate(request);
        Assertions.assertTrue(error.isEmpty());
    }

    @Test
    public void personLastNameShouldNotBeEmpty() {
        TravelCalculatePremiumRequest request = Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getPersonFirstName()).thenReturn("Artyom");
        Mockito.when(request.getPersonLastName()).thenReturn("");
        Mockito.when(request.getAgreementDateFrom()).thenReturn(new Date());
        List<ValidationError> error = validator.validate(request);
        Assertions.assertFalse(error.isEmpty());
        Assertions.assertEquals(error.size(), 1);
        Assertions.assertEquals(error.get(0).getField(), "personLastName");
        Assertions.assertEquals(error.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void personLastNameShouldNotBeNull() {
        TravelCalculatePremiumRequest request = Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getPersonFirstName()).thenReturn("Artyom");
        Mockito.when(request.getPersonLastName()).thenReturn(null);
        Mockito.when(request.getAgreementDateFrom()).thenReturn(new Date());
        List<ValidationError> error = validator.validate(request);
        Assertions.assertFalse(error.isEmpty());
        Assertions.assertEquals(error.size(), 1);
        Assertions.assertEquals(error.get(0).getField(), "personLastName");
        Assertions.assertEquals(error.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void personShouldBeNotNull() {
        TravelCalculatePremiumRequest request = Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getPersonFirstName()).thenReturn(null);
        Mockito.when(request.getPersonLastName()).thenReturn(null);
        Mockito.when(request.getAgreementDateFrom()).thenReturn(new Date());
        List<ValidationError> error = validator.validate(request);
        Assertions.assertFalse(error.isEmpty());
        Assertions.assertEquals(error.size(), 2);
        Assertions.assertEquals(error.get(0).getField(), "personFirstName");
        Assertions.assertEquals(error.get(0).getMessage(), "Must not be empty!");
        Assertions.assertEquals(error.get(1).getField(), "personLastName");
        Assertions.assertEquals(error.get(1).getMessage(), "Must not be empty!");
    }

    @Test
    public void personShouldNotBeEmpty() {
        TravelCalculatePremiumRequest request = Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getPersonFirstName()).thenReturn("");
        Mockito.when(request.getPersonLastName()).thenReturn("");
        Mockito.when(request.getAgreementDateFrom()).thenReturn(new Date());
        List<ValidationError> error = validator.validate(request);
        Assertions.assertFalse(error.isEmpty());
        Assertions.assertEquals(error.size(), 2);
        Assertions.assertEquals(error.get(0).getField(), "personFirstName");
        Assertions.assertEquals(error.get(0).getMessage(), "Must not be empty!");
        Assertions.assertEquals(error.get(1).getField(), "personLastName");
        Assertions.assertEquals(error.get(1).getMessage(), "Must not be empty!");
    }

    @Test
    public void personFirstNameShouldNotBeNullAndLastNameBeEmpty() {
        TravelCalculatePremiumRequest request = Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getPersonFirstName()).thenReturn(null);
        Mockito.when(request.getPersonLastName()).thenReturn("");
        Mockito.when(request.getAgreementDateFrom()).thenReturn(new Date());
        List<ValidationError> error = validator.validate(request);
        Assertions.assertFalse(error.isEmpty());
        Assertions.assertEquals(error.size(), 2);
        Assertions.assertEquals(error.get(0).getField(), "personFirstName");
        Assertions.assertEquals(error.get(0).getMessage(), "Must not be empty!");
        Assertions.assertEquals(error.get(1).getField(), "personLastName");
        Assertions.assertEquals(error.get(1).getMessage(), "Must not be empty!");
    }

    @Test
    public void personFirstNameShouldNotBeEmptyAndLastNameBeNull() {
        TravelCalculatePremiumRequest request = Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getPersonFirstName()).thenReturn("");
        Mockito.when(request.getPersonLastName()).thenReturn(null);
        Mockito.when(request.getAgreementDateFrom()).thenReturn(new Date());
        List<ValidationError> error = validator.validate(request);
        Assertions.assertFalse(error.isEmpty());
        Assertions.assertEquals(error.size(), 2);
        Assertions.assertEquals(error.get(0).getField(), "personFirstName");
        Assertions.assertEquals(error.get(0).getMessage(), "Must not be empty!");
        Assertions.assertEquals(error.get(1).getField(), "personLastName");
        Assertions.assertEquals(error.get(1).getMessage(), "Must not be empty!");
    }

    @Test
    public void agreementDateFromShouldNotBeEmpty () {
        TravelCalculatePremiumRequest request = Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getPersonFirstName()).thenReturn("Artyom");
        Mockito.when(request.getPersonLastName()).thenReturn("Vorobey");
        Mockito.when(request.getAgreementDateFrom()).thenReturn(null);
        List<ValidationError> error = validator.validate(request);
        Assertions.assertFalse(error.isEmpty());
        Assertions.assertEquals(error.size(), 1);
        Assertions.assertEquals(error.get(0).getField(), "agreementDateFrom");
        Assertions.assertEquals(error.get(0).getMessage(), "Must not be empty!");
    }

}

