package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DateToLessThanDateFromValidationTest {
    private DateFromLessThanDateToValidation validation = new DateFromLessThanDateToValidation();

    @Test
    public void testWhenDateToLessThanDateFrom() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createDate("2100.12.12"));
        when(request.getAgreementDateTo()).thenReturn(createDate("2100.10.10"));
        Optional<ValidationError> errorOpt = validation.validate(request);
        assertTrue(errorOpt.isPresent());
        assertEquals(errorOpt.get().getField(), "agreementDateFrom");
        assertEquals(errorOpt.get().getMessage(), "AgreementDateFrom should be less than agreementDateTo!");
    }

    @Test
    public void testWhenDateFromEqualsDateTo() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createDate("2100.12.12"));
        when(request.getAgreementDateTo()).thenReturn(createDate("2100.12.12"));
        Optional<ValidationError> errorOpt = validation.validate(request);
        assertTrue(errorOpt.isPresent());
        assertEquals(errorOpt.get().getField(), "agreementDateFrom");
        assertEquals(errorOpt.get().getMessage(), "AgreementDateFrom should be less than agreementDateTo!");
    }

    @Test
    public void testWhenDateFromLessThanDateTo(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createDate("2100.10.10"));
        when(request.getAgreementDateTo()).thenReturn(createDate("2100.12.12"));
        Optional<ValidationError> errorOpt = validation.validate(request);
        assertTrue(errorOpt.isEmpty());
    }

    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("yyyy.MM.dd").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}

