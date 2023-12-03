package lv.javaguru.travel.insurance.core.repositories;


import lv.javaguru.travel.insurance.core.domain.MedicalRiskLimitLevel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class MedicalRiskLimitLevelRepositoryTest {
    @Autowired
    MedicalRiskLimitLevelRepository repository;


    @Test
    void injectedRepositoryIsNotNull() {
        assertThat(repository).isNotNull();
    }



    @Test
    void shouldFindCoefficientForLimit_10000() {
        checkIfCoefficientForLimitIcExists("LEVEL_10000");
    }
    @Test
    void shouldFindCoefficientForLimit_15000() {
        checkIfCoefficientForLimitIcExists("LEVEL_15000");
    }
    @Test
    void shouldFindCoefficientForLimit_20000() {
        checkIfCoefficientForLimitIcExists("LEVEL_20000");
    }
    @Test
    void shouldFindCoefficientForLimit_50000() {
        checkIfCoefficientForLimitIcExists("LEVEL_50000");
    }

    @Test
    void shouldNotFindCoefficient() {
        Optional<MedicalRiskLimitLevel> rate = getLimitLevel("DUMMY");
        assertThat(rate.isEmpty());
    }

    private Optional<MedicalRiskLimitLevel> getLimitLevel(String limitIc) {
        return repository.findByMedicalRiskLimitLevelIc(limitIc);
    }

    private void checkIfCoefficientForLimitIcExists (String limitIc) {
        Optional<MedicalRiskLimitLevel> rate = getLimitLevel(limitIc);
        assertThat(rate.isPresent());
        assertThat(rate.get().getMedicalRiskLimitLevelIc()).isEqualTo(limitIc);
    }

}
