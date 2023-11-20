package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
class TravelCalculatePremiumServiceImplTest {


    @Mock
    TravelCalculatorPremiumRequestValidator validator;
    @Mock
    DateTimeService dateTimeService;

    @InjectMocks
    TravelCalculatePremiumServiceImpl service;

    @Test
    void shouldReturnResponseWithErrors() {
        TravelCalculatePremiumRequest request = Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(validator.validate(request)).thenReturn(buildErrors());
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertThat(response.hasErrors()).isEqualTo(true);
    }

    @Test
    void shouldReturnResponseWithErrorsFromValidator() {
        TravelCalculatePremiumRequest request = Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(validator.validate(request)).thenReturn(buildErrors());
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertThat(response.getValidationErrors().size()).isEqualTo(1);
        assertThat(response.getValidationErrors().get(0).getField()).isEqualTo("errorField");
        assertThat(response.getValidationErrors().get(0).getMessage()).isEqualTo("errorMessage");
    }

    @Test
    void responseWithValidFirstName() {
        TravelCalculatePremiumRequest request = Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getPersonFirstName()).thenReturn("firstName");
        Mockito.when(validator.validate(request)).thenReturn(List.of());
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertThat(response.getPersonFirstName()).isEqualTo("firstName");
    }

    @Test
    void responseWithValidLLastName() {
        TravelCalculatePremiumRequest request = Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getPersonLastName()).thenReturn("lastName");
        Mockito.when(validator.validate(request)).thenReturn(List.of());
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertThat(response.getPersonLastName()).isEqualTo("lastName");
    }

    @Test
    void responseWithValidAgreementDateFrom() {
        TravelCalculatePremiumRequest request = Mockito.mock(TravelCalculatePremiumRequest.class);
        Date dateFrom = createDate("11.11.2023");
        Mockito.when(request.getAgreementDateFrom()).thenReturn(dateFrom);
        Mockito.when(validator.validate(request)).thenReturn(List.of());
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertThat(response.getAgreementDateFrom()).isEqualTo(dateFrom);
    }

    @Test
    void responseWithValidAgreementDateTo() {
        TravelCalculatePremiumRequest request = Mockito.mock(TravelCalculatePremiumRequest.class);
        Date dateFrom = createDate("11.11.2023");
        Mockito.when(request.getAgreementDateTo()).thenReturn(dateFrom);
        Mockito.when(validator.validate(request)).thenReturn(List.of());
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertThat(response.getAgreementDateTo()).isEqualTo(dateFrom);
    }

    @Test
    void responseWithAgreementPrice() {
        TravelCalculatePremiumRequest request = Mockito.mock(TravelCalculatePremiumRequest.class);
        Date dateFrom = createDate("03.11.2020");
        Date dateTo = createDate("13.11.2020");
        Mockito.when(request.getAgreementDateFrom()).thenReturn(dateFrom);
        Mockito.when(request.getAgreementDateTo()).thenReturn(dateTo);
        Mockito.when(dateTimeService.getDaysBetween(dateFrom, dateTo)).thenReturn(10L);
        Mockito.when(validator.validate(request)).thenReturn(List.of());
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertThat(response.getAgreementPrice()).isEqualTo(BigDecimal.valueOf(10L));
    }

   private static List<ValidationError> buildErrors() {
        return List.of(new ValidationError("errorField", "errorMessage"));
    }

    private static Date createDate(String simpleDate) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(simpleDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


}