package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.ClassifierValue;
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
public class ClassifierValueRepositoryTest {
    @Autowired
    private ClassifierValueRepository classifierValueRepository;

    @Test
    public void injectedRepositoryIsNotNull() {
        assertThat(classifierValueRepository).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(strings = {"TRAVEL_MEDICAL", "TRAVEL_CANCELLATION", "TRAVEL_LOSS_BAGGAGE", "TRAVEL_THIRD_PARTY_LIABILITY",
            "TRAVEL_EVACUATION", "TRAVEL_SPORT_ACTIVITIES"})
    public void should_find_RiskType(String risk) {
        checkIfRiskTypeWithGivenIcExists(risk);
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
