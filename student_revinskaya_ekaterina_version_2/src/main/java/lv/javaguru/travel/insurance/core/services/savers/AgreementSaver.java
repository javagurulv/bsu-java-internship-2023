package lv.javaguru.travel.insurance.core.services.savers;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.domain.Agreement;
import lv.javaguru.travel.insurance.core.repositories.AgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AgreementSaver {
    @Autowired
    AgreementRepository agreementRepository;

    public void saveNotAlreadyExistAgreements(AgreementDTO agreementDTO) {
        Agreement agreement = convertToEntity(agreementDTO);
        if (notExist(agreement)) {
            saveIfNotExist(agreement);
        }
    }

    private boolean notExist(Agreement agreement) {
        return agreementRepository.findBy(
                        agreement.getDateFrom(),
                        agreement.getDateTo(),
                        agreement.getCountry(),
                        agreement.getPremium())
                .isEmpty();
    }

    private Agreement convertToEntity(AgreementDTO agreementDTO) {
        Agreement agreement = new Agreement();
        agreement.setDateFrom(agreementDTO.getAgreementDateFrom());
        agreement.setDateTo(agreementDTO.getAgreementDateTo());
        agreement.setCountry(agreementDTO.getCountry());
        agreement.setPremium(agreementDTO.getAgreementPremium());
        return agreement;
    }

    private void saveIfNotExist(Agreement agreement) {
        if (notExist(agreement)) {
            agreementRepository.save(agreement);
        }
    }

}
