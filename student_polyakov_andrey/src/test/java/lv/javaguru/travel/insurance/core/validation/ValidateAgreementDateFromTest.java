package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ValidateAgreementDateFromTest {
    private ValidateAgreementDateFrom validator = new ValidateAgreementDateFrom();

    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void agreementDateFromShouldNotBeNull() {
        TravelCalculatePremiumRequest reqMock = mock(TravelCalculatePremiumRequest.class);
        when(reqMock.getAgreementDateFrom()).thenReturn(null);
        Optional<ValidationError> errorOptional = validator.validation(reqMock);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get().getField(), "agreementDateFrom");
        assertEquals(errorOptional.get().getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldNotReturnErrorWhenAgreementDateFromIsPresent() {
        TravelCalculatePremiumRequest reqMock = mock(TravelCalculatePremiumRequest.class);
        when(reqMock.getAgreementDateFrom()).thenReturn(createDate("01.01.2027"));
        Optional<ValidationError> errorOptional = validator.validation(reqMock);
        assertTrue(errorOptional.isEmpty());
    }
}
