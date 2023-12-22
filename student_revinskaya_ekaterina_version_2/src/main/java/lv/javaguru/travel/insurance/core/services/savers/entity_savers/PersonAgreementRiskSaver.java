package lv.javaguru.travel.insurance.core.services.savers.entity_savers;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;
import lv.javaguru.travel.insurance.core.domain.PersonAgreement;
import lv.javaguru.travel.insurance.core.domain.PersonAgreementRisk;
import lv.javaguru.travel.insurance.core.repositories.PersonAgreementRiskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonAgreementRiskSaver {
    @Autowired
    PersonAgreementRiskRepository personAgreementRiskRepository;

    public void saveAllPersonAgreementRisk(PersonAgreement personAgreement, PersonDTO personDTO){
        personDTO.getRisks().stream()
                .map(riskDTO->convertToPersonAgreementRisk(personAgreement, riskDTO))
                .forEach(this::saveNotAlreadyExistPersonAgreementRisk);
    }
    private PersonAgreementRisk convertToPersonAgreementRisk(PersonAgreement personAgreement, RiskDTO riskDTO){
        PersonAgreementRisk personAgreementRisk = new PersonAgreementRisk();
        personAgreementRisk.setPersonAgreementId(personAgreement);
        personAgreementRisk.setRiskIc(riskDTO.getRiskIc());
        personAgreementRisk.setPremium(riskDTO.getPremium());
        return personAgreementRisk;
    }
    private void saveNotAlreadyExistPersonAgreementRisk(PersonAgreementRisk personAgreementRisk){
        if(personAgreementRiskRepository.findByPersonAgreementIdAndRiskIc(personAgreementRisk.getPersonAgreementId(),
                personAgreementRisk.getRiskIc()).isEmpty()){
            personAgreementRiskRepository.save(personAgreementRisk);
        }
    }
}
