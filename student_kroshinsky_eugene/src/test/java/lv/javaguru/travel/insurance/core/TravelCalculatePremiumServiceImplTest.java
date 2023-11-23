package lv.javaguru.travel.insurance.core;

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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {
    @Mock private DateDifferenceService dateDifferenceService;
    @Mock private TravelCalculatePremiumRequest request;
    @Mock private TravelCalculatePremiumRequestValidator requestValidator;
    @InjectMocks TravelCalculatePremiumServiceImpl calculator;
    @Test
    public void calculatePremiumTestFirstName() {
        when(request.getPersonFirstName()).thenReturn("personFirstName");
        when(requestValidator.validate(request)).thenReturn(List.of());
        TravelCalculatePremiumResponse response = calculator.calculatePremium(request);
        assertEquals(request.getPersonFirstName(), response.getPersonFirstName());
        assertFalse(response.hasErrors());
    }
    @Test
    public void calculatePremiumTestLastName() {
        when(request.getPersonLastName()).thenReturn("personLastName");
        when(requestValidator.validate(request)).thenReturn(List.of());
        TravelCalculatePremiumResponse response = calculator.calculatePremium(request);
        assertEquals(request.getPersonLastName(), response.getPersonLastName());
        assertFalse(response.hasErrors());
    }
    @Test
    public void calculatePremiumTestDateFrom() {
        when(request.getAgreementDateFrom()).thenReturn(createDate("16.11.2023"));
        when(requestValidator.validate(request)).thenReturn(List.of());
        TravelCalculatePremiumResponse response = calculator.calculatePremium(request);
        assertEquals(request.getAgreementDateFrom(), response.getAgreementDateFrom());
        assertFalse(response.hasErrors());
    }
    @Test
    public void calculatePremiumTestDateTo() {
        when(request.getAgreementDateTo()).thenReturn(createDate("24.11.2023"));
        when(requestValidator.validate(request)).thenReturn(List.of());
        TravelCalculatePremiumResponse response = calculator.calculatePremium(request);
        assertEquals(request.getAgreementDateTo(), response.getAgreementDateTo());
        assertFalse(response.hasErrors());
    }
    @Test
    public void calculatePremiumTestAgreementPrice() {
        when(request.getAgreementDateFrom()).thenReturn(createDate("16.11.2023"));
        when(request.getAgreementDateTo()).thenReturn(createDate("24.11.2023"));
        when(requestValidator.validate(request)).thenReturn(List.of());
        when(dateDifferenceService.calculateDateDifference(
                request.getAgreementDateFrom(), request.getAgreementDateTo())).thenReturn(new BigDecimal(8));
        TravelCalculatePremiumResponse response = calculator.calculatePremium(request);
        assertEquals(new BigDecimal(8), response.getAgreementPrice());
        assertFalse(response.hasErrors());
    }
    @Test
    public void calculatePremiumTestHasErrors() {
        when(requestValidator.validate(request)).thenReturn(createError());
        TravelCalculatePremiumResponse response = calculator.calculatePremium(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "field");
        assertEquals(response.getErrors().get(0).getMessage(), "message");
    }

    private List<ValidationError> createError() {
        return List.of(
                new ValidationError("field", "message")
        );
    }
    private Date createDate(String s) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(s);
        } catch (ParseException e) {
            throw new RuntimeException();
        }
    }
}