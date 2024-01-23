package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.TravelCostCoefficient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;

public interface TravelCostCoefficientRepository extends JpaRepository<TravelCostCoefficient, Long> {

    @Query(
            "SELECT tc FROM TravelCostCoefficient tc" +
            " WHERE tc.travelCostFrom <= :cost " +
            "AND tc.travelCostTo >= :cost"
    )
    Optional<TravelCostCoefficient> findCoefficient(@Param("cost") BigDecimal cost);
}
