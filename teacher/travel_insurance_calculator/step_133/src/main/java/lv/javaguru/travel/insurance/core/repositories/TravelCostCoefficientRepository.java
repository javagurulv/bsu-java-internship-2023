package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.TravelCostCoefficient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;

public interface TravelCostCoefficientRepository
        extends JpaRepository<TravelCostCoefficient, Long> {

    @Query("SELECT tc from TravelCostCoefficient tc " +
            "where tc.travelCostFrom <= :travelCost " +
            "and tc.travelCostTo >= :travelCost")
    Optional<TravelCostCoefficient> findCoefficient(@Param("travelCost") BigDecimal travelCost);

}
