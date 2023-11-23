package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import lv.javaguru.travel.insurance.core.validations.TravelCalculatePremiumRequestValidator;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class TravelCalculatePremiumServiceImplTest {
    @Mock
    private TravelCalculatePremiumRequestValidator requestValidator;
    @Mock
    TravelPremiumUnderwriting calculateUnderwriting;
    @InjectMocks
    private TravelCalculatePremiumServiceImpl service;
    private TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest
            ("Name", "Surname", createDate("09.07.2023"), createDate("19.07.2023"),
                    List.of("TRAVEL_MEDICAL", "TRAVEL_CANCELLATION"));
    @Test
    public void serviceImplWithError() {
        when(requestValidator.validate(request)).thenReturn(generateError());
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().get(0).getErrorCode(), "field");
        assertEquals(response.getErrors().get(0).getDescription(), "message");
    }

    @Test
    public void serviceImplWithCorrectPersonFirstName() {
        when(requestValidator.validate(request)).thenReturn(List.of());
        when(calculateUnderwriting.calculateAgreementPrice(request)).thenReturn(BigDecimal.valueOf(10));
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getPersonFirstName(), "Name");
    }
    @Test
    public void serviceImplWithCorrectPersonLastName() {
        when(requestValidator.validate(request)).thenReturn(List.of());
        when(calculateUnderwriting.calculateAgreementPrice(request)).thenReturn(BigDecimal.valueOf(10));

        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getPersonLastName(), "Surname");
    }
    @Test
    public void serviceImplWithCorrectDateFrom() {
        when(requestValidator.validate(request)).thenReturn(List.of());
        when(calculateUnderwriting.calculateAgreementPrice(request)).thenReturn(BigDecimal.valueOf(10));

        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getAgreementDateFrom(), createDate("09.07.2023"));
    }
    @Test
    public void serviceImplWithCorrectDateTo() {
        when(requestValidator.validate(request)).thenReturn(List.of());
        when(calculateUnderwriting.calculateAgreementPrice(request)).thenReturn(BigDecimal.valueOf(10));

        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getAgreementDateTo(), createDate("19.07.2023"));
    }
    @Test
    public void serviceImplWithCorrectAgreementPrice() {
        when(requestValidator.validate(request)).thenReturn(List.of());
        when(calculateUnderwriting.calculateAgreementPrice(request)).thenReturn(BigDecimal.valueOf(10));

        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getAgreementPrice(), BigDecimal.valueOf(10));
    }
    private List<ValidationError> generateError() {
        List<ValidationError> errors = new ArrayList<>();
        errors.add(new ValidationError("field", "message"));
       return errors;
    }

    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}