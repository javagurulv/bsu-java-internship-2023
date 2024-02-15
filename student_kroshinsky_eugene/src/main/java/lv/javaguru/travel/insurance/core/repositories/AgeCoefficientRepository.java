package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.AgeCoefficient;
import lv.javaguru.travel.insurance.core.domain.Classifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AgeCoefficientRepository extends JpaRepository<AgeCoefficient, Long> {
    @Query("SELECT ac FROM AgeCoefficient ac" +
            " WHERE :age BETWEEN ac.ageFrom AND ac.ageTo")
    Optional<AgeCoefficient> findByAge(@Param("age") int age);

}
