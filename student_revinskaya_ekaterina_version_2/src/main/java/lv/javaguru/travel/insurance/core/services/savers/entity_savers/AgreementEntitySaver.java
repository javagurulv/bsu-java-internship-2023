package lv.javaguru.travel.insurance.core.services.savers.entity_savers;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.domain.entity.AgreementEntity;
import lv.javaguru.travel.insurance.core.repositories.entity.AgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AgreementEntitySaver {
    @Autowired
    AgreementRepository agreementRepository;

    public AgreementEntity saveAgreementEntity(AgreementDTO agreementDTO){
        AgreementEntity agreementEntity = convertToAgreementEntity(agreementDTO);
        return saveAgreementEntity(agreementEntity);
    }
    private AgreementEntity saveAgreementEntity(AgreementEntity agreementEntity) {
        return agreementRepository.save(agreementEntity);
    }

    private AgreementEntity convertToAgreementEntity(AgreementDTO agreementDTO) {
        AgreementEntity agreementEntity = new AgreementEntity();
        agreementEntity.setUuid(createUuid());
        agreementEntity.setTravelCost(agreementDTO.getTravelCost());
        agreementEntity.setDateFrom(agreementDTO.getAgreementDateFrom());
        agreementEntity.setDateTo(agreementDTO.getAgreementDateTo());
        agreementEntity.setCountry(agreementDTO.getCountry());
        agreementEntity.setPremium(agreementDTO.getAgreementPremium());
        return agreementEntity;
    }
    private String createUuid(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
