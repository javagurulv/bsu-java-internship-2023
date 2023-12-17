package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.services.calculators.CalculatorForTotalAgreementPremium;
import lv.javaguru.travel.insurance.core.services.calculators.CalculatorRiskPremiumsForAllPersons;
import lv.javaguru.travel.insurance.core.services.savers.AgreementSaver;
import lv.javaguru.travel.insurance.core.services.savers.PersonSaver;
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
    private PersonSaver personSaver;
    @Mock
    private AgreementSaver agreementSaver;
    @Mock
    private TravelAgreementValidator agreementValidator;
    @Mock
    private CalculatorForTotalAgreementPremium calculatorForTotalAgreementPremium;
    @Mock
    private CalculatorRiskPremiumsForAllPersons calculatorRiskPremiumsForAllPersons;
    @Test
    public void calculatePremiumResultWithoutErrorsTest() {
        AgreementDTO agreement = new AgreementDTO();
        when(calculatorForTotalAgreementPremium.calculate(agreement)).thenReturn(BigDecimal.valueOf(12));
        Mockito.doNothing().when(agreementSaver).saveAgreements(agreement);
        Mockito.doNothing().when(calculatorRiskPremiumsForAllPersons).calculate(agreement);
        assertEquals(travelCalculatePremiumService
                .calculatePremium(new TravelCalculatePremiumCoreCommand(agreement)).getAgreement(), agreement);

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
