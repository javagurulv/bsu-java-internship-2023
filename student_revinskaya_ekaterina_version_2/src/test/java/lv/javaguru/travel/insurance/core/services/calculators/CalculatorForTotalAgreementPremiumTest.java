package lv.javaguru.travel.insurance.core.services.calculators;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CalculatorForTotalAgreementPremiumTest {
    @InjectMocks
    private CalculatorForTotalAgreementPremium calculatorForTotalAgreementPremium;

    @Test
    public void calculateForTotalAgreementPremium() {
        AgreementDTO agreementDTO = mock(AgreementDTO.class);
        PersonDTO person1 = mock(PersonDTO.class);
        PersonDTO person2 = mock(PersonDTO.class);
        RiskDTO risk = mock(RiskDTO.class);
        when(risk.getPremium()).thenReturn(BigDecimal.valueOf(6));
        when(person1.getRisks()).thenReturn(List.of(risk));
        when(person2.getRisks()).thenReturn(List.of(risk));
        when(agreementDTO.getPersons()).thenReturn(List.of(person1, person2));
        assertEquals(calculatorForTotalAgreementPremium.calculate(agreementDTO), BigDecimal.valueOf(12));
    }

}
