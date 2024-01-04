package lv.javaguru.travel.insurance.core.repositories;


import lv.javaguru.travel.insurance.core.domain.Classifier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ClassifierRepositoryTest {
    @Autowired
    ClassifierRepository cr;

    @Test
    public void hasNotNullRepository() {
        assertNotNull(cr);
    }

    @Test
    public void shouldFindRiskTypeClassifier()
    {
        Optional<Classifier> classifier = cr.findByTitle("RISK_TYPE");
        assertTrue(classifier.isPresent());
        assertEquals(classifier.get().getTitle(), "RISK_TYPE");
    }
    @Test
    public void shouldNotFindFakeClassifier() {
        Optional<Classifier> classifier = cr.findByTitle("FAKE");
        assertTrue(classifier.isEmpty());
    }
}
