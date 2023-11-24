package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.validators.TravelCalculatePremiumRequestValidator;
import lv.javaguru.travel.insurance.validators.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {
    private final DateTimeService dateTimeService = new DateTimeService();
    @Mock
    private TravelCalculatePremiumRequestValidator requestValidatorMock;
    @Mock
    private DateTimeService dateTimeServiceMock;
    @InjectMocks
    private TravelCalculatePremiumServiceImpl serviceMock;

    @Test
    public void shouldReturnResponseWithCorrectPersonFirstName() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("personFirstName");
        when(requestValidatorMock.validate(request)).thenReturn(List.of());
        TravelCalculatePremiumResponse response = serviceMock.calculatePremium(request);
        assertEquals(response.getPersonFirstName(), "personFirstName");
    }

    @Test
    public void shouldReturnResponseWithCorrectPersonLastName() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonLastName()).thenReturn("personLastName");
        when(requestValidatorMock.validate(request)).thenReturn(List.of());
        TravelCalculatePremiumResponse response = serviceMock.calculatePremium(request);
        assertEquals(response.getPersonLastName(), "personLastName");
    }

    @Test
    public void shouldReturnResponseWithCorrectAgreementDateFrom() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(dateTimeService.createDate("23.03.2023"));
        when(requestValidatorMock.validate(request)).thenReturn(List.of());
        TravelCalculatePremiumResponse response = serviceMock.calculatePremium(request);
        assertEquals(response.getAgreementDateFrom(), dateTimeService.createDate("23.03.2023"));
    }

    @Test
    public void shouldReturnResponseWithCorrectAgreementDateTo() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(dateTimeService.createDate("18.05.2023"));
        when(requestValidatorMock.validate(request)).thenReturn(List.of());
        TravelCalculatePremiumResponse response = serviceMock.calculatePremium(request);
        assertEquals(response.getAgreementDateTo(), dateTimeService.createDate("18.05.2023"));
    }

    @Test
    public void shouldReturnResponseWithCorrectAgreementPrice() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(dateTimeService.createDate("23.03.2023"));
        when(request.getAgreementDateTo()).thenReturn(dateTimeService.createDate("18.05.2023"));
        when(requestValidatorMock.validate(request)).thenReturn(List.of());
        when(dateTimeServiceMock.getDays(request.getAgreementDateFrom(), request.getAgreementDateTo())).thenReturn(57L);
        TravelCalculatePremiumResponse response = serviceMock.calculatePremium(request);
        assertEquals(response.getAgreementPrice(), new BigDecimal(57));
    }

    @Test
    public void shouldReturnWithErrors() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        List<ValidationError> errorList = createErrorList();
        when(requestValidatorMock.validate(request)).thenReturn(errorList);
        TravelCalculatePremiumResponse response = serviceMock.calculatePremium(request);

        assertTrue(response.hasErrors());

        List<ValidationError> responseErrorList = response.getErrors();
        assertEquals(responseErrorList.get(0).getField(), "field");
        assertEquals(responseErrorList.get(0).getMessage(), "message");
    }

    private List<ValidationError> createErrorList() {
        List<ValidationError> errorList = new ArrayList<>();
        errorList.add(new ValidationError("field", "message"));
        return errorList;
    }

}