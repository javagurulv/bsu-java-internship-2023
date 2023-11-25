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

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {

    @InjectMocks
    private TravelCalculatePremiumServiceImpl service;
    @Mock
    private TravelCalculatePremiumRequestValidator requestValidator;
    @Mock
    private TravelCalculatePremiumRequest request;

    @Test
    public void shouldReturnResponseWithOneError() {
        List<ValidationError> errors = List.of(new ValidationError("field", "error message"));
        Mockito.when(requestValidator.validate(request)).thenReturn(errors);
        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assertTrue(response.hasErrors());
        assertEquals(errors.get(0).getField(), "field");
        assertEquals(errors.get(0).getMessage(), "error message");
    }

    @Test
    public void shouldReturnResponseWithoutErrors() {
        Mockito.when(requestValidator.validate(request)).thenReturn(List.of());
        Mockito.when(request.getAgreementDateFrom()).thenReturn(new Date(10));//  без этого выдает NullPointerException
        Mockito.when(request.getAgreementDateTo()).thenReturn(new Date(12));//
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertFalse(response.hasErrors());
    }

    @Test
    public void correctRequestShouldBeEqualsToResponse() {
        Mockito.when(request.getPersonFirstName()).thenReturn("Roman");
        Mockito.when(request.getPersonLastName()).thenReturn("Shiyanov");
        Mockito.when(request.getAgreementDateFrom()).thenReturn(new Date(1000));
        Mockito.when(request.getAgreementDateTo()).thenReturn(new Date(1050));

        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assertEquals(response.getPersonFirstName(), request.getPersonFirstName());
        assertEquals(response.getPersonLastName(), request.getPersonLastName());
        assertEquals(response.getAgreementDateFrom(), request.getAgreementDateFrom());
        assertEquals(response.getAgreementDateTo(), request.getAgreementDateTo());
    }

    @Test
    public void incorrectRequestShouldEqualsToResponse() {
        Mockito.when(request.getPersonFirstName()).thenReturn(null);
        Mockito.when(request.getPersonLastName()).thenReturn(null);
        Mockito.when(request.getAgreementDateFrom()).thenReturn(new Date(1000));
        Mockito.when(request.getAgreementDateTo()).thenReturn(new Date(1050));

        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assertEquals(response.getPersonFirstName(), request.getPersonFirstName());
        assertEquals(response.getPersonLastName(), request.getPersonLastName());
        assertEquals(response.getAgreementDateFrom(), request.getAgreementDateFrom());
        assertEquals(response.getAgreementDateTo(), request.getAgreementDateTo());
    }

    @Test
    public void responseErrorsShouldErrorsList() {
        List<ValidationError> errors = List.of(
                new ValidationError("agreementDateTo", "..."),
                new ValidationError("agreementDateFrom", "..."),
                new ValidationError("personLastName", "..."),
                new ValidationError("personFirstName", "..."));

        Mockito.when(requestValidator.validate(request)).thenReturn(errors);
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getErrors(), errors);
    }


}


