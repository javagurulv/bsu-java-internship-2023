package lv.javaguru.travel.insurance.core.services.calculate.premium;

import lv.javaguru.travel.insurance.core.api.command.calculate.premium.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.calculate.premium.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.agreement.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.person.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.risk.RiskDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.services.calculate.premium.TravelCalculatePremiumServiceImpl;
import lv.javaguru.travel.insurance.core.services.entities.AgreementEntityFactory;
import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumCalculationResult;
import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import lv.javaguru.travel.insurance.core.validations.calculate.premium.TravelAgreementValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TravelCalculatePremiumServiceImplTest {
    @Mock
    private TravelAgreementValidator agreementValidator;
    @Mock
    private TravelPremiumUnderwriting premiumUnderwriting;
    @Mock
    private AgreementEntityFactory agreementEntityFactory;

    @InjectMocks
    private TravelCalculatePremiumServiceImpl service;
    private TravelCalculatePremiumCoreCommand command;

    @BeforeEach
    void init() {
        command = mock(TravelCalculatePremiumCoreCommand.class);
    }

    @Test
    void shouldReturnResultWithErrors() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        when(command.getAgreement()).thenReturn(agreement);
        ValidationErrorDTO error1 = mock(ValidationErrorDTO.class);
        ValidationErrorDTO error2 = mock(ValidationErrorDTO.class);
        List<ValidationErrorDTO> errors = List.of(error1, error2);
        when(agreementValidator.validate(agreement)).thenReturn(errors);
        TravelCalculatePremiumCoreResult result = service.calculatePremium(command);
        assertThat(result.getErrors().get(0)).isEqualTo(error1);
        assertThat(result.getErrors().get(1)).isEqualTo(error2);

    }

    @Test
    void shouldReturnResultWithCorrectAgreement() {
        AgreementDTO agreement = new AgreementDTO();
        when(command.getAgreement()).thenReturn(agreement);
        when(agreementValidator.validate(agreement)).thenReturn(List.of());
        PersonDTO person1 = new PersonDTO();
        PersonDTO person2 = new PersonDTO();
        List<PersonDTO> persons = List.of(person1, person2);
        agreement.setPersons(persons);
        TravelPremiumCalculationResult calcResult = mock(TravelPremiumCalculationResult.class);
        RiskDTO risk1 = mock(RiskDTO.class);
        RiskDTO risk2 = mock(RiskDTO.class);
        List<RiskDTO> risks = List.of(risk1, risk2);
        when(risk1.getPremium()).thenReturn(new BigDecimal("10"));
        when(risk2.getPremium()).thenReturn(new BigDecimal("10"));
        when(calcResult.getRisks()).thenReturn(risks);
        when(premiumUnderwriting.calculatePremium(eq(agreement), any(PersonDTO.class))).thenReturn(calcResult);
        TravelCalculatePremiumCoreResult result = service.calculatePremium(command);
        assertThat(result.getErrors()).isNull();
        assertThat(result.getAgreement().getAgreementPremium()).isEqualTo(new BigDecimal("40"));
    }
}
