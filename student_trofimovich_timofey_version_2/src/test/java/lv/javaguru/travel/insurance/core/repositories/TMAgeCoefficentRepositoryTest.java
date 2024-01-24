package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.TMAgeCoefficient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TMAgeCoefficentRepositoryTest {
    @Autowired
    private TMAgeCoefficientRepository repository;

    @Test
    public void injectedRepositoryIsNotNull() {
        assertNotNull(repository);
    }

    @ParameterizedTest
    @CsvSource({"1, 1.1", "7, 0.7", "15, 1", "30, 1.1", "50, 1.2", "100, 1.5"})
    public void shouldFindAgeCoefficientForAgeGroup(int age, BigDecimal expectedCoefficient) {
        Optional<TMAgeCoefficient> coefficient = repository.findCoefficient(age);
        assertThat(coefficient.isPresent());
        assertThat(coefficient.get().getCoefficient().stripTrailingZeros()).isEqualTo(expectedCoefficient);
    }

}
