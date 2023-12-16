package lv.javaguru.travel.insurance.core.services.savers;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.domain.Agreement;
import lv.javaguru.travel.insurance.core.repositories.AgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

@Component
public class AgreementSaver {
    @Autowired
    AgreementRepository agreementRepository;

    public void saveNotAlreadyExistAgreements(AgreementDTO agreementDTO) {
        Agreement agreement = convertToEntity(agreementDTO);
        saveAgreement(agreement);
    }

// //   private boolean notExist(Agreement agreement) {
//        return agreementRepository.findAll(Example.of(agreement)).isEmpty();
//    }

    private Agreement convertToEntity(AgreementDTO agreementDTO) {
        Agreement agreement = new Agreement();
        agreement.setDateFrom(agreementDTO.getAgreementDateFrom());
        agreement.setDateTo(agreementDTO.getAgreementDateTo());
        agreement.setCountry(agreementDTO.getCountry());
        agreement.setPremium(agreementDTO.getAgreementPremium());
        return agreement;
    }

    private void saveAgreement(Agreement agreement) {
       // if (notExist(agreement)) {
            agreementRepository.save(agreement);
      //  }
    }

}
