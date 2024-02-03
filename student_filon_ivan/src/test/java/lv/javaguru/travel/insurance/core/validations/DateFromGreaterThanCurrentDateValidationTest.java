package lv.javaguru.travel.insurance.core.validations;

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

public class DateFromGreaterThanCurrentDateValidationTest {
    private DateFromGreaterThanCurrentDateValidation validation = new DateFromGreaterThanCurrentDateValidation();
    private TravelCalculatePremiumRequest request;

    @Test
    public void lessThanCurrentDate() {
        request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createDate("2015-10-10"));
        Optional<ValidationError> errors = validation.execute(request);
        assertTrue(errors.isPresent());
        assertEquals(errors.get().getMessage(), "Must be greater than currentDate!");
        assertEquals(errors.get().getField(), "agreementDateFrom");
    }

    @Test
    public void normalTest() {
        request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createDate("2115-10-10"));
        Optional<ValidationError> errors = validation.execute(request);
        assertTrue(errors.isEmpty());
    }

    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
