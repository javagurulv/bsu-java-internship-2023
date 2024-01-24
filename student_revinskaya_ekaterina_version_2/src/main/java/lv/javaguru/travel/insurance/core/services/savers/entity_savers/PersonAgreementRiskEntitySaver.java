package lv.javaguru.travel.insurance.core.services.savers.entity_savers;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;
import lv.javaguru.travel.insurance.core.domain.entity.PersonAgreementEntity;
import lv.javaguru.travel.insurance.core.domain.entity.PersonAgreementRiskEntity;
import lv.javaguru.travel.insurance.core.repositories.entity.PersonAgreementRiskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonAgreementRiskEntitySaver {
    @Autowired
    PersonAgreementRiskRepository personAgreementRiskRepository;

    public void saveAllPersonAgreementRiskEntity(PersonAgreementEntity personAgreementEntity, PersonDTO personDTO){
        personDTO.getRisks().stream()
                .map(riskDTO-> convertToPersonAgreementRiskEntity(personAgreementEntity, riskDTO))
                .forEach(this::saveNotAlreadyExistPersonAgreementRiskEntity);
    }
    private PersonAgreementRiskEntity convertToPersonAgreementRiskEntity(PersonAgreementEntity personAgreementEntity, RiskDTO riskDTO){
        PersonAgreementRiskEntity personAgreementRiskEntity = new PersonAgreementRiskEntity();
        personAgreementRiskEntity.setPersonAgreementEntityId(personAgreementEntity);
        personAgreementRiskEntity.setRiskIc(riskDTO.getRiskIc());
        personAgreementRiskEntity.setPremium(riskDTO.getPremium());
        return personAgreementRiskEntity;
    }
    private void saveNotAlreadyExistPersonAgreementRiskEntity(PersonAgreementRiskEntity personAgreementRiskEntity){
        if(personAgreementRiskRepository.findByPersonAgreementIdAndRiskIc(personAgreementRiskEntity.getPersonAgreementEntityId(),
                        personAgreementRiskEntity.getRiskIc()).isEmpty()){
            personAgreementRiskRepository.save(personAgreementRiskEntity);
        }
    }
}
