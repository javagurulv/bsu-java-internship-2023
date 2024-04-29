package lv.javaguru.travel.insurance.core.services.agreement;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;
import lv.javaguru.travel.insurance.core.domain.agreement.AgreementPersonEntityDomain;
import lv.javaguru.travel.insurance.core.domain.agreement.PersonDTODomain;
import lv.javaguru.travel.insurance.core.domain.agreement.PersonRiskEntityDomain;
import lv.javaguru.travel.insurance.core.repositories.agreement.PersonRiskEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonRiskEntityService {
    @Autowired
    private PersonRiskEntityRepository personRiskEntityRepository;

    public PersonRiskEntityDomain savePersonRisk(RiskDTO risk, AgreementPersonEntityDomain personEntityDomain) {
        Optional<PersonRiskEntityDomain> optional = personRiskEntityRepository.findByIcAndPerson(risk.getRiskIc(), personEntityDomain);
        if (optional.isPresent()) {
            return optional.get();
        }

        PersonRiskEntityDomain riskDomain = new PersonRiskEntityDomain();
        riskDomain.setRiskIc(risk.getRiskIc());
        riskDomain.setPerson(personEntityDomain);
        riskDomain.setPremium(risk.getPremium());
        personRiskEntityRepository.save(riskDomain);

        return riskDomain;
    }
/*
    public List<PersonRiskEntityDomain> savePersonRisk(PersonDTO person, AgreementPersonEntityDomain personDomain) {
        return person.getSelectedRisks().stream().map(risk -> {

            Optional<PersonRiskEntityDomain> optional = personRiskEntityRepository.findByIcAndPerson(risk.getRiskIc(), personDomain);
            if (optional.isPresent()) {
                return optional.get();
            }

            PersonRiskEntityDomain riskDomain = new PersonRiskEntityDomain();
            riskDomain.setRiskIc(risk.getRiskIc());
            riskDomain.setPerson(personDomain);
            riskDomain.setPremium(risk.getPremium());
            personRiskEntityRepository.save(riskDomain);

            return riskDomain;
        }).toList();
    }

 */
}
