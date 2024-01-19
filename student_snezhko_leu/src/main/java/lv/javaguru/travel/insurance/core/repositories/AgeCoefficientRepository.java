package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.AgeCoefficient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AgeCoefficientRepository extends JpaRepository<AgeCoefficient, Long> {

/*    @Query("SELECT ac FROM AGE_COEFFICIENT AS ac" +
            "WHERE ac.age_from = :af AND ac.age_to = :at;")
  */
/*
    public Optional<AgeCoefficient> findByAgeFromAndAgeTo(Integer age_from, Integer age_to
           // @Param("af")Integer from,
           // @Param("at")Integer to
    );


 */


    @Query("SELECT ac from AgeCoefficient as ac " +
            "where age_from <= :age and age_to >= :age")
    public Optional<AgeCoefficient> findByAgeFromAndAgeTo(@Param("age")Integer age);


}
