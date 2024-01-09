package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.Classifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClassifierRepository extends JpaRepository<Classifier, Long> {

    //@Query("SELECT c from Classifier where c.title = :classifierTitle")
    public Optional<Classifier> findByTitle(String title);//@Param("classifierTitle") String title);
}
