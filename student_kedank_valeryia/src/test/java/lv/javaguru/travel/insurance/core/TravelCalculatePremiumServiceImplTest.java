package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelCalculatePremiumServiceImplTest {

    @Test
    public void ValidatorIsGoodFromTo() {

        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, Calendar.JANUARY, 1);
        Date dateFrom = calendar.getTime();
        calendar.set(2023, Calendar.JANUARY, 1);
        Date dateTo = calendar.getTime();

        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Valeryia", "Kedank", dateFrom, dateTo);

        TravelCalculatePremiumRequestValidator requestValidator = new TravelCalculatePremiumRequestValidator();
        List<ValidationError> error = requestValidator.validate(request);
        assertEquals(error.size(), 0);

    }

    @Test
    public void ValidatorIsEmptyNull() {

        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, Calendar.JANUARY, 1);
        Date dateFrom = calendar.getTime();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonLastName("Kedank");
        request.setPersonFirstName("Valeryia");
        request.setAgreementDateFrom(dateFrom);

        TravelCalculatePremiumRequestValidator requestValidator = new TravelCalculatePremiumRequestValidator();
        List<ValidationError> error = requestValidator.validate(request);
        assertEquals(error.get(0).getField(), "dateTo");
        assertEquals(error.get(0).getMessage(), "Most not be empty!");

    }

    @Test
    public void ValidatorIsEmptyNullTo() {

        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, Calendar.JANUARY, 1);
        Date dateTo = calendar.getTime();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonLastName("Kedank");
        request.setPersonFirstName("Valeryia");
        request.setAgreementDateTo(dateTo);

        TravelCalculatePremiumRequestValidator requestValidator = new TravelCalculatePremiumRequestValidator();
        List<ValidationError> error = requestValidator.validate(request);
        assertEquals(error.get(0).getField(), "dateFrom");
        assertEquals(error.get(0).getMessage(), "Most not be empty!");

    }

}