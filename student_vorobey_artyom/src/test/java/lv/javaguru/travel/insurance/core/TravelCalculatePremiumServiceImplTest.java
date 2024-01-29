package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.core.validations.TravelCalculatePremiumRequestValidator;
import lv.javaguru.travel.insurance.core.TravelCalculatePremiumServiceImpl;
import lv.javaguru.travel.insurance.core.TravelPremiumUnderwriting;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static lv.javaguru.travel.insurance.core.DateFunctions.createDateFromString;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {

    @InjectMocks
    private TravelCalculatePremiumServiceImpl service;
    @Mock
    private TravelCalculatePremiumRequestValidator requestValidator;
    @Mock
    private TravelPremiumUnderwriting premiumUnderwriting;

    @Test
    public void shouldReturnResponseWithErrors() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        List<ValidationError> errors = buildListOfErrorsWithError();
        when(requestValidator.validate(request)).thenReturn(errors);
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertTrue(response.hasErrors());
    }

    @Test
    public void shouldReturnResponseWithValidationErrors() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        List<ValidationError> errors = buildListOfErrorsWithError();
        when(requestValidator.validate(request)).thenReturn(errors);
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "field");
        assertEquals(response.getErrors().get(0).getMessage(), "errorMessage");
    }

    @Test
    public void shouldReturnResponseWithRelevantFirstName() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(requestValidator.validate(request)).thenReturn(List.of());
        when(request.getPersonFirstName()).thenReturn("Artyom");
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getPersonFirstName(), "Artyom");
    }

    @Test
    public void shouldReturnResponseWithRelevantLastName() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(requestValidator.validate(request)).thenReturn(List.of());
        when(request.getPersonLastName()).thenReturn("lastName");
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getPersonLastName(), "lastName");
    }

    @Test
    public void shouldReturnResponseWithRelevantAgreementDateFrom() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(requestValidator.validate(request)).thenReturn(List.of());
        when(request.getAgreementDateFrom()).thenReturn(createDateFromString("01.01.2023"));
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getAgreementDateFrom(), createDateFromString("01.01.2023"));
    }

    @Test
    public void shouldReturnResponseWithRelevantAgreementDateTo() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(requestValidator.validate(request)).thenReturn(List.of());
        when(request.getAgreementDateTo()).thenReturn(createDateFromString("01.01.2023"));
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getAgreementDateTo(), createDateFromString("01.01.2023"));
    }

    @Test
    public void shouldReturnResponseWithRelevantAgreementPrice() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(requestValidator.validate(request)).thenReturn(List.of());
        when(request.getAgreementDateFrom()).thenReturn(createDateFromString("01.01.2023"));
        when(request.getAgreementDateTo()).thenReturn(createDateFromString("10.01.2023"));
        when(premiumUnderwriting.calculatePremium(request)).thenReturn(new BigDecimal(9L));
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getAgreementPrice(), new BigDecimal(9L));
    }

    private List<ValidationError> buildListOfErrorsWithError() {
        return List.of(new ValidationError("field", "errorMessage"));
    }
}