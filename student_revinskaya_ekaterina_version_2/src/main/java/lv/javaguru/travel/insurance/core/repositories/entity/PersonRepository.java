package lv.javaguru.travel.insurance.core.repositories.entity;

import lv.javaguru.travel.insurance.core.domain.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PersonRepository  extends JpaRepository<PersonEntity, Long> {

    Optional<PersonEntity> findByPersonalCode(
            @Param("personalCode") String personCode
    );
}
