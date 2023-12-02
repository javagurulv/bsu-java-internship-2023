package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumCalculationResult;
import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import lv.javaguru.travel.insurance.core.validations.TravelCalculatePremiumRequestValidator;
import lv.javaguru.travel.insurance.dto.RiskPremium;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelCalculatePremiumServiceImplTest {
    @Mock
    private TravelCalculatePremiumRequestValidator validator;
    @Mock
    private TravelPremiumUnderwriting underwriting;
    @InjectMocks
    private TravelCalculatePremiumServiceImpl service;
    private TravelCalculatePremiumRequest request;
    @BeforeEach
    void createRequest() {
        request = mock(TravelCalculatePremiumRequest.class);
    }

    @Test
    public void shouldReturnResponseWithCorrectPersonFirstName() {
        when(request.getPersonFirstName()).thenReturn("firstName");
        when(validator.validate(request)).thenReturn(List.of());
        when(underwriting.calculatePremium(request)).thenReturn(mock(TravelPremiumCalculationResult.class));
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertThat(response.getPersonFirstName()).isEqualTo("firstName");
    }

    @Test
    public void shouldReturnResponseWithCorrectPersonLastName() {
        when(request.getPersonLastName()).thenReturn("lastName");
        when(validator.validate(request)).thenReturn(List.of());
        when(underwriting.calculatePremium(request)).thenReturn(mock(TravelPremiumCalculationResult.class));
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertThat(response.getPersonLastName()).isEqualTo("lastName");
    }

    @Test
    public void shouldReturnResponseWithCorrectPersonDateFrom() {
        when(request.getAgreementDateFrom()).thenReturn(createDate("12.12.2020"));
        when(validator.validate(request)).thenReturn(List.of());
        when(underwriting.calculatePremium(request)).thenReturn(mock(TravelPremiumCalculationResult.class));
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertThat(response.getAgreementDateFrom()).isEqualTo(createDate("12.12.2020"));
    }

    @Test
    public void shouldReturnResponseWithCorrectPersonDateTo() {
        when(request.getAgreementDateTo()).thenReturn(createDate("12.12.2020"));
        when(validator.validate(request)).thenReturn(List.of());
        when(underwriting.calculatePremium(request)).thenReturn(mock(TravelPremiumCalculationResult.class));
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertThat(response.getAgreementDateTo()).isEqualTo(createDate("12.12.2020"));
    }

    @Test
    public void shouldReturnResponseWithCorrectAgreementPrice() {
       when(validator.validate(request)).thenReturn(List.of());
        TravelPremiumCalculationResult travelPremiumCalculationResult = mock(TravelPremiumCalculationResult.class);
        when(travelPremiumCalculationResult.getTotalPremium()).thenReturn(new BigDecimal(30));
        when(underwriting.calculatePremium(request)).thenReturn(travelPremiumCalculationResult);
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getAgreementPremium(), new BigDecimal(30));
    }

    @Test
    public void shouldReturnResponseWithCorrectRisks() {
        when(validator.validate(request)).thenReturn(List.of());

        TravelPremiumCalculationResult calculationResult = mock(TravelPremiumCalculationResult.class);

        RiskPremium riskPremium1 = mock(RiskPremium.class);
        when(riskPremium1.getIc()).thenReturn("RISK_1");
        when(riskPremium1.getPremium()).thenReturn(new BigDecimal(10));
        RiskPremium riskPremium2 = mock(RiskPremium.class);
        when(riskPremium2.getIc()).thenReturn("RISK_2");

        when(calculationResult.getRiskPremiums()).thenReturn(List.of(riskPremium1, riskPremium2));

        when(underwriting.calculatePremium(request)).thenReturn(calculationResult);

        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertThat(response.getRisks().size()).isEqualTo(2);
        assertThat(response.getRisks().get(0).getIc()).isEqualTo("RISK_1");
        assertThat(response.getRisks().get(1).getIc()).isEqualTo("RISK_2");
        assertThat(response.getRisks().get(0).getPremium()).isEqualTo(new BigDecimal(10));
    }

    @Test
    public void shouldReturnResponseWithCorrectCountry() {
        when(request.getCountry()).thenReturn("country");
        when(validator.validate(request)).thenReturn(List.of());
        when(underwriting.calculatePremium(request)).thenReturn(mock(TravelPremiumCalculationResult.class));
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertThat(response.getCountry()).isEqualTo("country");
    }
    @Test
    public void shouldReturnResponseWithCorrectMedicalLimitLevel() {
        when(request.getMedicalRiskLimitLevel()).thenReturn("level");
        when(validator.validate(request)).thenReturn(List.of());
        when(underwriting.calculatePremium(request)).thenReturn(mock(TravelPremiumCalculationResult.class));
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertThat(response.getMedicalRiskLimitLevel()).isEqualTo("level");
    }
    @Test
    public void shouldReturnResponseWithCorrectDateOfBirth() {
        when(request.getDateOfBirth()).thenReturn(createDate("12.12.2003"));
        when(validator.validate(request)).thenReturn(List.of());
        when(underwriting.calculatePremium(request)).thenReturn(mock(TravelPremiumCalculationResult.class));
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertThat(response.getDateOfBirth()).isEqualTo(createDate("12.12.2003"));
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
                () -> assertThat(response.getErrors().get(0).getErrorCode()).isEqualTo("error code"),
                () -> assertThat(response.getErrors().get(0).getDescription()).isEqualTo("description")
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
                new ValidationError("error code", "description")
        );
    }
}

