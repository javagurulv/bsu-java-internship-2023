package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.domain.AgeCoefficient;
import lv.javaguru.travel.insurance.core.repositories.AgeCoefficientRepository;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TravelCalculateMedicalAgeCoefficientTest {
    @InjectMocks
    private TravelCalculateMedicalAgeCoefficient calculator = new TravelCalculateMedicalAgeCoefficient();

    @Mock
    private AgeCoefficientRepository acRepository = mock(AgeCoefficientRepository.class);

    TravelCalculatePremiumRequestV1 request;

    @Test
    public void MedicalAgeCoefficientCalculator() {
        init(15, 1.1);
        assertEquals(BigDecimal.valueOf(1.1), calculator.calculatePremium(request));
    }

    private void init(Integer age, Double ageCoefficient)
    {
        request = mock(TravelCalculatePremiumRequestV1.class);

        Date birthDate = new Date();
        birthDate.setYear(birthDate.getYear() - age);

        when(request.getPersonBirthDate()).thenReturn(birthDate);

        AgeCoefficient acValue = mock(AgeCoefficient.class);
        when(acValue.getCoefficient()).thenReturn(BigDecimal.valueOf(ageCoefficient));

        when(acRepository.findByAgeFromAndAgeTo(age)).thenReturn(Optional.of(acValue));


    }
}
