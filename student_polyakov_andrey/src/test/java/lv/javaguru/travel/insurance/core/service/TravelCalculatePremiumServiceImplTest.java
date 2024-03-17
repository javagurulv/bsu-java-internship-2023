package lv.javaguru.travel.insurance.core.service;

import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumCalculationResult;
import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import lv.javaguru.travel.insurance.core.validation.TravelCalculatePremiumRequestValidator;
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
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {
    @Mock
    private TravelPremiumUnderwriting underwritingProcessMock;
    @Mock
    private TravelPremiumCalculationResult calculationResultMock;
    @Mock
    private TravelCalculatePremiumRequestValidator requestValidatorMock;
    @Mock
    private TravelCalculatePremiumRequest reqMock;
    @InjectMocks
    private TravelCalculatePremiumServiceImpl service;

    private List<ValidationError> createValidationErrorList() {
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

    @Test
    public void checkResponseFirstName() {
        when(reqMock.getPersonFirstName()).thenReturn("Vasya");
        when(requestValidatorMock.validate(reqMock)).thenReturn(List.of());
        when(underwritingProcessMock.calculatePremium(reqMock)).thenReturn(calculationResultMock);
        TravelCalculatePremiumResponse response = service.calculatePremium(reqMock);
        assertEquals(response.getPersonFirstName(), "Vasya");
    }

    @Test
    public void checkResponseLastName() {
        when(reqMock.getPersonLastName()).thenReturn("Pupkin");
        when(requestValidatorMock.validate(reqMock)).thenReturn(List.of());
        when(underwritingProcessMock.calculatePremium(reqMock)).thenReturn(calculationResultMock);
        TravelCalculatePremiumResponse response = service.calculatePremium(reqMock);
        assertEquals(response.getPersonLastName(), "Pupkin");
    }

    @Test
    public void checkResponseAgreementDateFrom() {
        Date dateFrom = new Date();
        when(reqMock.getAgreementDateFrom()).thenReturn(dateFrom);
        when(requestValidatorMock.validate(reqMock)).thenReturn(List.of());
        when(underwritingProcessMock.calculatePremium(reqMock)).thenReturn(calculationResultMock);
        TravelCalculatePremiumResponse response = service.calculatePremium(reqMock);
        assertEquals(response.getAgreementDateFrom(), dateFrom);
    }

    @Test
    public void checkResponseAgreementDateTo() {
        Date dateTo = new Date();
        when(reqMock.getAgreementDateTo()).thenReturn(dateTo);
        when(requestValidatorMock.validate(reqMock)).thenReturn(List.of());
        when(underwritingProcessMock.calculatePremium(reqMock)).thenReturn(calculationResultMock);
        TravelCalculatePremiumResponse response = service.calculatePremium(reqMock);
        assertEquals(response.getAgreementDateTo(), dateTo);
    }

    @Test
    public void checkResponseAgreementPrice() {
        when(reqMock.getAgreementDateFrom()).thenReturn(createDate("01.01.2023"));
        when(reqMock.getAgreementDateTo()).thenReturn(createDate("11.01.2023"));
        when(requestValidatorMock.validate(reqMock)).thenReturn(List.of());
        TravelPremiumCalculationResult premiumCalculationResult = new TravelPremiumCalculationResult(new BigDecimal(10), null);
        when(underwritingProcessMock.calculatePremium(reqMock)).thenReturn(premiumCalculationResult);
        TravelCalculatePremiumResponse response = service.calculatePremium(reqMock);
        assertEquals(response.getAgreementPremium(), new BigDecimal(10));
        verifyNoInteractions(calculationResultMock);
    }

    @Test
    public void shouldReturnResponseWithErrors() {
        List<ValidationError> errors = createValidationErrorList();
        when(requestValidatorMock.validate(reqMock)).thenReturn(errors);
        TravelCalculatePremiumResponse response = service.calculatePremium(reqMock);
        assertTrue(response.hasErrors());
        verifyNoInteractions(calculationResultMock, underwritingProcessMock);
    }

    @Test
    public void shouldReturnResponseWithValidationErrors() {
        List<ValidationError> errors = createValidationErrorList();
        when(requestValidatorMock.validate(reqMock)).thenReturn(errors);
        TravelCalculatePremiumResponse response = service.calculatePremium(reqMock);
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getErrorCode(), "field");
        assertEquals(response.getErrors().get(0).getDescription(), "errorMessage");
        verifyNoInteractions(calculationResultMock, underwritingProcessMock);
    }

    @Test
    public void shouldNotInvokeDateTimeUtilWhenValidationErrors() {
        List<ValidationError> errors = createValidationErrorList();
        when(requestValidatorMock.validate(reqMock)).thenReturn(errors);
        TravelCalculatePremiumResponse response = service.calculatePremium(reqMock);
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getErrorCode(), "field");
        assertEquals(response.getErrors().get(0).getDescription(), "errorMessage");
        verifyNoInteractions(calculationResultMock, underwritingProcessMock);
    }
}