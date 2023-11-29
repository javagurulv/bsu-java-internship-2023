package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.ClassifierValue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ClassifierValueRepositoryTest {
    @Autowired
    ClassifierValueRepository classifierValueRepository;

    @Test
    public void injectedRepositoryIsNotNull() {
        assertThat(classifierValueRepository).isNotNull();
    }

    @Test
    public void should_find_RiskType_TRAVEL_MEDICAL() {
       checkIfRiskTypeWithGivenIcExists("TRAVEL_MEDICAL");
    }

    @Test
    public void should_find_RiskType_TRAVEL_CANCELLATION() {
        checkIfRiskTypeWithGivenIcExists("TRAVEL_CANCELLATION");

    }

    @Test
    public void should_find_RiskType_TRAVEL_LOSS_BAGGAGE() {
        checkIfRiskTypeWithGivenIcExists("TRAVEL_LOSS_BAGGAGE");

    }

    @Test
    public void should_find_RiskType_TRAVEL_THIRD_PARTY_LIABILITY() {
        checkIfRiskTypeWithGivenIcExists("TRAVEL_THIRD_PARTY_LIABILITY");
    }

    @Test
    public void should_find_RiskType_TRAVEL_EVACUATION() {
        checkIfRiskTypeWithGivenIcExists("TRAVEL_EVACUATION");
    }
    @Test
    public void should_find_RiskType_TRAVEL_SPORT_ACTIVITIES() {
        checkIfRiskTypeWithGivenIcExists("TRAVEL_SPORT_ACTIVITIES");
    }
    @Test
    public void shouldNotFindRiskType_FAKE() {
        Optional<ClassifierValue> optionalClassifierValue = classifierValueRepository
                .findByClassifierTitleAndIc("RISK_TYPE", "DUMMY");
        assertThat(optionalClassifierValue).isEmpty();
    }

    private void checkIfRiskTypeWithGivenIcExists(String ic) {
       Optional<ClassifierValue> optionalClassifierValue = classifierValueRepository
                .findByClassifierTitleAndIc("RISK_TYPE", ic);
        assertThat(optionalClassifierValue).isPresent();
        assertThat(optionalClassifierValue.get().getClassifier().getTitle()).isEqualTo("RISK_TYPE");
        assertThat(optionalClassifierValue.get().getIc()).isEqualTo(ic);
    }
}
