package lv.javaguru.travel.insurance.core.util;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.services.agreement.AgreementPersonEntityService;
import lv.javaguru.travel.insurance.core.services.agreement.AgreementRiskEntityService;
import lv.javaguru.travel.insurance.core.services.agreement.PersonEntityService;
import lv.javaguru.travel.insurance.core.services.agreement.AgreementEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/*
    risk service saves it's agreement if it hasn't saved
    agreement service saves each its person
    person saves only itself
 */
@Component
public class AgreementSaveUtil {
    @Autowired
    private AgreementEntityService agreementEntityService;

    @Autowired
    private PersonEntityService personEntityService;

    @Autowired
    private AgreementRiskEntityService riskEntityService;

    @Autowired
    private AgreementPersonEntityService agreementPersonEntityService;
    public void saveAll(AgreementDTO agreementDTO) {
        agreementEntityService.saveAgreement(agreementDTO);
        agreementDTO.getPersons().forEach(person-> personEntityService.getPersonEntity(person));
        agreementPersonEntityService.savePersons(agreementDTO);
        riskEntityService.saveRisks(agreementDTO);
    }
}
