package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.domain.entities.AgreementEntity;
import lv.javaguru.travel.insurance.core.repositories.entities.AgreementEntityRepository;
import lv.javaguru.travel.insurance.core.repositories.entities.PersonEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class AgreementEntityFactory {

    @Autowired private AgreementEntityRepository agreementEntityRepository;
    @Autowired private PersonEntityFactory personEntityFactory;

    AgreementEntity createAgreementEntity(AgreementDTO agreementDTO) {
        saveAllPersons(agreementDTO);

        AgreementEntity agreementEntity = new AgreementEntity();
        agreementEntity.setAgreementDateFrom(agreementDTO.getAgreementDateFrom());
        agreementEntity.setAgreementDateTo(agreementDTO.getAgreementDateTo());
        agreementEntity.setCountry(agreementDTO.getCountry());
        agreementEntity.setAgreementPremium(agreementDTO.getAgreementPremium());
        return agreementEntityRepository.save(agreementEntity);
    }

    private void saveAllPersons(AgreementDTO agreement) {
        agreement.getPersons().forEach(personDTO -> personEntityFactory.createPersonEntity(personDTO));
    }

}
