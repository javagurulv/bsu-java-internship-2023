package lv.javaguru.travel.insurance.validators;

import lv.javaguru.travel.insurance.core.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateFromIsLessThanDateToValidatorTest {

    public TravelCalculatePremiumRequest createObjectRequest() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, Calendar.JANUARY, 1);
        Date dateFrom = calendar.getTime();
        calendar.set(2023, Calendar.JANUARY, 1);
        Date dateTo = calendar.getTime();

        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("personFirstName",
                "personLastName",
                dateFrom, dateTo);

        return request;
    }
    @Test
    public void dateFromIsLessThanDateTo(){
        TravelCalculatePremiumRequest request = createObjectRequest();
        TravelCalculatePremiumRequestValidator requestValidator = new TravelCalculatePremiumRequestValidator();
        assertEquals(requestValidator.validate(request).size(), 0);
    }

    @Test
    public void dateFromIsNotLessThanDateTo(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.JANUARY, 1);
        Date dateFrom = calendar.getTime();
        calendar.set(2022, Calendar.JANUARY, 1);
        Date dateTo = calendar.getTime();

        TravelCalculatePremiumRequest request = createObjectRequest();
        request.setAgreementDateFrom(dateFrom);
        request.setAgreementDateTo(dateTo);

        TravelCalculatePremiumRequestValidator requestValidator = new TravelCalculatePremiumRequestValidator();
        assertEquals(requestValidator.validate(request).get(0).getField(), "dateFrom");
        assertEquals(requestValidator.validate(request).get(0).getMessage(), "must be less than dateTo");
    }
}
