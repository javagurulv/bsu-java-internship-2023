package lv.javaguru.travel.insurance.core.repositories;

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
public class ClassifierValueRepositoryTest {
    @Autowired
    private ClassifierValueRepository classifierValueRepository;

    @Test
    public void injectedRepositoryAreNotNull() {
        assertNotNull(classifierValueRepository);
    }
    public void shouldFind_RiskType_TRAVEL_MEDICAL(){
        String ic = "TRAVEL_MEDICAL";
        String classifier = "RISK_TYPE";
        test(classifier, ic);
    }

    public void shouldFind_RiskType_TRAVEL_CANCELLATION() {
        String ic = "TRAVEL_CANCELLATION";
        String classifier = "RISK_TYPE";
        test(classifier, ic);
    }

    public void shouldFind_RiskType_TRAVEL_LOSS_BAGGAGE() {
        String ic = "TRAVEL_LOSS_BAGGAGE";
        String classifier = "RISK_TYPE";
        test(classifier, ic);
    }

    @Test
    public void shouldFind_RiskType_TRAVEL_THIRD_PARTY_LIABILITY() {
        String ic = "TRAVEL_THIRD_PARTY_LIABILITY";
        String classifier = "RISK_TYPE";
        test(classifier, ic);
    }

    @Test
    public void shouldFind_RiskType_TRAVEL_EVACUATION() {
        String ic = "TRAVEL_EVACUATION";
        String classifier = "RISK_TYPE";
        test(classifier, ic);
    }

    @Test
    public void shouldFind_RiskType_TRAVEL_SPORT_ACTIVITIES() {
        String ic = "TRAVEL_SPORT_ACTIVITIES";
        String classifier = "RISK_TYPE";
        test(classifier, ic);
    }

    @Test
    public void shouldNotFind_RiskType_FAKE() {
        String ic = "FAKE";
        String classifier = "RISK_TYPE";
        Optional<ClassifierValue> cv = classifierValueRepository.findByClassifierTitleAndIc(classifier, ic);
        assertTrue(cv.isEmpty());
    }
    public void test(String classifier, String ic) {
        Optional<ClassifierValue> cv = classifierValueRepository.findByClassifierTitleAndIc(classifier, ic);
        assertTrue(cv.isPresent());
        assertEquals(cv.get().getIc(), ic);
        assertEquals(cv.get().getClassifier().getTitle(), classifier);
    }
}
