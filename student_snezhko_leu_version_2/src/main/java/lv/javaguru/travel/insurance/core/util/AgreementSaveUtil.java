package lv.javaguru.travel.insurance.core.util;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.services.agreement.PersonEntityService;
import lv.javaguru.travel.insurance.core.services.agreement.AgreementEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AgreementSaveUtil {
    @Autowired
    private AgreementEntityService agreementEntityService;

    @Autowired
    private PersonEntityService personEntityService;

    public void saveAll(AgreementDTO agreementDTO) {
        agreementEntityService.saveAgreement(agreementDTO);
        agreementDTO.getPersons().forEach(person-> personEntityService.getPersonEntity(person));
    }
}
