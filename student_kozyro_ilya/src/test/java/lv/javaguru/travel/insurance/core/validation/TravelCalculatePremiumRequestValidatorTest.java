package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.core.services.DateServiceImpl;
import lv.javaguru.travel.insurance.core.validator.TravelCalculatePremiumRequestValidator;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelCalculatePremiumRequestValidatorTest {

    @Mock
    DateServiceImpl dateService;

    @InjectMocks
    TravelCalculatePremiumRequestValidator validator = new TravelCalculatePremiumRequestValidator();


    void initFutureDateService() throws ParseException{
        when(dateService.createDate(eq("future"), any())).thenReturn(new Date(300L));
    }

    void initPastDateService() throws ParseException{
        when(dateService.createDate(eq("past"), any())).thenReturn(new Date(150L));
    }

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
        when(dateService.createDate(eq("from"), any())).thenReturn(new Date(100L));
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest
                .builder()
                .personFirstName("Gregory")
                .personLastName("Meow")
                .agreementDateFrom(dateService.createDate("from", "f"))
                .build();

        var errors = validator.validate(request);

        Assertions.assertEquals(1, errors.size());
    }

    @Test
    void testEmptyErrorsContainsOneSecond() throws ParseException {
        when(dateService.createDate(eq("to"), any())).thenReturn(new Date(200L));
        when(dateService.getTodayDate()).thenReturn(new Date(150L));
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest
                .builder()
                .personFirstName("Gregory")
                .personLastName("Meow")
                .agreementDateTo(dateService.createDate("to", "f"))
                .build();

        var errors = validator.validate(request);

        Assertions.assertEquals(1, errors.size());
    }
    @Test
    void testEmptyErrorsContainsOneThird() throws ParseException {
        when(dateService.createDate(eq("from"), any())).thenReturn(new Date(100L));
        when(dateService.createDate(eq("to"), any())).thenReturn(new Date(200L));
        when(dateService.getTodayDate()).thenReturn(new Date(150L));
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest
                .builder()
                .personFirstName("Gregory")
                .agreementDateFrom(dateService.createDate("from", "f"))
                .agreementDateTo(dateService.createDate("to", "f"))
                .build();

        var errors = validator.validate(request);

        Assertions.assertEquals(1, errors.size());
    }

    @Test
    void testEmptyErrorsContainsOneFourth() throws ParseException {
        when(dateService.createDate(eq("from"), any())).thenReturn(new Date(100L));
        when(dateService.createDate(eq("to"), any())).thenReturn(new Date(200L));
        when(dateService.getTodayDate()).thenReturn(new Date(150L));
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest
                .builder()
                .personLastName("Meow")
                .agreementDateFrom(dateService.createDate("from", "f"))
                .agreementDateTo(dateService.createDate("to", "f"))
                .build();

        var errors = validator.validate(request);

        Assertions.assertEquals(1, errors.size());
    }

    @Test
    void testAllOk() throws ParseException {
        when(dateService.createDate(eq("from"), any())).thenReturn(new Date(100L));
        when(dateService.createDate(eq("to"), any())).thenReturn(new Date(200L));
        when(dateService.getTodayDate()).thenReturn(new Date(150L));
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest
                .builder()
                .personFirstName("Gregory")
                .personLastName("Meow")
                .agreementDateFrom(dateService.createDate("from", "f"))
                .agreementDateTo(dateService.createDate("to", "f"))
                .build();

        var errors = validator.validate(request);

        Assertions.assertEquals(0, errors.size());
    }

    @Test
    void testDateTermsOk() throws ParseException {
        when(dateService.createDate(eq("from"), any())).thenReturn(new Date(100L));
        when(dateService.createDate(eq("to"), any())).thenReturn(new Date(200L));
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest
                .builder()
                .agreementDateFrom(dateService.createDate("from", "f"))
                .agreementDateTo(dateService.createDate("to", "f"))
                .build();
        var error = validator.validateDatesField(request);
        Assertions.assertTrue(error.isEmpty());
    }

    @Test
    void testDateTermsNotOk() throws ParseException {
        when(dateService.createDate(eq("from"), any())).thenReturn(new Date(100L));
        when(dateService.createDate(eq("to"), any())).thenReturn(new Date(200L));
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest
                .builder()
                .agreementDateFrom(dateService.createDate("to", "f"))
                .agreementDateTo(dateService.createDate("from", "f"))
                .build();

        var error = validator.validateDatesField(request);

        Assertions.assertFalse(error.isEmpty());
    }

    @Test
    void testAgreementDateToFail() throws ParseException {
        when(dateService.createDate(eq("toPast"), any())).thenReturn(new Date(300L));
        when(dateService.getTodayDate()).thenReturn(new Date(500L));

        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest
                .builder()
                .agreementDateTo(dateService.createDate("toPast", "f"))
                .build();
        var error = validator.validateAgreementDateFuture(request);
        Assertions.assertFalse(error.isEmpty());
    }

    @Test
    void testAgreementDateToOk() throws ParseException {
        when(dateService.createDate(eq("toFuture"), any())).thenReturn(new Date(700L));
        when(dateService.getTodayDate()).thenReturn(new Date(500L));

        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest
                .builder()
                .agreementDateTo(dateService.createDate("toFuture", "f"))
                .build();
        var error = validator.validateAgreementDateFuture(request);
        Assertions.assertTrue(error.isEmpty());
    }
}
