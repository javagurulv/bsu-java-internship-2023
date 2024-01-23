package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.TCTravelCostCoefficient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;

public interface TCTravelCostCoefficientRepository extends JpaRepository<TCTravelCostCoefficient, Long> {

    @Query(
            "SELECT tc FROM TCTravelCostCoefficient tc" +
            " WHERE tc.travelCostFrom <= :cost " +
            "AND tc.travelCostTo >= :cost"
    )
    Optional<TCTravelCostCoefficient> findCoefficient(@Param("cost") BigDecimal cost);
}
