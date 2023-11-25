package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.core.validations.TravelCalculatePremiumRequestValidatorImpl;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {

    @Mock private TravelCalculatePremiumRequestValidatorImpl requestValidator;

    @Mock private TravelCalculateUnderwriting underwritingCalculator;

    @Mock private TravelCalculatePremiumRequest request;

    @InjectMocks private TravelCalculatePremiumServiceImpl service;

    @Test
    public void responseParametersEqualToRequestParametersWhenReturnedByController() {
        when(request.getPersonFirstName()).thenReturn("Name");
        when(request.getPersonLastName()).thenReturn("Surname");
        when(request.getAgreementDateTo()).thenReturn(new Date(100L));
        when(request.getAgreementDateFrom()).thenReturn(new Date(105L));

        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assert(Objects.equals(response.getPersonLastName(), request.getPersonLastName()) &&
                Objects.equals(response.getPersonFirstName(), request.getPersonFirstName()) &&
                response.getAgreementDateFrom() == request.getAgreementDateFrom() &&
                response.getAgreementDateTo() == request.getAgreementDateTo()
              );
    }

    @Test
    public void responseConsistsOfErrorsIfRequestIsInvalid() {
        when(requestValidator.validate(request)).thenReturn(new ArrayList<ValidationError>(
                Arrays.asList(new ValidationError("field", "message")))
        );

        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assert(response.hasErrors());
    }

    @Test
    public void responseConsistsOfSpecificErrorsIfRequestIsInvalid() {
        ArrayList<ValidationError> errors = new ArrayList<ValidationError>(
                Arrays.asList(
                        new ValidationError("personFirstName", "Must not be empty!"),
                        new ValidationError("personLastName", "Must not be empty!"),
                        new ValidationError("agreementDateTo", "Must not be empty!")
                ));
        when(requestValidator.validate(request)).thenReturn(errors);

        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getErrors(), errors);
    }

    @Test
    public void responseConsistsOfDateErrorIfRequestIsInvalid() {
        ArrayList<ValidationError> errors = new ArrayList<ValidationError>(Arrays.asList(
                new ValidationError("agreementDateTo", "Must be after agreementDateFrom!")
        ));
        when(requestValidator.validate(request)).thenReturn(errors);

        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getErrors(), errors);
    }

    @Test
    public void responseDoesNotHaveErrorsIfRequestIsValid() {
        when(request.getPersonFirstName()).thenReturn("Name");
        when(requestValidator.validate(request)).thenReturn(List.of());
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertNull(response.getErrors());
        assertEquals(response.getPersonFirstName(), "Name");
    }

    @Test
    public void costOfPremiumIsOkIfRequestIsValid() {
        when(requestValidator.validate(request)).thenReturn(List.of());
        when(underwritingCalculator.calculatePremium(request)).thenReturn(BigDecimal.TEN);

        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getAgreementPrice(), BigDecimal.TEN);
    }
}