package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.Classifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClassifierRepository extends JpaRepository<Classifier, Long> {
    @Cacheable(cacheNames = {"classifierCache"}, key = "#title", condition="#result != null")
    Optional<Classifier> findByTitle(String title);

}
