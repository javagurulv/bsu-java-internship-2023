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
        Optional<ClassifierValue> optionalClassifierValue = classifierValueRepository
                .findByClassifierTitleAndIc("RISK_TYPE", "TRAVEL_MEDICAL");
        assertThat(optionalClassifierValue).isPresent();
        assertThat(optionalClassifierValue.get().getClassifier().getTitle()).isEqualTo("RISK_TYPE");
        assertThat(optionalClassifierValue.get().getIc()).isEqualTo("TRAVEL_MEDICAL");

    }

    @Test
    public void should_find_RiskType_TRAVEL_CANCELLATON() {
        Optional<ClassifierValue> optionalClassifierValue = classifierValueRepository
                .findByClassifierTitleAndIc("RISK_TYPE", "TRAVEL_CANCELLATION");
        assertThat(optionalClassifierValue).isPresent();
        assertThat(optionalClassifierValue.get().getClassifier().getTitle()).isEqualTo("RISK_TYPE");
        assertThat(optionalClassifierValue.get().getIc()).isEqualTo("TRAVEL_CANCELLATION");

    }

    @Test
    public void should_find_RiskType_TRAVEL_LOSS_BAGGAGE() {
        Optional<ClassifierValue> optionalClassifierValue = classifierValueRepository
                .findByClassifierTitleAndIc("RISK_TYPE", "TRAVEL_LOSS_BAGGAGE");
        assertThat(optionalClassifierValue).isPresent();
        assertThat(optionalClassifierValue.get().getClassifier().getTitle()).isEqualTo("RISK_TYPE");
        assertThat(optionalClassifierValue.get().getIc()).isEqualTo("TRAVEL_LOSS_BAGGAGE");

    }

    @Test
    public void should_find_RiskType_TRAVEL_THIRD_PARTY_LIABILITY() {
        Optional<ClassifierValue> optionalClassifierValue = classifierValueRepository
                .findByClassifierTitleAndIc("RISK_TYPE", "TRAVEL_THIRD_PARTY_LIABILITY");
        assertThat(optionalClassifierValue).isPresent();
        assertThat(optionalClassifierValue.get().getClassifier().getTitle()).isEqualTo("RISK_TYPE");
        assertThat(optionalClassifierValue.get().getIc()).isEqualTo("TRAVEL_THIRD_PARTY_LIABILITY");
    }

    @Test
    public void should_find_RiskType_TRAVEL_EVACUATION() {
        Optional<ClassifierValue> optionalClassifierValue = classifierValueRepository
                .findByClassifierTitleAndIc("RISK_TYPE", "TRAVEL_EVACUATION");
        assertThat(optionalClassifierValue).isPresent();
        assertThat(optionalClassifierValue.get().getClassifier().getTitle()).isEqualTo("RISK_TYPE");
        assertThat(optionalClassifierValue.get().getIc()).isEqualTo("TRAVEL_EVACUATION");
    }
    @Test
    public void should_find_RiskType_TRAVEL_SPORT_ACTIVITIES() {
        Optional<ClassifierValue> optionalClassifierValue = classifierValueRepository
                .findByClassifierTitleAndIc("RISK_TYPE", "TRAVEL_SPORT_ACTIVITIES");
        assertThat(optionalClassifierValue).isPresent();
        assertThat(optionalClassifierValue.get().getClassifier().getTitle()).isEqualTo("RISK_TYPE");
        assertThat(optionalClassifierValue.get().getIc()).isEqualTo("TRAVEL_SPORT_ACTIVITIES");
    }
    @Test
    public void shouldNotFindRiskType_FAKE() {
        Optional<ClassifierValue> optionalClassifierValue = classifierValueRepository
                .findByClassifierTitleAndIc("RISK_TYPE", "DUMMY");
        assertThat(optionalClassifierValue).isEmpty();
    }
}
