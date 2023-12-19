package lv.javaguru.travel.insurance.core.services.calculators;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;
import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumCalculationResult;
import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CalculatorRiskPremiumsForAllPersonsTest {
    @InjectMocks
    private CalculatorRiskPremiumsForAllPersons calculatorRiskPremiumsForAllPersons;
    @Mock
    private TravelPremiumUnderwriting premiumUnderwriting;
    @Test
    public void calculateRiskPremiumForSeveralPersonsTest(){
        AgreementDTO agreementDTO = mock(AgreementDTO.class);
        PersonDTO person1 = new PersonDTO();
        PersonDTO person2 = new PersonDTO();
        TravelPremiumCalculationResult result = mock(TravelPremiumCalculationResult.class);
        RiskDTO riskDTO = mock(RiskDTO.class);

        when(result.getRisks()).thenReturn(List.of(riskDTO));
        when(premiumUnderwriting.calculatePremium(agreementDTO, person1)).thenReturn(result);
        when(premiumUnderwriting.calculatePremium(agreementDTO, person2)).thenReturn(result);
        when(agreementDTO.getPersons()).thenReturn(List.of(person1, person2));

        calculatorRiskPremiumsForAllPersons.calculate(agreementDTO);
        assertEquals(agreementDTO.getPersons().get(0).getRisks(), List.of(riskDTO));
        assertEquals(agreementDTO.getPersons().get(1).getRisks(), List.of(riskDTO));

    }
    @Test
    public void calculateRiskPremiumsForPersonTest(){
        AgreementDTO agreementDTO = mock(AgreementDTO.class);
        PersonDTO person1 = new PersonDTO();
        TravelPremiumCalculationResult result = mock(TravelPremiumCalculationResult.class);
        RiskDTO risk1 = mock(RiskDTO.class);
        RiskDTO risk2 = mock(RiskDTO.class);

        when(result.getRisks()).thenReturn(List.of(risk1, risk2));
        when(premiumUnderwriting.calculatePremium(agreementDTO, person1)).thenReturn(result);
        when(agreementDTO.getPersons()).thenReturn(List.of(person1));

        calculatorRiskPremiumsForAllPersons.calculate(agreementDTO);
        assertEquals(agreementDTO.getPersons().get(0).getRisks(), List.of(risk1, risk2));
    }
}
