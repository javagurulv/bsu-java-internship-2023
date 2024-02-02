package lv.javaguru.travel.insurance.core.services;

//Need add more mocks (for underwriting and responseRisks)

import lv.javaguru.travel.insurance.core.TravelCalculatePremiumRequestValidator;
import lv.javaguru.travel.insurance.core.ValidationError;
import lv.javaguru.travel.insurance.core.repositories.ClassifierRepository;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.underwriting.TravelUnderwriting;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumResponseV1;

import java.math.BigDecimal;
import java.util.*;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRisk;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRiskFactory;
import lv.javaguru.travel.insurance.rest.loggers.TravelCalculatePremiumRequestExecutionTimeLogger;
import lv.javaguru.travel.insurance.rest.loggers.TravelCalculatePremiumRequestLogger;
import lv.javaguru.travel.insurance.rest.loggers.TravelCalculatePremiumResponseLogger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class
TravelCalculatePremiumServiceImplTest {

    @InjectMocks
    TravelCalculatePremiumServiceImpl test;
    @Mock
    TravelCalculatePremiumResponseLogger responseLogger;
    @Mock
    TravelCalculatePremiumRequestLogger requestLogger;

    @Mock
    ClassifierRepository classifierRepository;

    @Mock
    ClassifierValueRepository classifierValueRepository;
    @Mock
    TravelCalculatePremiumRequestExecutionTimeLogger timeLogger;

    @Mock
    TravelUnderwriting underwriting;
    @Mock
    TravelCalculatePremiumRiskFactory riskFactory;
    TravelCalculatePremiumRequestValidator validator = mock(TravelCalculatePremiumRequestValidator.class);

    TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);

    @Test
    public void hasErrorsTest() {
        String errorCode = "ERROR_CODE_1";
        String description = "Field personFirstName is empty!";
        List<ValidationError> errors = List.of(new ValidationError(errorCode, description));
        when(request.getPersonFirstName()).thenReturn("First Name");
        when(validator.validate(request)).thenReturn(errors);
        TravelCalculatePremiumResponseV1 response = test.calculatePremium(request);
        assertTrue(response.hasErrors());
    }
    @Test
    public void validationErrorsTest() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        List<ValidationError> errors = List.of(new ValidationError("ERROR_CODE_1", "Field personFirstName is empty!"));
        when(validator.validate(request)).thenReturn(errors);
        TravelCalculatePremiumResponseV1 response = test.calculatePremium(request);

        ValidationError error = errors.get(0);
        assertEquals(error.getErrorCode(), "ERROR_CODE_1");
        assertEquals(error.getDescription(), "Field personFirstName is empty!");
    }
    @Test
    public void correctReturnResponsesFirstNameTest() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
/*
        ArrayList<String> risks = new ArrayList<>();
        risks.add(riskName);
*/
        when(request.getPersonFirstName()).thenReturn("FirstName");
        when(request.getPersonLastName()).thenReturn("LastName");
        when(request.getAgreementDateFrom()).thenReturn(new Date(1000));
        when(request.getAgreementDateTo()).thenReturn(new Date(2000));

        String riskName = "TRAVEL_MEDICAL";
        List<TravelCalculatePremiumRisk> responseRisks = new ArrayList<>();
        responseRisks.add(new TravelCalculatePremiumRisk(riskName, BigDecimal.valueOf(1)));

        when(riskFactory.buildRisksList(request)).thenReturn(responseRisks);
        //when(underwriting.calculatePremium())
//        when(request.getSelected_risks()).thenReturn(risks);
        TravelCalculatePremiumResponseV1 response = test.buildResponse(request);
        assertEquals(response.getPersonFirstName(), "FirstName");
    }
    @Test
    public void correctReturnResponsesLastNameTest() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getPersonFirstName()).thenReturn("FirstName");
        when(request.getPersonLastName()).thenReturn("LastName");
        when(request.getAgreementDateFrom()).thenReturn(new Date(1000));
        when(request.getAgreementDateTo()).thenReturn(new Date(2000));

        String riskName = "TRAVEL_MEDICAL";
        List<TravelCalculatePremiumRisk> responseRisks = new ArrayList<>();
        responseRisks.add(new TravelCalculatePremiumRisk(riskName, BigDecimal.valueOf(1)));

        when(riskFactory.buildRisksList(request)).thenReturn(responseRisks);

        TravelCalculatePremiumResponseV1 response = test.buildResponse(request);
        assertEquals(response.getPersonLastName(), "LastName");
    }
    @Test
    public void correctReturnResponsesDateFromTest() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getPersonFirstName()).thenReturn("FirstName");
        when(request.getPersonLastName()).thenReturn("LastName");
        when(request.getAgreementDateFrom()).thenReturn(new Date(1000));
        when(request.getAgreementDateTo()).thenReturn(new Date(2000));

        String riskName = "TRAVEL_MEDICAL";
        List<TravelCalculatePremiumRisk> responseRisks = new ArrayList<>();
        responseRisks.add(new TravelCalculatePremiumRisk(riskName, BigDecimal.valueOf(1)));

        when(riskFactory.buildRisksList(request)).thenReturn(responseRisks);

        //when(validator.validate(request)).thenReturn(List.of());
        TravelCalculatePremiumResponseV1 response = test.buildResponse(request);
        assertEquals(response.getAgreementDateFrom(), new Date(1000));
    }
    @Test
    public void correctReturnResponsesDateToTest() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getPersonFirstName()).thenReturn("FirstName");
        when(request.getPersonLastName()).thenReturn("LastName");
        when(request.getAgreementDateFrom()).thenReturn(new Date(1000));
        when(request.getAgreementDateTo()).thenReturn(new Date(2000));

        String riskName = "TRAVEL_MEDICAL";
        List<TravelCalculatePremiumRisk> responseRisks = new ArrayList<>();
        responseRisks.add(new TravelCalculatePremiumRisk(riskName, BigDecimal.valueOf(1)));

        when(riskFactory.buildRisksList(request)).thenReturn(responseRisks);
        //when(validator.validate(request)).thenReturn(List.of());
        TravelCalculatePremiumResponseV1 response = test.buildResponse(request);
        assertEquals(response.getAgreementDateTo(), new Date(2000));
    }


}