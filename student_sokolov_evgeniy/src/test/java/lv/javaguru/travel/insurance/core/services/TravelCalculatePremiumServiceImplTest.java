package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumCalculationResult;
import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import lv.javaguru.travel.insurance.core.validations.TravelCalculatePremiumRequestValidator;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumResponseV1;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelCalculatePremiumServiceImplTest {

    @Mock private TravelCalculatePremiumRequestValidator requestValidator;
    @Mock private TravelPremiumUnderwriting premiumUnderwriting;

    @InjectMocks
    private TravelCalculatePremiumServiceImpl service;

    @Test
    public void shouldReturnResponseWithErrors() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        List<ValidationError> errors = buildValidationErrorList();
        when(requestValidator.validate(request)).thenReturn(errors);
        TravelCalculatePremiumResponseV1 response = service.calculatePremium(request);
        assertTrue(response.hasErrors());
    }

    @Test
    public void shouldReturnResponseWithValidationErrors() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        List<ValidationError> errors = buildValidationErrorList();
        when(requestValidator.validate(request)).thenReturn(errors);
        TravelCalculatePremiumResponseV1 response = service.calculatePremium(request);
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getErrorCode(), "field");
        assertEquals(response.getErrors().get(0).getDescription(), "errorMessage");
    }

    @Test
    public void shouldNotInvokeDateTimeUtilWhenValidationErrors() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        List<ValidationError> errors = buildValidationErrorList();
        when(requestValidator.validate(request)).thenReturn(errors);
        TravelCalculatePremiumResponseV1 response = service.calculatePremium(request);
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getErrorCode(), "field");
        assertEquals(response.getErrors().get(0).getDescription(), "errorMessage");
    }

    @Test
    public void shouldReturnResponseWithCorrectPersonFirstName() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getPersonFirstName()).thenReturn("personFirstName");
        when(requestValidator.validate(request)).thenReturn(List.of());
        TravelPremiumCalculationResult calculationResult = mock(TravelPremiumCalculationResult.class);
        when(premiumUnderwriting.calculatePremium(request)).thenReturn(calculationResult);
        TravelCalculatePremiumResponseV1 response = service.calculatePremium(request);
        assertEquals(response.getPersonFirstName(), "personFirstName");
    }

    @Test
    public void shouldReturnResponseWithCorrectPersonLastName() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getPersonLastName()).thenReturn("personLastName");
        when(requestValidator.validate(request)).thenReturn(List.of());
        TravelPremiumCalculationResult calculationResult = mock(TravelPremiumCalculationResult.class);
        when(premiumUnderwriting.calculatePremium(request)).thenReturn(calculationResult);
        TravelCalculatePremiumResponseV1 response = service.calculatePremium(request);
        assertEquals(response.getPersonLastName(), "personLastName");
    }

    @Test
    public void shouldReturnResponseWithCorrectAgreementDateFrom() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        Date dateFrom = new Date();
        when(request.getAgreementDateFrom()).thenReturn(dateFrom);
        when(requestValidator.validate(request)).thenReturn(List.of());
        TravelPremiumCalculationResult calculationResult = mock(TravelPremiumCalculationResult.class);
        when(premiumUnderwriting.calculatePremium(request)).thenReturn(calculationResult);
        TravelCalculatePremiumResponseV1 response = service.calculatePremium(request);
        assertEquals(response.getAgreementDateFrom(), dateFrom);
    }

    @Test
    public void shouldReturnResponseWithCorrectAgreementDateTo() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        Date dateTo = new Date();
        when(request.getAgreementDateTo()).thenReturn(dateTo);
        when(requestValidator.validate(request)).thenReturn(List.of());
        TravelPremiumCalculationResult calculationResult = mock(TravelPremiumCalculationResult.class);
        when(premiumUnderwriting.calculatePremium(request)).thenReturn(calculationResult);
        TravelCalculatePremiumResponseV1 response = service.calculatePremium(request);
        assertEquals(response.getAgreementDateTo(), dateTo);
    }

    @Test
    public void shouldReturnResponseWithCorrectAgreementPrice() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getAgreementDateFrom()).thenReturn(createDate("01.01.2023"));
        when(request.getAgreementDateTo()).thenReturn(createDate("10.01.2023"));
        when(requestValidator.validate(request)).thenReturn(List.of());
        TravelPremiumCalculationResult premiumCalculationResult = new TravelPremiumCalculationResult(new BigDecimal(9), null);
        when(premiumUnderwriting.calculatePremium(request)).thenReturn(premiumCalculationResult);
        TravelCalculatePremiumResponseV1 response = service.calculatePremium(request);
        assertEquals(response.getAgreementPremium(), new BigDecimal(9));
    }

    private List<ValidationError> buildValidationErrorList() {
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