package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.api.dto.person.PersonDTO;
import lv.javaguru.travel.insurance.core.domain.entities.AgreementEntity;
import lv.javaguru.travel.insurance.core.domain.entities.AgreementPersonEntity;
import lv.javaguru.travel.insurance.core.domain.entities.PersonEntity;
import lv.javaguru.travel.insurance.core.repositories.AgreementPersonEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class AgreementPersonEntityFactory {
    @Autowired
    private AgreementPersonEntityRepository repository;
    @Autowired
    private PersonEntityFactory personEntityFactory;
    AgreementPersonEntity createAgreementPersonEntity(PersonDTO personDTO, AgreementEntity agreementEntity) {
        AgreementPersonEntity agreementPersonEntity = new AgreementPersonEntity();
        agreementPersonEntity.setAgreement(agreementEntity);
        PersonEntity personEntity = personEntityFactory.createPersonEntity(personDTO);
        agreementPersonEntity.setPerson(personEntity);
        agreementPersonEntity.setMedicalRiskLimitLevel(personDTO.getMedicalRiskLimitLevel());
        return repository.save(agreementPersonEntity);
    }
}
