package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.TCAgeCoefficient;
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
public class TCAgeCoefficentRepositoryTest {
    @Autowired
    private TCAgeCoefficientRepository repository;

    @Test
    public void injectedRepositoryIsNotNull() {
        assertNotNull(repository);
    }

    @ParameterizedTest
    @CsvSource({"1, 5.00", "12, 10.00", "34, 20.00", "45, 30.00", "80, 50.00"})
    public void shouldFindAgeCoefficientForAgeGroup(int age, BigDecimal expectedCoefficient) {
        Optional<TCAgeCoefficient> coefficient = repository.findCoefficient(age);
        assertThat(coefficient.isPresent());
        assertThat(coefficient.get().getCoefficient().compareTo(expectedCoefficient)).isZero();
    }

}
