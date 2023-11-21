package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {

    @Mock private TravelCalculatePremiumRequestValidator requestValidator;
    @Mock private DateTimeService dateTimeService;

    @Mock private TravelCalculateUnderwriting underwritingCalculator;

    @InjectMocks private TravelCalculatePremiumServiceImpl service;

    @Test
    public void responseParametersEqualToRequestParametersWhenReturnedByController() {

        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(
                "Surname", "Name", new Date(12L), new Date(1212L)
        );

        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assert(Objects.equals(response.getPersonLastName(), request.getPersonLastName()) &&
                Objects.equals(response.getPersonFirstName(), request.getPersonFirstName()) &&
                response.getAgreementDateFrom() == request.getAgreementDateFrom() &&
                response.getAgreementDateTo() == request.getAgreementDateTo()
              );
    }

    @Test
    public void responseConsistsOfErrorsIfRequestIsInvalid() {

        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(
                "", "Name", new Date(1213L), new Date(1212L)
        );

        System.out.println("request is " + request);

        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        System.out.println("response is " + response);

        assert(response.hasErrors());
    }

    @Test
    public void responseConsistsOfSpecificErrorsIfRequestIsInvalid() {

        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(
                "", "", new Date(1213L), null
        );

        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assertEquals(response.getErrors(), new ArrayList<ValidationError>(Arrays.asList(
                new ValidationError("personFirstName", "Must not be empty!"),
                new ValidationError("personLastName", "Must not be empty!"),
                new ValidationError("agreementDateTo", "Must not be empty!")
        )));
    }

    @Test
    public void responseConsistsOfDateErrorIfRequestIsInvalid() {

        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(
                "Ilona", "Frolova", new Date(1213L), new Date(1212L)
        );

        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assertEquals(response.getErrors(), new ArrayList<ValidationError>(Arrays.asList(
                new ValidationError("agreementDateTo", "Must be after agreementDateFrom!")
        )));
    }

    @Test
    public void responseDoesNotHaveErrorsIfRequestIsValid() {

        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(
                "Ilona", "Frolova", new Date(1211L), new Date(1212L)
        );

        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assertNull(response.getErrors());
    }

    @Test
    public void costOfPremiumIsOkIfRequestIsValid() {

        when(dateTimeService.createDate(any())).thenReturn(new Date(86400000L));
        Date date1 = dateTimeService.createDate("");
        when(dateTimeService.createDate(any())).thenReturn(new Date(172800000L));
        Date date2 = dateTimeService.createDate("");

        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonLastName()).thenReturn("Surname");
        when(request.getPersonFirstName()).thenReturn("Name");
        when(request.getAgreementDateFrom()).thenReturn(date1);
        when(request.getAgreementDateTo()).thenReturn(date2);

        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assertEquals(response.getAgreementPrice(), BigDecimal.ONE);
    }

    @Test
    public void costOfPremiumIsZeroIfDaysAreEqual() {

        when(dateTimeService.createDate(any())).thenReturn(new Date(172800000L));
        Date date1 = dateTimeService.createDate("");
        Date date2 = dateTimeService.createDate("");

        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonLastName()).thenReturn("Surname");
        when(request.getPersonFirstName()).thenReturn("Name");
        when(request.getAgreementDateFrom()).thenReturn(date1);
        when(request.getAgreementDateTo()).thenReturn(date2);

        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assertEquals(response.getAgreementPrice().compareTo(BigDecimal.ZERO), 0);
    }
}