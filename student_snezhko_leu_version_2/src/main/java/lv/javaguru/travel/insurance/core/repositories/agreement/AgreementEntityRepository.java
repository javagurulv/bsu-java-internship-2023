package lv.javaguru.travel.insurance.core.repositories.agreement;

import lv.javaguru.travel.insurance.core.domain.agreement.AgreementEntityDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Optional;

public interface AgreementEntityRepository extends JpaRepository<AgreementEntityDomain, Long> {
    @Query("SELECT aed FROM AgreementEntityDomain aed " +
            "WHERE aed.dateFrom = :df " +
            "AND aed.dateTo = :dt " +
            "AND aed.country = :c " +
            "AND aed.premium = :p")
    Optional<AgreementEntityDomain> findBy(@Param("df")Date dateFrom,
                                           @Param("dt")Date dateTo,
                                           @Param("c") String country,
                                           @Param("p") BigDecimal premium);
}
