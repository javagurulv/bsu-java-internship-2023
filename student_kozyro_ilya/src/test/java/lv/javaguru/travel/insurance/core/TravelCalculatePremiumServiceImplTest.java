package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.core.TravelCalculatePremiumRequestValidator;
import lv.javaguru.travel.insurance.core.TravelCalculatePremiumServiceImpl;
import lv.javaguru.travel.insurance.core.services.DateServiceImpl;
import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.dto.ValidationError;
import lv.javaguru.travel.insurance.validation.RequestValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class TravelCalculatePremiumServiceImplTest {

    @Mock
    TravelCalculatePremiumRequestValidator requestValidator;

    @Mock
    TravelPremiumUnderwriting travelPremiumUnderwriting;

    @InjectMocks
    TravelCalculatePremiumServiceImpl travelCalculatePremiumService;

    @Test
    void checkResponseWithErrors() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        List<ValidationError> errors = getValidationErrorList();
        when(requestValidator.validate(request)).thenReturn(errors);

        TravelCalculatePremiumResponse response = travelCalculatePremiumService.calculatePremium(request);

        assertTrue(response.hasErrors());

    }

    @Test
    void checkResponseConcreteError() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        List<ValidationError> errors = getValidationErrorList();
        when(requestValidator.validate(request)).thenReturn(errors);

        TravelCalculatePremiumResponse response = travelCalculatePremiumService.calculatePremium(request);

        assertEquals(response.getErrorList().size(), 1);

        assertEquals(response.getErrorList().get(0).getField(), "field");
        assertEquals(response.getErrorList().get(0).getError(), "errorMessage");

    }

    @Test
    void checkResponseOkFirstName() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("firstName");
        List<ValidationError> errors = List.of();
        when(requestValidator.validate(request)).thenReturn(errors);

        TravelCalculatePremiumResponse response = travelCalculatePremiumService.calculatePremium(request);

        assertEquals("firstName", response.getPersonFirstName());

    }

    @Test
    void checkResponseOkLastName() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonLastName()).thenReturn("lastName");
        List<ValidationError> errors = List.of();

        when(requestValidator.validate(request)).thenReturn(errors);

        TravelCalculatePremiumResponse response = travelCalculatePremiumService.calculatePremium(request);

        assertEquals("lastName", response.getPersonLastName());
    }

    @Test
    void checkResponseAgreementDateFrom() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        var date = createDate("11.11.2021");
        when(request.getAgreementDateFrom()).thenReturn(date);
        when(requestValidator.validate(request)).thenReturn(List.of());

        TravelCalculatePremiumResponse response = travelCalculatePremiumService.calculatePremium(request);

        assertEquals(date, response.getAgreementDateFrom());
    }

    @Test
    void checkResponseAgreementDateTo() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        var date = createDate("11.11.2021");
        when(request.getAgreementDateTo()).thenReturn(date);
        when(requestValidator.validate(request)).thenReturn(List.of());

        TravelCalculatePremiumResponse response = travelCalculatePremiumService.calculatePremium(request);

        assertEquals(date, response.getAgreementDateTo());
    }

    @Test
    void checkResponseAgreementPrice() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(request.getAgreementDateFrom()).thenReturn(createDate("11.07.2030"));
        when(request.getAgreementDateTo()).thenReturn(createDate("21.07.2030"));

        when(requestValidator.validate(request)).thenReturn(List.of());
        when(travelPremiumUnderwriting.calculatePremium(request)).thenReturn(BigDecimal.valueOf(10));

        TravelCalculatePremiumResponse response = travelCalculatePremiumService.calculatePremium(request);

        assertFalse(response.hasErrors());
        assertEquals(response.getAgreementPrice(), BigDecimal.valueOf(10));

    }

    List<ValidationError> getValidationErrorList() {
        return List.of(
                new ValidationError("field", "errorMessage")
        );
    }

    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }



}