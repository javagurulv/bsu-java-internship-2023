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

public class ValidateDateFromLessThanDateToTest {
    private ValidateDateFromLessThanDateTo validator = new ValidateDateFromLessThanDateTo();

    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void agreementDateFromShouldNotBeLessThanAgreementDateTo() {
        TravelCalculatePremiumRequest reqMock = mock(TravelCalculatePremiumRequest.class);
        when(reqMock.getAgreementDateFrom()).thenReturn(createDate("02.01.2027"));
        when(reqMock.getAgreementDateTo()).thenReturn(createDate("01.01.2027"));
        Optional<ValidationError> errorOptional = validator.validation(reqMock);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get().getField(), "agreementDateFrom");
        assertEquals(errorOptional.get().getMessage(), "Must be less then agreementDateTo!");
    }

    @Test
    public void agreementDateFromShouldNotBeEqualToAgreementDateTo() {
        TravelCalculatePremiumRequest reqMock = mock(TravelCalculatePremiumRequest.class);
        when(reqMock.getAgreementDateFrom()).thenReturn(createDate("02.01.2027"));
        when(reqMock.getAgreementDateTo()).thenReturn(createDate("02.01.2027"));
        Optional<ValidationError> errorOptional = validator.validation(reqMock);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get().getField(), "agreementDateFrom");
        assertEquals(errorOptional.get().getMessage(), "Must be less then agreementDateTo!");
    }

    @Test
    public void shouldNotReturnErrorWhenDateFromIsLessDateTo() {
        TravelCalculatePremiumRequest reqMock = mock(TravelCalculatePremiumRequest.class);
        when(reqMock.getAgreementDateFrom()).thenReturn(createDate("01.01.2027"));
        when(reqMock.getAgreementDateTo()).thenReturn(createDate("02.01.2027"));
        Optional<ValidationError> errorOptional = validator.validation(reqMock);
        assertTrue(errorOptional.isEmpty());
    }
}
