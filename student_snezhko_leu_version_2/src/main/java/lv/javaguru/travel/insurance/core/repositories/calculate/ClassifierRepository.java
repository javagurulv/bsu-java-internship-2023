package lv.javaguru.travel.insurance.core.repositories.calculate;

import lv.javaguru.travel.insurance.core.domain.calculate.Classifier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClassifierRepository extends JpaRepository<Classifier, Long> {

    public Optional<Classifier> findByTitle(String title);

}
