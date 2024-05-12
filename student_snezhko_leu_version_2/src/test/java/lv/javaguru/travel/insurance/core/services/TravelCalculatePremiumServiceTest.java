package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.api.command.calculate.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.calculate.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.services.calculate.TravelCalculatePremiumServiceImpl;
import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumCalculationResult;
import lv.javaguru.travel.insurance.core.underwriting.TravelUnderwriting;
import lv.javaguru.travel.insurance.core.util.AgreementSaveUtil;
import lv.javaguru.travel.insurance.core.validations.calculate.TravelAgreementValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(SpringExtension.class)
public class TravelCalculatePremiumServiceTest {
    @InjectMocks
    private TravelCalculatePremiumServiceImpl service;

    @Mock
    private TravelAgreementValidator validator;

    @Mock
    private TravelUnderwriting underwriting;

    @Mock
    private AgreementSaveUtil agreementSaveUtil;
    private AgreementDTO agreement = new AgreementDTO();


    private PersonDTO person = new PersonDTO();

    @Mock
    private RiskDTO risk;

    //correct value
    //one error
    @Test
    public void correctValueServiceTest() {
        BigDecimal riskPremium = BigDecimal.ONE;
        BigDecimal totalPremium = BigDecimal.ONE;
        List<RiskDTO> risks = List.of(risk);
        person.setSelectedRisks(risks);
        when(risk.getPremium()).thenReturn(riskPremium);
        agreement.setPersons(List.of(person));
        when(underwriting.calculatePremium(agreement, person)).thenReturn(new TravelPremiumCalculationResult(totalPremium, risks));

        when(validator.validate(agreement)).thenReturn(List.of());


        TravelCalculatePremiumCoreResult result = service.calculatePremium(new TravelCalculatePremiumCoreCommand(agreement));
        assertEquals("", totalPremium, result.getAgreement().getAgreementPremium());
        assertEquals("", riskPremium, result.getAgreement().getPersons().get(0).getSelectedRisks().get(0).getPremium());
    }

    @Test
    public void oneErrorTest() {
        ValidationErrorDTO error = new ValidationErrorDTO("TEST_ERROR_CODE", "Test error description");
        when(validator.validate(agreement)).thenReturn(List.of(error));

        TravelCalculatePremiumCoreResult result = service.calculatePremium(new TravelCalculatePremiumCoreCommand(agreement));
        assertEquals("", error.getErrorCode(), result.getErrors().get(0).getErrorCode());
        assertEquals("", error.getDescription(), result.getErrors().get(0).getDescription());
    }
}
