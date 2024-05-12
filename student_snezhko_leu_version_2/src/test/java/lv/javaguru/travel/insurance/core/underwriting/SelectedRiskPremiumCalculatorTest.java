package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;
import lv.javaguru.travel.insurance.core.underwriting.calculators.TravelRiskPremiumCalculatorSportActivities;
import lv.javaguru.travel.insurance.core.underwriting.calculators.medical.TravelRiskPremiumCalculatorMedical;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static lv.javaguru.travel.insurance.core.api.dto.AgreementDTOBuilder.createAgreementDTO;
import static lv.javaguru.travel.insurance.core.validations.calculate.integration.CreateDateUtil.createDate;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class SelectedRiskPremiumCalculatorTest {
    private AgreementDTO agreement;

    private PersonDTO person;

    @InjectMocks
    private SelectedRisksPremiumCalculator calculator;

    @BeforeEach
    public void init() {
     //   agreement = mock(AgreementDTO.class);
      //  person = mock(PersonDTO.class);
        agreement = createAgreementDTO().withDateTo(createDate("2022-09-12"))
                .withDateFrom(createDate("2022-09-11"))
                .withSelectedRisks("TRAVEL_MEDICAL")
                .withSelectedRisks("TRAVEL_SPORT_ACTIVITIES")
                .build();
        /*
        List<String> risks = new ArrayList<>();
        risks.add("TRAVEL_MEDICAL");
        risks.add("TRAVEL_SPORT_ACTIVITIES");
        when(agreement.getAgreementDateTo()).thenReturn(Date.valueOf("2022-09-12"));
        when(agreement.getAgreementDateFrom()).thenReturn(Date.valueOf("2022-09-11"));
        when(agreement.getSelectedRisks()).thenReturn(risks);

         */
    }
    @Test
    public void findCalculatorByIcCorrectValueTest() {

        List<TravelRiskPremiumCalculator> calcs = new ArrayList<>();

        TravelRiskPremiumCalculator calc1 = mock(TravelRiskPremiumCalculatorMedical.class);
        when(calc1.calculatePremium(agreement, person)).thenReturn(BigDecimal.ONE);
        when(calc1.getIc()).thenReturn("TRAVEL_MEDICAL");

        TravelRiskPremiumCalculator calc2 = mock(TravelRiskPremiumCalculatorSportActivities.class);
        when(calc2.calculatePremium(agreement, person)).thenReturn(BigDecimal.ZERO);
        when(calc2.getIc()).thenReturn("TRAVEL_SPORT_ACTIVITIES");

        calcs.add(calc1);
        calcs.add(calc2);

        ReflectionTestUtils.setField(calculator, "calculators", calcs);

        assertTrue(compareCalculators(calculator.findCalculatorByIc(calc1.getIc()), calc1, agreement, person));
    }

    @Test
    public void findCalculatorByIcIncorrectValueTest() {

        String incorrectRiskIc = "TRAVEL_INCORRECT_IC";
        List<TravelRiskPremiumCalculator> calcs = new ArrayList<>();

        TravelRiskPremiumCalculator calc1 = mock(TravelRiskPremiumCalculatorMedical.class);
        when(calc1.calculatePremium(agreement, person)).thenReturn(BigDecimal.ONE);
        when(calc1.getIc()).thenReturn("TRAVEL_MEDICAL");

        TravelRiskPremiumCalculator calc2 = mock(TravelRiskPremiumCalculatorSportActivities.class);
        when(calc2.calculatePremium(agreement, person)).thenReturn(BigDecimal.ZERO);
        when(calc2.getIc()).thenReturn("TRAVEL_SPORT_ACTIVITIES");

        calcs.add(calc1);
        calcs.add(calc2);

        ReflectionTestUtils.setField(calculator, "calculators", calcs);

        assertThrows(RuntimeException.class,() -> calculator.findCalculatorByIc(incorrectRiskIc));
        //assertTrue(compareCalculators(calculator.findCalculatorByIc(calc1.getIc()), calc1, request));
    }


    @Test
    public void calculatePremiumForAllRisksTest() {
        List<TravelRiskPremiumCalculator> calcs = new ArrayList<>();

        TravelRiskPremiumCalculator calc1 = mock(TravelRiskPremiumCalculatorMedical.class);
        when(calc1.calculatePremium(agreement, person)).thenReturn(BigDecimal.ONE);
        when(calc1.getIc()).thenReturn("TRAVEL_MEDICAL");

        TravelRiskPremiumCalculator calc2 = mock(TravelRiskPremiumCalculatorSportActivities.class);
        when(calc2.calculatePremium(agreement, person)).thenReturn(BigDecimal.ZERO);
        when(calc2.getIc()).thenReturn("TRAVEL_SPORT_ACTIVITIES");

        calcs.add(calc1);
        calcs.add(calc2);

        ReflectionTestUtils.setField(calculator, "calculators", calcs);

        RiskDTO risk1 = new RiskDTO(calc1.getIc(), calc1.calculatePremium(agreement, person));
        RiskDTO risk2 = new RiskDTO(calc2.getIc(), calc2.calculatePremium(agreement, person));
        assertTrue(compareRisks(calculator.calculatePremiumForAllRisks(agreement, person).get(0), risk1));
        assertTrue(compareRisks(calculator.calculatePremiumForAllRisks(agreement, person).get(1), risk2));
    }
    private boolean compareCalculators(TravelRiskPremiumCalculator calculator1,
                                       TravelRiskPremiumCalculator calculator2,
                                       AgreementDTO agreement,
                                       PersonDTO person) {
        return calculator2.getIc().equals(calculator1.getIc())
                && calculator1.calculatePremium(agreement, person)
                .equals(
                        calculator2.calculatePremium(agreement, person)
                );
    }

    private boolean compareRisks(RiskDTO risk1, RiskDTO risk2) {
        return risk1.getRiskIc().equals(risk2.getRiskIc())
                && risk1.getPremium().equals(risk2.getPremium());
    }
}
