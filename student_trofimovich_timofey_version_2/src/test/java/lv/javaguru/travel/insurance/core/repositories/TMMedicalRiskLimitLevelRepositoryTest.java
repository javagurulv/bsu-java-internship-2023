package lv.javaguru.travel.insurance.core.repositories;


import lv.javaguru.travel.insurance.core.domain.TMMedicalRiskLimitLevel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TMMedicalRiskLimitLevelRepositoryTest {
    @Autowired
    private TMMedicalRiskLimitLevelRepository repository;


    @Test
    void injectedRepositoryIsNotNull() {
        assertThat(repository).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(strings = {"LEVEL_10000", "LEVEL_15000", "LEVEL_20000", "LEVEL_50000"})
    void shouldFindCoefficientForLimit(String limit) {
        checkIfCoefficientForLimitIcExists(limit);
    }

    @Test
    void shouldNotFindCoefficient() {
        Optional<TMMedicalRiskLimitLevel> rate = getLimitLevel("DUMMY");
        assertThat(rate).isEmpty();
    }

    private Optional<TMMedicalRiskLimitLevel> getLimitLevel(String limitIc) {
        return repository.findByMedicalRiskLimitLevelIc(limitIc);
    }

    private void checkIfCoefficientForLimitIcExists(String limitIc) {
        Optional<TMMedicalRiskLimitLevel> rate = getLimitLevel(limitIc);
        assertThat(rate).isPresent();
        assertThat(rate.get().getMedicalRiskLimitLevelIc()).isEqualTo(limitIc);
    }

}
