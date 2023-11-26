package lv.javaguru.travel.insurance.validators;

import lv.javaguru.travel.insurance.core.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.core.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PersonLastNameValidatorTest {

    TravelCalculatePremiumRequestValidator requestValidator = new TravelCalculatePremiumRequestValidator();

    @Test
    public void shouldNotHaveError() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("firstName");
        when(request.getPersonLastName()).thenReturn("lastName");
        when(request.getAgreementDateFrom()).thenReturn(createDate("01.01.2022"));
        when(request.getAgreementDateTo()).thenReturn(createDate("01.01.2023"));
        List<ValidationError> errors = requestValidator.validate(request);

        assertTrue(errors.isEmpty());
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnErrorIfPersonLastNameIsEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("firstName");
        when(request.getPersonLastName()).thenReturn("");
        when(request.getAgreementDateFrom()).thenReturn(createDate("01.01.2022"));
        when(request.getAgreementDateTo()).thenReturn(createDate("01.01.2023"));
        List<ValidationError> errors = requestValidator.validate(request);

        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "personLastName");
        assertEquals(errors.get(0).getMessage(), "Most not be empty!");
    }

    @Test
    public void shouldReturnErrorIfPersonLastNameIsNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("firstName");
        when(request.getPersonLastName()).thenReturn(null);
        when(request.getAgreementDateFrom()).thenReturn(createDate("01.01.2022"));
        when(request.getAgreementDateTo()).thenReturn(createDate("01.01.2023"));
        List<ValidationError> errors = requestValidator.validate(request);

        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "personLastName");
        assertEquals(errors.get(0).getMessage(), "Most not be empty!");
    }

    public Date createDate(String strDate) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(strDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
