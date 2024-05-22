package lv.javaguru.travel.insurance.core.underwriting.calculators.cancellation;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTOBuilder;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.domain.calculate.cancellation.TravelCostCoefficientDomain;
import lv.javaguru.travel.insurance.core.repositories.calculate.cancellation.TravelCostCoefficientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(SpringExtension.class)
public class TravelCancellationCostCoefficientTest {
    @InjectMocks
    private TravelCalculateCostCoefficientCancellation calculator;

    @Mock
    private TravelCostCoefficientRepository costCoefficientRepository;

    AgreementDTO agreement;
    PersonDTO person;

    @Test
    public void costCoefficientTest() {
        BigDecimal cost = BigDecimal.ONE;
        BigDecimal coefficient = BigDecimal.ZERO;
        agreement = AgreementDTOBuilder.createAgreementDTO().withCost(cost).build();

        TravelCostCoefficientDomain domain = new TravelCostCoefficientDomain();
        domain.setCoefficient(coefficient);
        when(costCoefficientRepository.findCoefficientByCost(cost)).thenReturn(Optional.of(domain));

        assertEquals("", coefficient, calculator.calculatePremium(agreement, person));
    }
}
