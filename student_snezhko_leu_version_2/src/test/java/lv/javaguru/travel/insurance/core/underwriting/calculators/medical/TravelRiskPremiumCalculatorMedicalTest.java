package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.domain.AgeCoefficient;
import lv.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
//@DataJpaTest
public class TravelRiskPremiumCalculatorMedicalTest {
    @InjectMocks
    private TravelRiskPremiumCalculatorMedical calculator = new TravelRiskPremiumCalculatorMedical();
    @Mock
    private TravelCalculateDayCount dayCountCalculator = mock(TravelCalculateDayCount.class);
    //CountryDefaultDayRateRepository cddrRepository = mock(CountryDefaultDayRateRepository.class);

    @Mock
    private TravelCalculateMedicalAgeCoefficient ageCoefficientCalculator = mock(TravelCalculateMedicalAgeCoefficient.class);
    //private AgeCoefficientRepository acRepository = mock(AgeCoefficientRepository.class);

    @Mock
    private TravelCalculateMedicalCountryDefaultDayRate cddrCalculator = mock(TravelCalculateMedicalCountryDefaultDayRate.class);

    @Mock
    private TravelCalculateInsuranceLimitCoefficient mrllCoefficientCalculator = mock(TravelCalculateInsuranceLimitCoefficient.class);

    private AgreementDTO agreement = mock(AgreementDTO.class);
    private PersonDTO person = mock(PersonDTO.class);
    /*
    @BeforeEach
    public void init() {
        request = mock(TravelCalculatePremiumRequest.class);
        String countryName = "LATVIA";
        Double cddrValue = 1.00d;
        List<String> risks = new ArrayList<>();
        risks.add(calculator.getIc());
        when(request.getAgreementDateTo()).thenReturn(Date.valueOf("2026-09-12"));
        when(request.getAgreementDateFrom()).thenReturn(Date.valueOf("2026-09-11"));
        when(request.getSelected_risks()).thenReturn(risks);
        when(request.getCountry()).thenReturn(countryName);

//        cddrRepository = mock(CountryDefaultDayRateRepository.class);
        CountryDefaultDayRate cddr = mock(CountryDefaultDayRate.class);
        when(cddr.getCountryIc()).thenReturn(countryName);
        when(cddr.getCountryDefaultDayRate()).thenReturn(cddrValue);
        when(cddrRepository.findByCountryIc(countryName)).thenReturn(Optional.of(cddr));
    }
*/
    @Test
    public void calculatePremiumForMedicalRiskLatviaTest() {
        init("LATVIA", BigDecimal.valueOf(1.00), 18);
        initMrllCoefficientCalculator(BigDecimal.valueOf(1.0));
        assertEquals(BigDecimal.valueOf(1.10).setScale(2, RoundingMode.HALF_UP), calculator.calculatePremium(agreement, person).setScale(2));
    }

    @Test
    public void calculatePremiumForMedicalRiskSpainTest() {
        init("SPAIN", BigDecimal.valueOf(2.50d), 18);
        initMrllCoefficientCalculator(BigDecimal.valueOf(1.5));
        assertEquals(BigDecimal.valueOf(4.125), calculator.calculatePremium(agreement, person));
    }

    @Test
    public void calculatePremiumForMedicalRiskJapanTest() {
        init("JAPAN", BigDecimal.valueOf(3.50d), 18);
        initMrllCoefficientCalculator(BigDecimal.valueOf(1.2));
        assertEquals(BigDecimal.valueOf(4.62), calculator.calculatePremium(agreement, person).setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    private void init(String countryName, BigDecimal cddrValue, int age) {
        //String countryName = "LATVIA";
        //Double cddrValue = 1.00d;
        List<String> risks = new ArrayList<>();
        risks.add(calculator.getIc());
        when(agreement.getAgreementDateTo()).thenReturn(java.sql.Date.valueOf("2026-09-12"));
        when(agreement.getAgreementDateFrom()).thenReturn(java.sql.Date.valueOf("2026-09-11"));
        when(agreement.getSelectedRisks()).thenReturn(risks);
        when(agreement.getCountry()).thenReturn(countryName);

        Date date = new Date();
        java.sql.Date birth = java.sql.Date.valueOf("2005-05-05");
        birth.setYear(date.getYear() - age);


        when(person.getPersonBirthDate()).thenReturn(birth);
//        cddrRepository = mock(CountryDefaultDayRateRepository.class);
        CountryDefaultDayRate cddr = mock(CountryDefaultDayRate.class);
        when(cddr.getCountryIc()).thenReturn(countryName);
        when(cddr.getDefaultDayRate()).thenReturn(cddrValue);
        //when(cddrRepository.findByCountryIc(countryName)).thenReturn(Optional.of(cddr));

        AgeCoefficient ac = mock(AgeCoefficient.class);
        when(ac.getCoefficient()).thenReturn(BigDecimal.valueOf(1.1));
        //when(acRepository.findByAgeFromAndAgeTo(age)).thenReturn(Optional.of(ac));
        when(dayCountCalculator.calculatePremium(agreement, person)).thenReturn(1l);
        when(ageCoefficientCalculator.calculatePremium(agreement, person)).thenReturn(BigDecimal.valueOf(1.1));
        when(cddrCalculator.calculatePremium(agreement, person)).thenReturn(cddrValue);
    }

    private void initMrllCoefficientCalculator(BigDecimal mrllValue) {
        when(mrllCoefficientCalculator.calculatePremium(agreement, person)).thenReturn(mrllValue);
        ReflectionTestUtils.setField(calculator, "mrllCoefficientCalculator", mrllCoefficientCalculator);
    }
}
