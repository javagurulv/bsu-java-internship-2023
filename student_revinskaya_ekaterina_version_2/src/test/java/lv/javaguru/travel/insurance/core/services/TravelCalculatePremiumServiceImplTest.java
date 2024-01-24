package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.services.calculators.CalculatorForTotalAgreementPremium;
import lv.javaguru.travel.insurance.core.services.calculators.CalculatorRiskPremiumsForAllPersons;
import lv.javaguru.travel.insurance.core.services.savers.PolicySaver;
import lv.javaguru.travel.insurance.core.validations.TravelAgreementValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TravelCalculatePremiumServiceImplTest {
    @InjectMocks
    private TravelCalculatePremiumServiceImpl travelCalculatePremiumService;
    @Mock
    private PolicySaver policySaver;
    @Mock
    private TravelAgreementValidator agreementValidator;
    @Mock
    private CalculatorForTotalAgreementPremium calculatorForTotalAgreementPremium;
    @Mock
    private CalculatorRiskPremiumsForAllPersons calculatorRiskPremiumsForAllPersons;
    @Test
    public void calculatePremiumResultWithoutErrorsTest() {
        AgreementDTO agreementDTO = new AgreementDTO();
        when(calculatorForTotalAgreementPremium.calculate(agreementDTO)).thenReturn(BigDecimal.valueOf(12));
        Mockito.doNothing().when(policySaver).savePolicy(agreementDTO);
        Mockito.doNothing().when(calculatorRiskPremiumsForAllPersons).calculate(agreementDTO);
        assertEquals(travelCalculatePremiumService
                .calculatePremium(new TravelCalculatePremiumCoreCommand(agreementDTO)).getAgreement(), agreementDTO);

    }
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
}
