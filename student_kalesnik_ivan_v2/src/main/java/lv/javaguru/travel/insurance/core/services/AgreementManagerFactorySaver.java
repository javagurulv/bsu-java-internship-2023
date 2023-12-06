package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDto;
import lv.javaguru.travel.insurance.core.domain.api.AgreementEntity;
import lv.javaguru.travel.insurance.core.repositories.api.AgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AgreementManagerFactorySaver {

    @Autowired
    private AgreementRepository agreementEntityRepository;
    @Autowired private EntityManagerFactorySaver personEntityFactory;

    AgreementEntity createAgreementEntity(AgreementDto agreementDTO) {
        saveAllPersons(agreementDTO);

        AgreementEntity agreementEntity = new AgreementEntity().builder()
                .agreementDateFrom(agreementDTO.getAgreementDateFrom())
                .agreementDateTo(agreementDTO.getAgreementDateTo())
                .country(agreementDTO.getCountry())
                .agreementPremium(agreementDTO.getAgreementPremium())
                .build();

        return agreementEntityRepository.save(agreementEntity);
    }

    private void saveAllPersons(AgreementDto agreement) {
        agreement.getPersons().forEach(personDTO -> personEntityFactory.savePerson(personDTO));
    }
}
