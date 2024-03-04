package lv.javaguru.travel.insurance.core;

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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {
    @Mock
    private TravelUnderwritingProcess underwritingProcess;
    @Mock
    private TravelCalculatePremiumRequestValidator requestValidator;
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
        TravelCalculatePremiumRequest reqMock = mock(TravelCalculatePremiumRequest.class);
        when(reqMock.getPersonFirstName()).thenReturn("Vasya");
        when(requestValidator.validate(reqMock)).thenReturn(List.of());
        TravelCalculatePremiumResponse response = service.calculatePremium(reqMock);
        assertEquals(response.getPersonFirstName(), "Vasya");
    }

    @Test
    public void checkResponseLastName() {
        TravelCalculatePremiumRequest reqMock = mock(TravelCalculatePremiumRequest.class);
        when(reqMock.getPersonLastName()).thenReturn("Pupkin");
        when(requestValidator.validate(reqMock)).thenReturn(List.of());
        TravelCalculatePremiumResponse response = service.calculatePremium(reqMock);
        assertEquals(response.getPersonLastName(), "Pupkin");
    }

    @Test
    public void checkResponseAgreementDateFrom() {
        TravelCalculatePremiumRequest reqMock = mock(TravelCalculatePremiumRequest.class);
        Date dateFrom = new Date();
        when(reqMock.getAgreementDateFrom()).thenReturn(dateFrom);
        when(requestValidator.validate(reqMock)).thenReturn(List.of());
        TravelCalculatePremiumResponse response = service.calculatePremium(reqMock);
        assertEquals(response.getAgreementDateFrom(), dateFrom);
    }

    @Test
    public void checkResponseAgreementDateTo() {
        TravelCalculatePremiumRequest reqMock = mock(TravelCalculatePremiumRequest.class);
        Date dateTo = new Date();
        when(reqMock.getAgreementDateTo()).thenReturn(dateTo);
        when(requestValidator.validate(reqMock)).thenReturn(List.of());
        TravelCalculatePremiumResponse response = service.calculatePremium(reqMock);
        assertEquals(response.getAgreementDateTo(), dateTo);
    }

    @Test
    public void checkResponseAgreementPrice() {
        TravelCalculatePremiumRequest reqMock = mock(TravelCalculatePremiumRequest.class);
        when(reqMock.getAgreementDateFrom()).thenReturn(createDate("01.01.2023"));
        when(reqMock.getAgreementDateTo()).thenReturn(createDate("11.01.2023"));
        when(requestValidator.validate(reqMock)).thenReturn(List.of());
        when(underwritingProcess.calculatePremium(reqMock)).thenReturn(new BigDecimal(10));
        TravelCalculatePremiumResponse response = service.calculatePremium(reqMock);
        assertEquals(response.getAgreementPrice(), new BigDecimal(10));
    }

    @Test
    public void shouldReturnResponseWithErrors() {
        TravelCalculatePremiumRequest reqMock = mock(TravelCalculatePremiumRequest.class);
        List<ValidationError> errors = createValidationErrorList();
        when(requestValidator.validate(reqMock)).thenReturn(errors);
        TravelCalculatePremiumResponse response = service.calculatePremium(reqMock);
        assertTrue(response.hasErrors());
    }

    @Test
    public void shouldReturnResponseWithValidationErrors() {
        TravelCalculatePremiumRequest reqMock = mock(TravelCalculatePremiumRequest.class);
        List<ValidationError> errors = createValidationErrorList();
        when(requestValidator.validate(reqMock)).thenReturn(errors);
        TravelCalculatePremiumResponse response = service.calculatePremium(reqMock);
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "field");
        assertEquals(response.getErrors().get(0).getMessage(), "errorMessage");
    }

    @Test
    public void shouldNotInvokeDateTimeUtilWhenValidationErrors() {
        TravelCalculatePremiumRequest reqMock = mock(TravelCalculatePremiumRequest.class);
        List<ValidationError> errors = createValidationErrorList();
        when(requestValidator.validate(reqMock)).thenReturn(errors);
        TravelCalculatePremiumResponse response = service.calculatePremium(reqMock);
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "field");
        assertEquals(response.getErrors().get(0).getMessage(), "errorMessage");
    }
}