package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.TravelCostCoefficient;
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
public class TravelCostCoefficientRepositoryTest {

    @Autowired
    private TravelCostCoefficientRepository repository;

    @Test
    public void injectedRepositoryIsNotNull() {
        assertNotNull(repository);
    }

    @ParameterizedTest
    @CsvSource({"2222.22, 10.00", "7777.77, 30.0", "15000.00, 100.00", "99999.99, 500.00"})
    public void shouldFindCostCoefficientForCost(BigDecimal cost, BigDecimal expectedCoefficient) {
        Optional<TravelCostCoefficient> coefficient = repository.findCoefficient(cost);
        assertThat(coefficient.isPresent()).isTrue();
        assertThat(coefficient.get().getCoefficient().compareTo(expectedCoefficient)).isZero();
    }
}
