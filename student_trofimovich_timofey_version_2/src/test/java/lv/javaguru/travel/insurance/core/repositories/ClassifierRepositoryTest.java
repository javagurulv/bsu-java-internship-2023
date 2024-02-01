package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.Classifier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ClassifierRepositoryTest {
    @Autowired
    private ClassifierRepository classifierRepository;

    @Test
    public void injectedRepositoryIsNotNull() {
        assertNotNull(classifierRepository);
    }

    @Test
    public void shouldFindRiskTypeClassifier() {
        Optional<Classifier> riskType = classifierRepository.findByTitle("RISK_TYPE");
        assertTrue(riskType.isPresent());
        assertThat(riskType.get().getTitle()).isEqualTo("RISK_TYPE");
    }

    @Test
    public void shouldNotFindFakeClassifier() {
        Optional<Classifier> myType = classifierRepository.findByTitle("MY_TYPE");
        assertThat(myType).isEmpty();
    }
}
