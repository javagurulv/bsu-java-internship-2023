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
class CLVALRepTest {

    @Autowired
    private ClassifierValueRepository classifierValueRepository;

    @Test
    public void injectedRepositoryIsNotNull() {
        assertNotNull(classifierValueRepository);
    }

    @Test
    public void shouldFindRiskTypeTravelMedical() {
        searchClassifierValueAndCheck("RISK_TYPE", "TRAVEL_MEDICAL");
    }

    @Test
    public void shouldFindRiskTypeTravelCancellation() {
        searchClassifierValueAndCheck("RISK_TYPE", "TRAVEL_CANCELLATION");
    }

    @Test
    public void shouldFindRiskTypeTravelLossBaggage() {
        searchClassifierValueAndCheck("RISK_TYPE", "TRAVEL_LOSS_BAGGAGE");
    }

    @Test
    public void shouldFindRiskTypeTravelThirdPartyLiability() {
        searchClassifierValueAndCheck("RISK_TYPE", "TRAVEL_THIRD_PARTY_LIABILITY");
    }

    @Test
    public void shouldFindRiskTypeTravelEvacuation() {
        searchClassifierValueAndCheck("RISK_TYPE", "TRAVEL_EVACUATION");
    }

    @Test
    public void shouldFindRiskTypeTravelSportActivities() {
        searchClassifierValueAndCheck("RISK_TYPE", "TRAVEL_SPORT_ACTIVITIES");
    }

    @Test
    public void shouldNotFindRiskTypeFake() {
        Optional<ClassifierValue> valueOpt = classifierValueRepository.findByClassifierTitleAndIc("RISK_TYPE", "FAKE");
        assertFalse(valueOpt.isPresent());
    }

    private void searchClassifierValueAndCheck(String classifierTitle, String ic) {
        Optional<ClassifierValue> valueOpt = classifierValueRepository.findByClassifierTitleAndIc(classifierTitle, ic);
        assertTrue(valueOpt.isPresent());
        ClassifierValue classifierValue = valueOpt.get();
        assertEquals(ic, classifierValue.getIc());
        assertEquals(classifierTitle, classifierValue.getClassifier().getTitle());
    }
}