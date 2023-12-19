package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDto;
import lv.javaguru.travel.insurance.core.api.dto.PersonDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class TravelMedicalRiskPremiumCalculatorTest {

    private TravelMedicalRiskPremiumCalculator calculator;

    @Mock private DayCountCalculator dayCountCalculator;
    @Mock private CountryDefaultDayRateCalculator countryDefaultDayRateCalculator;
    @Mock private AgeCoefficientCalculator ageCoefficientCalculator;
    @Mock private RiskLimitLevelCalculator riskLimitLevelCalculator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        calculator = new TravelMedicalRiskPremiumCalculator();
        calculator.dayCountCalculator = dayCountCalculator;
        calculator.countryDefaultDayRateCalculator = countryDefaultDayRateCalculator;
        calculator.ageCoefficientCalculator = ageCoefficientCalculator;
        calculator.riskLimitLevelCalculator = riskLimitLevelCalculator;
    }

    @Test
    void calculatePremium_shouldReturnCorrectPremium() {
        AgreementDto agreementDto = new AgreementDto();
        PersonDto personDto = new PersonDto();

        when(dayCountCalculator.calculate(agreementDto)).thenReturn(BigDecimal.valueOf(10));
        when(countryDefaultDayRateCalculator.calculate(agreementDto)).thenReturn(BigDecimal.valueOf(50));
        when(ageCoefficientCalculator.calculate(personDto)).thenReturn(BigDecimal.valueOf(0.8));
        when(riskLimitLevelCalculator.calculate(personDto)).thenReturn(BigDecimal.valueOf(1.2));

        BigDecimal expectedPremium = BigDecimal.valueOf(10).multiply(BigDecimal.valueOf(50))
                .multiply(BigDecimal.valueOf(0.8)).multiply(BigDecimal.valueOf(1.2))
                .setScale(2, BigDecimal.ROUND_HALF_UP);

        BigDecimal actualPremium = calculator.calculatePremium(agreementDto, personDto);

        assertEquals(expectedPremium, actualPremium);
    }

    @Test
    void getRiskIc_shouldReturnCorrectRiskIc() {
        String expectedRiskIc = "TRAVEL_MEDICAL";
        String actualRiskIc = calculator.getRiskIc();
        assertEquals(expectedRiskIc, actualRiskIc);
    }
}