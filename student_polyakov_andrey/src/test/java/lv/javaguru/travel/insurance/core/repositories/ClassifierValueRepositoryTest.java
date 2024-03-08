package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.ClassifierValue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ClassifierValueRepositoryTest {
    @Autowired
    private ClassifierValueRepository classifierValueRepository;

    private void findCorrectClassifier(String classifier_title) {
        Optional<ClassifierValue> valueOptional = classifierValueRepository.findByClassifierTitleAndIc("RISK_TYPE", classifier_title);
        assertTrue(valueOptional.isPresent());
        assertEquals(valueOptional.get().getClassifier().getTitle(), "RISK_TYPE");
        assertEquals(valueOptional.get().getIc(), classifier_title);
    }
    @Test
    public void injectedRepositoryIsNotNull() {
        assertNotNull(classifierValueRepository);
    }
    @Test
    public void findCorrect_TRAVEL_MEDICAL() {
        findCorrectClassifier("TRAVEL_MEDICAL");
    }
    @Test
    public void findCorrect_TRAVEL_CANCELLATION() {
        findCorrectClassifier("TRAVEL_CANCELLATION");
    }
    @Test
    public void findCorrect_TRAVEL_LOSS_BAGGAGE() {
        findCorrectClassifier("TRAVEL_LOSS_BAGGAGE");
    }
    @Test
    public void findCorrect_RiskType_TRAVEL_THIRD_PARTY_LIABILITY() {
        findCorrectClassifier("TRAVEL_THIRD_PARTY_LIABILITY");
    }
    @Test
    public void findCorrect_RiskType_TRAVEL_EVACUATION() {
        findCorrectClassifier("TRAVEL_EVACUATION");
    }
    @Test
    public void findCorrect_RiskType_TRAVEL_SPORT_ACTIVITIES() {
        findCorrectClassifier("TRAVEL_SPORT_ACTIVITIES");
    }
    @Test
    public void doesntFindIncorrect_RiskType_FAKE() {
        Optional<ClassifierValue> valueOptional = classifierValueRepository.findByClassifierTitleAndIc("RISK_TYPE", "FAKE");
        assertFalse(valueOptional.isPresent());
    }
}
