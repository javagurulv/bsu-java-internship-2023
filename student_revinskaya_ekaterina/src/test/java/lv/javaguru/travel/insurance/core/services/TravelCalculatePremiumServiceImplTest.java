package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import lv.javaguru.travel.insurance.core.validations.TravelCalculatePremiumRequestValidator;
import lv.javaguru.travel.insurance.dto.*;
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
    @Test
    public void serviceImplWithError() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(requestValidator.validate(request)).thenReturn(generateError());
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().get(0).getErrorCode(), "field");
        assertEquals(response.getErrors().get(0).getDescription(), "message");
    }

    @Test
    public void serviceImplWithCorrectPersonFirstName() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Name");
        when(requestValidator.validate(request)).thenReturn(List.of());
        TravelPremiumCalculatorResult result = mock(TravelPremiumCalculatorResult.class);
        when(calculateUnderwriting.calculatePremium(request)).thenReturn(result);
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getPersonFirstName(), "Name");
    }
    @Test
    public void serviceImplWithCorrectPersonLastName() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonLastName()).thenReturn("Surname");
        when(requestValidator.validate(request)).thenReturn(List.of());
        TravelPremiumCalculatorResult result = mock(TravelPremiumCalculatorResult.class);
        when(calculateUnderwriting.calculatePremium(request)).thenReturn(result);
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getPersonLastName(), "Surname");
    }
    @Test
    public void serviceImplWithCorrectDateFrom() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createDate("09.07.2023"));
        when(requestValidator.validate(request)).thenReturn(List.of());
        TravelPremiumCalculatorResult result = mock(TravelPremiumCalculatorResult.class);
        when(calculateUnderwriting.calculatePremium(request)).thenReturn(result);
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getAgreementDateFrom(), createDate("09.07.2023"));
    }
    @Test
    public void serviceImplWithCorrectDateTo() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(createDate("19.07.2023"));
        when(requestValidator.validate(request)).thenReturn(List.of());
        TravelPremiumCalculatorResult result = mock(TravelPremiumCalculatorResult.class);
        when(calculateUnderwriting.calculatePremium(request)).thenReturn(result);
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getAgreementDateTo(), createDate("19.07.2023"));
    }
    @Test
    public void serviceImplWithCorrectBirthDay() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getBirthday()).thenReturn(createDate("19.07.2020"));
        when(requestValidator.validate(request)).thenReturn(List.of());
        TravelPremiumCalculatorResult result = mock(TravelPremiumCalculatorResult.class);
        when(calculateUnderwriting.calculatePremium(request)).thenReturn(result);
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getBirthday(), createDate("19.07.2020"));
    }
    @Test
    public void serviceImplWithCorrectMedicalRiskLimitLevel() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getMedicalRiskLimitLevel()).thenReturn("LEVEL");
        when(requestValidator.validate(request)).thenReturn(List.of());
        TravelPremiumCalculatorResult result = mock(TravelPremiumCalculatorResult.class);
        when(calculateUnderwriting.calculatePremium(request)).thenReturn(result);
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getMedicalRiskLimitLevel(), "LEVEL");
    }
    @Test
    public void serviceImplWithCorrectRisks() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(requestValidator.validate(request)).thenReturn(List.of());
        TravelPremiumCalculatorResult result = mock(TravelPremiumCalculatorResult.class);
        TravelRisk risk = mock(TravelRisk.class);
        when(result.getTravelRisks()).thenReturn(List.of(risk));
        when(calculateUnderwriting.calculatePremium(request)).thenReturn(result);
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getRisks(), List.of(risk));
    }
    @Test
    public void serviceImplWithCorrectCountry() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getCountry()).thenReturn("COUNTRY");
        when(requestValidator.validate(request)).thenReturn(List.of());
        TravelPremiumCalculatorResult result = mock(TravelPremiumCalculatorResult.class);
        when(calculateUnderwriting.calculatePremium(request)).thenReturn(result);
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getCountry(), "COUNTRY");
    }
    @Test
    public void serviceImplWithCorrectAgreementPrice() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(requestValidator.validate(request)).thenReturn(List.of());
        TravelPremiumCalculatorResult result = mock(TravelPremiumCalculatorResult.class);
        when(result.getTotalPremium()).thenReturn( BigDecimal.valueOf(10));
        when(calculateUnderwriting.calculatePremium(request)).thenReturn(result);
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getAgreementPremium(), BigDecimal.valueOf(10));
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