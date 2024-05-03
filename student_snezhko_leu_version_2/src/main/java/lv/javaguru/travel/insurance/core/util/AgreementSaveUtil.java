package lv.javaguru.travel.insurance.core.util;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.domain.agreement.*;
import lv.javaguru.travel.insurance.core.services.agreement.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

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
    @Autowired
    private PersonRiskEntityService personRiskEntityService;
    public void saveAgreement(AgreementDTO agreementDTO) {
        AgreementEntityDomain agreementEntityDomain = agreementEntityService.saveAgreement(agreementDTO);

//        List<PersonDTODomain> personDTOs = agreementDTO.getPersons().stream().map(person-> personEntityService.getPersonEntity(person, agreementEntityDomain)).toList();
        List<PersonRiskEntityDomain> personRisks = new LinkedList<>();


        List<AgreementPersonEntityDomain> personDomains =
                agreementDTO.getPersons().stream().map(
                        person -> {
                            PersonDTODomain personDomain = personEntityService.getPersonEntity(person, agreementEntityDomain);
                            AgreementPersonEntityDomain personEntityDomain =
                                    agreementPersonEntityService.savePerson(
                                            personDomain, person, agreementEntityDomain
                                    );
                            personRisks.addAll(
                                person.getSelectedRisks().stream().map(
                                        risk -> {
                                            return personRiskEntityService.savePersonRisk(risk, personEntityDomain);
                                        })
                                        .toList()
                            );
                       return personEntityDomain;
                        })
                        .toList();

        List<AgreementRiskEntityDomain> agreementRisks = agreementDTO.getSelectedRisks().stream().map(riskIc -> {
            return riskEntityService.saveRisk(riskIc, agreementEntityDomain);
        }).toList();

//        List<AgreementPersonEntityDomain> persons = agreementPersonEntityService.savePersons(agreementDTO);
        //riskEntityService.saveRisks(agreementDTO, agreementEntityDomain);

    }
}
