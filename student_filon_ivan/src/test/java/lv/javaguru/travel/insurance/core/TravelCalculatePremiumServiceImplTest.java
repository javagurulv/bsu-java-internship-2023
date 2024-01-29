package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {
    @Mock
    private TravelCalculatePremiumRequest request;
    @Mock
    private TravelPremiumUnderwriting underwriting;
    @Mock
    private TravelCalculatePremiumRequestValidator validator;
    @InjectMocks
    private TravelCalculatePremiumServiceImpl service;
    @Test
    public void correctTest() {
        Mockito.when(request.getPersonFirstName()).thenReturn("Ivan");
        Mockito.when(request.getPersonLastName()).thenReturn("Filon");
        Mockito.when(request.getAgreementDateFrom()).thenReturn(new Date(1221));
        Mockito.when(request.getAgreementDateTo()).thenReturn(new Date(1200));

        TravelCalculatePremiumResponse response = service.calculatePremiumResponse(request);

        assertEquals(request.getPersonFirstName(), response.getPersonFirstName());
        assertEquals(request.getPersonLastName(), response.getPersonLastName());
        assertEquals(request.getAgreementDateFrom(), response.getAgreementDateFrom());
        assertEquals(request.getAgreementDateTo(), response.getAgreementDateTo());
    }

    @Test
    public void incorrectTest(){
        Mockito.when(request.getPersonFirstName()).thenReturn("");
        Mockito.when(request.getPersonLastName()).thenReturn(null);
        Mockito.when(request.getAgreementDateFrom()).thenReturn(new Date(1221));
        Mockito.when(request.getAgreementDateTo()).thenReturn(new Date(1200));

        TravelCalculatePremiumResponse response = service.calculatePremiumResponse(request);

        assertEquals(request.getPersonFirstName(), response.getPersonFirstName());
        assertEquals(request.getPersonLastName(), response.getPersonLastName());
    }

    @Test
    public void incorrectTestWithErrorList(){
        List<ValidationError> list = List.of(new ValidationError("personFirstName","!"),
                new ValidationError("personLastName", "!"));
        Mockito.when(validator.validate(request)).thenReturn(list);
        TravelCalculatePremiumResponse response = service.calculatePremiumResponse(request);
        assertEquals(response.getErrors().size(), 2);
    }

    @Test
    public void shouldReturnDateTo(){
        Date date = new Date();
        Mockito.when(request.getAgreementDateTo()).thenReturn(date);
        TravelCalculatePremiumResponse response = service.calculatePremiumResponse(request);
        assertEquals(response.getAgreementDateTo(), date);
    }

    @Test
    public void shouldReturnDateFrom(){
        Date date = new Date();
        Mockito.when(request.getAgreementDateFrom()).thenReturn(date);
        TravelCalculatePremiumResponse response = service.calculatePremiumResponse(request);
        assertEquals(response.getAgreementDateFrom(), date);
    }

    @Test
    public void shouldReturnFirstName(){
        Mockito.when(request.getPersonFirstName()).thenReturn("Ivan");
        TravelCalculatePremiumResponse response = service.calculatePremiumResponse(request);
        assertEquals(response.getPersonFirstName(), "Ivan");
    }

    @Test
    public void shouldReturnLastName(){
        Mockito.when(request.getPersonLastName()).thenReturn("Filon");
        TravelCalculatePremiumResponse response = service.calculatePremiumResponse(request);
        assertEquals(response.getPersonLastName(), "Filon");
    }

    @Test
    public void shouldReturnDaysBetween() throws ParseException {
        when(request.getAgreementDateTo()).thenReturn(new SimpleDateFormat("dd.MM.yyyy").parse("18.05.2000"));
        when(request.getAgreementDateFrom()).thenReturn(new SimpleDateFormat("dd.MM.yyyy").parse("24.05.2000"));
        when(validator.validate(request)).thenReturn(List.of());
        when(underwriting.calculatePremium(request)).thenReturn(new BigDecimal(6));
        TravelCalculatePremiumResponse response = service.calculatePremiumResponse(request);
        assertEquals(response.getAgreementPrice(), new BigDecimal(6));
    }

}