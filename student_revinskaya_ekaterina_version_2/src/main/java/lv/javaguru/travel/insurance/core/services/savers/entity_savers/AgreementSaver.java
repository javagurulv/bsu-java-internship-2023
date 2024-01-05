package lv.javaguru.travel.insurance.core.services.savers.entity_savers;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.domain.entity.Agreement;
import lv.javaguru.travel.insurance.core.repositories.entity.AgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AgreementSaver {
    @Autowired
    AgreementRepository agreementRepository;

    public Agreement saveAgreementEntity(AgreementDTO agreementDTO){
        Agreement agreement = convertToAgreement(agreementDTO);
        return saveAgreement(agreement);
    }
    private Agreement saveAgreement(Agreement agreement) {
        return agreementRepository.save(agreement);
    }

    private Agreement convertToAgreement(AgreementDTO agreementDTO) {
        Agreement agreement = new Agreement();
        agreement.setUuid(createUuid());
        agreement.setTravelCost(agreementDTO.getTravelCost());
        agreement.setDateFrom(agreementDTO.getAgreementDateFrom());
        agreement.setDateTo(agreementDTO.getAgreementDateTo());
        agreement.setCountry(agreementDTO.getCountry());
        agreement.setPremium(agreementDTO.getAgreementPremium());
        return agreement;
    }
    private String createUuid(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
