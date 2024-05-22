package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.domain.calculate.medical.TMAgeCoefficient;
import lv.javaguru.travel.insurance.core.domain.calculate.medical.CountryDefaultDayRate;
import lv.javaguru.travel.insurance.core.domain.calculate.medical.MedicalRiskLimitLevel;
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

import static lv.javaguru.travel.insurance.core.api.dto.AgreementDTOBuilder.createAgreementDTO;
import static lv.javaguru.travel.insurance.core.api.dto.PersonDTOBuilder.createPersonDTO;
import static lv.javaguru.travel.insurance.core.domain.calculate.builders.CountryDefaultDayRateBuilder.createCountryDefaultDayRate;
import static lv.javaguru.travel.insurance.core.domain.calculate.builders.MedicalRiskLimitLevelBuilder.createMedicalRiskLimitLevel;
import static lv.javaguru.travel.insurance.core.validations.calculate.integration.CreateDateUtil.createDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
//@DataJpaTest
public class TravelRiskPremiumCalculatorMedicalTest {
    @Mock
    private TravelCalculateDayCountMedical dayCountCalculator;

    @Mock
    private TravelCalculateMedicalAgeCoefficient ageCoefficientCalculator;

    @Mock
    private TravelCalculateMedicalCountryDefaultDayRate cddrCalculator;

    @Mock
    private TravelCalculateInsuranceLimitCoefficientMedical mrllCoefficientCalculator;

    @Mock
    private AgreementDTO agreement;

    @Mock
    private PersonDTO person;
    @InjectMocks
    private TravelRiskPremiumCalculatorMedical calculator;

    @Test
    public void calculatePremiumForMedicalRiskLatviaIntegrationTest() {
        PersonDTO personDTO = createPersonDTO().withBirthDate(createDate("2005-08-03")).build();
        AgreementDTO agreementDTO = createAgreementDTO()
                .withCountry("LATVIA")
                .withPersons(personDTO)
                .build();

        CountryDefaultDayRate cddr = createCountryDefaultDayRate()
                .withCountryIc("LATVIA")
                .withCoefficient(BigDecimal.valueOf(1.00))
                .build();
        MedicalRiskLimitLevel mrll = createMedicalRiskLimitLevel()
                .withIc("LEVEL_10000")
                .withCoefficient(BigDecimal.valueOf(1.0))
                .build();
        when(dayCountCalculator.calculatePremium(agreementDTO, personDTO)).thenReturn(BigDecimal.valueOf(1));
        when(ageCoefficientCalculator.calculatePremium(agreementDTO, personDTO)).thenReturn(BigDecimal.valueOf(1.1));
        when(cddrCalculator.calculatePremium(agreementDTO, personDTO)).thenReturn(BigDecimal.valueOf(1.0));
        when(mrllCoefficientCalculator.calculatePremium(agreementDTO, personDTO)).thenReturn(BigDecimal.valueOf(1.0));
        List<TravelRiskPremiumCalculatorMedicalComponent> calculators = List.of(dayCountCalculator, cddrCalculator, ageCoefficientCalculator, mrllCoefficientCalculator);
        ReflectionTestUtils.setField(calculator, "calculators", calculators);

        assertEquals(BigDecimal.valueOf(1.10).setScale(2, RoundingMode.HALF_UP), calculator.calculatePremium(agreementDTO, personDTO).setScale(2));
    }
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

        TMAgeCoefficient ac = mock(TMAgeCoefficient.class);
        when(ac.getCoefficient()).thenReturn(BigDecimal.valueOf(1.1));
        //when(acRepository.findByAgeFromAndAgeTo(age)).thenReturn(Optional.of(ac));
        when(dayCountCalculator.calculatePremium(agreement, person)).thenReturn(BigDecimal.valueOf(1));
        when(ageCoefficientCalculator.calculatePremium(agreement, person)).thenReturn(BigDecimal.valueOf(1.1));
        when(cddrCalculator.calculatePremium(agreement, person)).thenReturn(cddrValue);

        List<TravelRiskPremiumCalculatorMedicalComponent> calculators = List.of(dayCountCalculator, cddrCalculator, ageCoefficientCalculator, mrllCoefficientCalculator);
        ReflectionTestUtils.setField(calculator, "calculators", calculators);
    }

    private void initMrllCoefficientCalculator(BigDecimal mrllValue) {
        when(mrllCoefficientCalculator.calculatePremium(agreement, person)).thenReturn(mrllValue);
        //ReflectionTestUtils.setField(calculator, "mrllCoefficientCalculator", mrllCoefficientCalculator);
    }
}
