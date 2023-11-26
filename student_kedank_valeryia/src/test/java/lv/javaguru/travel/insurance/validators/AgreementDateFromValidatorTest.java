package lv.javaguru.travel.insurance.validators;

import lv.javaguru.travel.insurance.core.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.core.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AgreementDateFromValidatorTest {

    @Test
    public void shouldNotHaveError() {

        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, Calendar.JANUARY, 1);
        Date dateFrom = calendar.getTime();
        calendar.set(2023, Calendar.JANUARY, 1);
        Date dateTo = calendar.getTime();

        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Valeryia",
                "Kedank", dateFrom, dateTo);

        TravelCalculatePremiumRequestValidator requestValidator = new TravelCalculatePremiumRequestValidator();

        assertEquals(requestValidator.validate(request).size(), 0);
    }

    @Test
    public void shouldReturnErrorIfAgreementDateFromIsEmpty() {

        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.JANUARY, 1);
        Date dateTo = calendar.getTime();

        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Valeryia");
        request.setPersonLastName("Kedank");
        request.setAgreementDateTo(dateTo);

        TravelCalculatePremiumRequestValidator requestValidator = new TravelCalculatePremiumRequestValidator();
        List<ValidationError> error = requestValidator.validate(request);

        assertEquals(error.get(0).getField(), "agreementDateFrom");
        assertEquals(error.get(0).getMessage(), "Most not be empty!");
    }
}
