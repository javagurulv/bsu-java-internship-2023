package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.TCAgeCoefficient;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TCAgeCoefficientRepository extends JpaRepository<TCAgeCoefficient, Long> {

    @Cacheable(cacheNames = {"TCAgeCoefficientCache"}, key = "#age", condition="#result != null")
    @Query("SELECT ac from TCAgeCoefficient ac " +
            "where ac.ageFrom <= :age " +
            "and ac.ageTo > :age")
    Optional<TCAgeCoefficient> findByAge(
            @Param("age") int age
    );
}
