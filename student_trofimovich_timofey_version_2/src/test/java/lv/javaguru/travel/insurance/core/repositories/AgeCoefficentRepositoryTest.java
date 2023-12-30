package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.AgeCoefficient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class AgeCoefficentRepositoryTest {
    @Autowired
    private AgeCoefficientRepository repository;

    @Test
    public void injectedRepositoryIsNotNull() {
        assertNotNull(repository);
    }

    @Test
    public void shouldFindAgeCoefficientForAgeGroup1() {
        Optional<AgeCoefficient> coefficient = repository.findCoefficient(1);
        assertThat(coefficient.isPresent());
        assertThat(coefficient.get().getCoefficient().stripTrailingZeros()).isEqualTo(new BigDecimal("1.1"));
    }

    @Test
    public void shouldFindAgeCoefficientForAgeGroup2() {
        Optional<AgeCoefficient> coefficient = repository.findCoefficient(7);
        assertThat(coefficient.isPresent());
        assertThat(coefficient.get().getCoefficient().stripTrailingZeros()).isEqualTo(new BigDecimal("0.7"));
    }

    @Test
    public void shouldFindAgeCoefficientForAgeGroup3() {
        Optional<AgeCoefficient> coefficient = repository.findCoefficient(15);
        assertThat(coefficient.isPresent());
        assertThat(coefficient.get().getCoefficient().stripTrailingZeros()).isEqualTo(new BigDecimal("1"));
    }

    @Test
    public void shouldFindAgeCoefficientForAgeGroup4() {
        Optional<AgeCoefficient> coefficient = repository.findCoefficient(30);
        assertThat(coefficient.isPresent());
        assertThat(coefficient.get().getCoefficient().stripTrailingZeros()).isEqualTo(new BigDecimal("1.1"));
    }

    @Test
    public void shouldFindAgeCoefficientForAgeGroup5() {
        Optional<AgeCoefficient> coefficient = repository.findCoefficient(50);
        assertThat(coefficient.isPresent());
        assertThat(coefficient.get().getCoefficient().stripTrailingZeros()).isEqualTo(new BigDecimal("1.2"));

    }

    @Test
    public void shouldFindAgeCoefficientForAgeGroup6() {
        Optional<AgeCoefficient> coefficient = repository.findCoefficient(100);
        assertThat(coefficient.isPresent());
        assertThat(coefficient.get().getCoefficient().stripTrailingZeros()).isEqualTo(new BigDecimal("1.5"));

    }

}
