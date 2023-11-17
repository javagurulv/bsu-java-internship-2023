package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.core.services.DateServiceImpl;
import lv.javaguru.travel.insurance.core.validator.TravelCalculatePremiumRequestValidator;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.ArrayList;

@SpringBootTest
public class TravelCalculatePremiumRequestValidatorTest {
    @Autowired
    DateServiceImpl dateService;

    @Autowired
    TravelCalculatePremiumRequestValidator validator;

    @Test
    void testEmptyErrorsAmountOnEmpty() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest
                .builder()
                .build();
        var errors = validator.validate(request);

        Assertions.assertEquals(4, errors.size());
    }

    @Test
    void testEmptyErrorsSpecialOne() throws ParseException {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest
                .builder()
                .personFirstName("Gregory")
                .build();

        var errors = validator.validate(request);

        Assertions.assertEquals(3, errors.size());
    }

    @Test
    void testEmptyErrorsContainsOneFirst() throws ParseException {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest
                .builder()
                .personFirstName("Gregory")
                .personLastName("Meow")
                .agreementDateFrom(dateService.createDate("2000-10-10"))
                .build();

        var errors = validator.validate(request);

        Assertions.assertEquals(1, errors.size());
    }

    @Test
    void testEmptyErrorsContainsOneSecond() throws ParseException {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest
                .builder()
                .personFirstName("Gregory")
                .personLastName("Meow")
                .agreementDateTo(dateService.createDate("2000-10-11"))
                .build();

        var errors = validator.validate(request);

        Assertions.assertEquals(1, errors.size());
    }
    @Test
    void testEmptyErrorsContainsOneThird() throws ParseException {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest
                .builder()
                .personFirstName("Gregory")
                .agreementDateFrom(dateService.createDate("2000-10-10"))
                .agreementDateTo(dateService.createDate("2000-10-11"))
                .build();

        var errors = validator.validate(request);

        Assertions.assertEquals(1, errors.size());
    }

    @Test
    void testEmptyErrorsContainsOneFourth() throws ParseException {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest
                .builder()
                .personLastName("Meow")
                .agreementDateFrom(dateService.createDate("2000-10-10"))
                .agreementDateTo(dateService.createDate("2000-10-11"))
                .build();

        var errors = validator.validate(request);

        Assertions.assertEquals(1, errors.size());
    }

    @Test
    void testAllOk() throws ParseException {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest
                .builder()
                .personFirstName("Gregory")
                .personLastName("Meow")
                .agreementDateFrom(dateService.createDate("2000-10-10"))
                .agreementDateTo(dateService.createDate("2000-10-11"))
                .build();

        var errors = validator.validate(request);

        Assertions.assertEquals(0, errors.size());
    }

    @Test
    void testDateTermsOk() throws ParseException {

        var error = validator.validateDatesField(dateService.createDate("2000-10-10"), dateService.createDate("2000-10-11"));
        Assertions.assertTrue(error.isEmpty());
    }

    @Test
    void testDateTermsNotOk() throws ParseException {

        var error = validator.validateDatesField(dateService.createDate("2000-10-10"), dateService.createDate("2000-10-09"));
        Assertions.assertFalse(error.isEmpty());
    }
}
