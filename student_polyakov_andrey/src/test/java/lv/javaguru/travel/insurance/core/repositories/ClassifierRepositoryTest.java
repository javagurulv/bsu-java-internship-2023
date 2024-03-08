package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.Classifier;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ClassifierRepositoryTest {
    @Autowired
    private ClassifierRepository classifierRepository;

    @Test
    public void injectedRepositoryIsNotNull() {
        assertNotNull(classifierRepository);
    }
    @Test
    public void findByTitleFindsCorrectClassifier() {
        Optional<Classifier> riskTypeOptional = classifierRepository.findByTitle("RISK_TYPE");
        assertTrue(riskTypeOptional.isPresent());
        assertEquals(riskTypeOptional.get().getTitle(), "RISK_TYPE");
    }
    @Test
    public void findByTitleDoesntFindIncorrectClassifier() {
        Optional<Classifier> riskTypeOptional = classifierRepository.findByTitle("FAKE");
        assertFalse(riskTypeOptional.isPresent());
    }
}
