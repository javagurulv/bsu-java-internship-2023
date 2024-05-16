package lv.javaguru.travel.insurance.core.repositories.calculate.cancellation;

import lv.javaguru.travel.insurance.core.domain.calculate.cancellation.TravelCostCoefficientDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;

public interface TravelCostCoefficientRepository extends JpaRepository<TravelCostCoefficientDomain, Long> {
    @Query("SELECT domain FROM TravelCostCoefficientDomain domain " +
            "WHERE domain.costFrom <= :cost " +
            "AND domain.costTo > :cost")
    public Optional<TravelCostCoefficientDomain> findCoefficientByCost(@Param("cost") BigDecimal cost);
}
