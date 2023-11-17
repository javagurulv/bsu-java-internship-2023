package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {

    @Mock private TravelCalculatePremiumRequestValidator requestValidator;
    @Mock private DateTimeService dateTimeService;

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

        TravelCalculatePremiumResponse response = service.calculatePremium(request);

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
}