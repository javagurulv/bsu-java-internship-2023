package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.Classifier;
import lv.javaguru.travel.insurance.core.domain.ClassifierValue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@DataJpaTest
class ClassifierRepositoryTest {

    @Autowired private ClassifierRepository classifierRepository;

    @Test
    public void injectedRepositoryAreNotNull() {
        assertNotNull(classifierRepository);
    }

    @Test
    public void shouldFindRiskTypeClassifier() {
        shouldFindTitle("RISK_TYPE");
    }
    @Test
    void wrongQuery() {
        Optional<Classifier> actualRepository = classifierRepository.findByTitle("wrongData");
        assertTrue(actualRepository.isEmpty());
    }
    private void shouldFindTitle(String title) {
        Optional<Classifier> valueOpt = classifierRepository.findByTitle(title);
        assertTrue(valueOpt.isPresent());
        assertEquals(title, valueOpt.get().getTitle());
    }
}