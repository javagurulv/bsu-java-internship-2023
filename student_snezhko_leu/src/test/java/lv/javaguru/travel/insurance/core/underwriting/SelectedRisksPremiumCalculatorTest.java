package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.underwriting.calculators.medical.TravelRiskPremiumCalculatorMedical;
import lv.javaguru.travel.insurance.core.underwriting.calculators.TravelRiskPremiumCalculatorSportActivities;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRisk;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SelectedRisksPremiumCalculatorTest {
    private SelectedRisksPremiumCalculator calculator = new SelectedRisksPremiumCalculator();

    @Mock
    private TravelCalculatePremiumRequestV1 request;


    @BeforeEach
    public void init() {
        request = mock(TravelCalculatePremiumRequestV1.class);
        List<String> risks = new ArrayList<>();
        risks.add("TRAVEL_MEDICAL");
        risks.add("TRAVEL_SPORT_ACTIVITIES");
        when(request.getAgreementDateTo()).thenReturn(Date.valueOf("2022-09-12"));
        when(request.getAgreementDateFrom()).thenReturn(Date.valueOf("2022-09-11"));
        when(request.getSelectedRisks()).thenReturn(risks);
    }
    @Test
    public void findCalculatorByIcCorrectValueTest() {

        List<TravelRiskPremiumCalculator> calcs = new ArrayList<>();

        TravelRiskPremiumCalculator calc1 = mock(TravelRiskPremiumCalculatorMedical.class);
        when(calc1.calculatePremium(request)).thenReturn(BigDecimal.ONE);
        when(calc1.getIc()).thenReturn("TRAVEL_MEDICAL");

        TravelRiskPremiumCalculator calc2 = mock(TravelRiskPremiumCalculatorSportActivities.class);
        when(calc2.calculatePremium(request)).thenReturn(BigDecimal.ZERO);
        when(calc2.getIc()).thenReturn("TRAVEL_SPORT_ACTIVITIES");

        calcs.add(calc1);
        calcs.add(calc2);

        ReflectionTestUtils.setField(calculator, "calculators", calcs);

        assertTrue(compareCalculators(calculator.findCalculatorByIc(calc1.getIc()), calc1, request));
    }

    @Test
    public void findCalculatorByIcIncorrectValueTest() {

        String incorrectRiskIc = "TRAVEL_INCORRECT_IC";
        List<TravelRiskPremiumCalculator> calcs = new ArrayList<>();

        TravelRiskPremiumCalculator calc1 = mock(TravelRiskPremiumCalculatorMedical.class);
        when(calc1.calculatePremium(request)).thenReturn(BigDecimal.ONE);
        when(calc1.getIc()).thenReturn("TRAVEL_MEDICAL");

        TravelRiskPremiumCalculator calc2 = mock(TravelRiskPremiumCalculatorSportActivities.class);
        when(calc2.calculatePremium(request)).thenReturn(BigDecimal.ZERO);
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
        when(calc1.calculatePremium(request)).thenReturn(BigDecimal.ONE);
        when(calc1.getIc()).thenReturn("TRAVEL_MEDICAL");

        TravelRiskPremiumCalculator calc2 = mock(TravelRiskPremiumCalculatorSportActivities.class);
        when(calc2.calculatePremium(request)).thenReturn(BigDecimal.ZERO);
        when(calc2.getIc()).thenReturn("TRAVEL_SPORT_ACTIVITIES");

        calcs.add(calc1);
        calcs.add(calc2);

        ReflectionTestUtils.setField(calculator, "calculators", calcs);

        TravelCalculatePremiumRisk risk1 = new TravelCalculatePremiumRisk(calc1.getIc(), calc1.calculatePremium(request));
        TravelCalculatePremiumRisk risk2 = new TravelCalculatePremiumRisk(calc2.getIc(), calc2.calculatePremium(request));
        assertTrue(compareRisks(calculator.calculatePremiumForAllRisks(request).get(0), risk1));
        assertTrue(compareRisks(calculator.calculatePremiumForAllRisks(request).get(1), risk2));
    }
    private boolean compareCalculators(TravelRiskPremiumCalculator calculator1,
                                    TravelRiskPremiumCalculator calculator2,
                                    TravelCalculatePremiumRequestV1 request) {
        return calculator2.getIc().equals(calculator1.getIc())
                && calculator1.calculatePremium(request)
                .equals(
                        calculator2.calculatePremium(request)
                );
    }

    private boolean compareRisks(TravelCalculatePremiumRisk risk1, TravelCalculatePremiumRisk risk2) {
        return risk1.getRiskIc().equals(risk2.getRiskIc())
                && risk1.getPremium().equals(risk2.getPremium());
    }

}
