package lv.javaguru.travel.insurance.core.service;

import lv.javaguru.travel.insurance.core.underwriting.TravelUnderwritingProcess;
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
        when(requestValidator.validate(reqMock)).thenReturn(List.of());
        TravelCalculatePremiumResponse response = service.calculatePremium(reqMock);
        assertEquals(response.getPersonFirstName(), "Vasya");
    }

    @Test
    public void checkResponseLastName() {
        when(reqMock.getPersonLastName()).thenReturn("Pupkin");
        when(requestValidator.validate(reqMock)).thenReturn(List.of());
        TravelCalculatePremiumResponse response = service.calculatePremium(reqMock);
        assertEquals(response.getPersonLastName(), "Pupkin");
    }

    @Test
    public void checkResponseAgreementDateFrom() {
        Date dateFrom = new Date();
        when(reqMock.getAgreementDateFrom()).thenReturn(dateFrom);
        when(requestValidator.validate(reqMock)).thenReturn(List.of());
        TravelCalculatePremiumResponse response = service.calculatePremium(reqMock);
        assertEquals(response.getAgreementDateFrom(), dateFrom);
    }

    @Test
    public void checkResponseAgreementDateTo() {
        Date dateTo = new Date();
        when(reqMock.getAgreementDateTo()).thenReturn(dateTo);
        when(requestValidator.validate(reqMock)).thenReturn(List.of());
        TravelCalculatePremiumResponse response = service.calculatePremium(reqMock);
        assertEquals(response.getAgreementDateTo(), dateTo);
    }

    @Test
    public void checkResponseAgreementPrice() {
        when(reqMock.getAgreementDateFrom()).thenReturn(createDate("01.01.2023"));
        when(reqMock.getAgreementDateTo()).thenReturn(createDate("11.01.2023"));
        when(requestValidator.validate(reqMock)).thenReturn(List.of());
        when(underwritingProcess.calculatePremium(reqMock)).thenReturn(new BigDecimal(10));
        TravelCalculatePremiumResponse response = service.calculatePremium(reqMock);
        assertEquals(response.getAgreementPrice(), new BigDecimal(10));
    }

    @Test
    public void shouldReturnResponseWithErrors() {
        List<ValidationError> errors = createValidationErrorList();
        when(requestValidator.validate(reqMock)).thenReturn(errors);
        TravelCalculatePremiumResponse response = service.calculatePremium(reqMock);
        assertTrue(response.hasErrors());
    }

    @Test
    public void shouldReturnResponseWithValidationErrors() {
        List<ValidationError> errors = createValidationErrorList();
        when(requestValidator.validate(reqMock)).thenReturn(errors);
        TravelCalculatePremiumResponse response = service.calculatePremium(reqMock);
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getErrorCode(), "field");
        assertEquals(response.getErrors().get(0).getDescription(), "errorMessage");
    }

    @Test
    public void shouldNotInvokeDateTimeUtilWhenValidationErrors() {
        List<ValidationError> errors = createValidationErrorList();
        when(requestValidator.validate(reqMock)).thenReturn(errors);
        TravelCalculatePremiumResponse response = service.calculatePremium(reqMock);
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getErrorCode(), "field");
        assertEquals(response.getErrors().get(0).getDescription(), "errorMessage");
    }
}