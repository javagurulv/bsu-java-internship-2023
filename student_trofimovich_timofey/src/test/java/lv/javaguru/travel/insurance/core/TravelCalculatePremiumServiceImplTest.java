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
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelCalculatePremiumServiceImplTest {
    @Mock
    TravelCalculatePremiumRequestValidator validator;
    @Mock
    TravelCalculatePremiumRequest request;
    @Mock TravelPremiumUnderwriting underwriting;
    @InjectMocks
    TravelCalculatePremiumServiceImpl service;

    @Test
    public void shouldReturnResponseWithCorrectPersonFirstName() {
        when(request.getPersonFirstName()).thenReturn("firstName");
        when(validator.validate(request)).thenReturn(List.of());
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertThat(response.getPersonFirstName()).isEqualTo("firstName");
    }

    @Test
    public void shouldReturnResponseWithCorrectPersonLastName() {
        when(request.getPersonLastName()).thenReturn("lastName");
        when(validator.validate(request)).thenReturn(List.of());
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertThat(response.getPersonLastName()).isEqualTo("lastName");
    }

    @Test
    public void shouldReturnResponseWithCorrectPersonDateFrom() {
        when(request.getAgreementDateFrom()).thenReturn(createDate("12.12.2020"));
        when(validator.validate(request)).thenReturn(List.of());
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertThat(response.getAgreementDateFrom()).isEqualTo(createDate("12.12.2020"));
    }

    @Test
    public void shouldReturnResponseWithCorrectPersonDateTo() {
        when(request.getAgreementDateTo()).thenReturn(createDate("12.12.2020"));
        when(validator.validate(request)).thenReturn(List.of());
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertThat(response.getAgreementDateTo()).isEqualTo(createDate("12.12.2020"));
    }

    @Test
    public void shouldReturnResponseWithCorrectAgreementPrice() {
        when(request.getAgreementDateFrom()).thenReturn(createDate("01.01.2023"));
        when(request.getAgreementDateTo()).thenReturn(createDate("10.01.2023"));
        when(validator.validate(request)).thenReturn(List.of());
        when(underwriting.calculatePremium(request)).thenReturn(new BigDecimal(9L));
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getAgreementPrice(), new BigDecimal(9));
    }

    @Test
    public void shouldReturnResponseWithErrors() {
        when(validator.validate(request)).thenReturn(buildValidationErrorList());
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertThat(response.hasErrors()).isTrue();
    }

    @Test
    public void shouldReturnResponseWithValidationErrors() {
        when(validator.validate(request)).thenReturn(buildValidationErrorList());
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertAll(
                () -> assertThat(response.getErrors().size()).isEqualTo(1),
                () -> assertThat(response.getErrors().get(0).getField()).isEqualTo("field"),
                () -> assertThat(response.getErrors().get(0).getMessage()).isEqualTo("errorMessage")
                );
    }

    private Date createDate(String str) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(str);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private List<ValidationError> buildValidationErrorList() {
        return List.of(
                new ValidationError("field", "errorMessage")
        );
    }
}

