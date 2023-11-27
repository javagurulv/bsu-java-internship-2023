package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.validators.TravelCalculatePremiumRequestValidator;
import lv.javaguru.travel.insurance.validators.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {
    @InjectMocks
    private TravelCalculatePremiumServiceImpl service;
    @Mock
    private TravelCalculatePremiumRequestValidator requestValidator;

    public TravelCalculatePremiumRequest createObjectRequest() {

        Date dateFrom = createDate("01.01.2000");
        Date dateTo = createDate("01.01.2023");

        return new TravelCalculatePremiumRequest(
                "personFirstName",
                "personLastName",
                dateFrom, dateTo);
    }

    public Date createDate(String strData) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(strData);
        } catch (ParseException e) {
            throw new RuntimeException();
        }
    }

    public List<ValidationError> buildValidationErrorList() {
        return List.of(new ValidationError("field", "message"));
    }

    @Test
    public void shouldReturnResponseWithErrors() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        List<ValidationError> errors = buildValidationErrorList();
        when(requestValidator.validate(request)).thenReturn(errors);
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertTrue(response.hasErrors(request));
    }

    @Test
    public void shouldReturnResponseWithValidationErrors() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        List<ValidationError> errors = buildValidationErrorList();
        when(requestValidator.validate(request)).thenReturn(errors);
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "field");
        assertEquals(response.getErrors().get(0).getMessage(), "message");
    }

    @Test
    public void shouldReturnResponseWithCorrectPersonFirstName() {
        TravelCalculatePremiumRequest request = createObjectRequest();
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getPersonFirstName(), "personFirstName");
    }

    @Test
    public void shouldReturnResponseWithCorrectPersonLastName() {
        TravelCalculatePremiumRequest request = createObjectRequest();
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getPersonLastName(), "personLastName");
    }

    @Test
    public void shouldReturnResponseWithCorrectAgreementDateFrom() {
        Date dateFrom = createDate("01.01.2000");
        TravelCalculatePremiumRequest request = createObjectRequest();
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getAgreementDateFrom(), dateFrom);
    }

    @Test
    public void shouldReturnResponseWithCorrectAgreementDateTo() {
        Date dateTo = createDate("01.01.2023");
        TravelCalculatePremiumRequest request = createObjectRequest();
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getAgreementDateTo(), dateTo);
    }


    /*public void shouldReturnResponseWithCorrectAgreementPrice() {
        TravelCalculatePremiumRequest request = createObjectRequest();
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getAgreementPrice(), BigDecimal.valueOf(8400));
    }*/
}