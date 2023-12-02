package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumCalculationResult;
import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import lv.javaguru.travel.insurance.core.validations.TravelAgreementValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TravelCalculatePremiumServiceImplTest {
    @InjectMocks
    private TravelCalculatePremiumServiceImpl travelCalculatePremiumService;
    @Mock
    private TravelAgreementValidator agreementValidator;
    @Mock
    private TravelPremiumUnderwriting premiumUnderwriting;

    @Test
    public void calculatePremiumResultWithErrorsTest() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        TravelCalculatePremiumCoreCommand command = mock(TravelCalculatePremiumCoreCommand.class);
        when(command.getAgreement()).thenReturn(agreement);
        ValidationErrorDTO error = mock(ValidationErrorDTO.class);
        when(agreementValidator.validate(agreement)).thenReturn(List.of(error));
        assertEquals(travelCalculatePremiumService.calculatePremium(command).getErrors(), List.of(error));
        assertNull(travelCalculatePremiumService.calculatePremium(command).getAgreement());
    }

    @Test
    public void calculatePremiumAgreementPremiumWithTwoPersonsTest() {
        AgreementDTO agreementDTO =spy(AgreementDTO.class);
        TravelCalculatePremiumCoreCommand command = new TravelCalculatePremiumCoreCommand(agreementDTO);
        when(agreementValidator.validate(agreementDTO)).thenReturn(List.of());
        PersonDTO person1 = new PersonDTO();
        PersonDTO person2 = new PersonDTO();
        when(agreementDTO.getPersons()).thenReturn(List.of(person1, person2));
        TravelPremiumCalculationResult calculationResult = mock(TravelPremiumCalculationResult.class);
        RiskDTO riskDTO = new RiskDTO("RISK", BigDecimal.TEN);
        when(calculationResult.getRisks()).thenReturn(List.of(riskDTO));
        when(premiumUnderwriting.calculatePremium(agreementDTO, person1)).thenReturn(calculationResult);
        when(premiumUnderwriting.calculatePremium(agreementDTO, person2)).thenReturn(calculationResult);
        assertEquals(travelCalculatePremiumService.calculatePremium(command)
                .getAgreement().getAgreementPremium(), BigDecimal.valueOf(20));
        assertNull(travelCalculatePremiumService.calculatePremium(command).getErrors());
    }
    @Test
    public void calculatePremiumAgreementPremiumWithTwoRisksTest() {
        AgreementDTO agreementDTO =spy(AgreementDTO.class);
        TravelCalculatePremiumCoreCommand command = new TravelCalculatePremiumCoreCommand(agreementDTO);
        when(agreementValidator.validate(agreementDTO)).thenReturn(List.of());
        PersonDTO person1 = new PersonDTO();
        when(agreementDTO.getPersons()).thenReturn(List.of(person1));
        TravelPremiumCalculationResult calculationResult = mock(TravelPremiumCalculationResult.class);
        RiskDTO risk1 = new RiskDTO("RISK1", BigDecimal.TEN);
        RiskDTO risk2 = new RiskDTO("RISK2", BigDecimal.TEN);
        when(calculationResult.getRisks()).thenReturn(List.of(risk1, risk2));
        when(premiumUnderwriting.calculatePremium(agreementDTO, person1)).thenReturn(calculationResult);
        assertEquals(travelCalculatePremiumService.calculatePremium(command)
                .getAgreement().getAgreementPremium(), BigDecimal.valueOf(20));
        assertNull(travelCalculatePremiumService.calculatePremium(command).getErrors());
    }
    @Test
    public void calculatePremiumPersonRisksTest() {
        AgreementDTO agreementDTO =spy(AgreementDTO.class);
        TravelCalculatePremiumCoreCommand command = new TravelCalculatePremiumCoreCommand(agreementDTO);
        when(agreementValidator.validate(agreementDTO)).thenReturn(List.of());
        PersonDTO person1 = new PersonDTO();
        when(agreementDTO.getPersons()).thenReturn(List.of(person1));
        TravelPremiumCalculationResult calculationResult = mock(TravelPremiumCalculationResult.class);
        RiskDTO risk1 = new RiskDTO("RISK1", BigDecimal.TEN);
        RiskDTO risk2 = new RiskDTO("RISK2", BigDecimal.TEN);
        when(calculationResult.getRisks()).thenReturn(List.of(risk1, risk2));
        when(premiumUnderwriting.calculatePremium(agreementDTO, person1)).thenReturn(calculationResult);

        assertNull(travelCalculatePremiumService.calculatePremium(command).getErrors());
        assertFalse(travelCalculatePremiumService.calculatePremium(command)
                .getAgreement().getPersons().isEmpty());
        assertEquals(travelCalculatePremiumService.calculatePremium(command)
                .getAgreement().getPersons().size(), 1);
        assertEquals(travelCalculatePremiumService.calculatePremium(command)
                .getAgreement().getPersons().get(0).getRisks(), List.of(risk1, risk2));
    }
    @Test
    public void calculatePremiumPersonListRiskTest() {
        AgreementDTO agreementDTO =spy(AgreementDTO.class);
        TravelCalculatePremiumCoreCommand command = new TravelCalculatePremiumCoreCommand(agreementDTO);
        when(agreementValidator.validate(agreementDTO)).thenReturn(List.of());
        PersonDTO person1 = new PersonDTO();
        when(agreementDTO.getPersons()).thenReturn(List.of(person1));
        TravelPremiumCalculationResult calculationResult = mock(TravelPremiumCalculationResult.class);
        RiskDTO risk1 = new RiskDTO("RISK1", BigDecimal.TEN);
        RiskDTO risk2 = new RiskDTO("RISK2", BigDecimal.TEN);
        when(calculationResult.getRisks()).thenReturn(List.of(risk1, risk2));
        when(premiumUnderwriting.calculatePremium(agreementDTO, person1)).thenReturn(calculationResult);

        assertNull(travelCalculatePremiumService.calculatePremium(command).getErrors());
        assertFalse(travelCalculatePremiumService.calculatePremium(command)
                .getAgreement().getPersons().isEmpty());
        assertEquals(travelCalculatePremiumService.calculatePremium(command)
                .getAgreement().getPersons().size(), 1);
        assertEquals(travelCalculatePremiumService.calculatePremium(command)
                .getAgreement().getPersons().get(0).getRisks(), List.of(risk1, risk2));
    }
    @Test
    public void calculatePremiumPersonsRiskTest() {
        AgreementDTO agreementDTO =spy(AgreementDTO.class);
        TravelCalculatePremiumCoreCommand command = new TravelCalculatePremiumCoreCommand(agreementDTO);
        when(agreementValidator.validate(agreementDTO)).thenReturn(List.of());
        PersonDTO person1 = new PersonDTO();
        PersonDTO person2 = new PersonDTO();
        when(agreementDTO.getPersons()).thenReturn(List.of(person1, person2));
        TravelPremiumCalculationResult calculationResult = mock(TravelPremiumCalculationResult.class);
        RiskDTO risk1 = new RiskDTO("RISK1", BigDecimal.TEN);
        when(calculationResult.getRisks()).thenReturn(List.of(risk1));
        when(premiumUnderwriting.calculatePremium(agreementDTO, person1)).thenReturn(calculationResult);
        when(premiumUnderwriting.calculatePremium(agreementDTO, person2)).thenReturn(calculationResult);

        assertNull(travelCalculatePremiumService.calculatePremium(command).getErrors());
        assertFalse(travelCalculatePremiumService.calculatePremium(command)
                .getAgreement().getPersons().isEmpty());
        assertEquals(travelCalculatePremiumService.calculatePremium(command)
                .getAgreement().getPersons().size(), 2);
        assertEquals(travelCalculatePremiumService.calculatePremium(command)
                .getAgreement().getPersons().get(0).getRisks(), List.of(risk1));
        assertEquals(travelCalculatePremiumService.calculatePremium(command)
                .getAgreement().getPersons().get(1).getRisks(), List.of(risk1));
    }
}
