package lv.javaguru.travel.insurance.core.repositories.agreement;

import lv.javaguru.travel.insurance.core.domain.agreement.AgreementEntityDomain;
import lv.javaguru.travel.insurance.core.domain.agreement.AgreementPersonEntityDomain;
import lv.javaguru.travel.insurance.core.domain.agreement.PersonDTODomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AgreementPersonEntityRepository extends JpaRepository<AgreementPersonEntityDomain, Long> {

    @Query("SELECT pers FROM AgreementPersonEntityDomain pers " +
            "left join pers.person personDomain " +
            "where personDomain.personFirstName = :fname " +
            "AND personDomain.personLastName = :lname " +
            "AND personDomain.personIc = :ic")
    Optional<AgreementPersonEntityDomain> findByName(@Param("fname") String firstName,
                                                     @Param("lname") String lastName,
                                                     @Param("ic") UUID ic);

    List<AgreementPersonEntityDomain> findByAgreement(AgreementEntityDomain agreementEntityDomain);

}
