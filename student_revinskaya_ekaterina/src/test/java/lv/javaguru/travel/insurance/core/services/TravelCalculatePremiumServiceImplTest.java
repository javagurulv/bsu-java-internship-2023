package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import lv.javaguru.travel.insurance.core.validations.TravelCalculatePremiumRequestValidator;
import lv.javaguru.travel.insurance.dto.*;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumResponseV1;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelCalculatePremiumServiceImplTest {
    @Mock
    private TravelCalculatePremiumRequestValidator requestValidator;
    @Mock
    private TravelPremiumUnderwriting calculateUnderwriting;
    @InjectMocks
    private TravelCalculatePremiumServiceImpl service;

    @Test
    public void serviceImplWithError() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(requestValidator.validate(request)).thenReturn(generateError());
        TravelCalculatePremiumResponseV1 response = service.calculatePremium(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().get(0).getErrorCode(), "field");
        assertEquals(response.getErrors().get(0).getDescription(), "message");
    }

    @Test
    public void serviceImplWithCorrectPersonFirstName() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getPersonFirstName()).thenReturn("Name");
        when(requestValidator.validate(request)).thenReturn(List.of());
        TravelPremiumCalculatorResult result = mock(TravelPremiumCalculatorResult.class);
        when(calculateUnderwriting.calculatePremium(request)).thenReturn(result);
        TravelCalculatePremiumResponseV1 response = service.calculatePremium(request);
        assertEquals(response.getPersonFirstName(), "Name");
    }

    @Test
    public void serviceImplWithCorrectPersonLastName() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getPersonLastName()).thenReturn("Surname");
        when(requestValidator.validate(request)).thenReturn(List.of());
        TravelPremiumCalculatorResult result = mock(TravelPremiumCalculatorResult.class);
        when(calculateUnderwriting.calculatePremium(request)).thenReturn(result);
        TravelCalculatePremiumResponseV1 response = service.calculatePremium(request);
        assertEquals(response.getPersonLastName(), "Surname");
    }

    @Test
    public void serviceImplWithCorrectDateFrom() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getAgreementDateFrom()).thenReturn(createDate("09.07.2023"));
        when(requestValidator.validate(request)).thenReturn(List.of());
        TravelPremiumCalculatorResult result = mock(TravelPremiumCalculatorResult.class);
        when(calculateUnderwriting.calculatePremium(request)).thenReturn(result);
        TravelCalculatePremiumResponseV1 response = service.calculatePremium(request);
        assertEquals(response.getAgreementDateFrom(), createDate("09.07.2023"));
    }

    @Test
    public void serviceImplWithCorrectDateTo() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getAgreementDateTo()).thenReturn(createDate("19.07.2023"));
        when(requestValidator.validate(request)).thenReturn(List.of());
        TravelPremiumCalculatorResult result = mock(TravelPremiumCalculatorResult.class);
        when(calculateUnderwriting.calculatePremium(request)).thenReturn(result);
        TravelCalculatePremiumResponseV1 response = service.calculatePremium(request);
        assertEquals(response.getAgreementDateTo(), createDate("19.07.2023"));
    }

    @Test
    public void serviceImplWithCorrectBirthDay() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getBirthday()).thenReturn(createDate("19.07.2020"));
        when(requestValidator.validate(request)).thenReturn(List.of());
        TravelPremiumCalculatorResult result = mock(TravelPremiumCalculatorResult.class);
        when(calculateUnderwriting.calculatePremium(request)).thenReturn(result);
        TravelCalculatePremiumResponseV1 response = service.calculatePremium(request);
        assertEquals(response.getBirthday(), createDate("19.07.2020"));
    }

    @Test
    public void serviceImplWithCorrectMedicalRiskLimitLevel() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getMedicalRiskLimitLevel()).thenReturn("LEVEL");
        when(requestValidator.validate(request)).thenReturn(List.of());
        TravelPremiumCalculatorResult result = mock(TravelPremiumCalculatorResult.class);
        when(calculateUnderwriting.calculatePremium(request)).thenReturn(result);
        TravelCalculatePremiumResponseV1 response = service.calculatePremium(request);
        assertEquals(response.getMedicalRiskLimitLevel(), "LEVEL");
    }

    @Test
    public void serviceImplWithCorrectRisks() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(requestValidator.validate(request)).thenReturn(List.of());
        TravelPremiumCalculatorResult result = mock(TravelPremiumCalculatorResult.class);
        TravelRisk risk = mock(TravelRisk.class);
        when(result.getTravelRisks()).thenReturn(List.of(risk));
        when(calculateUnderwriting.calculatePremium(request)).thenReturn(result);
        TravelCalculatePremiumResponseV1 response = service.calculatePremium(request);
        assertEquals(response.getRisks(), List.of(risk));
    }

    @Test
    public void serviceImplWithCorrectCountry() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getCountry()).thenReturn("COUNTRY");
        when(requestValidator.validate(request)).thenReturn(List.of());
        TravelPremiumCalculatorResult result = mock(TravelPremiumCalculatorResult.class);
        when(calculateUnderwriting.calculatePremium(request)).thenReturn(result);
        TravelCalculatePremiumResponseV1 response = service.calculatePremium(request);
        assertEquals(response.getCountry(), "COUNTRY");
    }

    @Test
    public void serviceImplWithCorrectAgreementPrice() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(requestValidator.validate(request)).thenReturn(List.of());
        TravelPremiumCalculatorResult result = mock(TravelPremiumCalculatorResult.class);
        when(result.getTotalPremium()).thenReturn(BigDecimal.valueOf(10));
        when(calculateUnderwriting.calculatePremium(request)).thenReturn(result);
        TravelCalculatePremiumResponseV1 response = service.calculatePremium(request);
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