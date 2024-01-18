package lv.javaguru.travel.insurance.core.underwriting.calculators;

import lv.javaguru.travel.insurance.core.domain.AgeCoefficient;
import lv.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import lv.javaguru.travel.insurance.core.repositories.AgeCoefficientRepository;
import lv.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import lv.javaguru.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static lv.javaguru.travel.insurance.core.util.DateTimeUtil.findDiffBetweenTwoDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TravelRiskPremiumCalculatorMedicalTest {
    @InjectMocks
    TravelRiskPremiumCalculatorMedical calculator = new TravelRiskPremiumCalculatorMedical();
    @Mock
    CountryDefaultDayRateRepository cddrRepository = mock(CountryDefaultDayRateRepository.class);

    @Mock
    private AgeCoefficientRepository acRepository = mock(AgeCoefficientRepository.class);
    TravelCalculatePremiumRequest request;
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
        init("LATVIA", 1.00d, 18);
        assertEquals(calculator.calculatePremium(request), BigDecimal.valueOf(1.1));
    }

    @Test
    public void calculatePremiumForMedicalRiskSpainTest() {
        init("SPAIN", 2.50d, 18);
        assertEquals(calculator.calculatePremium(request), BigDecimal.valueOf(2.75));
    }

    @Test
    public void calculatePremiumForMedicalRiskJapanTest() {
        init("JAPAN", 3.50d, 18);
        assertEquals(BigDecimal.valueOf(3.85), calculator.calculatePremium(request).setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    private void init(String countryName, Double cddrValue, int age) {
        request = mock(TravelCalculatePremiumRequest.class);
        //String countryName = "LATVIA";
        //Double cddrValue = 1.00d;
        List<String> risks = new ArrayList<>();
        risks.add(calculator.getIc());
        when(request.getAgreementDateTo()).thenReturn(java.sql.Date.valueOf("2026-09-12"));
        when(request.getAgreementDateFrom()).thenReturn(java.sql.Date.valueOf("2026-09-11"));
        when(request.getSelected_risks()).thenReturn(risks);
        when(request.getCountry()).thenReturn(countryName);

        Date date = new Date();
        java.sql.Date birth = java.sql.Date.valueOf("2005-05-05");
        birth.setYear(date.getYear() - age);

        when(request.getPersonBirthDate()).thenReturn(birth);
//        cddrRepository = mock(CountryDefaultDayRateRepository.class);
        CountryDefaultDayRate cddr = mock(CountryDefaultDayRate.class);
        when(cddr.getCountryIc()).thenReturn(countryName);
        when(cddr.getCountryDefaultDayRate()).thenReturn(cddrValue);
        when(cddrRepository.findByCountryIc(countryName)).thenReturn(Optional.of(cddr));

        AgeCoefficient ac = mock(AgeCoefficient.class);
        when(ac.getCoefficient()).thenReturn(1.1);
        when(acRepository.findByAgeFromAndAgeTo(age)).thenReturn(Optional.of(ac));
    }
}
