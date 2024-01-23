package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.TravelCostCoefficient;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;

public interface TravelCostCoefficientRepository extends JpaRepository<TravelCostCoefficient, Long> {

    @Cacheable(cacheNames = {"travelCostCoefficientCache"}, key = "#cost", condition="#result != null")
    @Query("SELECT tc from TravelCostCoefficient tc " +
            "where tc.costFrom <= :cost " +
            "and tc.costTo > :cost")
    Optional<TravelCostCoefficient> findByCost(
            @Param("cost") BigDecimal cost
    );
}
