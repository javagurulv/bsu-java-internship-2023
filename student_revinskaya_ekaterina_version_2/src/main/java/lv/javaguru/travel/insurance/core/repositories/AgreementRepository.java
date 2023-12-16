package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

public interface AgreementRepository extends JpaRepository<Agreement, Long> {

}
